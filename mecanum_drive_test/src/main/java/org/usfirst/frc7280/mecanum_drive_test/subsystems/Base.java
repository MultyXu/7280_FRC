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

    public boolean visionTurnOK = false;
    public boolean visionDriveOK = false;
    public boolean tableOn = false;

    public Base() {

        robotMap.TalonSRXInit(leftFrontMotor, Constants.kBasePeakOutput);
        robotMap.TalonSRXInit(leftRearMotor, Constants.kBasePeakOutput);
        robotMap.TalonSRXInit(rightFrontMotor, Constants.kBasePeakOutput);
        robotMap.TalonSRXInit(rightRearMotor, Constants.kBasePeakOutput);

        configVelocityPID();

        // modified
        leftFrontMotor.setInverted(true);
        leftRearMotor.setInverted(true);
        //leftFrontMotor.setSensorPhase(false);
    }

    @Override
    public void initDefaultCommand() {

        setDefaultCommand(new Drive());
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop

    }

    public void drive(double yValue, double xValue, double zValue){
        motorMode(NeutralMode.Coast);
        configVelocityPID();

        if (Robot.elevator.targetPosition > -10000){
            frontLeftSpeed = (yValue - xValue - zValue / 1.3) * 1200 * 2;
            rearLeftSpeed = (yValue + xValue - zValue / 1.3) * 1200 * 2;
            frontRightSpeed = (yValue + xValue + zValue / 1.3) * 1200 * 2;
            rearRighttSpeed = (yValue - xValue + zValue / 1.3) * 1200 * 2;
        } else {
            frontLeftSpeed = (yValue - xValue - zValue / 1.3) * 750 * 2;
            rearLeftSpeed = (yValue + xValue - zValue / 1.3) * 750 * 2;
            frontRightSpeed = (yValue + xValue + zValue / 1.3) * 750 * 2;
            rearRighttSpeed = (yValue - xValue + zValue / 1.3) * 750 * 2;
        }
        
        speedDrive();

        SmartDashboard.putNumber("LF position", leftFrontMotor.getSelectedSensorPosition());
        SmartDashboard.putNumber("LR position", leftRearMotor.getSelectedSensorPosition());
        SmartDashboard.putNumber("RF position", rightFrontMotor.getSelectedSensorPosition());
        SmartDashboard.putNumber("RR position", rightRearMotor.getSelectedSensorPosition());
        
        SmartDashboard.putNumber("set base speed", frontLeftSpeed);
        SmartDashboard.putNumber("base speed", leftFrontMotor.getSelectedSensorVelocity());
        SmartDashboard.putNumber("output", leftFrontMotor.getMotorOutputPercent());
    }

    public double visionTurn() {
        tableOn = true;
        double zSpeed = 0;
        switch (Robot.netWorkTable.upTape){
            case 1: // the target is on the left
                zSpeed = -0.5;
                visionTurnOK = false;
                break;

            case 2: // the target is on the right
                zSpeed = 0.5;
                visionTurnOK = false;
                break;

            case 3: // the target is on the centre
                zSpeed = 0;
                visionTurnOK = true;
                break;
        }
        SmartDashboard.putBoolean("visionTurnOK", visionTurnOK);
        SmartDashboard.putNumber("visionTurn", zSpeed);
        SmartDashboard.putNumber("visionTurnCase", Robot.netWorkTable.upTape);

        return zSpeed;
    }

    public double[] visionDrive() {
        tableOn = true;
        double[] yxSpeed = {0,0};
        switch (Robot.netWorkTable.downTape) {
            case 1 | 4: // the target is on the left
                yxSpeed[0] = 0;
                yxSpeed[1] = 0.5;
                visionDriveOK = false;
                break;

            case 2 | 5: // the target is on the right
                yxSpeed[0] = 0;
                yxSpeed[1] = -0.5;
                visionDriveOK = false;
                break;

            case 3: // the target is on the centre
                yxSpeed[0] = 0; // modified for further version
                yxSpeed[1] = 0;
                visionDriveOK = true;
                break;
        }
        SmartDashboard.putBoolean("visionDriveOK", visionDriveOK);
        SmartDashboard.putNumber("visionTurn", yxSpeed[1]);

        return yxSpeed;
    }

    public void speed(double yValue, double xValue, double zValue){
        tableOn = false;
        double kfrontLeftSpeed = (yValue - xValue - zValue / 1.3) * 1500;
        double krearLeftSpeed = (yValue + xValue - zValue / 1.3) * 1500;
        double kfrontRightSpeed = (yValue + xValue + zValue / 1.3) * 1500;
        double krearRighttSpeed = (yValue - xValue + zValue / 1.3) * 1500;

        leftFrontMotor.set(ControlMode.Velocity, kfrontLeftSpeed);
        leftRearMotor.set(ControlMode.Velocity, krearLeftSpeed);
        rightFrontMotor.set(ControlMode.Velocity, kfrontRightSpeed);
        rightRearMotor.set(ControlMode.Velocity, krearRighttSpeed);
    }

    public void speedDrive(){
        leftFrontMotor.set(ControlMode.Velocity, frontLeftSpeed);
        leftRearMotor.set(ControlMode.Velocity, rearLeftSpeed);
        rightFrontMotor.set(ControlMode.Velocity, frontRightSpeed);
        rightRearMotor.set(ControlMode.Velocity, rearRighttSpeed);
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

        SmartDashboard.putNumber("LF position", leftFrontMotor.getSelectedSensorPosition());
        SmartDashboard.putNumber("LR position", leftRearMotor.getSelectedSensorPosition());
        SmartDashboard.putNumber("RF position", rightFrontMotor.getSelectedSensorPosition());
        SmartDashboard.putNumber("RR position", rightRearMotor.getSelectedSensorPosition());
    }

    public void percentageDrive(double _leftSpeed, double _rightSpeed){
        leftFrontMotor.set(ControlMode.PercentOutput, _leftSpeed);
        leftRearMotor.set(ControlMode.PercentOutput, _leftSpeed);
        rightFrontMotor.set(ControlMode.PercentOutput, _rightSpeed);
        rightRearMotor.set(ControlMode.PercentOutput, _rightSpeed);

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
        rightRearMotor.configClosedLoopPeakOutput(0, 1);
    }

    public void configPositionPID(){
        robotMap.setMotorPID(leftFrontMotor, 0, 0.05, 0, 0.5);
        robotMap.setMotorPID(leftRearMotor, 0, 0.02, 0, 0.2);
        robotMap.setMotorPID(rightFrontMotor, 0, 0.1, 0, 1);
        robotMap.setMotorPID(rightRearMotor, 0, 0.1, 0, 1);

        leftFrontMotor.configClosedLoopPeakOutput(0, 0.5);
        leftRearMotor.configClosedLoopPeakOutput(0, 0.5);
        rightFrontMotor.configClosedLoopPeakOutput(0, 0.5);
        rightRearMotor.configClosedLoopPeakOutput(0, 0.5);
    }

}

