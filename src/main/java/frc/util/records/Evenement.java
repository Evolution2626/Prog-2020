/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.util.records;

/**
 * Add your docs here.
 */
public class Evenement {
    public double elapsedTime;
    public String description;

    public Evenement(String description, double elapsedTime){
        this.elapsedTime = elapsedTime;
        this.description = description;
    }
    
}
