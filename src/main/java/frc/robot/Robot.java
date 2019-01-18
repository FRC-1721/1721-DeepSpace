package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Pneumatics;

public class Robot extends TimedRobot {

  public static OI m_oi;


  Command m_autonomousCommand; // Personalize me!
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  @Override
  public void robotInit() {
    m_oi = new OI();
    // Define joysticks
    RobotMap.driverStick = new Joystick(RobotMap.driverStickPort); // Drive joystick
    RobotMap.operatorController = new Joystick(RobotMap.controllerPort); // Operator controller
    // Define master drive (SRX) Talons
    RobotMap.starboardMaster = new TalonSRX(RobotMap.starboardMasterAddress); // Port master
    RobotMap.portMaster = new TalonSRX(RobotMap.portMasterAddress); // Starboard master
    // Define slave drive (SPX) Victors
    RobotMap.starboardSlave = new VictorSPX(RobotMap.starboardSlaveAddress); // Starboard slave 1
    RobotMap.portSlave = new VictorSPX(RobotMap.portSlaveAddress); // Port slave 1
    RobotMap.starboardSlaveMini = new VictorSPX(RobotMap.starboardSlaveMiniAddress); // Starboard slave 2 (mini)
    RobotMap.portSlaveMini = new VictorSPX(RobotMap.portSlaveMiniAddress); // Port slave 2 (mini)
    // Define pneumatics objects
    RobotMap.cp = new Compressor(RobotMap.compressorPort); // Compressor
    RobotMap.irisPiston = new DoubleSolenoid(RobotMap.irisForwardPort, RobotMap.irisReversePort); // Iris piston
    RobotMap.gearShifter = new Solenoid(RobotMap.gearShiftPort); // Gear shifter piston
    // Define subsystem motor controllers
    RobotMap.liftTalon = new TalonSRX (RobotMap.liftTalonAddress); // Lift master TalonSRX
    RobotMap.liftVictorPort = new VictorSPX(RobotMap.liftVictorPortAddress); // Lift port slave VictorSPX
    RobotMap.liftVictorStarboard = new VictorSPX(RobotMap.liftVictorStarboardAddress); // Lift starboard slave VictorSPX
    // Set drive slaves to follow master drive Talons
    RobotMap.starboardSlave.follow(RobotMap.starboardMaster);
    RobotMap.starboardSlaveMini.follow(RobotMap.starboardMaster);
    RobotMap.portSlave.follow(RobotMap.portMaster);
    RobotMap.portSlaveMini.follow(RobotMap.portMaster);
    // Set lift slaves to follow master lift Talon
    RobotMap.liftVictorPort.follow(RobotMap.liftTalon);
    RobotMap.liftVictorStarboard.follow(RobotMap.liftTalon);
  }

  @Override
  public void robotPeriodic() {
  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_chooser.getSelected();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
  if (m_autonomousCommand != null) {
    m_autonomousCommand.cancel();
  }
  //Inturrupt for Auto

  RobotMap.starboardMaster.configFactoryDefault(); //Set both motor controlers to default
  RobotMap.portMaster.configFactoryDefault();

  /* Config the peak and nominal outputs ([-1, 1] represents [-100, 100]%) */
	RobotMap.starboardMaster.configNominalOutputForward(0, Constants.kTimeoutMs);
	RobotMap.starboardMaster.configNominalOutputReverse(0, Constants.kTimeoutMs);
	RobotMap.starboardMaster.configPeakOutputForward(1, Constants.kTimeoutMs);
  RobotMap.starboardMaster.configPeakOutputReverse(-1, Constants.kTimeoutMs);

  RobotMap.portMaster.configNominalOutputForward(0, Constants.kTimeoutMs);
	RobotMap.portMaster.configNominalOutputReverse(0, Constants.kTimeoutMs);
	RobotMap.portMaster.configPeakOutputForward(1, Constants.kTimeoutMs);
  RobotMap.portMaster.configPeakOutputReverse(-1, Constants.kTimeoutMs);

  /**
	 * Config the allowable closed-loop error, Closed-Loop output will be
	 * neutral within this range. See Table here for units to use: 
   * https://github.com/CrossTheRoadElec/Phoenix-Documentation#what-are-the-units-of-my-sensor
	 */
  RobotMap.starboardMaster.configAllowableClosedloopError(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
  RobotMap.portMaster.configAllowableClosedloopError(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);

	/* Config closed loop gains for Primary closed loop (Current) */
	RobotMap.starboardMaster.config_kP(Constants.kPIDLoopIdx, Constants.kGains.kP, Constants.kTimeoutMs);
	RobotMap.starboardMaster.config_kI(Constants.kPIDLoopIdx, Constants.kGains.kI, Constants.kTimeoutMs);
  RobotMap.starboardMaster.config_kD(Constants.kPIDLoopIdx, Constants.kGains.kD, Constants.kTimeoutMs);
  RobotMap.starboardMaster.config_kF(Constants.kPIDLoopIdx, Constants.kGains.kF, Constants.kTimeoutMs);

  RobotMap.portMaster.config_kP(Constants.kPIDLoopIdx, Constants.kGains.kP, Constants.kTimeoutMs);
	RobotMap.portMaster.config_kI(Constants.kPIDLoopIdx, Constants.kGains.kI, Constants.kTimeoutMs);
  RobotMap.portMaster.config_kD(Constants.kPIDLoopIdx, Constants.kGains.kD, Constants.kTimeoutMs);
  RobotMap.portMaster.config_kF(Constants.kPIDLoopIdx, Constants.kGains.kF, Constants.kTimeoutMs);
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    
  // Compress when B is held
  Pneumatics.compress(RobotMap.operatorController, RobotMap.cp, RobotMap.compressorButton);

  // Open/close the intake
  Pneumatics.controlIris(RobotMap.operatorController, RobotMap.irisButton, RobotMap.irisPiston);

  // Establish link to limelight
  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  NetworkTableEntry tx = table.getEntry("tx");
  NetworkTableEntry ty = table.getEntry("ty");
  NetworkTableEntry ta = table.getEntry("ta");
  NetworkTableEntry tv = table.getEntry("tv");

  // Read values periodically
  double x = tx.getDouble(0.0); // Horizontal error
  double y = ty.getDouble(0.0); // Vertical error
  double area = ta.getDouble(0.0); // % area of vision target
  double hasTarget = tv.getDouble(0.0); // Whether or not the limelight has a target - 0 for no, 1 for yes

  float xFloat = (float)x;
  float areaFloat = (float)area;
  
  // Angular correction with limelight when A is held
  if(RobotMap.operatorController.getRawButton(RobotMap.trackingButton) && hasTarget == 1.0){
    float distanceTarget = Constants.accelerationP * (Constants.optimalArea - areaFloat); // Create a target value for distance
    float steeringAdjust = Constants.angularP * xFloat; // Create a target side-to-side adjustment
    DriveTrain.flyWithWires(RobotMap.starboardMaster, RobotMap.portMaster, steeringAdjust, distanceTarget);
  }else{
    DriveTrain.flyByWire(RobotMap.starboardMaster, RobotMap.portMaster, RobotMap.driverStick); // Drive using joystick
  }

	// Post to smart dashboard periodically
	SmartDashboard.putNumber("LimelightX", x);
	SmartDashboard.putNumber("LimelightY", y);
  SmartDashboard.putNumber("LimelightArea", area);
  }

  @Override
  public void testPeriodic() {
  }
}