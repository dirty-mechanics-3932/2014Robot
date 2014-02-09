package org.dirtymechanics.frc.sensors;

import edu.wpi.first.wpilibj.AnalogChannel;

/**
 *
 * @author System32
 */
public class StringEncoder extends AnalogChannel {

    /**
     * Creates a new <CODE>StringEncoder</CODE> on the default channel 2.
     */
    public StringEncoder() {
        this(2);
    }

    /**
     * Creates a new <CODE>StringEncoder</CODE> on the provided channel.
     *
     * @param channel The channel the encoder is on.
     */
    public StringEncoder(int channel) {
        super(channel);
    }

    /**
     * Gets the distance, in inches, of the string encoder.
     * @return The distance, from 0 - 28.
     */
    public int getDistance() {
        return (int) Math.floor((28D * (getVoltage() / 5D)) + .7);
    }

}
