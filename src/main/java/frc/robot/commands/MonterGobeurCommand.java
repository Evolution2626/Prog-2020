/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Gobeur;
import frc.util.Range;

public class MonterGobeurCommand extends CommandBase {
  /**
   * Creates a new MonterGobeur.
   */
  Gobeur gobeur;
  boolean previousPos;
  double positionUp = 0;
  double positionDown = 38; //41.142442;

  public MonterGobeurCommand(Gobeur gobeur){
    // Use addRequirements() here to declare subsystem dependencies.
    this.gobeur = gobeur;
    addRequirements(gobeur);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //gobeur.setGobeurPos(false);
    previousPos = gobeur.isGobeurDown();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (previousPos) {
      gobeur.setMonteurGobeurMode(IdleMode.kBrake);
      gobeur.setSpeedMonteur(Range.coerce(-.7, .7, positionUp - gobeur.getEncoderPosition()));
    } else {
      gobeur.setMonteurGobeurMode(IdleMode.kCoast);
      gobeur.setSpeedMonteur(Range.coerce(-.7, .7, positionDown - gobeur.getEncoderPosition()));
    }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    gobeur.setSpeedMonteur(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (previousPos) {
      return Range.inRange(-1, positionUp, gobeur.getEncoderPosition());
    } else{
      return Range.inRange(positionDown-4, positionDown+1, gobeur.getEncoderPosition());
    }
  }
}
