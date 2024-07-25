package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

/**
 * The main robot container for the command-based robot.
 */
public class RobotContainer {
    /*
     * SUBSYSTEMS
     */
    private final DriveSubsystem driveSubsystem;
    private final LightSubsystem lights;

    /*
     * CONTROLLERS
     */
    private final CommandXboxController controller;
    private final SendableChooser<Integer> speedChooser;

    /**
     * Used by {@link Robot}
     */
    public RobotContainer() {
        this.speedChooser = new SendableChooser<>();
        this.setupSpeedChooser();

        this.driveSubsystem = new DriveSubsystem();
        this.lights = new LightSubsystem();
        this.controller = new CommandXboxController(0);

        this.driveSubsystem.setDefaultCommand(new ArcadeDriveCommand(driveSubsystem,
                () -> this.controller.getRawAxis(4) / 3,
                () -> this.controller.getRawAxis(1) / this.speedChooser.getSelected()));

        this.lights.setDefaultCommand(new LightCommand(lights));
    }

    /**
     * Initialize a chooser for limits on the forward drive speed of the robot. 
     */
    private void setupSpeedChooser() {
        this.speedChooser.setDefaultOption("Speed 3", 3);
        this.speedChooser.addOption("Speed 1", 1);
        this.speedChooser.addOption("Speed 5", 5);
        this.speedChooser.addOption("Speed 10", 10);
        SmartDashboard.putData("Speed Choices", this.speedChooser);
    }

    public Command getAutonomousCommand() {
        return Commands.print("No autonomous command configured");
    }
}
