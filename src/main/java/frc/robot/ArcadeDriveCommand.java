package frc.robot;

import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.Command;

/**
 * <p>
 * A {@link Command} that continually reads values from two
 * {@link DoubleSupplier}s to
 * indicate the steer and speed of the robot.
 * <p>
 * 
 * @apiNote This command will never directly terminate.
 */
public class ArcadeDriveCommand extends Command {
    private final DriveSubsystem driveSubsystem;
    private final DoubleSupplier speedFunction;
    private final DoubleSupplier turnFunction;

    /**
     * Initialize the drive command
     * 
     * @param driveSubsystem the underlying subsystem
     * @param speedFunction  a function that provides the speed of the robot
     * @param turnFunction   a function that provides the turn of the robot
     */
    public ArcadeDriveCommand(DriveSubsystem driveSubsystem,
            DoubleSupplier speedFunction, DoubleSupplier turnFunction) {
        this.speedFunction = speedFunction;
        this.turnFunction = turnFunction;
        this.driveSubsystem = driveSubsystem;

        this.addRequirements(driveSubsystem);
    }

    @Override
    public void initialize() {
        System.out.println("ArcadeDriveCommand started!");
    }

    @Override
    public void execute() {
        double realTimeSpeed = speedFunction.getAsDouble();
        double realTimeTurn = turnFunction.getAsDouble();

        double left = realTimeSpeed + realTimeTurn;
        double right = realTimeSpeed - realTimeTurn;

        driveSubsystem.setLeft(left);
        driveSubsystem.setRight(right);
    }

    @Override
    public void end(boolean interrupted) {
        System.out.println("ArcadeDriveCommand ended!");
    }
}
