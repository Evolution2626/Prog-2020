/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.Drivetrain;
import frc.util.records.Logs;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class ActiverDesactiverSlowModeCommand extends InstantCommand {

  public Drivetrain drivetrain;

  public ActiverDesactiverSlowModeCommand(Drivetrain drivetrain){
    // Use addRequirements() here to declare subsystem dependencies.
    this.drivetrain = drivetrain;
    addRequirements(drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    drivetrain.setSlowMode(!drivetrain.getSlowMode());
    if (drivetrain.getSlowMode()) {
      Logs.addEvenement("SlowMode Activé");
    } else {
      Logs.addEvenement("SlowMode Désactivé");
    }
  }
}
