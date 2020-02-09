/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.List;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;

/**
 * Add your docs here.
 */
public class TrajectoryCommands {
    private static DifferentialDriveVoltageConstraint autoVoltageConstraint = new DifferentialDriveVoltageConstraint(
        new SimpleMotorFeedforward(Constants.ROBOT_CHARACTERIZATION.ksVolts, 
                                   Constants.ROBOT_CHARACTERIZATION.kvVoltSecondMeter, 
                                   Constants.ROBOT_CHARACTERIZATION.kaVoltSecondSquarePerMeter), 
            Constants.ROBOT_CHARACTERIZATION.kDriveKinematics, 
            10);
  
    private static TrajectoryConfig config = new TrajectoryConfig(
        Constants.ROBOT_CHARACTERIZATION.kMaxSpeedMeterPerSecond, 
        Constants.ROBOT_CHARACTERIZATION.kMaxAccelerationMeterPerSecondSquared).setReversed(false);

    public static Trajectory trajectoryTest(){
        return TrajectoryGenerator.generateTrajectory(
            new Pose2d(0 ,0, new Rotation2d(0)), 
            List.of(
                new Translation2d(.5, 0),
                new Translation2d(1, -1)

            ), 
            new Pose2d(2, 1, new Rotation2d(0)), 
            config);
    }

    public static Trajectory trajectoryStraight(){
        return TrajectoryGenerator.generateTrajectory(
            new Pose2d(0 ,0, new Rotation2d(0)), 
            List.of(
                //new Translation2d(3.1, 0)
            ), 
            new Pose2d(3.1, 0, new Rotation2d(0)), 
            config);
    }
    public static Trajectory trajectoryStraightSuite(){
        return TrajectoryGenerator.generateTrajectory(
            new Pose2d(3.1 ,0, new Rotation2d(0)), 
            List.of(
                new Translation2d(0, 1.68)

            ), 
            new Pose2d(-3.1, 1.68, new Rotation2d(Math.PI)), 
            config);
    }

    public static Command getCommand(Trajectory trajectory, Drivetrain drivetrain){
        
        RamseteCommand cmd = new RamseteCommand(
            trajectory, 
            drivetrain::getPose, 
            new RamseteController(
                Constants.ROBOT_CHARACTERIZATION.kRamseteB,
                Constants.ROBOT_CHARACTERIZATION.kRamseteZeta), 
            new SimpleMotorFeedforward(
                Constants.ROBOT_CHARACTERIZATION.ksVolts, 
                Constants.ROBOT_CHARACTERIZATION.kvVoltSecondMeter, 
                Constants.ROBOT_CHARACTERIZATION.kaVoltSecondSquarePerMeter),
            Constants.ROBOT_CHARACTERIZATION.kDriveKinematics,
            drivetrain::getWheelSpeed,
            new PIDController(Constants.ROBOT_CHARACTERIZATION.kPDriveVel, 0, 0),
            new PIDController(Constants.ROBOT_CHARACTERIZATION.kPDriveVel, 0, 0),
            drivetrain::driveTankVolts,
            drivetrain);
            return cmd.andThen(() -> drivetrain.driveTankVolts(0, 0));
    }

}
