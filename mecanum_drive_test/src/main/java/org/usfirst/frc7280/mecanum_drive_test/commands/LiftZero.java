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
  ArmChange x = new ArmChange();


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
    Robot.elevator.liftToPosition(targetPosition);
    x.change = false;
    Robot.arm.lift();
    // modify needed after installing 
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return (Math.abs(Robot.elevator.elevatorPosition - targetPosition) < 1000
        && Robot.elevator.elevatorMaster.getSelectedSensorVelocity() == 0);
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
