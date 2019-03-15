/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc7280.mecanum_drive_test.commands;

import org.usfirst.frc7280.mecanum_drive_test.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class MoveY extends Command {
  int targetDistance;
  public MoveY(int _distance) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.base);
    targetDistance = _distance;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.base.zeroSensorPostition();
    Robot.base.configPositionPID();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.base.moveY(targetDistance);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Math.abs(Robot.base.getCurrentDistance()-targetDistance) < 500;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
    Robot.base.configVelocityPID();
  }
}
