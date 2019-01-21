/**
 * Simple class containing constants used throughout project
 */
package frc.robot;

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

	public static final float angularP = 0.05f; //Angular P value
	public static final float accelerationP = 0.02f; // Distance-based P value
	public static final float optimalArea = 1.6f; // Target Area for Limlight

	public static final double heightOfCamera = 22.5; // Height of limelight camera in inches
	public static final double heightOfTarget = 28;
	public static final double heightDifference = heightOfTarget - heightOfCamera;

	public static final double targetDistance = 90;

	public static double countDistance(double angle){ //take an angle in degrees
		double baseAngleRad = Math.toRadians(angle + 5); //Convert to rad, and add 5 degrees
		double baseAngleTangent = Math.tan(baseAngleRad);
		double returnValue = heightDifference / baseAngleTangent;
		return returnValue; //give it to the camera n target
	}
}
