/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;
import frc.robot.*;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;


/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class PID extends Subsystem {

  TalonSRX starboardMotor = new TalonSRX(RobotMap.starboardMotorCAN); //Create the talon SRX's
  TalonSRX portMotor = new TalonSRX(RobotMap.portMotorCAN);

  public /*static*/ void populateValues(){
    starboardMotor.configFactoryDefault(); //Set both motor controlers to default
    portMotor.configFactoryDefault();

    /* Config the peak and nominal outputs ([-1, 1] represents [-100, 100]%) */
		starboardMotor.configNominalOutputForward(0, Constants.kTimeoutMs);
		starboardMotor.configNominalOutputReverse(0, Constants.kTimeoutMs);
		starboardMotor.configPeakOutputForward(1, Constants.kTimeoutMs);
    starboardMotor.configPeakOutputReverse(-1, Constants.kTimeoutMs);

    /**
		 * Config the allowable closed-loop error, Closed-Loop output will be
		 * neutral within this range. See Table here for units to use: 
         * https://github.com/CrossTheRoadElec/Phoenix-Documentation#what-are-the-units-of-my-sensor
		 */
		starboardMotor.configAllowableClosedloopError(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);

		/* Config closed loop gains for Primary closed loop (Current) */
		starboardMotor.config_kP(Constants.kPIDLoopIdx, Constants.kGains.kP, Constants.kTimeoutMs);
		starboardMotor.config_kI(Constants.kPIDLoopIdx, Constants.kGains.kI, Constants.kTimeoutMs);
    starboardMotor.config_kD(Constants.kPIDLoopIdx, Constants.kGains.kD, Constants.kTimeoutMs);
    starboardMotor.config_kF(Constants.kPIDLoopIdx, Constants.kGains.kF, Constants.kTimeoutMs);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
