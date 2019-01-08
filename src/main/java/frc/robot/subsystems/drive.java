package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * This shit aint on auto-pilot
 */
public class drive extends Subsystem {
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
  public static void flyByWire(WPI_TalonSRX starboard, WPI_TalonSRX port, Joystick DriverJoystick){
    double thro = DriverJoystick.getRawAxis(1);
    double yaw = DriverJoystick.getRawAxis(2);

    starboard.set(thro + yaw); //Subtract the steerage for arcade drive
    port.set((thro - yaw) * -1); //Subtract the steerage for arcade drive, reverse
  }
}
