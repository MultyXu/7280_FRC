package org.usfirst.frc7280.mecanum_drive_test;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class RobotMap {

    // base motor
    public static final int leftFrontMotor = 1;
    public static final int leftRearMotor = 2;
    public static final int rightFrontMotor = 3;
    public static final int rightRearMotor = 4;

    // elevator motor
    public static final int elevatorMasterMotor = 5;
    public static final int elevatorSlaveMotor = 6;

    // intaker motor & solenoid
    public static final int intakerMotor = 7;
    public static final int intakerSolenoid = 1;


    // arm motor
    public static final int armMotor = 8;

    public RobotMap(){

    }

    public void TalonSRXInit(TalonSRX _talon, double _peakOutput) {
    
		// set up TalonSRX and closed loop
        // select an encoder and set it
        _talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
    
        _talon.configFactoryDefault();
        // make sure the sensor gieves the postive value whent the output is positive. 
		_talon.setSensorPhase(true);

		_talon.configNominalOutputForward(0, Constants.kTimeoutMs);
		_talon.configNominalOutputReverse(0, Constants.kTimeoutMs);
		_talon.configPeakOutputForward(1, Constants.kTimeoutMs);
		_talon.configPeakOutputReverse(-1, Constants.kTimeoutMs);

        _talon.setSelectedSensorPosition(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
    
        _talon.configAllowableClosedloopError(1, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
    
        _talon.configClosedLoopPeakOutput(Constants.kSlotIdx, _peakOutput, Constants.kTimeoutMs);
  }

  public void setMotorPID(TalonSRX _talon, double kF, double kP, double kI, double kD){
    
    /* Config Position Closed Loop gains in slot0, tsypically kF stays zero. */
		_talon.config_kF(Constants.kSlotIdx, kF, Constants.kTimeoutMs);
		_talon.config_kP(Constants.kSlotIdx, kP, Constants.kTimeoutMs);
		_talon.config_kI(Constants.kSlotIdx, kI, Constants.kTimeoutMs);
        _talon.config_kD(Constants.kSlotIdx, kD, Constants.kTimeoutMs);
  }

}

