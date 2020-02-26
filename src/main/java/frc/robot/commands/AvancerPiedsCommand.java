/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;
import frc.util.Range;
import frc.util.records.Logs;

public class AvancerPiedsCommand extends CommandBase {
  /**
   * Creates a new AvancerPieds.
   */
  private Drivetrain drivetrain;
  private double pieds;
  private double toursEncoder;
  private Range.DoubleCoerce doubleCoerce;
   

  public AvancerPiedsCommand(Drivetrain drivetrain, double pieds){
    // Use addRequirements() here to declare subsystem dependencies.
    this.drivetrain = drivetrain;
    this.pieds = pieds;
    addRequirements(drivetrain);
    toursEncoder = pieds / Constants.ROBOT_CHARACTERIZATION.encoderConstantPieds;
    doubleCoerce = new Range.DoubleCoerce();

  }

  private double PID(double value, double P){
    double error = pieds - value;
    return P*error;
  }



  private double getVelocityError(){
    return ((drivetrain.getLeftEncodersVelocity() + drivetrain.getRightEncodersVelocity())/2);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    drivetrain.resetEncoder();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double speedDroit = PID(drivetrain.getRightEncodersPosition()*Constants.ROBOT_CHARACTERIZATION.encoderConstantPieds, .2);
    double speedGauche = PID(drivetrain.getLeftEncodersPosition()*Constants.ROBOT_CHARACTERIZATION.encoderConstantPieds, .2);
    double alignement = PID((drivetrain.getRightEncodersPosition()-drivetrain.getLeftEncodersPosition())* Constants.ROBOT_CHARACTERIZATION.encoderConstantPieds, .2);
    //speedDroit -= alignement;
    //speedGauche += alignement;
    doubleCoerce.setSpeed(speedGauche, speedDroit, .5);
    drivetrain.driveTank(doubleCoerce.getSpeedLeft(), doubleCoerce.getSpeedRight());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drivetrain.driveTank(0, 0);
    Logs.addEvenement("Avancer de " + pieds + " pieds");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return (drivetrain.getLeftEncodersPosition() + drivetrain.getRightEncodersPosition()) / 2 >= toursEncoder;
  }
}
