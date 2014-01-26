package org.dirtymechanics.frc2014.library;

import edu.wpi.first.wpilibj.Joystick;

/**
 *
 * @author admin
 */
public class SmartJoystick extends Joystick {
    
    private final Button[] buttons;
    
    public SmartJoystick(int id, int toggles[]) {
        super(id);
        this.buttons = new Button[12];
        for (int i = 0; i < toggles.length; ++i) {
            this.buttons[toggles[i]] = new ToggleButton(toggles[i]);
        }
    }

    public SmartJoystick(int id) {
        this(id, new int[] {});
    }
    
    public void update() {
        for (int i = 0; i < buttons.length; ++i) {
            if (buttons[i] != null) {
                buttons[i].update(getRawButton(buttons[i].id));
            }
        }
    }
    
    public Button getButton(int id) {
        return buttons[id] == null? new Button(id, getRawButton(id)) : buttons[id];
    }
    
    public class Button {
        private final int id;
        protected boolean state = false;
        
        private Button(int id, boolean state) {
            this.id = id;
            this.state = state;
        }
        
        private Button(int id) {
            this(id, false);
        }
        
        protected void update(boolean current) {
            state = current;
        }
        
        public boolean getState() {
            return state;
        }
    }
        
    public class ToggleButton extends Button {
        private boolean lastState = false;
        private int flip = 0;
        
        private ToggleButton(int id) {
            super(id);
        }
        
        protected void update(boolean current) {
            if (lastState != current) {
                if (flip++ % 2 == 0) {
                    state = !state;
                }
            }
            lastState = current;
        }
        
        public boolean getState() {
            return state;
        }
    }
}