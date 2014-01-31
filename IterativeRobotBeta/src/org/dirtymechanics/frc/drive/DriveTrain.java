package org.dirtymechanics.frc.drive;

import org.dirtymechanics.frc.control.OldSmartJoystick;
import org.dirtymechanics.frc.drive.Transmission;
import org.dirtymechanics.frc.drive.JaguarPair;

public class DriveTrain {
    private final JaguarPair left;
    private final JaguarPair right;
    private final Transmission tran;
    private final OldSmartJoystick leftStick;
    private final OldSmartJoystick rightStick;
    
    public DriveTrain(JaguarPair left, JaguarPair right, Transmission tran,
            OldSmartJoystick leftStick, OldSmartJoystick rightStick) {
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