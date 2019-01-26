package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Constants;

/**
 * This thing aint on an auto-pilot, except when it is
 */
public class DriveTrain extends Subsystem {
  @Override
  public void initDefaultCommand() {
    // setDefaultCommand(new MySpecialCommand());
  }
  /** Drive using two talons and a joystick **/
  public static void flyByWire(TalonSRX starboard, TalonSRX port, Joystick DriverJoystick, DoubleSolenoid gearShift){
    double thro = DriverJoystick.getRawAxis(1); //Populate the double thro with the raw axis 1
    double yaw = DriverJoystick.getRawAxis(2); //Populate the double yaw with the raw axis 2

    if(gearShift.get() == DoubleSolenoid.Value.kReverse){ // If bot is in high gear
      starboard.set(ControlMode.PercentOutput, (Constants.speedDampener * -1 * thro) - yaw); //Subtract the steerage for arcade drive
      port.set(ControlMode.PercentOutput, (Constants.speedDampener * thro) - yaw); //Subtract the steerage for arcade drive, reverse
    }else{
      starboard.set(ControlMode.PercentOutput, (-1 * thro) - yaw);
      port.set(ControlMode.PercentOutput, thro - yaw);
    }
  }

  /** Drive using two TalonSRX and adjustment values for horizontal and distance correction */
  public static void flyWithWires(TalonSRX starboard, TalonSRX port, float heading, float throttle){
    boolean inAngularRange = heading > Constants.minimumAngularCommand && heading < Constants.powerRequirement; // If error is between max acceptable error and minimum power
    boolean inDistanceRange = throttle > Constants.minimumRangeCommand && throttle < Constants.powerRequirement; // ^^^^^
    if(inAngularRange){ 
      starboard.set(ControlMode.PercentOutput, Constants.powerRequirement + throttle); // Set the starboard drivetrain motor to the steering power requirement added to the base speed
      port.set(ControlMode.PercentOutput, Constants.powerRequirement - throttle); // Does the same but on the other side
    }else if(inDistanceRange){
      starboard.set(ControlMode.PercentOutput, heading + Constants.powerRequirement); // Set the starboard drivetrain motor to the heading (steering angle) added to the power requirement
      port.set(ControlMode.PercentOutput, heading - Constants.powerRequirement); // Does the same but on the other side
    }else{
      starboard.set(ControlMode.PercentOutput, heading + throttle); // Set the starboard drivetrain motor to the heading (steering angle) added to the base speed
      port.set(ControlMode.PercentOutput, heading - throttle); // Does the same but on the other side
    }
  }
}
