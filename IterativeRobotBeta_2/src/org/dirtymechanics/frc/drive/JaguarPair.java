package org.dirtymechanics.frc.drive;

import edu.wpi.first.wpilibj.Jaguar;

/**
 * A class used to run two Jaguars as a pair (esp. for the transmission).
 *
 * @author Daniel Ruess
 */
public class JaguarPair {

    private final Jaguar motorA;
    private final Jaguar motorB;

    /**
     * @param motorA The first motor in the pair.
     * @param motorB The second motor in the pair.
     */
    public JaguarPair(Jaguar motorA, Jaguar motorB) {
        this.motorA = motorA;
        this.motorB = motorB;
    }

    /**
     * Sets the speed for the jaguar pair.
     *
     * @param speed The speed.
     */
    public void set(double speed) {
        motorA.set(speed);
        motorB.set(speed);
    }
}
