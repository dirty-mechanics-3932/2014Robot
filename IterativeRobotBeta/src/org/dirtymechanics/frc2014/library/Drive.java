package org.dirtymechanics.frc2014.library;

import org.dirtymechanics.frc2014.library.JaguarPair;

public class Drive {
    private final JaguarPair left;
    private final JaguarPair right;
    private final Transmission tran;
    private final SmartJoystick leftStick;
    private final SmartJoystick rightStick;
    
    public Drive(JaguarPair left, JaguarPair right, Transmission tran,
            SmartJoystick leftStick, SmartJoystick rightStick) {
        this.left = left;
        this.right = right;
        this.tran = tran;
        this.leftStick = leftStick;
        this.rightStick = rightStick;
    }
    
    public void update() {
        tran.update();
        left.set(leftStick.getY());
        right.set(rightStick.getY());
    }
}