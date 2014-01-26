package org.dirtymechanics.frc2014.library;

import edu.wpi.first.wpilibj.Jaguar;

public class JaguarPair {
    private final Jaguar[] motors;
    
    /**
     *
     * @param motors
     */
    public JaguarPair(Jaguar[] motors) {
        this.motors = motors;
    }
    
    public void set(double d) {
        for (int i = 0; i < motors.length; ++i) {
            motors[i].set(d);
        }
    }
}