/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.util.records;

import java.util.Vector;

/**
 * Add your docs here.
 */
public class Logs {
    public static Vector<Evenement> evenements = new Vector<Evenement>();

    public static void addEvenement(double elapsedTime, String description){
        evenements.add(new Evenement(elapsedTime, description));
    }
}
