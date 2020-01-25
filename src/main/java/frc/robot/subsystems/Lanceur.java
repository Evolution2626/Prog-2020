/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Lanceur extends SubsystemBase {
  /**
   * Creates a new Lanceur.
   */
  public VictorSPX lanceurHaut;
  public VictorSPX lanceurBas;
  public DigitalInput capteur;
  

  public Lanceur() {
    lanceurHaut = new VictorSPX(Constants.CAN.MOTEUR_LANCEUR_HAUT);
    lanceurBas = new VictorSPX(Constants.CAN.MOTEUR_LANCEUR_BAS);
    capteur = new DigitalInput(Constants.DIO.CAPTEUR);

  }
  public void setSpeed(double speed){
    lanceurBas.set(ControlMode.PercentOutput, speed);
    lanceurHaut.set(ControlMode.PercentOutput, -speed);
  }
  public boolean getCapteurValue(){
    return capteur.get();
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
