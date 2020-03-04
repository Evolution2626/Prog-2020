/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Feeder;

public class FeederTournerBasCommand extends CommandBase {
  /**
   * Creates a new FeederTournerBasCommand.
   */
  private Feeder feeder;

  public FeederTournerBasCommand(Feeder feeder) {
    this.feeder = feeder;
    addRequirements(feeder);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    feeder.setSpeedHaut(0);
    if (feeder.getAcvtivateBas()) {
      feeder.setSpeedBas(-.65);
    }else{
      feeder.setSpeedBas(0);
    }
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    feeder.setSpeedBas(0);
    feeder.setSpeedHaut(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
