package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class RobotContainer {
    private final DriveSubsystem driveSubsystem;
    private final CommandXboxController controller;
    private final LightSubsystem lights;

    private final SendableChooser<String> speedChooser;

    public RobotContainer() {
        this.speedChooser = new SendableChooser<>();
        this.speedChooser.setDefaultOption("Speed 3", "3");
        this.speedChooser.addOption("Speed 1", "1");
        this.speedChooser.addOption("Speed 5", "5");
        this.speedChooser.addOption("Speed 10", "10");
        SmartDashboard.putData("Speed Choices", this.speedChooser);

        this.driveSubsystem = new DriveSubsystem();
        this.lights = new LightSubsystem();
        this.controller = new CommandXboxController(0);

        this.driveSubsystem.setDefaultCommand(new ArcadeDriveCommand(driveSubsystem, //
                () -> this.controller.getRawAxis(4) / 3,
                () -> this.controller.getRawAxis(1) / getSpeedDivisor()));

        this.lights.setDefaultCommand(new LightCommand(lights));
    }

    private int getSpeedDivisor() {
        return switch (this.speedChooser.getSelected()) {
            case "3" -> 3;
            case "5" -> 5;
            case "10" -> 10;
            default -> 1;
        };
    }

    public Command getAutonomousCommand() {
        return Commands.print("No autonomous command configured");
    }
}
