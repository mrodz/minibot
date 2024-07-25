package frc.robot;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.Command;

/**
 * <p>
 * A {@link Command} that will cause two rings of color to shoot around the
 * robot
 * at a specified tick speed.
 * <p>
 * This {@link Command} hooks into the robot lifecycle but does not
 * directly get its ticks from the
 * {@link edu.wpi.first.wpilibj2.command.CommandScheduler CommandScheduler},
 * as we cannot directly change its tick speed and thus the speed of the
 * flashing ring is out of our control.
 * 
 * @apiNote This command will never directly terminate.
 */
public class LightCommand extends Command {
    private final LightSubsystem lightSubsystem;
    private Thread movementExecuter;
    private int tickDelayMilliseconds;
    private int index = 0;

    /**
     * Build a {@link LightCommand} with a specified delay
     * 
     * @param lightSubsystem        the underlying subsystem
     * @param tickDelayMilliseconds delay between updating the LED buffer and
     *                              flushing it
     */
    public LightCommand(LightSubsystem lightSubsystem, int tickDelayMilliseconds) {
        this.lightSubsystem = lightSubsystem;
        this.tickDelayMilliseconds = tickDelayMilliseconds;
        this.addRequirements(lightSubsystem);
    }

    /**
     * Build a {@link LightCommand} with a default delay of <code>250ms</code>
     * 
     * @param lightSubsystem the underlying subsystem
     * @see LightCommand#LightCommand(LightSubsystem, int)
     */
    public LightCommand(LightSubsystem lightSubsystem) {
        this(lightSubsystem, 175);
    }

    /**
     * A custom runner that will carry out the LED state updates
     */
    private class CustomLightTiming implements Runnable {
        private final int HALF_LENGTH = LightCommand.this.lightSubsystem.getBufferLength() / 2;
        private final int QUARTER_LENGTH = HALF_LENGTH / 2;

        @Override
        public void run() {
            while (true) {
                for (int i = 0; i < QUARTER_LENGTH; i++) {
                    LightCommand.this.lightSubsystem.setLED((index + i) % HALF_LENGTH, Color.kDarkBlue);
                    LightCommand.this.lightSubsystem.setLED((index + i) % HALF_LENGTH + HALF_LENGTH, Color.kDarkBlue);
                    LightCommand.this.lightSubsystem.setLED((index + i + QUARTER_LENGTH) % HALF_LENGTH, Color.kDimGray);
                    LightCommand.this.lightSubsystem.setLED((index + i + QUARTER_LENGTH) % HALF_LENGTH + HALF_LENGTH,
                            Color.kDimGray);
                }

                try {
                    Thread.sleep(LightCommand.this.tickDelayMilliseconds);
                } catch (InterruptedException e) {
                    // the command was interrupted
                    return;
                }

                LightCommand.this.index = ++LightCommand.this.index % HALF_LENGTH;
                LightCommand.this.lightSubsystem.update();
            }
        }
    }

    /**
     * Initializes a new {@link CustomLightTiming} for every command run to prevent
     * a double-free in the underlying WPILIB C++ code. It then starts the thread
     * and updates {@link #movementExecuter} to store a reference to this thread.
     * 
     * @see Command#initialize()
     */
    @Override
    public void initialize() {
        this.movementExecuter = new Thread(new CustomLightTiming(), "Lights Custom Executor");
        this.movementExecuter.start();
    }

    /**
     * Interrupts {@link #movementExecuter}, which will cause its thread to
     * immediately
     * stop ticking. Thus, the wave LED effect will stop. The lights will stay on
     * frozen.
     */
    @Override
    public void end(boolean interrupted) {
        this.movementExecuter.interrupt();
    }
}
