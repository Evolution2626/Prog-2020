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

public class AvancerPiedsCommand extends CommandBase {
  /**
   * Creates a new AvancerPieds.
   */
  private Drivetrain drivetrain;
  private double pieds;
  private double toursEncoder;
  private double P, I, D = 1;
  private double integral, error, previous_error, derivative = 0;
  

  public AvancerPiedsCommand(Drivetrain drivetrain, double pieds){
    // Use addRequirements() here to declare subsystem dependencies.
    this.drivetrain = drivetrain;
    this.pieds = pieds;
    addRequirements(drivetrain);
    toursEncoder = pieds / Constants.ROBOT_CHARACTERIZATION.encoderConstantPieds;
  }

  private double PID(){
    error = pieds - ((drivetrain.getLeftEncodersPosition() + drivetrain.getRightEncodersPosition())/2)*Constants.ROBOT_CHARACTERIZATION.encoderConstantPieds;
    integral += (error*.02);
    derivative = (error - previous_error)/.02;
    return P*error + I*integral + D*derivative;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    drivetrain.resetEncoder();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double speed = PID();
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
