/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotMap;

public class upTwo extends CommandBase {
  public upTwo() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  public void initialize() {
    double currentPos = RobotMap.liftTalon.getSelectedSensorPosition();

    //the target before safty
    double proposedTarget = currentPos + (2 * Constants.distanceBetweenTargets * Constants.pulsesPerLiftInch); //Set our proposed target

    if (proposedTarget > Constants.maxPhyiscalLiftMovement) //if the proposed target is bigger than we can move
    {
      proposedTarget = Constants.maxPhyiscalLiftMovement - Constants.saftyMargin; //dont do it
    }

    RobotMap.liftTalon.set(ControlMode.Position, proposedTarget); //idk
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  public void execute() {
  }

  // Called once after isFinished returns true
  @Override
  public void end(boolean interrupted) {
  }
}
