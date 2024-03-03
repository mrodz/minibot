package frc.robot;

import java.util.function.Supplier;
import edu.wpi.first.wpilibj2.command.Command;

public class ArcadeDriveCommand extends Command {
    private final DriveSubsystem driveSubsystem;
    private final Supplier<Double> speedFunction, turnFunction;

    public ArcadeDriveCommand(DriveSubsystem driveSubsystem,
            Supplier<Double> speedFunction, Supplier<Double> turnFunction) {
        this.speedFunction = speedFunction;
        this.turnFunction = turnFunction;
        this.driveSubsystem = driveSubsystem;

        this.addRequirements(driveSubsystem);
    }

    public void initialize() {
        System.out.println("ArcadeDriveCommand started!");
    }

    public void execute() {
        double realTimeSpeed = speedFunction.get();
        double realTimeTurn = turnFunction.get();

        double left = realTimeSpeed + realTimeTurn;
        double right = realTimeSpeed - realTimeTurn;

        driveSubsystem.setLeft(left);
        driveSubsystem.setRight(right);
    }

    public void end(boolean interrupted) {
        System.out.println("ArcadeDriveCommand ended!");
    }

    public boolean isFinished() {
        return false;
    }
}
