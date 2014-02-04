package org.dirtymechanics.frc.sensors;

import edu.wpi.first.wpilibj.AnalogChannel;

/**
 *
 * @author System32
 */
public class UltrasonicSensor {
    private final AnalogChannel rangefinder;
    private final int SCALING_FACTOR = 512/5*24/23;
    
    public UltrasonicSensor(int portNumber){
        rangefinder = new AnalogChannel(portNumber);
        rangefinder.setOversampleBits(2);
        rangefinder.setAverageBits(5);
    }
    
    public double getInches(){
        double volts = rangefinder.getAverageVoltage();
        return (double) (volts * SCALING_FACTOR);
    }
    
    public double getFeet(){
        return this.getInches() / 12.0;
    }
    
    public String getReadable(){
        return (int)getFeet() + "' " + (int)(getInches() - (int)getFeet()*12) + "\"";
    }
}
