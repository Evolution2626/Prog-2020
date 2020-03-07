/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.subsystems.Lanceur;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class SetLanceurActiveCommand extends ActiverDesactiverLanceurCommand {
  private boolean state;
  public SetLanceurActiveCommand(Lanceur lanceur, boolean state) {
    super(lanceur);
    this.state = state;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    lanceur.enableLanceur(state);
  }
}
