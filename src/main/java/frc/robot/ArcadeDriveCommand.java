package frc.robot;

import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.Command;

public class ArcadeDriveCommand extends Command {
    private final DriveSubsystem driveSubsystem;
    private final DoubleSupplier speedFunction;
    private final DoubleSupplier turnFunction;

    public ArcadeDriveCommand(DriveSubsystem driveSubsystem,
            DoubleSupplier speedFunction, DoubleSupplier turnFunction) {
        this.speedFunction = speedFunction;
        this.turnFunction = turnFunction;
        this.driveSubsystem = driveSubsystem;

        this.addRequirements(driveSubsystem);
    }

    public void initialize() {
        System.out.println("ArcadeDriveCommand started!");
    }

    public void execute() {
        double realTimeSpeed = speedFunction.getAsDouble();
        double realTimeTurn = turnFunction.getAsDouble();

        double left = realTimeSpeed + realTimeTurn;
        double right = realTimeSpeed - realTimeTurn;

        driveSubsystem.setLeft(left);
        driveSubsystem.setRight(right);
    }

    public void end(boolean interrupted) {
        System.out.println("ArcadeDriveCommand ended!");
    }

    /**
     * Because this command will be bound to the subsystem, it
     * should never finish.
     */
    public boolean isFinished() {
        return false;
    }
}
