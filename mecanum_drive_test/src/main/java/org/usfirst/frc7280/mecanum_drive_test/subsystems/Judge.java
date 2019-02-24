/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc7280.mecanum_drive_test.subsystems;

import org.usfirst.frc7280.mecanum_drive_test.Robot;
import org.usfirst.frc7280.mecanum_drive_test.commands.Judging;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class Judge extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public boolean manualModeOn = false;

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new Judging());
  }

  public void setManualMode(){
    if (Robot.oi.functionStick.getPOV() == 0){
      manualModeOn = true;
    } else if (Robot.oi.functionStick.getPOV() == 180){
      manualModeOn = false;
    }
  }
}
