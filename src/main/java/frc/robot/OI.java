/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.cargoMode;
import frc.robot.commands.expandIris;
import frc.robot.commands.extendIris;
import frc.robot.commands.manualZero;
import frc.robot.commands.upOne;
import frc.robot.commands.upTwo;
import frc.robot.commands.zeroLift;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  Joystick controller = new Joystick(RobotMap.controllerPort);
  Button irisExpandButton = new JoystickButton(controller, RobotMap.irisExpandButton);
  Button irisExtendButton = new JoystickButton(controller, RobotMap.irisExtendButton);
  Button zeroButton = new JoystickButton(controller, RobotMap.hatchModeButton);
  Button cargoButton = new JoystickButton(controller, RobotMap.cargoModeButton);
  Button upOneButton = new JoystickButton(controller, RobotMap.upOneButton);
  Button upTwoButton = new JoystickButton(controller, RobotMap.upTwoButton);
  Button manualZeroButton = new JoystickButton(controller, RobotMap.manualZeroButton);

  public OI(){
    irisExpandButton.whenPressed(new expandIris());
    irisExtendButton.whenPressed(new extendIris());
    zeroButton.whenPressed(new zeroLift());
    cargoButton.whenPressed(new cargoMode());
    upOneButton.whenPressed(new upOne());
    upTwoButton.whenPressed(new upTwo());
    manualZeroButton.whenPressed(new manualZero());
  }
  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);

  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());
}
