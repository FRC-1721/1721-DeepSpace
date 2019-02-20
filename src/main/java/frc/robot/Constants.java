package frc.robot;

/**
 * Class containing numbers used throughout project - PID values, as well as mathematic constants
 */
public class Constants {
	/**
	 * Which PID slot to pull gains from. Starting 2018, you can choose from
	 * 0,1,2 or 3.
	 */
	public static final int kSlotIdx = 0;

	/**
	 * Talon SRX/ Victor SPX will supported multiple (cascaded) PID loops. For
	 * now we just want the primary one.
	 */
	public static final int kPIDLoopIdx = 0;

    /**
	 * Timeout value generally used in parameter configs
     * Non-zero to block the config until success, zero to skip checking 
     */
    public static final int kTimeoutMs = 30;
    
    /**
	 * Gains used in Current Closed Loop, to be adjusted accordingly
     * Gains(kp, ki, kd, kf, izone, peak output);
     */

	static final Gains kGains = new Gains(0.027, 0.000001, 0.82, 0.0, 0, 1.0);


    /** ---- Flat constants, you should not need to change these ---- */
	/* We allow either a 0 or 1 when selecting a PID Index, where 0 is primary and 1 is auxiliary */
	public final static int PID_PRIMARY = 0;
	public final static int PID_TURN = 1;

	public static final double liftP = 0.025; //0.025
	public static final double liftI = 0.0;
	public static final double liftD = 3.7; //3.7 // 4.15
	public static final double liftF = 0.0;
	public static final int liftIZone = 0;

	public static final double angularScaleUp = 475; // Scale-up for TX error
	public static final double distanceScaleUp = 5; // Scale-up for TY error

	public static final double heightOfCamera = 40.5;// Height  of limelight camera in inches
	public static final double heightOfCalibrationTarget = 28.574; // Height of the center of the low vision target

	public static final double heightOfHighTarget = 35.96; // Height of the center of the high vision target

	public static final double heightDifference = heightOfCamera - heightOfCalibrationTarget; // Difference in heights

	public static final double frameOffset = 10; // Distance between front of frame and limelight
	public static final double lowTargetDistance = 2 + frameOffset; // Low target distance for auto to navigate to, in ghanas
	public static final double highTargetDistance = 58 + frameOffset; // High target distance for auto to navigate to, in ghanas
	public static final double calibrationDistance = 30; // Calibration distance, in ghanas
	
	public static final double turnDampener = 0.8; // Dampener on yaw while driving

	public static final double maxSpeed = 32960; // Max speed for encoders, in pulses

	public static final double pulsesPerRev = 18063.3; // Pulses read for every one wheel rotation

	public static final double wheelCircumference = 12.56; // Circumference of drive wheels

	public static final double navigationTime = 30; // Time allotted to navigate to target, in hundreds of milliseconds

	public static final double pulsesPerInch = 1450.97; // Pulses per inch of navigational tavel

	public static final double pulsesPerLiftInch = 14412; // Pulses per inch of lift travel

	// Heights and distances of targets on the rocket
	public static final double hatchTargetHeight = 0; // Height in inches of the lowest hatch target
	public static final double cargoTargetHeight = 7.5; // Height in inches of the lowest cargo target on the rocket
	public static final double distanceBetweenTargets = 28; // Distance between the centers of subsequent rocket targets
}