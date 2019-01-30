package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Pneumatics;

public class Robot extends TimedRobot {

  public static OI m_oi;

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
    RobotMap.gearShifter = new DoubleSolenoid(RobotMap.gearShiftForwardPort, RobotMap.gearShiftReversePort); // Gear shifter piston
    // Define subsystem motor controllers
    RobotMap.liftTalon = new TalonSRX (RobotMap.liftTalonAddress); // Lift master TalonSRX
    RobotMap.liftVictor = new VictorSPX(RobotMap.liftVictorAddress); // Lift slave VictorSPX
    // Set drive slaves to follow master drive Talons
    RobotMap.starboardSlave.follow(RobotMap.starboardMaster);
    RobotMap.starboardSlaveMini.follow(RobotMap.starboardMaster);
    RobotMap.portSlave.follow(RobotMap.portMaster);
    RobotMap.portSlaveMini.follow(RobotMap.portMaster);
    // Set lift slaves to follow master lift Talon
    RobotMap.liftVictor.follow(RobotMap.liftTalon);
    // Sensor for reading pressure values
    RobotMap.pressureSensor = new AnalogInput(0);
    Pneumatics.initSensor(RobotMap.pressureSensor);

    //Set both motor controlers to default
    RobotMap.starboardMaster.configFactoryDefault();
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

    RobotMap.portMaster.setSensorPhase(false);
    RobotMap.starboardMaster.setSensorPhase(false);

    RobotMap.portMaster.setSelectedSensorPosition(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
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
    RobotMap.portMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
    RobotMap.portMaster.configMotionCruiseVelocity(16000, Constants.kTimeoutMs);
    RobotMap.portMaster.configMotionAcceleration(16000, Constants.kTimeoutMs);
    RobotMap.starboardMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
    RobotMap.starboardMaster.configMotionCruiseVelocity(16000, Constants.kTimeoutMs);
    RobotMap.starboardMaster.configMotionAcceleration(16000, Constants.kTimeoutMs);
    
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();

    if (RobotMap.driverStick.getRawButton(1)) {
      /* Motion Magic */ 
      double targetPos = Mathematics.calcPulses(Constants.targetDistance);
      RobotMap.portMaster.set(ControlMode.MotionMagic, -1 * targetPos);
      RobotMap.starboardMaster.set(ControlMode.MotionMagic, targetPos);
    }else{
      RobotMap.portMaster.set(ControlMode.PercentOutput, 0);
      RobotMap.starboardMaster.set(ControlMode.PercentOutput, 0);
      RobotMap.portMaster.setSelectedSensorPosition(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
      RobotMap.starboardMaster.setSelectedSensorPosition(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
    }
  }

  @Override
  public void teleopInit() {
    
  }

  @Override
  public void teleopPeriodic() {
     Scheduler.getInstance().run();
     // Compress when B is held
    Pneumatics.compress(RobotMap.operatorController, RobotMap.cp, RobotMap.compressorButton);

    // Open/close the intake with X
    Pneumatics.controlIris(RobotMap.operatorController, RobotMap.irisButton, RobotMap.irisPiston);

    if(RobotMap.operatorController.getRawButton(RobotMap.shiftDownButton)){
      Pneumatics.shiftUp(RobotMap.gearShifter);
    }else if(RobotMap.operatorController.getRawButton(RobotMap.shiftUpButton)){
      Pneumatics.shiftDown(RobotMap.gearShifter);
    }

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

    float xFloat = (float)x; // Float of horizontal error
    float areaFloat = (float)area; // Float of area

    double pressure = Pneumatics.calcPressure(RobotMap.pressureSensor, 5); // Current stored pressure in tanks
    
    // Angular correction with limelight when A is held
    if(RobotMap.operatorController.getRawButton(1) && hasTarget == 1.0){
      float distanceAdjust = -1 * Constants.accelerationP * (float)(Constants.targetDistance - Mathematics.countDistance(y)); // Creates a distance adjustment based on error
      float steeringAdjust = Constants.angularP * xFloat; // Creates a side-to-side adjustment based on error
      DriveTrain.flyWithWires(RobotMap.starboardMaster, RobotMap.portMaster, steeringAdjust, distanceAdjust); // Drive using adjustment values
    }else{
      DriveTrain.flyByWire(RobotMap.starboardMaster, RobotMap.portMaster, RobotMap.driverStick, RobotMap.gearShifter); // Drive using joystick when A is not held
    }

    // Post to smart dashboard periodically
    SmartDashboard.putNumber("LimelightX", x); // Horizontal error
    SmartDashboard.putNumber("LimelightY", y); // Vertical error
    SmartDashboard.putNumber("LimelightArea", area); // Area of limelight target
    SmartDashboard.putNumber("Current pressure", pressure); // Stored pressure
  }

  @Override
  public void testPeriodic() {
  }
}
