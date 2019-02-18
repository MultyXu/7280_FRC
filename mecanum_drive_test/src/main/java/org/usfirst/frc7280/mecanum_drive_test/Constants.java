package org.usfirst.frc7280.mecanum_drive_test;

public class Constants {

    public static final int kTimeoutMs = 30;

    public static final int kPIDLoopIdx = 0;

    public static final int kSlotIdx = 0;

    public static boolean kSensorPhase = true;

    public static boolean kMotorInverted = false;

    // Base PID
    public static final double kBasePeakOutput = 0.8;


    // Base speed 
    
    // Elevator PID values 
    public static final double kElevatorP = 0.04;
    public static final double kElevatorI = 0.0;
    public static final double kElevatorD = 0.0;
    public static final double kElevatorF = 0.0;
    public static final double kElevatorPeakOutput = 0.8;
    
    public static final double kElevatorDownP = 0.04;
    public static final double kElevatorDownI = 0.0;
    public static final double kElevatorDownD = 0.0;
    public static final double kElevatorDownPeakOutput = 0.2;

    public static final double kElevatorHigherP = 0.06;
    public static final double kElevatorHigherI = 0.0;
    public static final double kElevatorHigherD = 0.0;
    public static final double kElevatorHigherF = 0.0;
    public static final double kElevatorHigherPeakOutput = 0.8;






    // Elevator height values
    public static final double kZeroLevel = 0;
    public static final double kFirstLevel = -13000;
    public static final double kSecondLevel = -60000;
    public static final double kThirdLevel = -90000;
    public static final double kFourthLevel = -120000;
    public static final double kFifthLevel = -125000;

    // Elevator current limit values
    public static final int kContinueCurrentLimit = 7;
    public static final int kPeakCurrentLimit = 13;
    public static final int kpeakCurrentDuration = 10;

    // Intaker motor Speed values
    public static final double takeSpeed = 0.95;
    public static final double shootSpeed = -0.95;
    
    // Arm PID values
    public static final double kArmP = 0.09;
    public static final double kArmI = 0;
    public static final double kArmD = 0;
    public static final double kArmF = 0.0;
    public static final double kArmPeakOutput = 1;
    
    // Arm lift values
    public static final int kLift = 33000;
    public static final int kDown = 0;



}