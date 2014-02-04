package org.dirtymechanics.frc.control;

import edu.wpi.first.wpilibj.Joystick;

/**
 * A <CODE>Joystick</CODE> implementation with toggle button logic.
 *
 * @author Daniel Ruess
 */
public class ToggleJoystick extends Joystick {

    private final ToggleButton[] buttons;

    /**
     * @param port The USB port the joystick is connected to.
     */
    public ToggleJoystick(int port) {
        super(port);
        buttons = new ToggleButton[15];
    }

    public void registerButton(ToggleButton button) {
        if (buttons[button.getNum()] != null) {
            throw new IllegalArgumentException("Duplicate button registration: " + button.getNum());
        }
        buttons[button.getNum()] = button;
    }

    public void updateStates() {
        for (int i = 0; i < buttons.length; ++i) {
            if (buttons[i] != null) {
                buttons[i].update();
            }
        }
    }

    /**
     * Represents a toggle button.
     */
    public class ToggleButton {

        /**
         * The button number.
         */
        private final int num;

        /**
         * The current state of the switch.
         */
        private boolean state;
        
        /**
         * The event to call on flip.
         */
        private final Runnable event;

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
         * @param num The button number.
         * @param initialState The initial button state;
         * @param event The event to call when a button is pressed.
         */
        public ToggleButton(int num, boolean initialState, Runnable event) {
            this.num = num;
            this.state = initialState;
            this.event = event;
        }

        /**
         * Initializes the class with an initial state of false.
         *
         * @param num The button number.
         * @param event The event to call when a button is pressed.
         */
        public ToggleButton(int num, Runnable event) {
            this(num, false, event);
        }

        /**
         * Updates the state of the switch.
         */
        private void update() {
            boolean current = getRawButton(num);

            if (lastState != current) {
                if (flip++ % 2 == 0) {
                    flip();
                }
            }
            lastState = current;
        }
        
        /**
         * Flips the state and calls the event.
         */
        private void flip() {
            if (event != null) {
                event.run();
            }
            state = !state;
        }

        /**
         * @return Gets the id of the button.
         */
        public int getNum() {
            return num;
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
}
