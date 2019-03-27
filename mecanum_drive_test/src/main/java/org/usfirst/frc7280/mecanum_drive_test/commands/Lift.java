/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc7280.mecanum_drive_test.commands;

import org.usfirst.frc7280.mecanum_drive_test.Constants;
import org.usfirst.frc7280.mecanum_drive_test.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Lift extends Command {

  int targetPosition;
  boolean finished = false;

  public Lift(int _position) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.elevator);
    requires(Robot.base); // moified
    requires(Robot.arm);
    requires(Robot.intaker);

    // determine target position
    targetPosition = _position;

  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    finished = false;

    Robot.base.drive(Robot.oi.motionStick.getY(), Robot.oi.motionStick.getX(), Robot.oi.motionStick.getZ());

    if (targetPosition == Constants.kFirstLevel) {
      Robot.arm.down();
      Robot.intaker.cylinderUp(); // added
      Robot.elevator.liftToPosition(targetPosition);

    }

    if (Robot.judge.manualModeOn) {

      if (targetPosition != Constants.kFirstLevel){
        Robot.arm.lift();
      }

      if (targetPosition == Constants.kThirdLevel ||
      targetPosition == Constants.kFifthLevel){
        Robot.intaker.cylinderDown();
      }
      
      Robot.elevator.liftToPosition(targetPosition);
      // Robot.base.drive(Robot.oi.motionStick.getY(), Robot.oi.motionStick.getX(), Robot.oi.motionStick.getZ());
    } else {
      if (Robot.base.visionDriveOK && Robot.base.visionTurnOK) {

        Robot.elevator.liftToPosition(targetPosition);

        // modified 
        if (targetPosition == Constants.kThirdLevel ||
        targetPosition == Constants.kFifthLevel) {
          Robot.intaker.cylinderDown();
          // Robot.base.drive(0, 0, 0); // added
        }

        finished = true;
      } else {

        if (targetPosition != Constants.kFirstLevel){
          Robot.arm.lift();
        }

        Robot.base.speed(Robot.base.visionDrive()[0], Robot.base.visionDrive()[1], Robot.base.visionTurn());
        Robot.base.speedDrive();

      }
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return finished;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.elevator.liftToPosition(targetPosition);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
