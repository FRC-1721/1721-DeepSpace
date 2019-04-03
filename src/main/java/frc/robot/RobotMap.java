package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SerialPort;

/** Class for defining/mapping objects and port numbers - not to be confused with Constants.java */
public class RobotMap {
 
  // Driver Sticks/Control information
  public static Joystick driverStick, operatorController, gearShiftStick;
  public static int driverStickPort = 0; // USB address of the driver joystick
  public static int controllerPort = 1; // USB address of the operator controller
  public static int gearShiftStickPort = 2; // USB address of gearshift joystick
  
  // CAN Addresses - TalonSRX
  public static int starboardMasterAddress = 0; // Starboard master TalonSRX address
  public static int portMasterAddress = 1; // Port master TalonSRX address
  public static int liftTalonAddress = 2; // Lift master TalonSRX address

  //Filter Variables
  public static double filterCoef = 0.9; // The percentege of percistance the previous filter value has over the current
  public static double filterValue = 1000;

  // CAN Addresses - VictorSPX
  public static int starboardSlaveAddress = 0; // Starboard slave VictorSPX address
  public static int portSlaveAddress  = 1; // Port slave VictorSPX address 
  public static int liftVictorAddress = 2; // Slaved lift VictorSPX address
  public static int starboardSlaveMiniAddress = 3; // Starboard slave VictorSPX address (miniCIM)
  public static int portSlaveMiniAddress = 4; // Port slave VictorSPX address (miniCIM)
  public static int cargoIntakeWheelsAddress = 7; // Intake wheels VictorSPX address
  public static int intakeFolderAddress = 5; // Intake folder VictorSPX address

  // Pneumatics objects
  public static Compressor cp; // Compressor
  public static DoubleSolenoid irisExpander; // Iris open/close piston
  public static DoubleSolenoid irisExtender; // Iris forward/back piston
  public static DoubleSolenoid gearShifter; // Gear shift piston

  // Drive motor controllers
  public static TalonSRX starboardMaster; // Starboard master TalonSRX
  public static VictorSPX starboardSlave; // Starboard slave VictorSPX
  public static VictorSPX starboardSlaveMini; // Starboard slave VictorSPX (miniCIM)
  public static TalonSRX portMaster; // Port master TalonSRX
  public static VictorSPX portSlave; // Port slave VictorSPX
  public static VictorSPX portSlaveMini; // Port slave VictorSPX (miniCIM)

  // Subsystem motor controllers
  public static TalonSRX liftTalon; // Master TalonSRX for lift
  public static VictorSPX liftVictor; // Slave VictorSPX for lift
  public static VictorSPX cargoIntakeWheels; // VictorSPX for running the cargo intake wheels
  public static VictorSPX intakeFolder; // VictorSPX for folding the cargo intake back into the frame

  // PCM Ports
  public static int compressorPort = 0; // PCM port for the compressor - should always be zero, why would we have more than one?
  public static int irisExpandForwardPort = 4; // PCM port to open the iris
  public static int irisExpandReversePort = 5; // PCM port to close the iris
  public static int gearShiftForwardPort = 2; // PCM port to shift gears forward
  public static int gearShiftReversePort = 3; // PCM port to shift gears reverse
  public static int irisExtendForwardPort = 0; // PCM port to push iris out
  public static int irisExtendReversePort = 1; // PCM port to pull iris in

  // Button/axis numbers on drive stick
  public static int trackLowButton = 8; // 8 to track hatch target
  public static int trackHighButton = 7; // 7 to track cargo target
  public static int autocorrectButton = 2; // Side button to enable autocorrect

  // Button/axis numbers on controller
  public static int irisExpandButton = 7; // LT to open/close the iris
  public static int irisExtendButton = 8; // RT to push/pull the iris
  public static int manualZeroButton = 6; // RB to manually zero the lift Talon
  public static int cargoModeButton = 1; // X to move lift to the height of the first cargo target
  public static int hatchModeButton = 2; // A to move lift to the height of the first hatch target (zero)
  public static int upOneButton = 3; // B to move lift up one target
  public static int upTwoButton = 4; // Y to move lift up two targets
  public static int liftOverrideButton = 5; //Unused joystick button (left button) will activiate manual mode while held

  public static int liftOverrideAxis = 1; // LEFT STICK to control manual lift override
  public static int intakeSpinAxis = 3; // RIGHT STICK to spin the intake

  // POV angles on controller
  public static int inAngle = 180; // Straight down to fold intake in
  public static int outAngle = 0; // Straight up to fold intake out

  // Sensor information
  public static AnalogInput pressureSensor; // Displays current pressure
  public static int sensorPort = 1; // RoboRIO port for pressure sensor
  public static DigitalInput minExtension; // Lift limit switch
  public static int limitSwitchPort = 0; // RoboRIO port for limit switch

  // Subsystem booleans
  public static boolean hatchMode; // Dictates whether lift is in "hatch mode"

  public static SerialPort ledPort; // Serial port for the LEDs
}