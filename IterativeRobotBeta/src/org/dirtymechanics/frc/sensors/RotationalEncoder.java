package org.dirtymechanics.frc.sensors;

import edu.wpi.first.wpilibj.AnalogChannel;

/**
 *
 * @author Daniel Ruess
 */
public class RotationalEncoder extends AnalogChannel {

    /**
     * Creates a new <CODE>StringEncoder</CODE> on the default channel 2.
     */
    public RotationalEncoder() {
        this(3);
    }

    /**
     * Creates a new <CODE>StringEncoder</CODE> on the provided channel.
     *
     * @param channel The channel the encoder is on.
     */
    public RotationalEncoder(int channel) {
        super(channel);
    }
    
    public double getDegrees() {
        double a = truncate(getVoltage() - .45, 2);
        return truncate(120 * (a / 4), 0);
    }
    
    private double truncate(double d, int decimalPoints) {
        double scale = 1;
        for (int i = 0; i < decimalPoints; ++i) {
            scale *= 10;
        }
        int num = (int) (d * scale);
        return num / scale;
    }
}
