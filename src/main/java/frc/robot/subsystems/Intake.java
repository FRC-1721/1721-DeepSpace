/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Intake extends SubsystemBase {
  /** Spins the intake wheels */
  public static void spin(Joystick controller, int axis, VictorSPX intake){
    double throttle = controller.getRawAxis(axis);
    if(RobotMap.irisExtender.get() == DoubleSolenoid.Value.kForward){
      intake.set(ControlMode.PercentOutput, 0);
    }else{
      intake.set(ControlMode.PercentOutput, throttle);
    }
  }

  /** Folds up the intake */
  public static void fold(Joystick controller, int inAngle, int outAngle, VictorSPX folder){
    double povAngle = controller.getPOV(0);
    if(povAngle == inAngle){
      folder.set(ControlMode.PercentOutput, 1);
    }else if (povAngle == outAngle){
      folder.set(ControlMode.PercentOutput, -1);
    }else{
      folder.set(ControlMode.PercentOutput, 0);
    }
  }
}