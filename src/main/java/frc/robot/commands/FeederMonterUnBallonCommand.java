/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Feeder;

public class FeederMonterUnBallonCommand extends CommandBase {
  /**
   * Creates a new FeederMonterUnBallonCommand.
   */
  private Feeder feeder;

  private int etape;
  private boolean stop;

  public FeederMonterUnBallonCommand(Feeder feeder) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.feeder = feeder;
    addRequirements(feeder);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    etape = 1;
    stop = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (etape == 1){
      if (feeder.getCapteurValue(2)) {
        feeder.setSpeedBas(0);
        stop = true;
      }else{
        feeder.setSpeedHaut(1);
        etape = 2;
      }
    }else if (etape == 2) {
      if (feeder.getCapteurValue(1)) {
        feeder.setSpeedBas(0);
      }else{
        feeder.setSpeedBas(1);
        etape = 3;
      }
    }else if (etape == 3) {
      if (!feeder.getCapteurValue(0) && feeder.getCapteurValue(1)){
        feeder.setSpeedHaut(0);
        stop = true;
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
    return stop;
  }
}
