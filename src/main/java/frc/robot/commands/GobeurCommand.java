/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Gobeur;
import frc.util.records.Logs;

public class GobeurCommand extends CommandBase {
  /**
   * Creates a new GobeurCommand.
   */
  private Gobeur gobeur;
  private double speed;

  public GobeurCommand(Gobeur gobeur, double speed) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.gobeur = gobeur;
    this.speed = speed;
    addRequirements(gobeur);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    gobeur.setSpeed(speed);
    Logs.addEvenement("Gobeur commence à tourner");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    gobeur.setSpeed(0);
    Logs.addEvenement("Gobeur a fini de tourner");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
