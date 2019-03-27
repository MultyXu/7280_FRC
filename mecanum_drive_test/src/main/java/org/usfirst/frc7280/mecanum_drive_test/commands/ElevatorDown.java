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

public class ElevatorDown extends Command {

  public ElevatorDown() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.elevator);
    // requires(Robot.intaker);
    // requires(Robot.base);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.elevator.elevatorPosition = Robot.elevator.elevatorMaster.getSelectedSensorPosition(Constants.kSlotIdx);
    Robot.base.zeroSensorPostition();
    Robot.base.configPositionPID();

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    // Robot.intaker.cylinderUp();
    Robot.elevator.elevatorDown();
    // Robot.base.moveY(-5000);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    /*
    from elevator get the elevator position and target postition and 
    evaluate their differece.
    if the difference is less than 300 and the motor speed gets form
    elevator class is equal to 0, we end the command. 
    */
    return (Math.abs(Robot.elevator.elevatorPosition - Robot.elevator.targetPosition) < 300 && Robot.elevator.elevatorMaster.getSelectedSensorVelocity() == 0);
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
  }
}
