package org.dirtymechanics.frc;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Servo;

import org.dirtymechanics.frc.control.SmartJoystick;
import org.dirtymechanics.frc.sensors.RotationalEncoder;
import org.dirtymechanics.frc.sensors.StringEncoder;
import org.dirtymechanics.frc.sensors.UltrasonicSensor;
import org.dirtymechanics.frc.sensors.Camera;

/**
 *
 */
public class Stitch extends IterativeRobot {
    
    private final Compressor compressor;
    private final SmartJoystick leftStick;
    private final SmartJoystick rightStick;
    private final SmartJoystick armController;
    
    /* A regular jostick to use for testing instead of Dan's SmartJoysticks*/
    private final Joystick mainTestController;
    private final Joystick mainTestController2;
    
    private final Jaguar rotationalEncoderJaguar;
    private final Jaguar stringEncoderJaguar;
    private final Jaguar armDrive;
    
    private final Relay distanceLight;
    private final Relay pneumaticRelay1; //Two pheumatic relays for the shooter that
    private final Relay pneumaticRelay2; //will probably be replaced by a single relay
    
    private final UltrasonicSensor ultrasonicSensor;
    private final StringEncoder stringEncoder;
    private final RotationalEncoder rotateEncoder;
    private final DigitalInput limitSwitch;
    
    private final Servo cameraUpDown;
    private final Servo cameraLeftRight;
    private final Camera camera;
    
    private long lastFireTime;
    private boolean movingTo60;
    private boolean isAutoOn;
    
    public Stitch() {
        compressor = new Compressor(1, 6);
        leftStick = new SmartJoystick(1);
        rightStick = new SmartJoystick(2);
        armController = new SmartJoystick(3);
        mainTestController = new Joystick(1);
        mainTestController2 = new Joystick(2);
        ultrasonicSensor = new UltrasonicSensor(1);
        stringEncoder = new StringEncoder();
        rotateEncoder = new RotationalEncoder();
        
        cameraUpDown = new Servo(1);
        cameraLeftRight = new Servo(2);
        camera = new Camera();
        camera.StartCamera();
        
        rotationalEncoderJaguar = new Jaguar(1);
        stringEncoderJaguar = new Jaguar(2);
        armDrive = new Jaguar(3);
        
        //Distance light circuit is on Spike 5
        distanceLight = new Relay(5);
        pneumaticRelay1 = new Relay(1);
        pneumaticRelay2 = new Relay(2);
        limitSwitch = new DigitalInput(2);
        
    }

    public void robotInit() {
        compressor.start();
    }

    public void autonomousPeriodic()
    {
        isAutoOn = isAutonomous();
        camera.autonomousVision(isAutoOn);
    }
    
    public void teleopPeriodic() {
        /**update drivetrain and arm
         * 
         * TODO: these use Dan's smartJoystick methods that confuse the hell
         * out of me.
         * 
        leftStick.update();
        rightStick.update();
        leftDrive.set(leftStick.getY());
        rightDrive.set(rightStick.getY());
        armDrive.set(armController.getY());
        **/
        
        if(mainTestController.getRawButton(1)) {
            fireDualPneumatic();
        }
        if(!limitSwitch.get()){
            fireDualPneumatic();
        }
        
        adjustFirePower();
        adjustFireAngle();
        if(mainTestController.getRawButton(2)) {
            movingTo60 = true;
        }
        if(movingTo60) {
            goTo60();
        }
        //print current values from all sensors
        SmartDashboard.putString("Distance to target: ", ultrasonicSensor.getReadable());
        SmartDashboard.putString("String encoder distance: ", "" + stringEncoder.getDistance());
        SmartDashboard.putString("Rotational encoder degrees: ", "" + rotateEncoder.getDegrees());
        
        //updates distance lights on robot
        ultrasonicSensor.setLightState(distanceLight);
    }
    
    /** 
     * @author Nick
     * This method will most likely be rendered obsolete when we use a single
     *  pneumatic to click the seatbelt instead of a dual one.
     */
    private void fireDualPneumatic() {
        if(lastFireTime - System.currentTimeMillis() < -3000) {
            lastFireTime = System.currentTimeMillis();
            pneumaticRelay1.set(Relay.Value.kOn);
            pneumaticRelay2.set(Relay.Value.kForward);
            Timer.delay(0.05);
            pneumaticRelay1.set(Relay.Value.kForward);
            pneumaticRelay2.set(Relay.Value.kOn);
        }
    }
    
    /**
     * @author Nick
     * resets the angle to 60(+-10) degrees
     */
    private void goTo60() {
        if(rotateEncoder.getDegrees() > 60) {
            rotationalEncoderJaguar.set(-.3);
        } else if(rotateEncoder.getDegrees() < 60) {
            rotationalEncoderJaguar.set(.3);
        } else {
            movingTo60 = false;
        }
    }
    
    /**
     * @author Nick
     * The purpose of this method is to check if whether or not we're above
     * or below 120 or 0 degrees respectively.
     * 
     * TODO: RANGE CHECKING ABOVE/BELOW IS NOT WORKING
     */
    private void adjustFireAngle() {
        if(!movingTo60) {
            if(mainTestController.getY() < 0) {
                if(rotateEncoder.getDegrees() < 120) {
                    rotationalEncoderJaguar.set(mainTestController.getY());
                }
            } else if(mainTestController.getY() > 0) {
                if(rotateEncoder.getDegrees() > 0) {
                    rotationalEncoderJaguar.set(mainTestController.getY());
                }
            }
        
        }
    }
    
    /**
     * @author Nick
     * Move the jaguar inside the range of 6-21 as read by
     * the String encoder.
     * 
     * TODO: Check whether or not the encoder is in range.
     */
    private void adjustFirePower() {
        stringEncoderJaguar.set(mainTestController2.getY());
    }
}
