/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * Subsystem for controlling LEDs
 */
public class LEDs extends SubsystemBase {
  public static void setLightColor(SerialPort port){
    DriverStation.Alliance alliance = DriverStation.getInstance().getAlliance();
    if(alliance == DriverStation.Alliance.Red){
      port.writeString("setredsolid");
    }
    if(alliance == DriverStation.Alliance.Blue){
      port.writeString("setbluesolid");
    }
  }
}
