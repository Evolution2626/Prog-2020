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
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Gobeur extends SubsystemBase {
  /**
   * Creates a new Gobeur.
   */
  private TalonSRX gobeur;
  private CANSparkMax monteurGobeur;
  private boolean isGobeurDown = false;

  public Gobeur() {
    gobeur = new TalonSRX(Constants.CAN.GOBEUR);
    monteurGobeur = new CANSparkMax(Constants.CAN.MOTEUR_LEVER_GOBEUR, MotorType.kBrushless);

    monteurGobeur.setIdleMode(IdleMode.kBrake);
    gobeur.setNeutralMode(NeutralMode.Brake);

    resetEncoder();
  }
  public void setSpeed(double speed){
    gobeur.set(ControlMode.PercentOutput, speed);
  }

  public void setSpeedMonteur(double speed){
    monteurGobeur.set(speed);
  }

  public void setGobeurPos(boolean state){
    isGobeurDown = state;
  }

  public void setMonteurGobeurMode(IdleMode mode){
    monteurGobeur.setIdleMode(mode);
  }

  public boolean isGobeurDown(){
    return isGobeurDown;
  }

  public double getEncoderPosition(){
    return monteurGobeur.getEncoder().getPosition();
  }

  public void resetEncoder(){
    monteurGobeur.getEncoder().setPosition(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Encodeur Gobeur", getEncoderPosition());
  }
}
