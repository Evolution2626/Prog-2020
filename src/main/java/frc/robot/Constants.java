/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

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
        int MOTEUR_AVANT_DROIT = 0;
        int MOTEUR_AVANT_GAUCHE = 0;
        int MOTEUR_ARRIERE_DROIT = 0;
        int MOTEUR_ARRIERE_GAUCHE = 0;
        int GOBEUR = 0;
        int MOTEUR_CANON_HAUT = 0;
        int MOTEUR_CANON_BAS =0;
        
    }
    public interface DIO {
        int CAPTEUR = 0;
        
    }
    public interface ANALOG {
    
        
    }
    public interface RELAY {
    
        
    }
}
