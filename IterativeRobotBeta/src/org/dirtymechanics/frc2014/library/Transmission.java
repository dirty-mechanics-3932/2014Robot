package org.dirtymechanics.frc2014.library;

import edu.wpi.first.wpilibj.Relay;
import org.dirtymechanics.frc2014.library.SmartJoystick.Button;

/**
 *
 * @author admin
 */
public class Transmission {
    private final Relay relay;
    private final SmartJoystick joy;
    private boolean state;
    
    public Transmission(Relay relay, SmartJoystick joy) {
        this.relay = relay;
        this.joy = joy;
    }
    
    public void update() {
        Button button = joy.getButton(1);
        state = button.getState();
        if (state) {
            relay.set(Relay.Value.kOff);
        } else {
            relay.set(Relay.Value.kForward);
        }
    }
    
    public boolean getState() {
        return state;
    }
}