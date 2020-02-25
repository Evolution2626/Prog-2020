/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;


import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Lanceur extends SubsystemBase {
  /**
   * Creates a new Lanceur.
   */
  public TalonSRX lanceurHaut;
  public TalonSRX lanceurBas;
  public boolean lanceurEnable = false;
  
  

  public Lanceur() {
    lanceurHaut = new TalonSRX(Constants.CAN.MOTEUR_LANCEUR_RIGHT);
    lanceurBas = new TalonSRX(Constants.CAN.MOTEUR_LANCEUR_LEFT);
    
    lanceurBas.setNeutralMode(NeutralMode.Brake);
    lanceurHaut.setNeutralMode(NeutralMode.Brake);
  }

  public void setSpeed(double speed){
    lanceurBas.set(ControlMode.PercentOutput, speed);
    lanceurHaut.set(ControlMode.PercentOutput, -speed);
  }
  
  public void enableLanceur(boolean enable){
    lanceurEnable = enable;
  }

  public boolean getLanceurEnable(){
    return lanceurEnable;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
