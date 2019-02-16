/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc7280.mecanum_drive_test.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc7280.mecanum_drive_test.commands.*;
import org.usfirst.frc7280.mecanum_drive_test.Constants;
import org.usfirst.frc7280.mecanum_drive_test.RobotMap;

/**
 * Add your docs here.
 */
public class Elevator extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private TalonSRX elevatorMaster = new TalonSRX(RobotMap.elevatorMasterMotor);
  private TalonSRX elevatorSlave = new TalonSRX(RobotMap.elevatorSlaveMotor);

  RobotMap robotMap = new RobotMap();
  public int elevatorPosition;

  

  public Elevator(){
    
    robotMap.TalonSRXInit(elevatorMaster, Constants.kElevatorPeakOutput); 
    
    elevatorMaster.setNeutralMode(NeutralMode.Brake);
    elevatorSlave.setNeutralMode(NeutralMode.Brake);
    
    elevatorSlave.follow(elevatorMaster); 

    // set whether you need to invert the motor to get right value
    elevatorMaster.setInverted(Constants.kMotorInverted);
    elevatorSlave.setInverted(Constants.kMotorInverted);

    robotMap.setMotorPID(
    elevatorMaster, 
    Constants.kElevatorF, 
    Constants.kElevatorP, 
    Constants.kElevatorI, 
    Constants.kElevatorD);

    // elevatorMaster.setSelectedSensorPosition(0, Constants.kSlotIdx, Constants.kTimeoutMs);

    // current limit 
    elevatorMaster.enableCurrentLimit(true);
    elevatorMaster.configContinuousCurrentLimit(Constants.kContinueCurrentLimit, Constants.kTimeoutMs);
    elevatorMaster.configPeakCurrentLimit(Constants.kPeakCurrentLimit, Constants.kTimeoutMs);
    elevatorMaster.configPeakCurrentDuration(Constants.kpeakCurrentDuration, Constants.kTimeoutMs);

  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void liftToPosition(double _position){

 
    elevatorMaster.set(ControlMode.Position, _position);
    elevatorPosition = elevatorMaster.getSelectedSensorPosition(Constants.kSlotIdx);

    SmartDashboard.putNumber("current position", elevatorMaster.getSelectedSensorPosition(Constants.kSlotIdx));
    SmartDashboard.putNumber("Target position", _position);
    SmartDashboard.putNumber("output", elevatorMaster.getMotorOutputPercent());
  }

  public void stop(){
    elevatorMaster.set(ControlMode.PercentOutput, 0);
  }


  // use for testing
  public void testRun(double _speed){
    elevatorMaster.set(ControlMode.PercentOutput, _speed/3);
    elevatorSlave.follow(elevatorMaster);
  }

}
