/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.WaitCommand;

/**
 * Add your docs here.
 */
public class WaitAutonomousTimerCommand extends WaitCommand{
    public WaitAutonomousTimerCommand(){
        super(0);
    }

    @Override
    public boolean isFinished(){
        return m_timer.hasPeriodPassed(SmartDashboard.getNumber("Autonomous Wait", 0));
    }
}
