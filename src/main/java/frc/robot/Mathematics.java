package frc.robot;

/** Class for mathematic functions */
public class Mathematics {

    /**  Takes an angle in degrees and calculates a distance in inches
	@return Distance in inches */
	public static double countDistance(double angle){
		double baseAngleRad = Math.toRadians(angle + Constants.cameraAngle); // Convert total camera angle to radians
		double baseAngleTangent = Math.tan(baseAngleRad); // Take the tangent of total angle
		double returnValue = Constants.heightDifference / baseAngleTangent; // Divide to calculate distance
		return returnValue; // Returns current distance in inches
    }
}