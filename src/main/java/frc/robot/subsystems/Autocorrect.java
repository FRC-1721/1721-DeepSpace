/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotMap;

/**
 * Subsystem for functions to automatically adjust the robot in unfavorable conditions
 */
public class Autocorrect extends SubsystemBase {
  /** Lowers the lift to cargo height if the angle becomes too drastic */
  public static void adjustCG(double angle, TalonSRX lift, double maxAngle){
    boolean isTooLow = angle < 0 && angle <= -1 * maxAngle;
    boolean isTooHigh = angle > 0 && angle >= maxAngle;
    if(isTooLow || isTooHigh){
      RobotMap.liftTalon.set(ControlMode.Position, Constants.cargoTargetHeight * Constants.pulsesPerLiftInch);
      SmartDashboard.putString("Tippin?", "TIPPIN");
    }else{
      SmartDashboard.putString("Tippin?", "nahhhhh");
    }
  }
}
