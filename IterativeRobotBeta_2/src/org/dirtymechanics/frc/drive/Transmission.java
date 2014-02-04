package org.dirtymechanics.frc.drive;

import edu.wpi.first.wpilibj.Relay;
import org.dirtymechanics.frc.control.SmartJoystick;
import org.dirtymechanics.frc.control.SmartJoystick.Button;

/**
 * A class to handle all transmission related logic.
 *
 * @author Daniel Ruess
 */
public class Transmission {

    private final Relay relay;
    private final SmartJoystick joy;
    private boolean highGear;

    /**
     * @param relay The <CODE>Relay</CODE> used for controlling the
     * transmission.
     * @param joy The joystick used for controlling the transmission.
     */
    public Transmission(Relay relay, SmartJoystick joy) {
        this.relay = relay;
        this.joy = joy;
    }

    /**
     * Called once per cycle. Updates the boolean logic handling transmission
     * state.
     */
    public void update() {
        Button button = joy.getButton(1);
        highGear = button.getState();
        if (highGear) {
            relay.set(Relay.Value.kOff);
        } else {
            relay.set(Relay.Value.kForward);
        }
    }

    /**
     * @return The current state of the transmission.
     */
    public boolean isHighGear() {
        return highGear;
    }
}
