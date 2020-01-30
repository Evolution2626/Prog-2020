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

public class Feeder extends SubsystemBase {
  /**
   * Creates a new Feeder.
   */
  private VictorSPX feeder;
  private DigitalInput[] capteurs = new DigitalInput[5];
  private DigitalInput capteurRest;



  public Feeder() {
    feeder = new VictorSPX(Constants.CAN.MOTEUR_FEEDER);

    capteurs[0] = new DigitalInput(Constants.DIO.CAPTEUR_UN);
    capteurs[1] = new DigitalInput(Constants.DIO.CAPTEUR_DEUX);
    capteurs[2] = new DigitalInput(Constants.DIO.CAPTEUR_TROIS);
    capteurs[3] = new DigitalInput(Constants.DIO.CAPTEUR_QUATRE);
    capteurs[4] = new DigitalInput(Constants.DIO.CAPTEUR_CINQ);
    capteurRest = new DigitalInput(Constants.DIO.CAPTEUR_REST);

  }

  public void setSpeed(double speed){
    feeder.set(ControlMode.PercentOutput, speed);
  }

  public void enableFeeder(boolean enable){
    if (enable) {
      setSpeed(Constants.SPEED.FEEDER_SPEED);
    }else {
      setSpeed(0);
    }
  }

  public boolean getCapteurValue(int capteur){
    return !capteurs[capteur].get();
  }

  public boolean getCapteurRestValue(){
    return !capteurRest.get();
  }

  public DigitalInput getCapteurRest(){
    return capteurRest;
  }

  public int countBallon(){
    int count = 0;
    for(int i = 0; i <= 4; i++){
      if (getCapteurValue(i)) {
        count ++;
      }
    }
    return count;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
