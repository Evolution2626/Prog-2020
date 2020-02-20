/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;
import frc.util.Range;

public class AvancerPiedsCommand extends CommandBase {
  /**
   * Creates a new AvancerPieds.
   */
  private Drivetrain drivetrain;
  private double pieds;
  private double toursEncoder;
  private double P = .2;
  

  public AvancerPiedsCommand(Drivetrain drivetrain, double pieds){
    // Use addRequirements() here to declare subsystem dependencies.
    this.drivetrain = drivetrain;
    this.pieds = pieds;
    addRequirements(drivetrain);
    toursEncoder = pieds / Constants.ROBOT_CHARACTERIZATION.encoderConstantPieds;
  }

  private double PID(){
    double error = pieds - ((drivetrain.getLeftEncodersPosition() + drivetrain.getRightEncodersPosition())/2)*Constants.ROBOT_CHARACTERIZATION.encoderConstantPieds;
    return P*error;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    drivetrain.resetEncoder();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double speed = Range.coerce(-.5, .5, PID());
    speed = Range.minCoerce(.1, speed);
    drivetrain.driveTank(speed, speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drivetrain.driveTank(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return (drivetrain.getLeftEncodersPosition() + drivetrain.getRightEncodersPosition()) / 2 >= toursEncoder;
  }
}
