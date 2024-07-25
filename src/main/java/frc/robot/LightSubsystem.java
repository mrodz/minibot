package frc.robot;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.util.Color;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * <p>
 * A {@link SubsystemBase Subsystem} that manages the LED ring
 * around the base of the robot.
 */
public class LightSubsystem extends SubsystemBase {
    private AddressableLED led = new AddressableLED(9);
    private AddressableLEDBuffer buffer = new AddressableLEDBuffer(56);

    /**
     * Initialize the subsystem
     */
    public LightSubsystem() {
        this.led.setLength(buffer.getLength());
        this.led.setData(buffer);
        this.led.start();
    }

    /**
     * Set the {@link Color} of an LED at an index on the strip
     * 
     * @param index the zero-based index into the LED strip
     * @param color the color to be displayed
     */
    public void setLED(int index, Color color) {
        this.buffer.setLED(index, color);
    }

    /**
     * Set the {@link Color} of every LED on the strip
     * 
     * @param color the color to be displayed
     */
    public void setColor(Color color) {
        for (int i = 0; i < buffer.getLength(); i++) {
            this.buffer.setLED(i, color);
        }
        this.update();
    }

    /**
     * Write the buffer's content to the LED strip via PWM. The buffer is not
     * cleared.
     * 
     * @apiNote This is an expensive operation. Prefer using a buffered write.
     */
    public void update() {
        this.led.setData(buffer);
    }

    /**
     * Get the numbers of addressable LEDs supported by this {@link SubsystemBase
     * Subsystem}
     * 
     * @return the number of addressable LEDs
     */
    public int getBufferLength() {
        return this.buffer.getLength();
    }
}
