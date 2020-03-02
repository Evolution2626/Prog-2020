/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.util;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.button.Button;

/**
 * Add your docs here.
 */

public class DigitalInputButton extends Button{

    private DigitalInput input;
    public DigitalInputButton(DigitalInput input){
        this.input = input;

    }

    @Override
    public boolean get() {
        return input.get();
    }

}
