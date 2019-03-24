package org.usfirst.frc7280.mecanum_drive_test;

public class Constants {

    public static final int kTimeoutMs = 30;

    public static final int kPIDLoopIdx = 0;

    public static final int kSlotIdx = 0;

    public static boolean kSensorPhase = true;

    public static boolean kMotorInverted = false;

    // Base PID
    public static final double kBasePeakOutput = 0.8;

    // Base turning values, 362unit/degree, turn +- 45/90 degree left is positive and right is negative
    public static final int leftTurn45 = -16331;
    public static final int rightTurn45 = 16331;
    public static final int leftTurn90 = -32662;
    public static final int rightTurn90 = 32662;


    // Base speed 
    
    // Elevator PID values 
    public static final double kElevatorP = 0.04;
    public static final double kElevatorI = 0.0;
    public static final double kElevatorD = 0.0;
    public static final double kElevatorF = 0.0;
    public static final double kElevatorPeakOutput = 0.4;
    
    // elevator down pid
    public static final double kElevatorDownP = 0.04;
    public static final double kElevatorDownI = 0.0;
    public static final double kElevatorDownD = 0.0;
    public static final double kElevatorDownF = 0.0;
    public static final double kElevatorDownPeakOutput = 0.25;

    // elevator go to highest point pid
    public static final double kElevatorHigherP = 0.5;
    public static final double kElevatorHigherI = 0.0;
    public static final double kElevatorHigherD = 0.03;
    public static final double kElevatorHigherF = 0.0;
    public static final double kElevatorHigherPeakOutput = 0.5;

    // Elevator height values
    public static final int kZeroLevel = 0;
    public static final int kFirstLevel = -13000;
    public static final int kSecondLevel = -30000;
    public static final int kThirdLevel = -60000;
    public static final int kFourthLevel = -90000;
    public static final int kFifthLevel = -125000;

    // Elevator current limit values
    public static final int kContinueCurrentLimit = 18;
    public static final int kPeakCurrentLimit = 21;
    public static final int kpeakCurrentDuration = 10;


    // Intaker motor Speed values
    public static final double takeSpeed = 0.95;
    public static final double shootSpeed = -0.95;
    

    // Arm PID values
    public static final double kArmP = 0.09;
    public static final double kArmI = 0;
    public static final double kArmD = 0;
    public static final double kArmF = 0.0;
    public static final double kArmPeakOutput = 0.2;
    
    // Arm lift values
    public static final int kLift = -3000;
    public static final int kDown = -33000;


    // climb PID values and height
    public static final double kClimbPeakOutput = 0.6;
    public static final double kClimbBackOutput = 0.9;
    public static final int kClimbSecondLevel = 166000;
    public static final int kClimbFirstLevel = 100000;
    public static final int kRetrieveLevel = 0;
    public static final int climbSpeed = 2000;
}