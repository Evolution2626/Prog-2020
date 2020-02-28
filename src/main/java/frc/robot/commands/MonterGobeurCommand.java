/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Gobeur;
import frc.util.Range;

public class MonterGobeurCommand extends CommandBase {
  /**
   * Creates a new MonterGobeur.
   */
  Gobeur gobeur;
  double positionUp = 0;
  double positionDown = 0;

  public MonterGobeurCommand(Gobeur gobeur){
    // Use addRequirements() here to declare subsystem dependencies.
    this.gobeur = gobeur;
    addRequirements(gobeur);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (gobeur.isGobeurDown()) {
      gobeur.setMonteurGobeurMode(IdleMode.kBrake);
      gobeur.setSpeedMonteur(Range.coerce(-.5, .5, positionUp - gobeur.getEncoderPosition()));
    } else {
      gobeur.setMonteurGobeurMode(IdleMode.kCoast);
      gobeur.setSpeedMonteur(Range.coerce(-.5, .5, positionDown - gobeur.getEncoderPosition()));
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    gobeur.setSpeedMonteur(0);
    gobeur.setGobeurPos(!gobeur.isGobeurDown());
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (gobeur.isGobeurDown()) {
      return gobeur.getEncoderPosition() <= positionUp;
    } else{
      return gobeur.getEncoderPosition() >= positionDown;
    }
    
  }
}
