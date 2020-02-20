/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import frc.util.Range;

public class TournerGyroCommand extends CommandBase {
  /**
   * Creates a new TournerGyroCommand.
   */
  private Drivetrain drivetrain;
  private double angle;
  private double startingAngle;
  private double P = 0.006;

  public TournerGyroCommand(Drivetrain drivetrain, double angle) {
    this.drivetrain = drivetrain;
    this.angle = angle;

    addRequirements(drivetrain);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    drivetrain.resetGyro();
    startingAngle = drivetrain.getGyroAngle();

  }

  private double PID(){
    double error = (angle - (drivetrain.getGyroAngle() - startingAngle));
    return P*error;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double speed = Range.coerce(-.3, .3, PID());
    speed = Range.minCoerce(.05, speed);
    drivetrain.driveTank(speed, -speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drivetrain.driveTank(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Range.inRange(angle - .5, angle + .5, drivetrain.getGyroAngle() - startingAngle);
  }
}
