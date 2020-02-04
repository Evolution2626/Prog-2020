/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Feeder;

public class ChargerCommand extends CommandBase {
  /**
   * Creates a new ChargerCommand.
   */
  private Feeder feeder;
  private int previousCount;

  public ChargerCommand(Feeder feeder) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.feeder = feeder;
    addRequirements(feeder);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    previousCount = feeder.countBallon();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    feeder.enableFeeder(true);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    feeder.enableFeeder(false);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (feeder.getCapteurValue(4)) {
      return true;
    }
    return (feeder.countBallon() > previousCount) && !feeder.getCapteurRestValue();
  }
}
