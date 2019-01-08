package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class RobotMap {

  //Driver Sticks/Control information
  public static Joystick driverStick;
  public static int driverStickPort = 0; //The usb address of the driver
  
  //CAN Ports
  public static int starboardMotorCAN = 1; //Define Starboard Motor (Through RIO)
  public static int portMotorCAN = 0; //Define Port Motor (Through RIO) 
  public static int starboardSlave = 0;
  public static int portSlave = 1;
  public static int cargoIntake = 2;

  //Types
  public static WPI_TalonSRX starboardMotor; 
  public static WPI_TalonSRX portMotor; 
  /*  Joe recomends that starboard and port be used to differentiate robot sides rather than 
   *  left and right beacuse of the ambigious nature of robot construction, where a face or 
   * front is not always easily noted upon. 
   */
  
  //public static Victor starboardSlave;
  //public static Victor portSlave;
  //public static Victor cargoIntake;
}