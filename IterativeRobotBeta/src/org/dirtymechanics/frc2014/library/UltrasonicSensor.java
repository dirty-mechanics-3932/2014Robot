/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.dirtymechanics.frc2014.library;

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
}
