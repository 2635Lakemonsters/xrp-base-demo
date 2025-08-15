// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.XRPDrivetrain;
import frc.robot.commands.ArmToPoseCommand;
import frc.robot.commands.Autos;;


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  public final static XboxController m_controller = new XboxController(0);


  //keyboard A & D = Axis 0, W & S = Axis 1, E & R (throttle) = Axis 2, Button 1 = Z, Button 2 = X, Button 3 = C, Button 4 = V
  public final static Joystick m_keyboard = new Joystick(1);

  // The robot's subsystems and commands are defined here...
  private final XRPDrivetrain m_xrpDrivetrain = new XRPDrivetrain();
  private final ArmSubsystem m_armSubsystem = new ArmSubsystem();
  
  private final Autos m_autos = new Autos(m_xrpDrivetrain, m_armSubsystem);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link edu.wpi.first.wpilibj.GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    JoystickButton posZeroXbox = new JoystickButton(m_controller, XboxController.Button.kA.value);
    JoystickButton posOneXbox = new JoystickButton(m_controller, XboxController.Button.kY.value);
    
    JoystickButton posZeroKeyboard = new JoystickButton(m_keyboard, 1);
    JoystickButton posOneKeyboard = new JoystickButton(m_keyboard, 2);



    //You can do servo by angle as well but general .setPosition is between 0-1 (got those limits from experimentation)
    posZeroXbox.onTrue(new ArmToPoseCommand(m_armSubsystem, 0));
    posOneXbox.onTrue(new ArmToPoseCommand(m_armSubsystem, 0.5));
    
    posZeroKeyboard.onTrue(new ArmToPoseCommand(m_armSubsystem, 0));
    posOneKeyboard.onTrue(new ArmToPoseCommand(m_armSubsystem, 0.5));


  
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autos.goStraightAndServo();
  }
}
