package frc.robot;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.Command;

public class LightCommand extends Command {
    private final LightSubsystem lightSubsystem;
    private Thread movementExecuter;
    private int index = 0;

    public LightCommand(LightSubsystem lightSubsystem) {
        this.lightSubsystem = lightSubsystem;
        this.addRequirements(lightSubsystem);
    }

    @Override
    public void initialize() {
        this.movementExecuter = new Thread(() -> {
            while (true) {
                for (var i = 0; i < 14; i++) {
                    this.lightSubsystem.setLED((index + i) % 28, Color.kDarkBlue);
                    this.lightSubsystem.setLED((index + i) % 28 + 28, Color.kDarkBlue);
                    this.lightSubsystem.setLED((index + i + 14) % 28, Color.kDimGray);
                    this.lightSubsystem.setLED((index + i + 14) % 28 + 28, Color.kDimGray);
                }
                try {
                    Thread.sleep(175);
                } catch (InterruptedException e) {
                    // the command was interrupted
                    break;
                }
                LightCommand.this.index = ++index % 28;
                LightCommand.this.lightSubsystem.update();
            }
        }, "Lights Custom Executor");
        this.movementExecuter.start();
    }

    @Override
    public void end(boolean interrupted) {
        this.movementExecuter.interrupt();
    }
}
