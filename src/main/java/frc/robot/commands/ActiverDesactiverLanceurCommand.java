/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.Lanceur;
import frc.util.records.Logs;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class ActiverDesactiverLanceurCommand extends InstantCommand {

  public Lanceur lanceur;
    
  public ActiverDesactiverLanceurCommand(Lanceur lanceur) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.lanceur = lanceur;
    addRequirements(lanceur);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    lanceur.enableLanceur(!lanceur.getLanceurEnable());
    if (lanceur.getLanceurEnable()) {
      Logs.addEvenement("Lanceur Activé");
    }else{
      Logs.addEvenement("Lanceur Désactivé");
    }
  }
}
