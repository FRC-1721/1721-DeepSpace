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
	static final Gains kGains = new Gains(0.06, 0.000001, 0.55, 0.0, 0, 1.0); //0.025, 0.00001, 0.005, 0.0, 1, 1.0

    /** ---- Flat constants, you should not need to change these ---- */
	/* We allow either a 0 or 1 when selecting a PID Index, where 0 is primary and 1 is auxiliary */
	public final static int PID_PRIMARY = 0;
	public final static int PID_TURN = 1;

	public static final double liftP = 0.0;
	public static final double liftI = 0.0;
	public static final double liftD = 0.0;
	public static final double liftF = 0.0;
	public static final int liftIZone = 0;

	public static final float angularScaleUp = 350f; // Scale-up for TX error
	public static final double distanceP = 1.5;

	public static final double heightOfCamera = 40.5; //41.6; // Height of limelight camera in inches
	public static final double cameraAngle = 71.33; //71.7; // Mounting angle of limelight
	public static final double heightOfLowTarget = 28.574; // Height of the center of the low vision target
	public static final double heightOfHighTarget = 35.96; // Height of the center of the high vision target

	public static final double lowHeightDifference = heightOfCamera - heightOfLowTarget;

	public static final double distanceScaleUp = 5;

	public static final double frameOffset = 10;
	public static final double targetDistance = 1 + frameOffset; // Target distance for auto to navigate to
	public static final double calibrationDistance = 30;
	
	public static final double turnDampener = 0.8; // Dampener on yaw while driving

	public static final double maxSpeed = 32960; // Max speed for encoders, in pulses

	public static final double pulsesPerRev = 18063.3; // Pulses read for every one wheel rotation

	public static final double wheelCircumference = 12.56; // Circumference of drive wheels

	public static final double navigationTime = 30; // Time allotted to navigate to target, in hundreds of milliseconds

	public static final double pulsesPerInch = 1450.97;

	public static final double pulsesPerLiftInch = 42590.71;

	// Heights and distances of targets on the rocket
	public static final double hatchTargetHeight = 0; // Height in pulses of the lowest hatch target
	public static final double cargoTargetHeight = 100; // Height in pulses of the lowest cargo target on the rocket
	public static final double distanceBetweenTargets = 24000; // Distance between the centers of subsequent rocket targets
}