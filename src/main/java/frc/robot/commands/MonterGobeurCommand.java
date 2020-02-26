/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

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
  XboxController controller;
  public MonterGobeurCommand(Gobeur gobeur, XboxController controller){
    // Use addRequirements() here to declare subsystem dependencies.
    this.gobeur = gobeur;
    this.controller = controller;
    addRequirements(gobeur);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    gobeur.setSpeedMonteur(Range.threshold(.1, controller.getRawAxis(Constants.AXES.AXES_GAUCHE)));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    gobeur.setSpeedMonteur(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
