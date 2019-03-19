/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc7280.mecanum_drive_test.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import org.usfirst.frc7280.mecanum_drive_test.Constants;
import org.usfirst.frc7280.mecanum_drive_test.RobotMap;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Add your docs here.
 */
public class Climb extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  
  private TalonSRX backClimbMotor = new TalonSRX(RobotMap.backClimbMotor);
  private TalonSRX frontMasterMotor = new TalonSRX(RobotMap.frontMasterMotor);
  private VictorSPX frontSlaveMotor = new VictorSPX(RobotMap.frontSlaveMotor);
  private VictorSPX climbMotionMotor = new VictorSPX(RobotMap.climbMotionMotor);

  RobotMap robotMap = new RobotMap();

  public Climb(){
    frontSlaveMotor.follow(frontMasterMotor);

    robotMap.TalonSRXInit(backClimbMotor, Constants.kBasePeakOutput);
    robotMap.TalonSRXInit(frontMasterMotor, Constants.kBasePeakOutput);

    robotMap.setMotorPID(backClimbMotor, 0, 0, 0, 0);
    robotMap.setMotorPID(frontMasterMotor, 0, 0, 0, 0);

    frontMasterMotor.setNeutralMode(NeutralMode.Brake);
    backClimbMotor.setNeutralMode(NeutralMode.Brake);    
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void frontClimb(int _level){
    frontMasterMotor.set(ControlMode.Position, _level);
  }

  public void backClimb(int _level){
    backClimbMotor.set(ControlMode.Position, _level);
  }

  public void climbMotion(){
    climbMotionMotor.set(ControlMode.PercentOutput, 0.5);
  }

  public void motionStop(){
    climbMotionMotor.set(ControlMode.PercentOutput, 0);

  }
}
