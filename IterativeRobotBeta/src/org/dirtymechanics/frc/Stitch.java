package org.dirtymechanics.frc;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.dirtymechanics.frc.control.SmartJoystick;
import org.dirtymechanics.frc.sensors.RotationalEncoder;
import org.dirtymechanics.frc.sensors.StringEncoder;
import org.dirtymechanics.frc.sensors.UltrasonicSensor;

/**
 *
 */
public class Stitch extends IterativeRobot {

    private final Compressor compressor;

    private final SmartJoystick leftStick;
    private final SmartJoystick rightStick;
    private final SmartJoystick armController;

    private final Jaguar leftDrive;
    private final Jaguar rightDrive;
    private final Jaguar armDrive;

    //private final UltrasonicSensor ultrasonicSensor;
    private final StringEncoder stringEncoder;
    private final RotationalEncoder rotEncoder;

    public Stitch() {

        compressor = new Compressor(1, 6);

        leftStick = new SmartJoystick(1);
        rightStick = new SmartJoystick(2);

        armController = new SmartJoystick(3);
        //ultrasonicSensor = new UltrasonicSensor(1);
        stringEncoder = new StringEncoder();
        rotEncoder = new RotationalEncoder();

        leftDrive = new Jaguar(1);
        rightDrive = new Jaguar(2);
        armDrive = new Jaguar(3);
    }

    public void robotInit() {
        compressor.start();
    }

    public void teleopPeriodic() {
        leftStick.update();
        rightStick.update();
        leftDrive.set(leftStick.getY());
        rightDrive.set(rightStick.getY());
        armDrive.set(armController.getY());
        //SmartDashboard.putString("Distance to target:", ultrasonicSensor.getReadable());
        SmartDashboard.putString("String Encoder dist:", "" + stringEncoder.getDistance());
        SmartDashboard.putString("Rot Encoder degrees:", "" + rotEncoder.getDegrees());
    }
}
