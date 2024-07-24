package frc.robot;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.util.Color;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LightSubsystem extends SubsystemBase {
    /** Creates a new LimitSwitch. */
    private AddressableLED led = new AddressableLED(9);
    private AddressableLEDBuffer buffer = new AddressableLEDBuffer(56);

    public LightSubsystem() {
        led.setLength(buffer.getLength());
        led.setData(buffer);
        led.start();
    }

    public void setLED(int index, Color color) {
        this.buffer.setLED(index, color);
    }

    public void setColor(Color color) {
        for (int i = 0; i < buffer.getLength(); i++) {
            this.buffer.setLED(i, color);
        }
        this.update();
    }

    public void update() {
        led.setData(buffer);
    }
}
// package frc.robot;

// import edu.wpi.first.wpilibj.AddressableLED;
// import edu.wpi.first.wpilibj.AddressableLEDBuffer;
// import edu.wpi.first.wpilibj.util.Color;
// import edu.wpi.first.wpilibj2.command.SubsystemBase;

// public class LightSubsystem extends SubsystemBase {
// public static final int LIGHTS_LENGTH = 56;
// public static final int HALF_LENGTH = LIGHTS_LENGTH / 2;

// private final AddressableLED led = new AddressableLED(3);
// private final AddressableLEDBuffer buffer = new AddressableLEDBuffer(56);

// public LightSubsystem() {
// led.setLength(this.buffer.getLength());
// led.setData(this.buffer);
// led.start();
// }

// public void setLED(int index, Color color) {
// this.buffer.setLED(index, color);
// }
// }
