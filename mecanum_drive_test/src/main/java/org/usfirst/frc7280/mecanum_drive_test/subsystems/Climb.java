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
import org.usfirst.frc7280.mecanum_drive_test.Robot;
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

  private int level = 0;
  private int frontLevel;
  private int backLevel;

  RobotMap robotMap = new RobotMap();

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

    robotMap.setMotorPID(backClimbMotor, 0, 0.07, 0, 0);
    robotMap.setMotorPID(frontMasterMotor, 0, 0.16, 0, 0);
    robotMap.setMotorPID(frontSlaveMotor, 0, 0.14, 0, 0);

    frontMasterMotor.setNeutralMode(NeutralMode.Brake);
    frontSlaveMotor.setNeutralMode(NeutralMode.Brake);
    backClimbMotor.setNeutralMode(NeutralMode.Brake);

    backClimbMotor.setSensorPhase(false);
    frontSlaveMotor.setSensorPhase(false);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void climbStage(int _level){
    int step = _level / 4;

    robotMap.TalonSRXInit(backClimbMotor, Constants.kClimbPeakOutput);
    robotMap.TalonSRXInit(frontMasterMotor, Constants.kClimbPeakOutput);
    robotMap.TalonSRXInit(frontSlaveMotor, Constants.kClimbPeakOutput);

    
    if (Math.abs(Math.abs(frontMasterMotor.getSelectedSensorPosition()) - level)  <= 1000 
        && Math.abs(Math.abs(frontSlaveMotor.getSelectedSensorPosition()) - level) <= 1000
        && Math.abs(Math.abs(backClimbMotor.getSelectedSensorPosition()) - level) <= 1200){

      if (level >= _level){
        level = _level;
      } else {
        level += step;
      }

    } else {
      frontMasterMotor.set(ControlMode.Position, level);
      frontSlaveMotor.set(ControlMode.Position, level);
      backClimbMotor.set(ControlMode.Position, level);

      SmartDashboard.putNumber("clim num", level);
      SmartDashboard.putNumber("frontMaster pos", frontMasterMotor.getSelectedSensorPosition());
      SmartDashboard.putNumber("frontSlave pos", frontSlaveMotor.getSelectedSensorPosition());
      SmartDashboard.putNumber("back pos", backClimbMotor.getSelectedSensorPosition());

    }
    frontLevel = _level;
    backLevel = _level;
    SmartDashboard.putNumber("front Level", frontLevel);
    SmartDashboard.putNumber("back Level", backLevel);
  }

  public void retrieveBack(){
    robotMap.TalonSRXInit(backClimbMotor, Constants.kClimbBackOutput);

    backClimbMotor.set(ControlMode.Position, 0);
    Robot.base.drive(-0.2, 0, 0);
  }

  public void retrieveFront() {
    robotMap.TalonSRXInit(frontMasterMotor, Constants.kClimbBackOutput);
    robotMap.TalonSRXInit(frontSlaveMotor, Constants.kClimbBackOutput);

    frontMasterMotor.set(ControlMode.Position, 0);
    frontSlaveMotor.set(ControlMode.Position, 0);
    Robot.base.drive(-0.2, 0, 0);
  }

  public void climbMotion(){
    climbMotionMotor.set(ControlMode.PercentOutput, -0.4);
  }

  public void motionStop(){
    climbMotionMotor.set(ControlMode.PercentOutput, 0);

  }

  public void climbStop(){
    frontMasterMotor.set(ControlMode.PercentOutput, 0);
    frontMasterMotor.set(ControlMode.PercentOutput, 0);
    frontMasterMotor.set(ControlMode.PercentOutput, 0);
  }
}
