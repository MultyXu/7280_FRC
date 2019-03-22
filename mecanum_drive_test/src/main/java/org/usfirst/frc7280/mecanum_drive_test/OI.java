// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package org.usfirst.frc7280.mecanum_drive_test;

import org.usfirst.frc7280.mecanum_drive_test.command_group.*;
import org.usfirst.frc7280.mecanum_drive_test.commands.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc7280.mecanum_drive_test.subsystems.*;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);

    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.

    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:

    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());

    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());

    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());



    public Joystick functionStick;
    public Joystick motionStick;
    
    public JoystickButton shoot;
    public JoystickButton grab;

    // elevator level button
    public JoystickButton liftZero;
    public JoystickButton liftFirstLevel;
    public JoystickButton liftSecondLevel;
    public JoystickButton liftThirdLevel;
    public JoystickButton liftFourthLevel;
    public JoystickButton liftFifthLevel;
    public JoystickButton elevatorDown;
    
    public JoystickButton solenoidActivate;

    public JoystickButton armLift;
    public JoystickButton armDown;

    //auto button
    public JoystickButton grabPlate;
    public JoystickButton putPlate;
    public JoystickButton grabBall;
    public JoystickButton putBall;

    
    public OI() {
    
        boolean setManual = Robot.judge.manualModeOn;
        SmartDashboard.putBoolean("set Manual", setManual);

        // config joystck port
        functionStick = new Joystick(1);
        motionStick = new Joystick(0);

        // Config elevator button
        liftZero = new JoystickButton(functionStick, 1);
        liftFirstLevel = new JoystickButton(functionStick, 2);
        liftSecondLevel = new JoystickButton(functionStick, 3);
        liftThirdLevel = new JoystickButton(functionStick, 4);
        liftFourthLevel = new JoystickButton(functionStick, 5);
        liftFifthLevel = new JoystickButton(functionStick, 6);

        // asociate elevaor button to function 
        liftZero.whenPressed(new Lift(Constants.kZeroLevel));
        liftFirstLevel.whenPressed(new Lift(Constants.kFirstLevel));
        liftSecondLevel.whenPressed(new Lift(Constants.kSecondLevel));
        liftThirdLevel.whenPressed(new Lift(Constants.kThirdLevel));
        liftFourthLevel.whenPressed(new Lift(Constants.kFourthLevel));
        liftFifthLevel.whenPressed(new Lift(Constants.kFifthLevel));


        // manual move arm
        armLift= new JoystickButton(functionStick, 7);
        armDown = new JoystickButton(functionStick, 8);
        armLift.whenPressed(new ArmLift());
        armDown.whenPressed(new ArmDown());

        // manual solenoid
        solenoidActivate = new JoystickButton(functionStick, 9);
        solenoidActivate.toggleWhenPressed(new SolenoidActivate());

        elevatorDown = new JoystickButton(functionStick, 10);
        elevatorDown.whenPressed(new ElevatorDown());



        // manually grab and shoot the ball, config button
        shoot = new JoystickButton(motionStick, 7);
        grab = new JoystickButton(motionStick, 8);

        shoot.whileHeld(new Take());
        grab.whileHeld(new Grab());

        // auto plate
        grabPlate = new JoystickButton(motionStick, 3);
        putPlate = new JoystickButton(motionStick, 4);

        grabPlate.whenPressed(new GrabPlate());
        putPlate.whenPressed(new PutPlate());

        // auto Ball
        grabBall = new JoystickButton(motionStick, 1);
        putBall = new JoystickButton(motionStick, 2);

        grabBall.whenPressed(new GrabBall());
        putBall.whenPressed(new PutBall());

        // virtual button 
        SmartDashboard.putData("Climb Second Level", new ClimbStage(Constants.kClimbSecondLevel));
        SmartDashboard.putData("Climb First Level", new ClimbStage(Constants.kClimbFirstLevel));
        SmartDashboard.putData("put back front climb", new ClimbFront());
        SmartDashboard.putData("pub back back climb", new ClimbBack());
        SmartDashboard.putData("move climb", new ClimbMotion());
        SmartDashboard.putData("put both back", new retreiveClimb());
    }
}