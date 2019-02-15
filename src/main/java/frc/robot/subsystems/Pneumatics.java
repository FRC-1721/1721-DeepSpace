/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem for controlling the compressor and all solenoids
 */
public class Pneumatics extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  /** Switches a piston from whatever position it's in to the opposite position on a button press */
  public static void switchPiston(Joystick controller, DoubleSolenoid piston){
    if(piston.get() == DoubleSolenoid.Value.kForward){
      piston.set(DoubleSolenoid.Value.kReverse);
    }else{
      piston.set(DoubleSolenoid.Value.kForward);
    }
  }

  /** Compress on button input */
  public static void compress(Joystick controller, Compressor compressor, int button){
    if(controller.getRawButton(button)){
      compressor.setClosedLoopControl(true);
    }else{
      compressor.setClosedLoopControl(false);
    }
  }

  /** Initializes analog inputs, should only be used for the pressure sensor */
  public static void initSensor(AnalogInput sensor){
    sensor.setAverageBits(4);
    AnalogInput.setGlobalSampleRate(62500);
  }

  /** Calculates pressure based on sensor values and sensor input voltage (usually 5) */
  public static double calcPressure(AnalogInput sensor, double inputVoltage){
    double returnVoltage = sensor.getVoltage();
    double pressure = (250 * (returnVoltage / inputVoltage)) - 25;
    return pressure;
  }

  /** Shifts gears using a joystick */
  public static void shiftGears(Joystick stick, double threshold, int axis, DoubleSolenoid gearShifter){
    if(stick.getRawAxis(axis) >= 0.5){
      gearShifter.set(DoubleSolenoid.Value.kForward);
    }else if(stick.getRawAxis(axis) <= -0.5){
      gearShifter.set(DoubleSolenoid.Value.kReverse);
    }else{
      
    }
  }
}
