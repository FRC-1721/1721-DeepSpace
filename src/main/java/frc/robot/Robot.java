package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.DriveTrain;

public class Robot extends TimedRobot {
  public static OI m_oi;

  Command m_autonomousCommand; //Personalize me!
  SendableChooser<Command> m_chooser = new SendableChooser<>();
  //Init drive motors
  TalonSRX starboardMotor = new TalonSRX(RobotMap.starboardMotorCAN); //Create the talon SRX's
  TalonSRX portMotor = new TalonSRX(RobotMap.portMotorCAN);

  @Override
  public void robotInit() {
    m_oi = new OI();
    // Init joystick and controller
    RobotMap.driverStick = new Joystick(0);
    RobotMap.operatorController = new Joystick(1);
  }

  @Override
  public void robotPeriodic() {
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
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

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */

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

    starboardMotor.configFactoryDefault(); //Set both motor controlers to default
    portMotor.configFactoryDefault();

    /* Config the peak and nominal outputs ([-1, 1] represents [-100, 100]%) */
		starboardMotor.configNominalOutputForward(0, Constants.kTimeoutMs);
		starboardMotor.configNominalOutputReverse(0, Constants.kTimeoutMs);
		starboardMotor.configPeakOutputForward(1, Constants.kTimeoutMs);
    starboardMotor.configPeakOutputReverse(-1, Constants.kTimeoutMs);

    portMotor.configNominalOutputForward(0, Constants.kTimeoutMs);
		portMotor.configNominalOutputReverse(0, Constants.kTimeoutMs);
		portMotor.configPeakOutputForward(1, Constants.kTimeoutMs);
    portMotor.configPeakOutputReverse(-1, Constants.kTimeoutMs);

    /**
		 * Config the allowable closed-loop error, Closed-Loop output will be
		 * neutral within this range. See Table here for units to use: 
         * https://github.com/CrossTheRoadElec/Phoenix-Documentation#what-are-the-units-of-my-sensor
		 */
    starboardMotor.configAllowableClosedloopError(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
    portMotor.configAllowableClosedloopError(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);

		/* Config closed loop gains for Primary closed loop (Current) */
		starboardMotor.config_kP(Constants.kPIDLoopIdx, Constants.kGains.kP, Constants.kTimeoutMs);
		starboardMotor.config_kI(Constants.kPIDLoopIdx, Constants.kGains.kI, Constants.kTimeoutMs);
    starboardMotor.config_kD(Constants.kPIDLoopIdx, Constants.kGains.kD, Constants.kTimeoutMs);
    starboardMotor.config_kF(Constants.kPIDLoopIdx, Constants.kGains.kF, Constants.kTimeoutMs);

    portMotor.config_kP(Constants.kPIDLoopIdx, Constants.kGains.kP, Constants.kTimeoutMs);
		portMotor.config_kI(Constants.kPIDLoopIdx, Constants.kGains.kI, Constants.kTimeoutMs);
    portMotor.config_kD(Constants.kPIDLoopIdx, Constants.kGains.kD, Constants.kTimeoutMs);
    portMotor.config_kF(Constants.kPIDLoopIdx, Constants.kGains.kF, Constants.kTimeoutMs);
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  // Commenting out control for vison testing
  /*if(RobotMap.driverStick.getRawButton(1)){
    starboardMotor.set(ControlMode.Position, -1500);
    portMotor.set(ControlMode.Position, 1500);
  }
  else
  {
    DriveTrain.flyByWire(starboardMotor, portMotor, RobotMap.driverStick);
  }*/
  
  
	// Establish link to limelight - Josh, make this its own class when you get to your computer
  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
	NetworkTableEntry tx = table.getEntry("tx");
	NetworkTableEntry ty = table.getEntry("ty");
  NetworkTableEntry ta = table.getEntry("ta");

	//read values periodically
	double x = tx.getDouble(0.0);
	double y = ty.getDouble(0.0);
  double area = ta.getDouble(0.0);

  float xFloat = (float)x;
  float areaFloat = (float)area;
  
   // Angular correction with limelight when A is held
   if(RobotMap.operatorController.getRawButton(1)){

    float distanceTarget = Constants.accelerationP * (Constants.optimalArea - areaFloat); //Math to create a target value for distance
    float steeringAdjust = Constants.angularP * xFloat; // Math to create a target side-to-side adjustment
    starboardMotor.set(ControlMode.PercentOutput, steeringAdjust + distanceTarget); // Set port motor
    portMotor.set(ControlMode.PercentOutput, steeringAdjust - distanceTarget); // Set starboard motor
  }else{
    DriveTrain.flyByWire(starboardMotor, portMotor, RobotMap.driverStick); // Drive using joystick
  }

	//post to smart dashboard periodically
	SmartDashboard.putNumber("LimelightX", x);
	SmartDashboard.putNumber("LimelightY", y);
  SmartDashboard.putNumber("LimelightArea", area);
  
  //Drive - fix this to only work when PID is not running
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}