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

import org.usfirst.frc7280.mecanum_drive_test.Constants;
import org.usfirst.frc7280.mecanum_drive_test.Robot;
import org.usfirst.frc7280.mecanum_drive_test.RobotMap;
import org.usfirst.frc7280.mecanum_drive_test.commands.ManualArm;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Add your docs here.
 */
public class Arm extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private TalonSRX armMotor = new TalonSRX(RobotMap.armMotor);
  private RobotMap robotMap = new RobotMap();

  public int armPosition;
  public double armSpeed;
  
  public Arm() {

    armMotor.configFactoryDefault();
    robotMap.TalonSRXInit(armMotor, Constants.kArmPeakOutput);
    armMotor.setSensorPhase(false);

    armMotor.setNeutralMode(NeutralMode.Brake);
    robotMap.setMotorPID(armMotor,
    Constants.kArmF, 
    Constants.kArmP, 
    Constants.kArmI, 
    Constants.kArmD);

    armMotor.enableCurrentLimit(true);
    armMotor.configContinuousCurrentLimit(9, Constants.kTimeoutMs);
    armMotor.configPeakCurrentLimit(Constants.kPeakCurrentLimit, Constants.kTimeoutMs);
    armMotor.configPeakCurrentDuration(Constants.kpeakCurrentDuration, Constants.kTimeoutMs);

  }
  
  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ManualArm());
    //modify needed check whether they will interrupt themselves
  }

  public void lift(){
    armMotor.configClosedLoopPeakOutput(Constants.kSlotIdx, 1, Constants.kTimeoutMs);
    armMotor.set(ControlMode.Position, Constants.kLift);
    armPosition = armMotor.getSelectedSensorPosition();
    armSpeed = armMotor.getSelectedSensorVelocity();
    
    SmartDashboard.putNumber("arm position", armPosition);
  }

  public void down(){
    armMotor.configClosedLoopPeakOutput(Constants.kSlotIdx, 0.2, Constants.kTimeoutMs);
    armMotor.set(ControlMode.Position, Constants.kDown);
    armPosition = armMotor.getSelectedSensorPosition();
    armSpeed = armMotor.getSelectedSensorVelocity();

    SmartDashboard.putNumber("arm position", armPosition);

  }

  // manual control method for arms, control it with right stick
  public void ManualRun(double _outPut){
    armMotor.set(ControlMode.PercentOutput, _outPut);
  }
  public void stop(){
    armMotor.set(ControlMode.PercentOutput,0);

  }

}
