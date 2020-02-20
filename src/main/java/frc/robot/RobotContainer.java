/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.ActiverDesactiverLanceurCommand;
import frc.robot.commands.ActiverDesactiverSlowModeCommand;
import frc.robot.commands.AutonomousCommands;
import frc.robot.commands.AvancerPiedsCommand;
import frc.robot.commands.ChargerCommand;
import frc.robot.commands.DrivetrainDriveCommand;
import frc.robot.commands.GobeurCommand;
import frc.robot.commands.LanceurCommand;
import frc.robot.commands.MonterPourTirerCommand;
import frc.robot.commands.TournerFeederCommand;
import frc.robot.commands.TournerGyroCommand;
import frc.robot.commands.WaitAutonomousTimerCommand;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Gobeur;
import frc.robot.subsystems.Lanceur;
import frc.util.DigitalInputButton;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  private final Drivetrain drivetrain = new Drivetrain();

  private final Gobeur gobeur = new Gobeur();

  private final Lanceur lanceur = new Lanceur();

  private final Feeder feeder = new Feeder();

  private final XboxController driverController = new XboxController(Constants.USB.DRIVER_GAMEPAD);

  private final XboxController coDriverController = new XboxController(Constants.USB.CO_DRIVER_GAMEPAD);

  private SendableChooser<Command> chooser = new SendableChooser<>();

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    SmartDashboard.putNumber("Autonomous Wait", 0);
    chooser.addOption("Straight", new SequentialCommandGroup(new WaitAutonomousTimerCommand(), AutonomousCommands.autonomousStraightCommand(drivetrain, lanceur, feeder)));
    chooser.addOption("Right", new SequentialCommandGroup(new WaitAutonomousTimerCommand(), AutonomousCommands.autonomousRightCommand(drivetrain, lanceur, feeder)));
    chooser.addOption("Left", new SequentialCommandGroup(new WaitAutonomousTimerCommand(), AutonomousCommands.autonomousLeftCommand(drivetrain, lanceur, feeder)));
    chooser.addOption("AvancerPieds", new AvancerPiedsCommand(drivetrain, 6));
    chooser.addOption("Tourner 180", new TournerGyroCommand(drivetrain, 90));
    chooser.addOption("Autonomous Test", AutonomousCommands.autonomousTestCommand(drivetrain, lanceur, feeder));
    chooser.addOption("Autonomous Rotate Test", AutonomousCommands.autonomousRotateTestCommand(drivetrain, lanceur, feeder));
    SmartDashboard.putData("Auto Choice", chooser);
    drivetrain.setDefaultCommand(new DrivetrainDriveCommand(drivetrain, driverController));
    lanceur.setDefaultCommand(new LanceurCommand(lanceur));
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    new JoystickButton(coDriverController, Button.kBumperRight.value).whileHeld(new GobeurCommand(gobeur, Constants.SPEED.GOBEUR_SPEED));
    new JoystickButton(coDriverController, Button.kBumperLeft.value).whileHeld(new GobeurCommand(gobeur, -Constants.SPEED.GOBEUR_SPEED));
    new JoystickButton(driverController, Button.kA.value).whenPressed(new ActiverDesactiverSlowModeCommand(drivetrain));
    new JoystickButton(coDriverController, Button.kB.value).whenPressed(new MonterPourTirerCommand(feeder));
    new JoystickButton(coDriverController, Button.kX.value).whileHeld(new TournerFeederCommand(feeder));
    new JoystickButton(coDriverController, Button.kA.value).whenPressed(new ActiverDesactiverLanceurCommand(lanceur));
    new DigitalInputButton(feeder.getCapteurRest()).whenPressed(new ChargerCommand(feeder));

    }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    drivetrain.resetEncoder();
    drivetrain.resetOdometry(new Pose2d());
    return chooser.getSelected();
  }
}
