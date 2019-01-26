package frc.robot;

/**
 * Simple class containing constants used throughout project
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
	static final Gains kGains = new Gains(0.2, 0.0, 0.0, 0.0, 0, 1.0);

    /** ---- Flat constants, you should not need to change these ---- */
	/* We allow either a 0 or 1 when selecting a PID Index, where 0 is primary and 1 is auxiliary */
	public final static int PID_PRIMARY = 0;
	public final static int PID_TURN = 1;

	public static final float angularP = 0.0415f; //Angular P value
	public static final float accelerationP = 0.013f; // Distance-based P value
	public static final float optimalArea = 1.6f; // Target Area for Limlight

	public static final double heightOfCamera = 22.5; // Height of limelight camera in inches
	public static final double cameraAngle = 5; // Mounting angle of limelight
	public static final double heightOfTarget = 28; // Height of the center of the vision target
	public static final double heightDifference = heightOfTarget - heightOfCamera; // Difference in height between camera and target

	public static final double targetDistance = 90; // Target distance for auto to navigate to

	public static final double powerRequirement = 0.2; // The minimum power neccessary to make the robot move

	
    public static final float minimumAngularCommand = 0.01f; // Maximum acceptable error for angular adjustment
    public static final float minimumRangeCommand = 0.15f; // Maximum acceptable error for distance adjustment
	
	public static final double speedDampener = 1; // Dampener on speed while driving
}
