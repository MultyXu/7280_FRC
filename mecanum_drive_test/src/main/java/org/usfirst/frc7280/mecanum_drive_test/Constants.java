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
    public static final double kElevatorDownF = 0.0;
    public static final double kElevatorDownPeakOutput = 0.25;

    public static final double kElevatorHigherP = 0.5;
    public static final double kElevatorHigherI = 0.0;
    public static final double kElevatorHigherD = 0.03;
    public static final double kElevatorHigherF = 0.0;
    public static final double kElevatorHigherPeakOutput = 1;






    // Elevator height values
    public static final int kZeroLevel = 0;
    public static final int kFirstLevel = -13000;
    public static final int kSecondLevel = -30000;
    public static final int kThirdLevel = -60000;
    public static final int kFourthLevel = -90000;
    public static final int kFifthLevel = -125000;

    // Elevator current limit values
    public static final int kContinueCurrentLimit = 12;
    public static final int kPeakCurrentLimit = 15;
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