/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Gobeur;
import frc.robot.subsystems.Lanceur;

/**pieds
 * Add your docs here.
 */
public class AutonomousCommands {
    public static enum StartPosition {centre, droite, gauche}
    public static enum EndPosition {ballon, droite, gauche}
    
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

    public static Command avanceDroitAndShoot(Drivetrain drivetrain, Lanceur lanceur, Feeder feeder, Gobeur gobeur){
        return new SequentialCommandGroup(  new MonterGobeurCommand(gobeur),
                                            new AvancerPiedsCommand(drivetrain, 9),
                                            new ActiverDesactiverLanceurCommand(lanceur),
                                            new WaitCommand(1),
                                            new ParallelRaceGroup(
                                            new FeederTournerHautBasCommand(feeder, -1),
                                            new WaitCommand(3)),
                                            new ActiverDesactiverLanceurCommand(lanceur),
                                            new TournerGyroCommand(drivetrain, 121.78),
                                            new AvancerPiedsCommand(drivetrain, 10.59),
                                            new TournerGyroCommand(drivetrain, 58.2),
                                            new AvancerPiedsCommand(drivetrain, 10.22)
                                            );


    }

    public static Command autonomous(Drivetrain drivetrain, Lanceur lanceur, Feeder feeder, Gobeur gobeur, StartPosition startPosition, EndPosition endPosition){
        SequentialCommandGroup seRendre = new SequentialCommandGroup();
        SequentialCommandGroup allerTirer = new SequentialCommandGroup( new MonterGobeurCommand(gobeur),
                                                                        new AvancerPiedsCommand(drivetrain, 9),
                                                                        new ActiverDesactiverLanceurCommand(lanceur),
                                                                        new WaitCommand(1),
                                                                        new ParallelRaceGroup(
                                                                        new FeederTournerHautBasCommand(feeder, -1),
                                                                        new WaitCommand(3)),
                                                                        new ActiverDesactiverLanceurCommand(lanceur));
        SequentialCommandGroup seTasser = new SequentialCommandGroup();
        if(startPosition == StartPosition.gauche){
            seRendre = new SequentialCommandGroup(  new AvancerPiedsCommand(drivetrain, 5.58),
                                                    new TournerGyroCommand(drivetrain, -90));
        }
        if(startPosition == StartPosition.droite){
            seRendre = new SequentialCommandGroup(  new AvancerPiedsCommand(drivetrain, 5.58),
                                                    new TournerGyroCommand(drivetrain, 90));
        }

        if(endPosition == EndPosition.ballon){
            seTasser = new SequentialCommandGroup(  new TournerGyroCommand(drivetrain, 121.78),
                                                    new AvancerPiedsCommand(drivetrain, 10.59),
                                                    new TournerGyroCommand(drivetrain, 58.2),
                                                    new AvancerPiedsCommand(drivetrain, 10.22));
        }
        if(endPosition == EndPosition.droite){
            seTasser = new SequentialCommandGroup(  new TournerGyroCommand(drivetrain, 90),
                                                    new AvancerPiedsCommand(drivetrain, 5.58));
        }
        if(endPosition == EndPosition.gauche){
            seTasser = new SequentialCommandGroup(  new TournerGyroCommand(drivetrain, -90),
                                                    new AvancerPiedsCommand(drivetrain, 4.17));
        }
        return new SequentialCommandGroup(seRendre, allerTirer, seTasser);
    }

}
