package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.RobotMap;

/**
 * This thing aint on an auto-pilot, except when it is
 */
public class DriveTrain extends Subsystem {
  @Override
  public void initDefaultCommand() {
    // setDefaultCommand(new MySpecialCommand());
  }

  public static void autoDrift(Joystick DriverJoystick, DoubleSolenoid gearShift)
  {
    int gear = 1;
    if(Math.abs(DriverJoystick.getRawAxis(1)) < RobotMap.lowerLimit) //If you're driving slower than the lower limit
    {
      gearShift.set(DoubleSolenoid.Value.kReverse);
      gear = 2;
    }
    
    if(Math.abs(DriverJoystick.getRawAxis(1)) > RobotMap.upperLimit) //If you're driving faster than the upper limit
    {
      gearShift.set(DoubleSolenoid.Value.kForward);
      gear = 0;
    }

    SmartDashboard.putNumber("Gear", gear);
  }

  /** Drive using two talons and a joystick **/
  public static void flyByWire(TalonSRX starboard, TalonSRX port, Joystick DriverJoystick, DoubleSolenoid gearShift){
    double thro = DriverJoystick.getRawAxis(1); //Populate the double thro with the raw axis 1
    double yaw = Constants.turnDampener * DriverJoystick.getRawAxis(2); //Populate the double yaw with the raw axis 2

    if(gearShift.get() == DoubleSolenoid.Value.kReverse){ // If bot is in high gear
      starboard.set(ControlMode.PercentOutput, (-1 * thro) - yaw); // Subtract the steerage for arcade drive
      port.set(ControlMode.PercentOutput, thro - yaw); // Subtract the steerage for arcade drive, reverse
    }else{ // If bot is in low gear
      starboard.set(ControlMode.PercentOutput, (-1 * thro) - yaw); // Drive normally
      port.set(ControlMode.PercentOutput, thro - yaw); // Drive normally
    }
  }

  /** Drive using two TalonSRX and adjustment values for horizontal and distance correction */
  public static void flyWithWires(TalonSRX starboard, TalonSRX port, double heading, double throttle){
    starboard.set(ControlMode.Velocity, -1 * heading - throttle); // Set the starboard drivetrain motor to the steering power requirement added to the base speed
    port.set(ControlMode.Velocity, -1 * heading + throttle); // Does the same but on the other side
  }
}