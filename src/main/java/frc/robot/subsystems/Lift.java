/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Constants;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Lift extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public static void raiseLift(TalonSRX lift, Joystick controller){
    if (controller.getRawButton(RobotMap.hatchModeButton)){ // If A is held
      lift.set(ControlMode.Position, 0); // Move to height of lowest hatch target
    }else if(controller.getRawButton(RobotMap.cargoModeButton)){ // If B is held
      lift.set(ControlMode.Position, Constants.cargoTargetHeight); // Move to height of lowest cargo target
    }else if(controller.getRawButton(RobotMap.upOneButton)){
      double height = lift.getSelectedSensorPosition();
      double target = height + Constants.distanceBetweenTargets;
      lift.set(ControlMode.Position, target);
    }else if(controller.getRawButton(RobotMap.upTwoButton)){
      double height = lift.getSelectedSensorPosition();
      double target = height + (2 * Constants.distanceBetweenTargets);
      lift.set(ControlMode.Position, target);
    }
  }
}
