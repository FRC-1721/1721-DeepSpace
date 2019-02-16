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
 * Subsystem for PID lift control - in development
 */
public class Lift extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  /** Takes a lift TalonSRX and several controller button inputs and moves lift
  using PID */
  public static void raiseLift(TalonSRX lift, Joystick controller){
    if (controller.getRawButton(RobotMap.hatchModeButton)){ // If A is held
      lift.set(ControlMode.Position, Constants.hatchTargetHeight); // Move to height of lowest hatch target
    }else if(controller.getRawButton(RobotMap.cargoModeButton)){ // If B is held
      lift.set(ControlMode.Position, Constants.cargoTargetHeight); // Move to height of lowest cargo target
    }else if(controller.getRawButton(RobotMap.upOneButton)){ // If X is held
      double height = lift.getSelectedSensorPosition(); // Current height
      double target = height + Constants.distanceBetweenTargets; // Creates a target height
      lift.set(ControlMode.Position, target); // Move to target height
    }else if(controller.getRawButton(RobotMap.upTwoButton)){ // If Y is held
      double height = lift.getSelectedSensorPosition(); // Current height
      double target = height + (2 * Constants.distanceBetweenTargets); // Creates a target height
      lift.set(ControlMode.Position, target); // Move to target height
    }
  }

  /** Overrides the lift PID for manual control with an axis while a button is held - be sure to
   * return lift to zero when done */
  public static void manualOverride(Joystick controller, int button, int axis, TalonSRX lift){
    if(controller.getRawButton(button)){
      double throttle = -0.4 * controller.getRawAxis(axis);
      lift.set(ControlMode.PercentOutput, throttle);
    }else{
      lift.set(ControlMode.PercentOutput, 0);
    }
  }
}
