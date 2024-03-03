package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class RobotContainer {
  private final DriveSubsystem driveSubsystem;
  private final CommandXboxController controller = new CommandXboxController(0);

  public RobotContainer() {
    this.driveSubsystem = new DriveSubsystem();

    this.driveSubsystem.setDefaultCommand(new ArcadeDriveCommand(driveSubsystem, //
        () -> this.controller.getRawAxis(4) / 3,
        () -> this.controller.getRawAxis(1)));

    configureBindings();
  }

  private void configureBindings() {
    // none
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
