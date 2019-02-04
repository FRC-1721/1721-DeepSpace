/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.Mathematics;
import frc.robot.RobotMap;

/**
 * Class for vision tracking commands from the limelight
 */
public class LimeLight extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  /** Tracks a vision target given x and y offset when a button is held
   * @param heightDifference Difference between height of limelight and height of currently tracked target
   */
  public static void trackTarget(double x, double y, double heightDifference, double hasTarget, int button){
    if(RobotMap.operatorController.getRawButton(button) && hasTarget == 1.0){
      double currentDistance = Mathematics.countDistance(y, heightDifference); // Distance from target
      double distanceDifference = Mathematics.calcPulses(Constants.targetDistance) - Mathematics.calcPulses(currentDistance); // Difference in distance (error)
      double distanceAdjust = distanceDifference / Constants.navigationTime; // Calculates a distance adjustment based on error
      double steeringAdjust = Constants.angularScaleUp * x; // Creates a side-to-side adjustment based on error
      SmartDashboard.putNumber("Distance adjust", distanceAdjust);
      DriveTrain.flyWithWires(RobotMap.starboardMaster, RobotMap.portMaster, steeringAdjust, distanceAdjust); // Drive using adjustment values
    }else{
      DriveTrain.flyByWire(RobotMap.starboardMaster, RobotMap.portMaster, RobotMap.driverStick, RobotMap.gearShifter); // Drive using joystick when A is not held
    }
  }
}
