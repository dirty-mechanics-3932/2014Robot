/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.dirtymechanics.frc.acuators;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Relay;

/**
 *
 * @author System32
 */
public class DriveTrain {
    private final Jaguar left;
    private final Jaguar right;
    private final Relay tranny;

    public DriveTrain(Jaguar left, Jaguar right, Relay tranny) {
        this.left = left;
        this.right = left;
        this.tranny = tranny;
    }
    
    public void update(double leftSpeed, double rightSpeed, boolean gear) {
        left.set(leftSpeed);
        right.set(rightSpeed);
        if (gear) {
            tranny.set(Relay.Value.kOn);
        } else {
            tranny.set(Relay.Value.kOff);
        }
    }
}