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
import org.usfirst.frc7280.mecanum_drive_test.Robot;
import org.usfirst.frc7280.mecanum_drive_test.RobotMap;
import org.usfirst.frc7280.mecanum_drive_test.commands.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.ControlMode;
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

    double frontLeftSpeed;
    double rearLeftSpeed;
    double frontRightSpeed;
    double rearRighttSpeed;

    int targetDistanceX;
    int targetDistanceY;
    int targetDistanceZ;


    public Base() {

        robotMap.TalonSRXInit(leftFrontMotor, Constants.kBasePeakOutput);
        robotMap.TalonSRXInit(leftRearMotor, Constants.kBasePeakOutput);
        robotMap.TalonSRXInit(rightFrontMotor, Constants.kBasePeakOutput);
        robotMap.TalonSRXInit(rightRearMotor, Constants.kBasePeakOutput);

        configVelocityPID();

        leftFrontMotor.setInverted(false);
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
        configVelocityPID();

        if (Robot.elevator.targetPosition > -10000){
            frontLeftSpeed = (yValue - xValue - zValue) * 1500 * 2;
            rearLeftSpeed = (yValue + xValue - zValue) * 1500 * 2;
            frontRightSpeed = (yValue + xValue + zValue) * 1500 * 2;
            rearRighttSpeed = (yValue - xValue + zValue) * 1500 * 2;
        } else {
            frontLeftSpeed = (yValue - xValue - zValue) * 750;
            rearLeftSpeed = (yValue + xValue - zValue) * 750;
            frontRightSpeed = (yValue + xValue + zValue) * 750;
            rearRighttSpeed = (yValue - xValue + zValue) * 750;
        }
        // setLimit(frontLeftSpeed);
        // setLimit(rearLeftSpeed);
        // setLimit(frontRightSpeed);
        // setLimit(rearRighttSpeed);
        
        leftFrontMotor.set(ControlMode.Velocity, frontLeftSpeed);
        leftRearMotor.set(ControlMode.Velocity, rearLeftSpeed);
        rightFrontMotor.set(ControlMode.Velocity, frontRightSpeed);
        rightRearMotor.set(ControlMode.Velocity, rearRighttSpeed);

        SmartDashboard.putNumber("base position", leftFrontMotor.getSelectedSensorPosition());
        SmartDashboard.putNumber("set base speed", frontLeftSpeed);
        SmartDashboard.putNumber("base speed", leftFrontMotor.getSelectedSensorVelocity());
        SmartDashboard.putNumber("output", leftFrontMotor.getMotorOutputPercent());

        // double frontLeftSpeed = (yValue - xValue - zValue)/2 ;
        // double rearLeftSpeed = (yValue + xValue - zValue)/2 ;
        // double frontRightSpeed = (yValue + xValue + zValue)/2 ;
        // double rearRighttSpeed = (yValue - xValue + zValue)/2 ;



        // leftFrontMotor.set(ControlMode.PercentageOutput, frontLeftSpeed);
        // leftRearMotor.set(ControlMode.PercentageOutput, rearLeftSpeed);
        // rightFrontMotor.set(ControlMode.PercentageOutput, frontRightSpeed);
        // rightRearMotor.set(ControlMode.PercentageOutput, rearRighttSpeed);
    }

    public void moveY(int _distance){
        targetDistanceY = _distance;
        motorMode(NeutralMode.Brake);

        leftFrontMotor.set(ControlMode.Position, _distance);
        leftRearMotor.set(ControlMode.Position, _distance);
        rightFrontMotor.set(ControlMode.Position, _distance);
        rightRearMotor.set(ControlMode.Position, _distance);
    }


    public void moveX(int _distance){
        targetDistanceX = _distance;
        motorMode(NeutralMode.Brake);

        leftFrontMotor.set(ControlMode.Position, _distance);
        leftRearMotor.set(ControlMode.Position, -_distance);
        rightFrontMotor.set(ControlMode.Position, -_distance);
        rightRearMotor.set(ControlMode.Position, _distance);
    }


    // turn the robot, 363unit/degree
    public void turnZ(int _distance){
        targetDistanceZ = _distance;
        motorMode(NeutralMode.Brake);
        leftFrontMotor.set(ControlMode.Position, -_distance);
        leftRearMotor.set(ControlMode.Position, -_distance);
        rightFrontMotor.set(ControlMode.Position, _distance);
        rightRearMotor.set(ControlMode.Position, _distance);
    }

    public int getCurrentDistance() {
        return rightFrontMotor.getSelectedSensorPosition();
    }

    //function that set the encoder position to zero
    public void zeroSensorPostition(){
        leftFrontMotor.setSelectedSensorPosition(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
        leftRearMotor.setSelectedSensorPosition(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
        rightFrontMotor.setSelectedSensorPosition(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
        rightRearMotor.setSelectedSensorPosition(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
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

    public void configVelocityPID(){
        robotMap.setMotorPID(leftFrontMotor, 0.197, 0, 0, 0);
        robotMap.setMotorPID(leftRearMotor, 0.197, 0, 0, 0);
        robotMap.setMotorPID(rightFrontMotor, 0.197, 0, 0, 0);
        robotMap.setMotorPID(rightRearMotor, 0.197, 0, 0, 0);

        leftFrontMotor.configClosedLoopPeakOutput(0, 1);
        leftRearMotor.configClosedLoopPeakOutput(0, 1);
        rightFrontMotor.configClosedLoopPeakOutput(0, 1);
        rightFrontMotor.configClosedLoopPeakOutput(0, 1);
    }

    public void configPositionPID(){
        robotMap.setMotorPID(leftFrontMotor, 0, 0.025, 0, 0);
        robotMap.setMotorPID(leftRearMotor, 0, 0.025, 0, 0);
        robotMap.setMotorPID(rightFrontMotor, 0, 0.025, 0, 0);
        robotMap.setMotorPID(rightRearMotor, 0, 0.025, 0, 0);

        leftFrontMotor.configClosedLoopPeakOutput(0, 0.5);
        leftRearMotor.configClosedLoopPeakOutput(0, 0.5);
        rightFrontMotor.configClosedLoopPeakOutput(0, 0.5);
        rightFrontMotor.configClosedLoopPeakOutput(0, 0.5);


    }

}

