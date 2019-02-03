package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;

public class RobotMap {
 
  // Driver Sticks/Control information
  public static Joystick driverStick, operatorController;
  public static int driverStickPort = 0; // USB address of the driver joystick
  public static int controllerPort = 1; // USB address of the operator controller
  
  // CAN Addresses - TalonSRX
  public static int starboardMasterAddress = 0; // Starboard master TalonSRX address
  public static int portMasterAddress = 1; // Port master TalonSRX address
  public static int liftTalonAddress = 2; // Lift master TalonSRX address

  // CAN Addresses - VictorSPX
  public static int starboardSlaveAddress = 0; // Starboard slave VictorSPX address
  public static int portSlaveAddress  = 1; // Port slave VictorSPX address 
  public static int liftVictorAddress = 2; // Slaved lift VictorSPX address
  public static int starboardSlaveMiniAddress = 3; // Starboard slave VictorSPX address (miniCIM)
  public static int portSlaveMiniAddress = 4; // Port slave VictorSPX address (miniCIM)
  public static int cargoIntakeWheelsAddress = 6; // Intake wheels VictorSPX address
  public static int intakeFolderAddress = 5; // Intake folder VictorSPX address

  // Pneumatics objects
  public static Compressor cp; // Compressor
  public static DoubleSolenoid irisExpander; // Iris open/close piston
  public static DoubleSolenoid irisExtender; // Iris forward/back piston
  public static DoubleSolenoid gearShifter; // Gear shift portside

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
  public static int irisExtendForwardPort = 0; // PCM port to open the iris
  public static int irisExtendReversePort = 1; // PCM port to close the iris
  public static int gearShiftForwardPort = 2; // PCM port to shift gears forward
  public static int gearShiftReversePort = 3; // PCM port to shift gears reverse

  // Button/axis numbers on controller
  public static int irisButton = 8; // RT to open/close the iris
  public static int shiftUpButton = 5; // LB to shift up
  public static int shiftDownButton = 6; // RB to shift down
  public static int cargoModeButton = 2; // B to move lift to the height of the first cargo target
  public static int hatchModeButton = 1; // A to move lift to the height of the first hatch target (zero)
  public static int upOneButton = 3; // X to move lift up one target
  public static int upTwoButton = 4; // Y to move lift up two targets

  // Button/axis numbers on drive joystick
  public static int trackingButton = 1; // A to vision track

  // Sensors
  public static AnalogInput pressureSensor; // Displays current pressure

  // Subsystem booleans
  public static boolean hatchMode; // Dictates whether lift is in "hatch mode"
}