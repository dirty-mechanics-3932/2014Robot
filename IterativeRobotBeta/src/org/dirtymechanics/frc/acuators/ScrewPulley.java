package org.dirtymechanics.frc.acuators;

import edu.wpi.first.wpilibj.Jaguar;
import org.dirtymechanics.frc.sensors.StringEncoder;

/**
 *
 * @author Dan
 */
public class ScrewPulley {

    private static double SPEED = 0.8D;

    public static int REST = 1;

    private final Jaguar motor;
    private final StringEncoder sensor;
    private int dest;

    public ScrewPulley(Jaguar motor, StringEncoder sensor) {
        this.motor = motor;
        this.sensor = sensor;
    }

    public void setDestination(int dest) {
        this.dest = dest;
    }

    public void update() {
        int pos = sensor.getDistance();
        double speed = 0;
        if (Math.abs(pos - dest) < 1) {
            speed = 0;
        } else if (pos > dest) {
            if (pos - dest < 5) {
                speed = SPEED * (5D / pos - dest);
            } else {
                speed = -1 * SPEED;
            }
        } else if (pos < dest) {
            speed = SPEED;
        }
        motor.set(speed);
    }
}
