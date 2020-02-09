/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Lanceur;

/**
 * Add your docs here.
 */
public class AutonomousCommands {

    public static Command autonomusStraightCommand(Drivetrain drivetrain, Lanceur lanceur, Feeder feeder){
        return new SequentialCommandGroup(TrajectoryCommands.getCommand(TrajectoryCommands.trajectoryStraight(), drivetrain),
                                          new ActiverDesactiverLanceurCommand(lanceur),
                                          new LancerUnBallonCommand(feeder),
                                          new LancerUnBallonCommand(feeder),
                                          new LancerUnBallonCommand(feeder),
                                          new ActiverDesactiverLanceurCommand(lanceur),
                                          TrajectoryCommands.getCommand(TrajectoryCommands.trajectoryStraightSuite(), drivetrain)
                                          );
    }
    public static Command autonomusRightCommand(Drivetrain drivetrain, Lanceur lanceur, Feeder feeder){
        return new SequentialCommandGroup(TrajectoryCommands.getCommand(TrajectoryCommands.trajectoryStraight(), drivetrain),
                                          new ActiverDesactiverLanceurCommand(lanceur),
                                          new LancerUnBallonCommand(feeder),
                                          new LancerUnBallonCommand(feeder),
                                          new LancerUnBallonCommand(feeder),
                                          new ActiverDesactiverLanceurCommand(lanceur),
                                          TrajectoryCommands.getCommand(TrajectoryCommands.trajectoryStraightSuite(), drivetrain)
                                          );
    }
    public static Command autonomusLeftCommand(Drivetrain drivetrain, Lanceur lanceur, Feeder feeder){
        return new SequentialCommandGroup(TrajectoryCommands.getCommand(TrajectoryCommands.trajectoryStraight(), drivetrain),
                                          new ActiverDesactiverLanceurCommand(lanceur),
                                          new LancerUnBallonCommand(feeder),
                                          new LancerUnBallonCommand(feeder),
                                          new LancerUnBallonCommand(feeder),
                                          new ActiverDesactiverLanceurCommand(lanceur),
                                          TrajectoryCommands.getCommand(TrajectoryCommands.trajectoryStraightSuite(), drivetrain)
                                          );
    }

}
