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
  public int elevatorPosition;

  public Elevator(){
    elevatorMaster.setNeutralMode(NeutralMode.Brake);
    elevatorSlave.setNeutralMode(NeutralMode.Brake);
    
    TalonSRXInit(elevatorMaster); 
    elevatorSlave.follow(elevatorMaster); 

    // set whether you need to invert the motor to get right value
    elevatorMaster.setInverted(Constants.kMotorInverted);
    elevatorSlave.setInverted(Constants.kMotorInverted);

    elevatorMaster.configClosedLoopPeakOutput(Constants.kSlotIdx, Constants.kElevatorPeakOutput, Constants.kTimeoutMs);

    setMotorPID(elevatorMaster);

    elevatorMaster.setSelectedSensorPosition(0, Constants.kSlotIdx, Constants.kTimeoutMs);

    // current limit 
    elevatorMaster.enableCurrentLimit(true);
    elevatorMaster.configFactoryDefault();
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
  }

  public void stop(){
    elevatorMaster.set(ControlMode.PercentOutput, 0);
  }


  private void TalonSRXInit(TalonSRX _talon) {

		// set up TalonSRX and closed loop
    // select an encoder and set it
    _talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
    
    // make sure the sensor gieves the postive value whent the output is positive. 
		_talon.setSensorPhase(true);

		_talon.configNominalOutputForward(0, Constants.kTimeoutMs);
		_talon.configNominalOutputReverse(0, Constants.kTimeoutMs);
		_talon.configPeakOutputForward(1, Constants.kTimeoutMs);
		_talon.configPeakOutputReverse(-1, Constants.kTimeoutMs);

		_talon.setSelectedSensorPosition(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);

  }
  
  private void setMotorPID(TalonSRX _talon){
    
    /* Config Position Closed Loop gains in slot0, tsypically kF stays zero. */
		_talon.config_kF(Constants.kPIDLoopIdx, Constants.kElevatorF, Constants.kTimeoutMs);
		_talon.config_kP(Constants.kPIDLoopIdx, Constants.kElevatorP, Constants.kTimeoutMs);
		_talon.config_kI(Constants.kPIDLoopIdx, Constants.kElevatorI, Constants.kTimeoutMs);
		_talon.config_kD(Constants.kPIDLoopIdx, Constants.kElevatorD, Constants.kTimeoutMs);

  }

}
