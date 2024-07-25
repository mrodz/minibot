package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * <p>
 * A {@link SubsystemBase Subsystem} that manages the two drive motors
 */
public class DriveSubsystem extends SubsystemBase {
    private final TalonSRX rightMotor;
    private final TalonSRX leftMotor;

    /**
     * Initialize the {@link SubsystemBase Subsystem}
     */
    public DriveSubsystem() {
        this.rightMotor = new TalonSRX(60);
        this.leftMotor = new TalonSRX(61);
    }

    /**
     * Immediately set the speed of the motors to zero,
     * effectively decelerating the robot.
     */
    public void stop() {
        this.setLeft(0);
        this.setRight(0);
    }

    /**
     * Set the speed of the <b>left</b> motor
     * 
     * @param speed in terms of {@link ControlMode#PercentOutput}
     *              <code>[-1, 1]</code>
     */
    public void setLeft(double speed) {
        this.leftMotor.set(ControlMode.PercentOutput, speed);
    }

    /**
     * Set the speed of the <b>right</b> motor
     * 
     * @param speed in terms of {@link ControlMode#PercentOutput}
     *              <code>[-1, 1]</code>
     */
    public void setRight(double speed) {
        this.rightMotor.set(ControlMode.PercentOutput, speed);
    }
}
