/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
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

  /** Tracks a vision target given x and y offset
   * @param heightDifference Difference between height of limelight and height of currently tracked target
   */
  public static void trackTarget(double heightDifference, double x, double y){
    double currentDistance = Mathematics.countDistance(y, heightDifference); // Distance from target
    double distanceDifference = Mathematics.calcPulses(Constants.targetDistance) - Mathematics.calcPulses(currentDistance); // Difference in distance (error)
    double distanceAdjust = distanceDifference / Constants.navigationTime; // Calculates a distance adjustment based on error
    double steeringAdjust = Constants.angularScaleUp * x; // Creates a side-to-side adjustment based on error
    DriveTrain.flyWithWires(RobotMap.starboardMaster, RobotMap.portMaster, steeringAdjust, distanceAdjust); // Drive using adjustment values
  }
}
