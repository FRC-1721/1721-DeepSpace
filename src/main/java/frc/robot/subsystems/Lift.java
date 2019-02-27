/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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

  /** Zeroes the lift when a limit switch is pressed down*/
  public static void zeroLift(TalonSRX lift, DigitalInput minExtension){
    boolean isZero = minExtension.get();
    SmartDashboard.putBoolean("Is zero", isZero);
    if(isZero){
      lift.setSelectedSensorPosition(0);
    }
  }

  /** Overrides the lift PID for manual control with an axis while a button is held - be sure to
   * return lift to position zero when done */
  public static void manualOverride(Joystick controller, int button, int axis, TalonSRX lift){
    double throttle = -0.75 * controller.getRawAxis(axis);
    if(controller.getRawButton(button)){
      if(throttle >= 0.175){
        lift.set(ControlMode.PercentOutput, throttle);
      }else{
        lift.set(ControlMode.PercentOutput, throttle / 2);
      }
    }
  }
}
