package frc.robot;

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
import frc.robot.subsystems.Lift;
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

    //Set motor controlers to default
    RobotMap.starboardMaster.configFactoryDefault();
    RobotMap.portMaster.configFactoryDefault();
    RobotMap.liftTalon.configFactoryDefault();

    /* Config the peak and nominal outputs ([-1, 1] represents [-100, 100]%) */
    RobotMap.starboardMaster.configNominalOutputForward(0, Constants.kTimeoutMs);
    RobotMap.starboardMaster.configNominalOutputReverse(0, Constants.kTimeoutMs);
    RobotMap.starboardMaster.configPeakOutputForward(1, Constants.kTimeoutMs);
    RobotMap.starboardMaster.configPeakOutputReverse(-1, Constants.kTimeoutMs);

    RobotMap.portMaster.configNominalOutputForward(0, Constants.kTimeoutMs);
    RobotMap.portMaster.configNominalOutputReverse(0, Constants.kTimeoutMs);
    RobotMap.portMaster.configPeakOutputForward(1, Constants.kTimeoutMs);
    RobotMap.portMaster.configPeakOutputReverse(-1, Constants.kTimeoutMs);

    RobotMap.liftTalon .configNominalOutputForward(0, Constants.kTimeoutMs);
    RobotMap.liftTalon.configNominalOutputReverse(0, Constants.kTimeoutMs);
    RobotMap.liftTalon.configPeakOutputForward(1, Constants.kTimeoutMs);
    RobotMap.liftTalon.configPeakOutputReverse(-1, Constants.kTimeoutMs);

    /**
     * Config the allowable closed-loop error, Closed-Loop output will be
     * neutral within this range. See Table here for units to use: 
     * https://github.com/CrossTheRoadElec/Phoenix-Documentation#what-are-the-units-of-my-sensor
     */
    RobotMap.starboardMaster.configAllowableClosedloopError(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
    RobotMap.portMaster.configAllowableClosedloopError(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
    RobotMap.liftTalon.configAllowableClosedloopError(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);

    /* Config closed loop gains for Primary closed loop (Current) */
    RobotMap.starboardMaster.config_kP(Constants.kPIDLoopIdx, Constants.kGains.kP, Constants.kTimeoutMs);
    RobotMap.starboardMaster.config_kI(Constants.kPIDLoopIdx, Constants.kGains.kI, Constants.kTimeoutMs);
    RobotMap.starboardMaster.config_kD(Constants.kPIDLoopIdx, Constants.kGains.kD, Constants.kTimeoutMs);
    RobotMap.starboardMaster.config_kF(Constants.kPIDLoopIdx, Constants.kGains.kF, Constants.kTimeoutMs);
    RobotMap.starboardMaster.config_IntegralZone(Constants.kPIDLoopIdx, Constants.kGains.kIzone, Constants.kTimeoutMs);

    RobotMap.portMaster.config_kP(Constants.kPIDLoopIdx, Constants.kGains.kP, Constants.kTimeoutMs);
    RobotMap.portMaster.config_kI(Constants.kPIDLoopIdx, Constants.kGains.kI, Constants.kTimeoutMs);
    RobotMap.portMaster.config_kD(Constants.kPIDLoopIdx, Constants.kGains.kD, Constants.kTimeoutMs);
    RobotMap.portMaster.config_kF(Constants.kPIDLoopIdx, Constants.kGains.kF, Constants.kTimeoutMs);
    RobotMap.portMaster.config_IntegralZone(Constants.kPIDLoopIdx, Constants.kGains.kIzone, Constants.kTimeoutMs);

    RobotMap.liftTalon.config_kP(Constants.kPIDLoopIdx, Constants.liftP, Constants.kTimeoutMs);
    RobotMap.liftTalon.config_kI(Constants.kPIDLoopIdx, Constants.liftI, Constants.kTimeoutMs);
    RobotMap.liftTalon.config_kD(Constants.kPIDLoopIdx, Constants.liftD, Constants.kTimeoutMs);
    RobotMap.liftTalon.config_kF(Constants.kPIDLoopIdx, Constants.liftF, Constants.kTimeoutMs);
    RobotMap.liftTalon.config_IntegralZone(Constants.kPIDLoopIdx, Constants.liftIZone, Constants.kTimeoutMs);
    
    // Initalizes encoders
    RobotMap.portMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
    RobotMap.starboardMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
    RobotMap.liftTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, Constants.kPIDLoopIdx, Constants.kTimeoutMs);

    // Ensures motor output and encoder velocity are proportional to each other
    // If they become inverted, set these to true
    RobotMap.portMaster.setSensorPhase(false);
    RobotMap.starboardMaster.setSensorPhase(false);
    RobotMap.liftTalon.setSensorPhase(false);

    // Zeroes encoders
    RobotMap.portMaster.setSelectedSensorPosition(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
    RobotMap.starboardMaster.setSelectedSensorPosition(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
    RobotMap.liftTalon.setSelectedSensorPosition(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);

  }

  @Override
  public void robotPeriodic() {

    // Compress automatically
    RobotMap.cp.setClosedLoopControl(true);

    // Open/close the intake with RT
    Pneumatics.controlIris(RobotMap.operatorController, RobotMap.irisButton, RobotMap.irisPiston);

    // Shift the gearbox high/low using LB and RB
    if(RobotMap.operatorController.getRawButton(RobotMap.shiftDownButton)){
      Pneumatics.shiftUp(RobotMap.gearShifter);
    }else if(RobotMap.operatorController.getRawButton(RobotMap.shiftUpButton)){
      Pneumatics.shiftDown(RobotMap.gearShifter);
    }

    // Raise/lower the lift with A, B, X, and Y
    Lift.raiseLift(RobotMap.liftTalon, RobotMap.operatorController);

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

    double pressure = Pneumatics.calcPressure(RobotMap.pressureSensor, 5); // Current stored pressure in tanks

    // PID navigation to limelight target when the trigger is held
    if(RobotMap.driverStick.getRawButton(RobotMap.trackingButton) && hasTarget == 1.0){
      double currentDistance = Mathematics.countDistance(y); // Distance from target
      double distanceDifference = Mathematics.calcPulses(Constants.targetDistance) - Mathematics.countDistance(currentDistance); // Difference in distance (error)
      double distanceAdjust = distanceDifference / Constants.navigationTime; // Calculates velocity based on error and max speed
      double steeringAdjust = Constants.angularScaleUp * x; // Creates a side-to-side adjustment based on error
      double velocityRampDown = 1; // Ramp down on velocity based on current lift height
      double maximumVelocity = velocityRampDown * Constants.maxSpeed;
      if (distanceAdjust <= Constants.maxSpeed){ // If speed is less than maximum
        DriveTrain.flyWithWires(RobotMap.starboardMaster, RobotMap.portMaster, steeringAdjust, distanceAdjust); // Drive using adjustment values
      }else{
        DriveTrain.flyWithWires(RobotMap.starboardMaster, RobotMap.portMaster, steeringAdjust, maximumVelocity); // Drive by setting to max speed
      }
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
  public void disabledInit() {
  } 

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void autonomousInit() {
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
} 



  @Override
  public void teleopInit() {
    
  }

  @Override
  public void teleopPeriodic() {
     Scheduler.getInstance().run();
  }
  @Override
  public void testPeriodic() {
  }
}