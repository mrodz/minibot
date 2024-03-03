package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class RobotContainer {
    private final DriveSubsystem driveSubsystem;
    private final CommandXboxController controller;

    public RobotContainer() {
        this.driveSubsystem = new DriveSubsystem();
        this.controller = new CommandXboxController(0);

        this.driveSubsystem.setDefaultCommand(new ArcadeDriveCommand(driveSubsystem, //
                () -> this.controller.getRawAxis(4) / 3,
                () -> this.controller.getRawAxis(1)));
    }

    public Command getAutonomousCommand() {
        return Commands.print("No autonomous command configured");
    }
}
