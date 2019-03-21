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
  private TalonSRX frontSlaveMotor = new TalonSRX(RobotMap.frontSlaveMotor);
  private VictorSPX climbMotionMotor = new VictorSPX(RobotMap.climbMotionMotor);

  RobotMap robotMap = new RobotMap();

  private int frontPosition;
  private int backPosition;

  private boolean frontClimbFinished = false;
  private boolean backClimbFinished = false;


  public Climb(){
    frontMasterMotor.configFactoryDefault();
    frontSlaveMotor.configFactoryDefault();
    backClimbMotor.configFactoryDefault();

    robotMap.TalonSRXInit(backClimbMotor, Constants.kClimbPeakOutput);
    robotMap.TalonSRXInit(frontMasterMotor, Constants.kClimbPeakOutput);
    robotMap.TalonSRXInit(frontSlaveMotor, Constants.kClimbPeakOutput);


    frontSlaveMotor.setInverted(false);
    climbMotionMotor.setInverted(true);
    //frontSlaveMotor.follow(frontMasterMotor);

    robotMap.setMotorPID(backClimbMotor, 0.197, 0, 0, 0);
    robotMap.setMotorPID(frontMasterMotor, 0.197, 0, 0, 0);
    robotMap.setMotorPID(frontSlaveMotor, 0.197, 0, 0, 0);

    frontMasterMotor.setNeutralMode(NeutralMode.Brake);
    frontSlaveMotor.setNeutralMode(NeutralMode.Brake);
    backClimbMotor.setNeutralMode(NeutralMode.Brake);    

    backClimbMotor.setSensorPhase(false);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void frontClimb(int _level, int _speed){

    frontPosition = Math.abs(frontMasterMotor.getSelectedSensorPosition());
    frontClimbFinished = (frontPosition >= _level);

    if (frontClimbFinished) {
      frontMasterMotor.set(ControlMode.Velocity, 0);
      frontSlaveMotor.set(ControlMode.Velocity, 0);  
    } else {
      frontMasterMotor.set(ControlMode.Velocity, _speed);
      frontSlaveMotor.set(ControlMode.Velocity, _speed);
    }
  }

  public void backClimb(int _level, int _speed){

    backPosition = Math.abs(backClimbMotor.getSelectedSensorPosition());
    backClimbFinished = (backPosition >= _level);

    if (backClimbFinished){
      backClimbMotor.set(ControlMode.Velocity, 0);
    } else {
      backClimbMotor.set(ControlMode.Velocity, _speed);
    }
  }

  public void climbMotion(){
    climbMotionMotor.set(ControlMode.PercentOutput, -0.2);
  }

  public void motionStop(){
    climbMotionMotor.set(ControlMode.PercentOutput, 0);

  }
}
