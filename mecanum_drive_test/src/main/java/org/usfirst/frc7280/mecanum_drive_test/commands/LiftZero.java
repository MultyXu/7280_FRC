/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc7280.mecanum_drive_test.commands;

import org.usfirst.frc7280.mecanum_drive_test.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class LiftZero extends Command {

  int targetPosition;
  boolean finished = false;


  public LiftZero(int _position) {
    requires(Robot.elevator);
    requires(Robot.arm);

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
    Robot.arm.lift();
    Robot.elevator.liftToPosition(targetPosition);

    Robot.intaker.cylinderDown();

    // modify needed after installing 

    if (Robot.oi.functionStick.getPOV() == 90) {
      if (Robot.base.visionDriveOK && Robot.base.visionTurnOK) {

        Robot.elevator.liftToPosition(targetPosition);

        finished = true;
      } else {

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
    // Robot.arm.lift();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
