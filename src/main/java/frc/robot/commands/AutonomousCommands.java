/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Lanceur;

/**
 * Add your docs here.
 */
public class AutonomousCommands {

    
    public static Command autonomousTestCommand(Drivetrain drivetrain, Lanceur lanceur, Feeder feeder){
        return new SequentialCommandGroup(new AvancerPiedsCommand(drivetrain, 3.5),
                                          new WaitCommand(1),
                                          new TournerGyroCommand(drivetrain, 90),
                                          new WaitCommand(1),
                                          new AvancerPiedsCommand(drivetrain, 3.5),
                                          new WaitCommand(1),
                                          new TournerGyroCommand(drivetrain, 90),
                                          new WaitCommand(1),
                                          new AvancerPiedsCommand(drivetrain, 7),
                                          new WaitCommand(1),
                                          new TournerGyroCommand(drivetrain, 90),
                                          new WaitCommand(1),
                                          new AvancerPiedsCommand(drivetrain, 3.5),
                                          new WaitCommand(1),
                                          new TournerGyroCommand(drivetrain, 90),
                                          new WaitCommand(1),
                                          new AvancerPiedsCommand(drivetrain, 3.5),
                                          new WaitCommand(1),
                                          new StopDrivetrainMotorsCommand(drivetrain) 
                                          );
    }
    public static Command autonomousRotateTestCommand(Drivetrain drivetrain, Lanceur lanceur, Feeder feeder){
        return new SequentialCommandGroup(new TournerGyroCommand(drivetrain, 90),
                                          new WaitCommand(1),
                                          new TournerGyroCommand(drivetrain, -90),
                                          new WaitCommand(1),
                                          new TournerGyroCommand(drivetrain, 180),
                                          new WaitCommand(1),
                                          new TournerGyroCommand(drivetrain, -180),
                                          new StopDrivetrainMotorsCommand(drivetrain) 
                                          );
    }

}
