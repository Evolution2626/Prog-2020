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

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
  public boolean slowMode = false;

  

  public Gyro gyro = new ADXRS450_Gyro();

  public DifferentialDriveOdometry odometry;

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
    avantGauche.setInverted(true);
    arriereDroit.setInverted(false);
    arriereGauche.setInverted(true);

    avantDroit.clearFaults();
    avantGauche.clearFaults();
    arriereDroit.clearFaults();
    arriereGauche.clearFaults();

    setAllCurrentLimit(20, 15);

    resetEncoder();

    odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(getHeading()));
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

  public void driveTankVolts(double gauche, double droite){
    avantDroit.setVoltage(droite);
    avantGauche.setVoltage(gauche);
    arriereDroit.setVoltage(droite);
    arriereGauche.setVoltage(gauche);
  }

  public double getLeftEncodersPosition(){
    return ((avantGauche.getEncoder().getPosition() + arriereGauche.getEncoder().getPosition()) /2) * Constants.ROBOT_CHARACTERIZATION.encoderConstant;
  }

  public double getRightEncodersPosition(){
    return ((avantDroit.getEncoder().getPosition() + arriereDroit.getEncoder().getPosition()) /2) * Constants.ROBOT_CHARACTERIZATION.encoderConstant;
  }

  public double getLeftEncodersRate(){
    return ((avantGauche.getEncoder().getVelocity() + arriereGauche.getEncoder().getVelocity()) /2) * Constants.ROBOT_CHARACTERIZATION.encoderConstant / 60.;
  }

  public double getRightEncodersRate(){
    return ((avantDroit.getEncoder().getVelocity() + arriereDroit.getEncoder().getVelocity()) /2) * Constants.ROBOT_CHARACTERIZATION.encoderConstant / 60.;
  }
  
  public void resetEncoder(){
    avantDroit.getEncoder().setPosition(0);
    avantGauche.getEncoder().setPosition(0);
    arriereDroit.getEncoder().setPosition(0);
    arriereGauche.getEncoder().setPosition(0);
  }

  public double getHeading(){
    return Math.IEEEremainder(gyro.getAngle(), 360);
  }

  public Pose2d getPose(){
    return odometry.getPoseMeters();
  }

  public void resetOdometry(Pose2d pose){
    resetEncoder();
    odometry.resetPosition(pose, Rotation2d.fromDegrees(getHeading()));
  }
  
  public DifferentialDriveWheelSpeeds getWheelSpeed(){
    return new DifferentialDriveWheelSpeeds(getLeftEncodersRate(), getRightEncodersRate());
  }

  public void setSlowMode(boolean state){
    slowMode = state;
  }

  public boolean getSlowMode(){
    return slowMode;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    odometry.update(Rotation2d.fromDegrees(getHeading()), getLeftEncodersPosition(), getRightEncodersPosition());
    SmartDashboard.putBoolean("Slow Mode", getSlowMode());
  }
}
