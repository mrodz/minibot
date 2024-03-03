package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveSubsystem extends SubsystemBase {
    TalonSRX rightMotor = new TalonSRX(60);
    TalonSRX leftMotor = new TalonSRX(61);

    public DriveSubsystem() {
    }

    public void stop() {
        this.setLeft(0);
        this.setRight(0);
    }

    public void setLeft(double speed) {
        this.leftMotor.set(ControlMode.PercentOutput, speed);
    }

    public void setRight(double speed) {
        this.rightMotor.set(ControlMode.PercentOutput, speed);
    }
}