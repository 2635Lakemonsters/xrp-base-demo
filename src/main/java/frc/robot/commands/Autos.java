package frc.robot.commands;

import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.XRPDrivetrain;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;


public class Autos {
    XRPDrivetrain m_dts;
    ArmSubsystem m_as;
    public Autos(XRPDrivetrain dts, ArmSubsystem as){
        m_dts = dts;
        m_as = as;
    }
    public Command goStraightAndServo(){
        return new SequentialCommandGroup(
        new ParallelCommandGroup(
            //RunCommand is an instant command that runs continuously till it ends (1 second)
            new RunCommand(() -> m_dts.arcadeDrive(0.75, 0), m_dts).withTimeout(1),
            new ArmToPoseCommand(m_as, 1)
        ),
        new InstantCommand(()->System.out.println("after parellel command group"))
        // new RunCommand(() -> m_dts.stopMotors(), m_dts)
    );
    }
}
