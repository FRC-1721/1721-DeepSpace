package frc.robot;

/** Class for mathematic functions */
public class Mathematics {

    /**  Takes an angle in degrees and calculates a distance in inches
	@return Distance in inches */
	public static double countDistance(double angle, double heightOfTarget){
		double heightDifference = Constants.heightOfCamera - heightOfTarget;
		double baseAngleRad = calibrate(Constants.calibrationDistance, heightDifference) + Math.toRadians(angle); // Convert total camera angle to radians
		double baseAngleTangent = Math.tan(baseAngleRad); // Take the tangent of total angle
		double returnValue = heightDifference * baseAngleTangent; // Divide to calculate distance
		return returnValue; // Returns current distance in inches
	}
	
	/** Takes a distances in inches and calculates a target in encoder pulses 
	@return Encoder pulses
	*/
	public static double calcPulses(double targetDistance){
		double revs = targetDistance / Constants.wheelCircumference;
		double pulses = revs * Constants.pulsesPerRev;
		return pulses;
	}

	public static double calibrate(double distance, double heightDifference){
		double inverseTangent = Math.atan(distance / heightDifference);
		return inverseTangent;
	}
}