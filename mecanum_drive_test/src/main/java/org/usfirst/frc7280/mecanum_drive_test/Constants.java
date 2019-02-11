package org.usfirst.frc7280.mecanum_drive_test;

public class Constants {

    public static final int kTimeoutMs = 30;

    public static final int kPIDLoopIdx = 0;

    public static final int kSlotIdx = 0;

    public static boolean kSensorPhase = true;

    public static boolean kMotorInverted = true;
    
    // Elevator PID values 
    public static final double kElevatorP = 0.005;
    public static final double kElevatorI = 0.0;
    public static final double kElevatorD = 0.0;
    public static final double kElevatorF = 0.0;
    public static final double kElevatorPeakOutput = 0.7;

    // Elevator height values
    public static final double kFirstLevel = 8 * 4096;
    public static final double kSecondLevel = 16 * 4096;
    public static final double kThirdLevel = 24 * 4096;

    // Elevator current limit values
    public static final int kContinueCurrentLimit = 9;
    public static final int kPeakCurrentLimit = 15;
    public static final int kpeakCurrentDuration = 10;

    // Intaker motor Speed values
    public static final double takeSpeed = 0.5;
    public static final double shootSpeed = -0.5;


}