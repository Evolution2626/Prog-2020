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
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.ActivateDeactivateFeederBasCommand;
import frc.robot.commands.ActiverDesactiverLanceurCommand;
import frc.robot.commands.AutonomousCommands;
import frc.robot.commands.AvancerPiedsCommand;
import frc.robot.commands.DescendreGrimpeurCommand;
import frc.robot.commands.DescendreWinchCommand;
import frc.robot.commands.DrivetrainDriveCommand;
import frc.robot.commands.FeederMonterUnBallonCommand;
import frc.robot.commands.FeederTournerBasCommand;
import frc.robot.commands.FeederTournerHautBasCommand;
import frc.robot.commands.GobeurCommand;
import frc.robot.commands.LanceurCommand;
import frc.robot.commands.MonterGobeurCommand;
import frc.robot.commands.MonterGrimpeurCommand;
import frc.robot.commands.MonterWinchGrimpeurCommand;
import frc.robot.commands.SetGobeurEncoderPosCommand;
import frc.robot.commands.WaitAutonomousTimerCommand;
import frc.robot.commands.AutonomousCommands.EndPosition;
import frc.robot.commands.AutonomousCommands.StartPosition;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Gobeur;
import frc.robot.subsystems.Grimpeur;
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

  private final Grimpeur grimpeur = new Grimpeur();

  private final XboxController driverController = new XboxController(Constants.USB.DRIVER_GAMEPAD);

  private final XboxController coDriverController = new XboxController(Constants.USB.CO_DRIVER_GAMEPAD);

  private SendableChooser<StartPosition> chooserStart = new SendableChooser<>();
  private SendableChooser<EndPosition> chooserEnd = new SendableChooser<>();


  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    SmartDashboard.putNumber("Autonomous Wait", 0);
    chooserStart.addOption("Straight Start", StartPosition.centre);
    chooserStart.addOption("Right Start", StartPosition.droite);
    chooserStart.addOption("Left Start", StartPosition.gauche);
    chooserStart.addOption("Avancer", StartPosition.avancer);
    chooserEnd.addOption("Ballon End", EndPosition.ballon);
    chooserEnd.addOption("Right End", EndPosition.droite);
    chooserEnd.addOption("Left End", EndPosition.gauche);
    chooserEnd.addOption("Stay", EndPosition.stay);
    SmartDashboard.putData("Auto Start", chooserStart);
    SmartDashboard.putData("Auto End", chooserEnd);
    drivetrain.setDefaultCommand(new DrivetrainDriveCommand(drivetrain, driverController));
    lanceur.setDefaultCommand(new LanceurCommand(lanceur));
    feeder.setDefaultCommand(new FeederTournerBasCommand(feeder));
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
    new JoystickButton(coDriverController, Button.kX.value).whileHeld(new FeederTournerHautBasCommand(feeder, -1));
    new JoystickButton(coDriverController, Button.kY.value).whileHeld(new FeederTournerHautBasCommand(feeder, 1));
    new JoystickButton(coDriverController, Button.kA.value).whenPressed(new ActiverDesactiverLanceurCommand(lanceur));
    new DigitalInputButton(feeder.getCapteurRaw(0)).whenPressed(new FeederMonterUnBallonCommand(feeder));
    new JoystickButton(coDriverController, Button.kB.value).whenPressed(new MonterGobeurCommand(gobeur));
    new JoystickButton(driverController, Button.kY.value).whileHeld(new MonterGrimpeurCommand(grimpeur));
    new JoystickButton(driverController, Button.kA.value).whileHeld(new DescendreGrimpeurCommand(grimpeur));
    new JoystickButton(driverController, Button.kB.value).whileHeld(new MonterWinchGrimpeurCommand(grimpeur));
    new JoystickButton(driverController, Button.kX.value).whileHeld(new DescendreWinchCommand(grimpeur));
    new JoystickButton(coDriverController, Button.kStart.value).whenPressed(new ActivateDeactivateFeederBasCommand(feeder));
    new JoystickButton(driverController, Button.kStart.value).whenPressed(new AvancerPiedsCommand(drivetrain, -1.2));
    new JoystickButton(coDriverController, Button.kBack.value).whenPressed(new SetGobeurEncoderPosCommand(gobeur, 38));
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    drivetrain.resetEncoder();
    return new SequentialCommandGroup(new WaitAutonomousTimerCommand(), AutonomousCommands.autonomous(drivetrain, lanceur, feeder, gobeur, chooserStart.getSelected(), chooserEnd.getSelected()));
  }
}
