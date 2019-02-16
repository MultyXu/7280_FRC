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
import org.usfirst.frc7280.mecanum_drive_test.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class Arm extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private TalonSRX armMotor = new TalonSRX(RobotMap.armMotor);
  private RobotMap robotMap = new RobotMap();
  
  public Arm() {

    armMotor.configFactoryDefault();
    robotMap.TalonSRXInit(armMotor, Constants.kArmPeakOutput); 

    armMotor.setNeutralMode(NeutralMode.Brake);
    robotMap.setMotorPID(armMotor,
    Constants.kArmF, 
    Constants.kArmP, 
    Constants.kArmI, 
    Constants.kArmD);

    armMotor.enableCurrentLimit(true);
    armMotor.configContinuousCurrentLimit(Constants.kContinueCurrentLimit, Constants.kTimeoutMs);
    armMotor.configPeakCurrentLimit(Constants.kPeakCurrentLimit, Constants.kTimeoutMs);
    armMotor.configPeakCurrentDuration(Constants.kpeakCurrentDuration, Constants.kTimeoutMs);

  }
  @Override
  public void initDefaultCommand() {

  }

  public void lift(){
    armMotor.set(ControlMode.Position, Constants.kLift);

  }

  public void down(){
    armMotor.set(ControlMode.Position, Constants.kDown);

  }

}
