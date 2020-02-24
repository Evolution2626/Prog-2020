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
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Grimpeur extends SubsystemBase {
  /**
   * Creates a new Grimpeur.
   */
  private VictorSPX monteur;
  private CANSparkMax winch;
  private DigitalInput limitSwitch;
  public Grimpeur() {
    monteur = new VictorSPX(Constants.CAN.MOTEUR_GRIMPEUR);
    winch = new CANSparkMax(Constants.CAN.MOTEUR_GRIMPEUR_WINCH, MotorType.kBrushless);
    winch.setIdleMode(IdleMode.kBrake);
    monteur.setNeutralMode(NeutralMode.Brake);
    limitSwitch = new DigitalInput(Constants.DIO.LIMIT_SWITCH_GRIMPEUR);
  }

  public void setMonteurSpeed(double speed) {
    monteur.set(ControlMode.PercentOutput, speed);
  }
  public void setWinchSpeed(double speed) {
    winch.set(speed);
  }
  public boolean getLimitSwitchValue() {
    return limitSwitch.get();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
