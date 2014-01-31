package org.dirtymechanics.frc.control;

import edu.wpi.first.wpilibj.Joystick;

/**
 * A <CODE>Joystick</CODE> implementation with toggle button logic.
 *
 * @author Daniel Ruess
 */
public class SmartJoystick extends Joystick {

    /**
     * An array of all possible buttons to press.
     */
    private final ToggleButton[] buttons;

    /**
     * @param port The USB port the joystick is connected to.
     */
    public SmartJoystick(int port) {
        super(port);
        buttons = new ToggleButton[15];
    }

    /**
     * Registers a button with the <CODE>Joystick</CODE>
     *
     * @param num The button number.
     * @param button The button.
     */
    public void registerButton(int num, ToggleButton button) {
        if (buttons[num] != null) {
            throw new IllegalArgumentException("Duplicate button registration: " + num);
        }
        buttons[num] = button;
    }

    /**
     * Iterates through all the buttons available and updates the current state.
     */
    public void update() {
        for (int i = 0; i < buttons.length; ++i) {
            if (buttons[i] != null) {
                buttons[i].update(getRawButton(i));
            }
        }
    }

    /**
     * Represents a toggle button.
     */
    public static abstract class ToggleButton {

        /**
         * The current state of the switch.
         */
        private boolean state;

        /**
         * The button's state last update.
         */
        private boolean lastState;

        /**
         * Used to make sure the switch is pressed and released before switching
         * state.
         */
        private int flip = 0;

        /**
         * @param initialState The initial button state;
         */
        public ToggleButton(boolean initialState) {
            this.state = initialState;
        }

        /**
         * Initializes the class with an initial state of false.
         */
        public ToggleButton() {
            this(false);
        }

        /**
         * Updates the state of the switch.
         *
         * @param state The current state of the button.
         */
        private void update(boolean state) {
            if (lastState != state) {
                if (flip++ % 2 == 0) {
                    state = !state;
                    changeState(state);
                }
            }
            lastState = state;
        }
        
        /**
         * Called when the toggle state changes.
         * 
         * @param state The current state.
         */
        protected abstract void changeState(boolean state);

        /**
         * The state of the toggle button, default state is false;
         *
         * @return The current state of the robot.
         */
        public boolean getState() {
            return state;
        }
    }

    public static abstract class StandardButton {
        /**
         * The last state.
         */
        private boolean lastState;
        
        /**
         * The current state.
         */
        private boolean state;

        /**
         * Updates the state of the switch.
         *
         * @param state The current state of the button.
         */
        private void update(boolean state) {
            if (lastState != state && state) {
                changeState(state);
            }
            lastState = state;
        }
        
        /**
         * Called when the toggle state changes.
         * 
         * @param state The current state.
         */
        protected abstract void changeState(boolean state);
    }
}
