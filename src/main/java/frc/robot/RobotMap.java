package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Joystick;

public class RobotMap {

  //Driver Sticks/Control information
  public static Joystick driverStick, operatorController;
  public static int driverStickPort = 0; //The usb address of the driver
  
  //CAN Ports
  public static int starboardMotorCAN = 1; //Define Starboard Motor (Through RIO)
  public static int portMotorCAN = 0; //Define Port Motor (Through RIO) 
  public static int starboardSlave = 0; // Starboard victor port
  public static int portSlave = 1; // Port victor port
  public static int cargoIntake = 2; // Intake victor port

  //Types
  public static TalonSRX starboardMotor; // Starboard motor
  public static TalonSRX portMotor; // Port motor
  /*  Joe recomends that starboard and port be used to differentiate robot sides rather than 
   *  left and right beacuse of the ambigious nature of robot construction, where a face or 
   * front is not always easily noted upon. 
   */

   //PID tuning values
   public static float kP;
   public static float kI;
   public static float kD;
}