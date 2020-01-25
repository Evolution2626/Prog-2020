/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {
  /**
   * Creates a new Drivetrain.
   */
  
  private CANSparkMax avantDroit;
  private CANSparkMax avantGauche;
  private CANSparkMax arriereDroit;
  private CANSparkMax arriereGauche;

  public Drivetrain() {

    avantDroit = new CANSparkMax(Constants.CAN.MOTEUR_AVANT_DROIT, MotorType.kBrushless);
    avantGauche = new CANSparkMax(Constants.CAN.MOTEUR_AVANT_GAUCHE, MotorType.kBrushless);
    arriereDroit = new CANSparkMax(Constants.CAN.MOTEUR_ARRIERE_DROIT, MotorType.kBrushless);
    arriereGauche = new CANSparkMax(Constants.CAN.MOTEUR_ARRIERE_GAUCHE, MotorType.kBrushless);

    avantDroit.setIdleMode(IdleMode.kBrake);
    avantGauche.setIdleMode(IdleMode.kBrake);
    arriereDroit.setIdleMode(IdleMode.kBrake);
    arriereGauche.setIdleMode(IdleMode.kBrake);

    avantDroit.setClosedLoopRampRate(.1);
    avantGauche.setClosedLoopRampRate(.1);
    arriereDroit.setClosedLoopRampRate(.1);
    arriereGauche.setClosedLoopRampRate(.1);

    avantDroit.setInverted(false);
    avantGauche.setInverted(false);
    arriereDroit.setInverted(false);
    arriereGauche.setInverted(false);

    setAllCurrentLimit(55, 20);
  }

  public void setAllCurrentLimit(int stall, int free){
    avantDroit.setSmartCurrentLimit(stall,free);
    avantGauche.setSmartCurrentLimit(stall,free);
    arriereDroit.setSmartCurrentLimit(stall,free);
    arriereGauche.setSmartCurrentLimit(stall,free);
  }

  public void driveTank(double gauche, double droite){
    avantDroit.set(droite);
    avantGauche.set(gauche);
    arriereDroit.set(droite);
    arriereGauche.set(gauche);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
