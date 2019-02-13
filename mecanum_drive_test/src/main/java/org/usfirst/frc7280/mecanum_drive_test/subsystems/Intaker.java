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


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import org.usfirst.frc7280.mecanum_drive_test.RobotMap;
import org.usfirst.frc7280.mecanum_drive_test.commands.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.Solenoid;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 *
 */
public class Intaker extends Subsystem {

    private TalonSRX intakerRightMotor = new TalonSRX(RobotMap.intakerLeftMotor);
    private TalonSRX intakerLeftMotor = new TalonSRX(RobotMap.intakerRightMotor);

    Solenoid intakerArmSolenoid = new Solenoid(RobotMap.intakerArmSolenoid);
    DoubleSolenoid intakerAngleSolenoid = new DoubleSolenoid(RobotMap.intakerAngleForwardSolenoid,RobotMap.intakerAngleReverseSolenoid);
    Solenoid intakerLiftSolenoid = new Solenoid(RobotMap.intakerLiftSolenoid);
    Solenoid intakerPushSolenoid = new Solenoid(3);

    public Intaker() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        
    }

    @Override
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


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

    public void take(double _motorSpeed){
        intakerRightMotor.set(ControlMode.PercentOutput, _motorSpeed);
        intakerLeftMotor.set(ControlMode.PercentOutput, _motorSpeed/2);
    }

    public void intakerArmOpen(){
        intakerArmSolenoid.set(true);
    }

    public void intakerArmClose(){
        intakerArmSolenoid.set(false);
    }

    public void intakerLiftUp(){
        intakerLiftSolenoid.set(true);        
    }

    public void intakerLiftDown(){
        intakerLiftSolenoid.set(false);
    }

    public void intakerAngleRaise(){
        intakerAngleSolenoid.set(DoubleSolenoid.Value.kReverse);
    }

    public void intakerAngleLower(){
        intakerAngleSolenoid.set(DoubleSolenoid.Value.kForward);
    }

    public void intakerPushClose(){
        intakerPushSolenoid.set(false);
    }
}

