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
    private final Button[] buttons;

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
                buttons[i].changeState(getRawButton(i));
            }
        }
    }

    /**
     * Skeleton class used to define <CODE>Button</CODE> objects for
     * <CODE>SmartJoystick</CODE>.
     */
    public static abstract class Button {

        /**
         * Called per cycle to update the state of the button from the joystick.
         *
         * @param state The new state of the button.
         */
        protected abstract void update(boolean state);

        /**
         * Called when the button is updated.
         *
         * @param state The new state of the button.
         */
        protected abstract void changeState(boolean state);
    }

    /**
     * Represents a toggle button.
     */
    public static abstract class ToggleButton extends Button {

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
        protected void update(boolean state) {
            if (lastState != state) {
                if (flip++ % 2 == 0) {
                    state = !state;
                    changeState(state);
                }
            }
            lastState = state;
        }

        /**
         * The state of the toggle button, default state is false;
         *
         * @return The current state of the robot.
         */
        public boolean getState() {
            return state;
        }
    }

    public static abstract class StandardButton extends Button {

        /**
         * The last state.
         */
        private boolean lastState;

        /**
         * Updates the state of the switch.
         *
         * @param state The current state of the button.
         */
        protected void update(boolean state) {
            if (lastState != state && state) {
                changeState(state);
            }
            lastState = state;
        }
    }
}
