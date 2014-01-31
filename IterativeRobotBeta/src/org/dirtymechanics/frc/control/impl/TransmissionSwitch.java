package org.dirtymechanics.frc.control.impl;

import edu.wpi.first.wpilibj.Relay;
import org.dirtymechanics.frc.control.SmartJoystick.ToggleButton;

/**
 * @author Daniel Ruess
 */
public class TransmissionSwitch extends ToggleButton {

    private final Relay relay;

    public TransmissionSwitch(Relay relay) {
        super(true);
        this.relay = relay;
    }

    public void changeState(boolean state) {
        if (state) {
            relay.set(Relay.Value.kOff);
        } else {
            relay.set(Relay.Value.kForward);
        }
    }
}
