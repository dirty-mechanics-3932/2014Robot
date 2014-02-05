package org.dirtymechanics.frc.sensors;

import edu.wpi.first.wpilibj.AnalogChannel;

/**
 *
 * @author Zach Sussman
 * 
 */
 
public class UltrasonicSensor {
    
    // The handle to access the sensor
    private final AnalogChannel rangefinder;
    
    // The scaling factor:  distance in inches = volts returned / SCALING_FACTOR
    private final int SCALING_FACTOR = 512/5*24/23;
    
    /** Creates a new ultrasonic sensor hooked up to <code>portNumber</code> on the analog breakout.
     * @params portNumber The port number on the breakout.
     */
    public UltrasonicSensor(int portNumber){
        rangefinder = new AnalogChannel(portNumber);
        rangefinder.setOversampleBits(2); // Completely arbitrary
        rangefinder.setAverageBits(5); // Ditto
    }
    
    /** Returns the distance measured in inches.  */
    public double getInches(){
        double volts = rangefinder.getAverageVoltage();
        return (double) (volts * SCALING_FACTOR);
    }
    
    /** Returns the distance measured in feet.  */
    public double getFeet(){
        return this.getInches() / 12.0;
    }
    
    /** Returns the distance measured as "feet<code>'</code> inches<code>"</code>".  */
    
    public String getReadable(){
        return (int)getFeet() + "' " + (int)(getInches() - (int)getFeet()*12) + "\"";
    }
}
