/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public interface Constants {

    public interface USB {
        int DRIVER_GAMEPAD = 0;
        int CO_DRIVER_GAMEPAD = 1;
        
    }
    public interface AXES {
        int AXES_GAUCHE = 1;
        int AXES_DROITE = 5;

    }
    public interface PWN {
        
    }
    public interface CAN {
        int MOTEUR_AVANT_DROIT = 30;
        int MOTEUR_AVANT_GAUCHE = 33;
        int MOTEUR_ARRIERE_DROIT = 32;
        int MOTEUR_ARRIERE_GAUCHE = 31;
        int GOBEUR = 3;
        int MOTEUR_LANCEUR_LEFT = 4;
        int MOTEUR_LANCEUR_RIGHT = 5;
        int MOTEUR_FEEDER_BAS = 6;
        int MOTEUR_FEEDER_HAUT = 7;
        int MOTEUR_GRIMPEUR = 8;
        int MOTEUR_LEVER_GOBEUR = 10;
        int MOTEUR_GRIMPEUR_WINCH = 12;
        
    }
    public interface DIO {
        int CAPTEUR_DEUX = 11;
        int CAPTEUR_TROIS = 13;
        int CAPTEUR_QUATRE = 12;

        int LIMIT_SWITCH_GRIMPEUR = 4;
        
    }
    public interface ANALOG {
    
        
    }
    public interface RELAY {
        int ACTUATOR = 0;
        
    }

    public interface SPEED {
        double FEEDER_SPEED = .2;
        double LANCEUR_SPEED = .7;
        double GOBEUR_SPEED = .6;
    }

    public interface ROBOT_CHARACTERIZATION{
        double ksVolts = 0.222;
        double kvVoltSecondMeter = 2.79;
        double kaVoltSecondSquarePerMeter = 0.355;

        double kPDriveVel = 2.56;

        double kTrackWidthMeter = 0.724818;
        DifferentialDriveKinematics kDriveKinematics = new DifferentialDriveKinematics(kTrackWidthMeter);

        double kMaxSpeedMeterPerSecond = 1;
        double kMaxAccelerationMeterPerSecondSquared = 1;

        double kRamseteB = 2;
        double kRamseteZeta = 0.7;

        double encoderConstant = (1/10.71) * .1524 * Math.PI;

        double encoderConstantPieds = (1/10.71) * .47119304 * Math.PI;

    }
}
