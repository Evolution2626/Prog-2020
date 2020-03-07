/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Feeder extends SubsystemBase {
  /**
   * Creates a new Feeder.
   */
  private VictorSPX feederHaut;
  private VictorSPX feederBas;
  private boolean activateBas;

  private DigitalInput[] capteurs = new DigitalInput[3];


  public Feeder() {
    feederHaut = new VictorSPX(Constants.CAN.MOTEUR_FEEDER_HAUT);
    feederBas = new VictorSPX(Constants.CAN.MOTEUR_FEEDER_BAS);

    capteurs[0] = new DigitalInput(Constants.DIO.CAPTEUR_DEUX);
    capteurs[1] = new DigitalInput(Constants.DIO.CAPTEUR_TROIS);
    capteurs[2] = new DigitalInput(Constants.DIO.CAPTEUR_QUATRE);
    
    feederBas.setNeutralMode(NeutralMode.Brake);
    feederHaut.setNeutralMode(NeutralMode.Brake); 

  }

  public boolean getAcvtivateBas(){
    return activateBas;
  }

  public void setActivateBas(boolean state){
    activateBas = state;
  }

  public void setSpeedHaut(double speed) {
    feederHaut.set(ControlMode.PercentOutput, speed);
  }

  public void setSpeedBas(double speed) {
    feederBas.set(ControlMode.PercentOutput, speed);
  }

  public boolean getCapteurValue(int capteur) {
    return capteurs[capteur].get();
  }

  public DigitalInput getCapteurRaw(int capteur) {
    return capteurs[capteur];
  }
  
  @Override
  public void periodic() {
    SmartDashboard.putBoolean("Capteur un", capteurs[0].get());
    SmartDashboard.putBoolean("Capteur deux", capteurs[1].get());
    SmartDashboard.putBoolean("Capteur trois", capteurs[2].get());

    // This method will be called once per scheduler run
  }
}
