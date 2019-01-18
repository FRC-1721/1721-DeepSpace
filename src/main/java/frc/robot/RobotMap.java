package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;

public class RobotMap {
 
  // Driver Sticks/Control information
  public static Joystick driverStick, operatorController;
  public static int driverStickPort = 0; // USB address of the driver joystick
  public static int controllerPort = 1; // USB address of the operator controller
  
  // CAN Addresses - TalonSRX
  public static int starboardMasterAddress = 1; // Starboard master TalonSRX address
  public static int portMasterAddress = 0; // Port master TalonSRX address
  public static int liftTalonAddress = 2; // Lift master TalonSRX address

  // CAN Addresses - VictorSPX
  public static int starboardSlaveAddress = 0; // Starboard slave VictorSPX address
  public static int portSlaveAddress  = 1; // Port slave VictorSPX address
  public static int cargoIntakeWheelsAddress = 2; // Intake wheels VictorSPX address
  public static int starboardSlaveMiniAddress = 3; // Starboard slave VictorSPX address (miniCIM)
  public static int portSlaveMiniAddress = 4; // Port slave VictorSPX address (miniCIM)
  public static int intakeFolderAddress = 5; // Intake folder VictorSPX address
  public static int liftVictorStarboardAddress = 6; // Lift slaved VictorSPX address
  public static int liftVictorPortAddress = 7;

  // Pneumatics objects
  public static Compressor cp; // Compressor
  public static DoubleSolenoid irisPiston; // Iris open/close piston
  public static Solenoid gearShifter; // Gear shift

  // Drive motor controllers
  public static TalonSRX starboardMaster; // Starboard master TalonSRX
  public static VictorSPX starboardSlave; // Starboard slave VictorSPX
  public static VictorSPX starboardSlaveMini; // Starboard slave VictorSPX (miniCIM)
  public static TalonSRX portMaster; // Port master TalonSRX
  public static VictorSPX portSlave; // Port slave VictorSPX
  public static VictorSPX portSlaveMini; // Port slave VictorSPX (miniCIM)

  // Subsystem motor controllers
  public static TalonSRX liftTalon; // Master TalonSRX for lift
  public static VictorSPX liftVictorPort; // One slave VictorSPX for lift
  public static VictorSPX liftVictorStarboard; // The other slave VictorSPX for lift
  public static VictorSPX cargoIntakeWheels; // VictorSPX for running the cargo intake wheels
  public static VictorSPX intakeFolder; // VictorSPX for folding the cargo intake back into the frame

  // PID tuning values
  public static float kP;
  public static float kI;
  public static float kD;

  // PCM Ports
  public static int compressorPort = 0; // PCM port for the compressor - should always be zero, why would we have more than one?
  public static int irisForwardPort = 0; // PCM port to open the iris
  public static int irisReversePort = 1; // PCM port to close the iris
  public static int gearShiftPort = 2; // PCM port to shift gears

  // Button/axis numbers
  public static int trackingButton = 1; // A to vision track
  public static int compressorButton = 2; // B to compress
  public static int irisButton = 3; // X to open/close the iris
}