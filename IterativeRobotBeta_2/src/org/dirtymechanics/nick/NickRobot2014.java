/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.dirtymechanics.nick;


import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class NickRobot2014 extends IterativeRobot {
    private Joystick mainController;
    private Jaguar jaguar;
    private Relay relay1;
    private Relay relay2;
    private Compressor compressor;
    
    public NickRobot2014() {
        mainController = new Joystick(1);
        jaguar = new Jaguar(1);
        relay1 = new Relay(1);
        relay2 = new Relay(2);
        compressor = new Compressor(1, 6);
        
    }
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        compressor.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        if(mainController.getRawButton(1)) {
            relay1.set(Relay.Value.kOff);
            relay2.set(Relay.Value.kOff);
        }
        if(mainController.getRawButton(3)) {
            relay1.set(Relay.Value.kOff);
            relay2.set(Relay.Value.kForward);
        } 
        if(mainController.getRawButton(4)) {
            relay2.set(Relay.Value.kOff);
            relay1.set(Relay.Value.kForward);
        }
        
       jaguar.set(mainController.getY());
        
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}
