/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc7280.mecanum_drive_test.command_group;

import org.usfirst.frc7280.mecanum_drive_test.Constants;
import org.usfirst.frc7280.mecanum_drive_test.Robot;
import org.usfirst.frc7280.mecanum_drive_test.commands.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class GrabPlate extends CommandGroup {
  /**
   * Add your docs here.
   */
  
  public GrabPlate() {
    /* 
    0.Arm down to position 
    1. move cylinder out
    2. lift up elevator to grab plate height 
    3. adjust the robot in Z axis 
    4. move X and move Y to touch the plate 
    5. lift up the arm 
    6. move back 
    7. move cylinder in 

    0.1.2.3.
    4.
    5.6.7.
    */


    addParallel(new ArmDown());
    addParallel(new SolenoidOut());
    addParallel(new Lift(Constants.kFirstLevel));
    addSequential(new visionMotion());

    addParallel(new ArmLift());
    addParallel(new Lift(Constants.kZeroLevel));
    addSequential(new MoveY(-3000));

    addParallel(new SolenoidIn());


  }
}