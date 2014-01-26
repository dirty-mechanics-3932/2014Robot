/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.dirtymechanics.frc2014.robot;


import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Victor;
import org.dirtymechanics.frc2014.library.JaguarPair;
import org.dirtymechanics.frc2014.library.SmartJoystick;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Reuben extends IterativeRobot {
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    
    private final Jaguar jaguar1;
    private final Jaguar jaguar2;
    private final Jaguar jaguar3;
    private final Jaguar jaguar4;
    private final Jaguar jaguar5;
    private final Jaguar jaguar6;
    
    private final JaguarPair leftPair, rightPair;
    
    private final Victor shooterSpinner;
    
    private final Compressor mainCompressor;
    private final DoubleSolenoid doubleTest;
    
    private final SmartJoystick leftStick;
    private final SmartJoystick rightStick;
    private final SmartJoystick gamepad;
    
    private final int distanceInInches;
    
    public Reuben() {
        leftStick = new SmartJoystick(1);
        rightStick = new SmartJoystick(2);
        gamepad = new SmartJoystick(3);
        
        doubleTest = new DoubleSolenoid(1,2);
        mainCompressor = new Compressor(1,1);
        
        jaguar1 = new Jaguar(1);
        jaguar2 = new Jaguar(2);
        jaguar3 = new Jaguar(3);
        jaguar4 = new Jaguar(4);
        jaguar5 = new Jaguar(5);
        jaguar6 = new Jaguar(6);
        
        leftPair = new JaguarPair(new Jaguar[]{jaguar1, jaguar2});
        rightPair = new JaguarPair(new Jaguar[]{jaguar3, jaguar4});
        
        shooterSpinner = new Victor(7);
        
        
        
    }

    
    public void robotInit() {
        
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
        
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}
