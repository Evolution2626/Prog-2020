/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;



import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;
import frc.util.Range;

public class DrivetrainDriveCommand extends CommandBase {
  /**
   * Creates a new DrivetrainDriveCommand.
   */
  private Drivetrain drivetrain;
  private XboxController controller;

  public DrivetrainDriveCommand(Drivetrain drivetrain, XboxController controller) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.controller = controller;
    this.drivetrain = drivetrain;
    addRequirements(drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(RobotState.isOperatorControl()){
      double speedDroit = -controller.getRawAxis(Constants.AXES.AXES_DROITE);
      speedDroit = Range.threshold(.1, speedDroit);
      speedDroit = Math.pow(speedDroit, 3);
      double speedGauche = -controller.getRawAxis(Constants.AXES.AXES_GAUCHE);
      speedGauche = Range.threshold(.1, speedGauche);
      speedGauche = Math.pow(speedGauche, 3);
      if (controller.getBumper(Hand.kLeft)) {
        drivetrain.driveTank(speedGauche * .5, speedDroit * .5);
      }else {
        drivetrain.driveTank(speedGauche, speedDroit);
        
      }
    }
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
