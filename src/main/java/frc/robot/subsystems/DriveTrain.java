package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * This thing aint on an auto-pilot
 */
public class DriveTrain extends Subsystem {
  @Override
  public void initDefaultCommand() {
    // setDefaultCommand(new MySpecialCommand());
  }
  /** Drive using two talons and a joystick **/
  public static void flyByWire(TalonSRX starboard, TalonSRX port, Joystick DriverJoystick){
    double thro = DriverJoystick.getRawAxis(1); //Populate the double thro with the raw axis 1
    double yaw = DriverJoystick.getRawAxis(2); //Populate the double yaw with the raw axis 2

    starboard.set(ControlMode.PercentOutput, (-1 * thro) + yaw); //Subtract the steerage for arcade drive
    port.set(ControlMode.PercentOutput, thro + yaw); //Subtract the steerage for arcade drive, reverse

    
  }
}
