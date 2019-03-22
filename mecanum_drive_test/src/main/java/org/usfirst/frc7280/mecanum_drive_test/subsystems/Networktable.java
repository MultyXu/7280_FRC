/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc7280.mecanum_drive_test.subsystems;

import org.usfirst.frc7280.mecanum_drive_test.commands.GetTableData;

import edu.wpi.first.networktables.*;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class Networktable extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  NetworkTableInstance inst = NetworkTableInstance.getDefault();
  NetworkTable put = inst.getTable("tape");
  NetworkTable ball = inst.getTable("ball");
  NetworkTableEntry ballPositioneEntry = ball.getEntry("Y");
  NetworkTableEntry upTapeEntry = put.getEntry("X");
  NetworkTableEntry downTapeEntry = put.getEntry("X");



  public int ballPosition;
  public int upTape;
  public int downTape;

  public Networktable(){
    inst.startClientTeam(7280);
    inst.startDSClient();
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new GetTableData());
  }

  public void getTableData() {
    ballPosition = (int) ballPositioneEntry.getDouble(5.0);
    upTape = (int) upTapeEntry.getDouble(5.0);
    downTape = (int) downTapeEntry.getDouble(5.0);
  }
}