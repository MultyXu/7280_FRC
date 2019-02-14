// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc7280.mecanum_drive_test.subsystems;


import org.usfirst.frc7280.mecanum_drive_test.Constants;
import org.usfirst.frc7280.mecanum_drive_test.RobotMap;
import org.usfirst.frc7280.mecanum_drive_test.commands.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;



/**
 *
 */
public class Base extends Subsystem {

    private TalonSRX leftFrontMotor = new TalonSRX(RobotMap.leftFrontMotor);
    private TalonSRX leftRearMotor = new TalonSRX(RobotMap.leftRearMotor);
    private TalonSRX rightFrontMotor = new TalonSRX(RobotMap.rightFrontMotor);
    private TalonSRX rightRearMotor = new TalonSRX(RobotMap.rightRearMotor);

    RobotMap robotMap = new RobotMap();

    public Base() {

        robotMap.TalonSRXInit(leftFrontMotor);
        robotMap.TalonSRXInit(leftRearMotor);
        robotMap.TalonSRXInit(rightFrontMotor);
        robotMap.TalonSRXInit(rightRearMotor);

        robotMap.setMotorPID(leftFrontMotor, 0, 0, 0, 0);
        robotMap.setMotorPID(leftRearMotor, 0, 0, 0, 0);
        robotMap.setMotorPID(rightFrontMotor, 0, 0, 0, 0);
        robotMap.setMotorPID(rightRearMotor, 0, 0, 0, 0);

        leftFrontMotor.setInverted(true);
        leftRearMotor.setInverted(true);


    }

    @Override
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        setDefaultCommand(new Drive());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop

    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void drive(double yValue, double xValue, double zValue){
        motorMode(NeutralMode.Coast);

        double frontLeftSpeed = (yValue + xValue - zValue)/4 * 500 * 4096 /600;
        double rearLeftSpeed = (yValue - xValue - zValue)/4 * 500 * 4096 /600;
        double frontRightSpeed = (yValue - xValue + zValue)/4 * 500 * 4096 /600;
        double rearRighttSpeed = (yValue + xValue + zValue)/4 * 500 * 4096 /600;
        
        leftFrontMotor.set(ControlMode.Velocity, frontLeftSpeed);
        leftRearMotor.set(ControlMode.Velocity, rearLeftSpeed);
        rightFrontMotor.set(ControlMode.Velocity, frontRightSpeed);
        rightRearMotor.set(ControlMode.Velocity, rearRighttSpeed);
    }

    public void stop(){
        motorMode(NeutralMode.Brake);

        drive(0,0,0);
    }

    private void motorMode(NeutralMode mode) {
        leftFrontMotor.setNeutralMode(mode);
        leftRearMotor.setNeutralMode(mode);
        rightFrontMotor.setNeutralMode(mode);
        rightRearMotor.setNeutralMode(mode);
    }

}

