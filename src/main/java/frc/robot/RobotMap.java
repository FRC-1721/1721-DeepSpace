package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

public class RobotMap {

  //Driver Sticks/Control information
  public static Joystick driverStick;
  public static int driverStickPort = 0; //The usb address of the driver
  
  //CAN Ports
  public static int starboardMotorCAN = 0; //Define Starboard Motor (Through RIO)
  public static int portMotorCAN = 1; //Define Port Motor (Through RIO) 

  //Types
  public static WPI_TalonSRX starboardMotor; 
  public static WPI_TalonSRX portMotor; 
  /*  Joe recomends that starboard and port be used to differentiate robot sides rather than 
   *  left and right beacuse of the ambigious nature of robot construction, where a face or 
   * front is not always easily noted upon. 
   */
}