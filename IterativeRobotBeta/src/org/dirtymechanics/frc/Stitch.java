package org.dirtymechanics.frc;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Relay;
import org.dirtymechanics.frc.acuators.DriveTrain;
import org.dirtymechanics.frc.acuators.ScrewPulley;

import org.dirtymechanics.frc.sensors.RotationalEncoder;
import org.dirtymechanics.frc.sensors.StringEncoder;
import org.dirtymechanics.frc.sensors.UltrasonicSensor;
import org.dirtymechanics.frc.sensors.Camera;

/**
 *
 */
public class Stitch extends IterativeRobot {
    
    //Please define meaningful constants for values instead of inlining
    //  numbers in the code.
    public static final int COMPRESSOR_RELAY = 6;
    public static final int COMPRESSOR_PRESSURE_SWITCH_CHANNEL = 1;
    public static final double ARM_POWER_PERCENTAGE = .8;

    private final Compressor compressor;
    private final Joystick leftStick;
    private final Joystick rightStick;
    private final Joystick armController;

    private final Jaguar leftJag;
    private final Jaguar rightJag;
    private final Jaguar screwJag;
    private final Jaguar armJag;

    private final Relay distanceLight;
    private final Relay pneumaticRelay1; //Two pheumatic relays for the shooter that
    private final Relay pneumaticRelay2; //will probably be replaced by a single relay

    private final UltrasonicSensor ultrasonicSensor;
    private final StringEncoder stringEncoder;
    private final RotationalEncoder rotEncoder;
    private final DigitalInput limitSwitch;
    


    //private final Servo cameraUpDown;
    //private final Servo cameraLeftRight;
    private final Camera camera;

    private long lastFireTime;
    private boolean movingTo60;
    private boolean isAutoOn;
    private final ScrewPulley pulley;
    private final Relay tranny;
    private final DriveTrain driveTrain;
    
    private boolean gear = false;
    private boolean gearButtonPressed = false;

    public Stitch() {
        compressor = new Compressor(COMPRESSOR_PRESSURE_SWITCH_CHANNEL, COMPRESSOR_RELAY);
        leftStick = new Joystick(JOYSTICK_PORT_1);
        rightStick = new Joystick(2);
        armController = new Joystick(3);
        ultrasonicSensor = new UltrasonicSensor(1);
        stringEncoder = new StringEncoder();
        rotEncoder = new RotationalEncoder();

        //cameraUpDown = new Servo(1);
        //cameraLeftRight = new Servo(2);
        camera = new Camera();
        camera.StartCamera();

        leftJag = new Jaguar(1);
        rightJag = new Jaguar(2);
        screwJag = new Jaguar(3);
        armJag = new Jaguar(4);
        tranny = new Relay(3);
        
        driveTrain = new DriveTrain(leftJag, rightJag, tranny);

        //Distance light circuit is on Spike 5
        distanceLight = new Relay(5);
        pneumaticRelay1 = new Relay(1);
        pneumaticRelay2 = new Relay(2);
        limitSwitch = new DigitalInput(2);
        pulley = new ScrewPulley(leftJag, stringEncoder);

    }
    public static final int JOYSTICK_PORT_1 = 1;
    

    public void robotInit() {
        compressor.start();
    }

    public void autonomousPeriodic() {
        isAutoOn = isAutonomous();
        camera.autonomousVision(isAutoOn);
    }

    public void teleopPeriodic() {
        //This is why we need SmartJoystick and its ToggleButton
        //Bring back, bring back, bring back my SmartJoystick to me!
        if (rightStick.getRawButton(2) != gearButtonPressed){
            gearButtonPressed = !gearButtonPressed;
            gear = !gear;
        }
        driveTrain.update(leftStick.getX(), rightStick.getX(), gear);
        //leftDrive.set(leftStick.getY());
        //rightDrive.set(rightStick.getY());

        if (leftStick.getRawButton(1)) {
            fireDualPneumatic();
        }
        if (!limitSwitch.get()) {
            fireDualPneumatic();
        }

        adjustFirePower();
        adjustFireAngle();
        /*if(mainTestController.getRawButton(2)) {
         movingTo60 = true;
         }*/
//        if (movingTo60) {
//            goTo60();
//        }
//        
        armJag.set(armController.getY()*ARM_POWER_PERCENTAGE);
        //print current values from all sensors
        SmartDashboard.putString("Distance to target:", ultrasonicSensor.getReadable());
        SmartDashboard.putString("String encoder distance:", "" + stringEncoder.getDistance());
        SmartDashboard.putString("Rotational encoder degrees:", "" + rotEncoder.getDegrees());
        SmartDashboard.putString("Limit switch state:", "" + limitSwitch.get());

        //updates distance lights on robot
        ultrasonicSensor.setLightState(distanceLight);
    }
    

    /**
     * @author Nick This method will most likely be rendered obsolete when we
     * use a single pneumatic to click the seatbelt instead of a dual one.
     */
    private void fireDualPneumatic() {
        if (lastFireTime - System.currentTimeMillis() < -3000) {
            lastFireTime = System.currentTimeMillis();
            pneumaticRelay1.set(Relay.Value.kOn);
            pneumaticRelay2.set(Relay.Value.kForward);
            pneumaticRelay1.set(Relay.Value.kForward);
            pneumaticRelay2.set(Relay.Value.kOn);
        }
    }

    /**
     * @author Nick resets the angle to 60(+-10) degrees
     */
//    private void goTo60() {
//        if (rotEncoder.getDegrees() > 60) {
//            leftJag.set(-.3);
//        } else if (rotEncoder.getDegrees() < 60) {
//            leftJag.set(.3);
//        } else {
//            movingTo60 = false;
//        }
//    }

    /**
     * @author Nick The purpose of this method is to check if whether or not
     * we're above or below 120 or 0 degrees respectively.
     *
     * TODO: RANGE CHECKING ABOVE/BELOW IS NOT WORKING
     */
    private void adjustFireAngle() {
        /*if(!movingTo60) {
         if(mainTestController.getY() < 0) {
         if(rotateEncoder.getDegrees() < 120) {
         rotationalEncoderJaguar.set(mainTestController.getY());
         }
         } else if(mainTestController.getY() > 0) {
         if(rotateEncoder.getDegrees() > 0) {
         rotationalEncoderJaguar.set(mainTestController.getY());
         }
         }
        
         }*/
    }

    /**
     * @author Nick Move the jaguar inside the range of 6-21 as read by the
     * String encoder.
     *
     * TODO: Check whether or not the encoder is in range.
     */
    private void adjustFirePower() {
        //stringEncoderJaguar.set(mainTestController2.getY());
    }
}
