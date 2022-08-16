package turtle;

import java.util.ArrayList;
import java.util.List;

import shape.Circle;
import drawing.Canvas;
import geometry.CartesianCoordinate;
import geometry.LineSegment;


/**
 * @author Y3890185
 *
 */
public class Boid extends DynamicTurtle {
	
	private static final int NO_CHANGE = 0;
	private static final double NULL = 0;

	public Boid(Canvas canvas, CartesianCoordinate initPos) {
		super(canvas, initPos);
	}

	/**
	 * @param circlesArray , input a list array with obstacles of type circles
	 */
	public void checkColision(List<Circle> circlesArray) {
		for (Circle circle : circlesArray) {
			//find distance to circle from boid
			LineSegment distanceToCircle = new LineSegment(this.turtleloc, circle.getPosCords());
			if (circle.getRadius() >= distanceToCircle.length()) {
				turn(180);
				move(10);
			}
		}
	}

	/**
	 * @param boids , list of boids on canvas
	 * @param detectionRadius , radius defined by the slider, visual range of a boid
	 * @param cohesionCoef , defined by the slider, how strong the effect of coheasion will be
	 * @return returns the angle by which the boid will turn
	 */
	public double cohesion(List<Boid> boids, int detectionRadius, double cohesionCoef) {

		// array for boids in radius
		List<Boid> boidsInRadius = new ArrayList<>();
		//check 
		boidsInRadius = checkInRadius(boids, detectionRadius);

		// for each item in array, take position and average it,
		double avgX = NULL;
		double avgY = NULL;
		for (Boid dynamicTurtle : boidsInRadius) {
			avgX = avgX + dynamicTurtle.turtleloc.getX();
			avgY = avgY + dynamicTurtle.turtleloc.getY();
		}
		if (!boidsInRadius.isEmpty()) {

			avgX = avgX / boidsInRadius.size();
			avgY = avgY / boidsInRadius.size();
			CartesianCoordinate AveragePos = new CartesianCoordinate(avgX/boidsInRadius.size(),
			avgY/boidsInRadius.size());
			//use arctan to find the angle of the average position
			double xDiff = AveragePos.getX() - turtleloc.getX();
			double yDiff = AveragePos.getY() - turtleloc.getY();
			double coheasionAngle = Math.atan2(yDiff, xDiff);
			double thetaC = (Math.toDegrees(coheasionAngle) - turtleangle);
			//calculate the distance to the average position
			LineSegment distanceToAvg = new LineSegment(turtleloc, AveragePos);
			//create a coeficccient from the distance
			double distanceCoef = 5 / distanceToAvg.length();
			
			double theta = thetaC * cohesionCoef * distanceCoef;
			//return the turning angle
			return (theta);
		}
		return NO_CHANGE;
	}

	/**
	 * @param boids , list of boids on canvas
	 * @param detectionRadius , radius defined by the slider, visual range of a boid
	 * @param seperationCoef , defined by the slider, how strong the effect of seperation will be
	 * @return returns the angle by which the boid will turn
	 */
	public double seperation(List<Boid> boids, int detectionRadius, double seperationCoef) {

		// array for turtles in radius
		List<Boid> boidsInRadius = new ArrayList<>();
		boidsInRadius = checkInRadius(boids, detectionRadius);

		// for each item in array, take position and average it,
		double avgX = NULL;
		double avgY = NULL;
		for (Boid dynamicTurtle : boidsInRadius) {
			avgX = avgX + dynamicTurtle.turtleloc.getX();
			avgY = avgY + dynamicTurtle.turtleloc.getY();
		}

		if (!boidsInRadius.isEmpty()) {
			CartesianCoordinate AveragePos = new CartesianCoordinate(avgX/(boidsInRadius.size()),
					avgY/(boidsInRadius.size()));
			//use arctan to find the angle of the average position
			double xDiff = this.turtleloc.getX() - AveragePos.getX();
			double yDiff = this.turtleloc.getY() - AveragePos.getY();
			double seperationAngle = Math.atan2(yDiff, xDiff);
			double thetaS = Math.toDegrees(seperationAngle) - this.turtleangle;
			//calculate the distance to the average position
			LineSegment distanceToAvg = new LineSegment(this.turtleloc, AveragePos);
			//create a coeficccient from the distance
			double distanceCoef = 5 / distanceToAvg.length();

			double theta = thetaS * seperationCoef * distanceCoef;
			//return the turning angle
			return (theta);
		}

		return NO_CHANGE;
	}

	/**
	 * @param boids , list of boids on canvas
	 * @param detectionRadius , radius defined by the slider, visual range of a boid
	 * @param alignmentCoef , defined by the slider, how strong the effect of seperation will be
	 * @return returns the angle by which the boid will turn
	 */
	public double alignment(List<Boid> boids, int detectionRadius, double alignmentCoef) {
		// array for turtles in radius
		List<Boid> boidsInRadius = new ArrayList<>();
		boidsInRadius = checkInRadius(boids, detectionRadius);
		// for each item in array, take position and average it,
		double avgA = NULL;
		for (Boid dynamicTurtle : boidsInRadius) {
			avgA = avgA + dynamicTurtle.turtleangle;
		}
		if (!boidsInRadius.isEmpty()) {
			//calculate the distance to the average position
			avgA = avgA/boidsInRadius.size();
			double thetaA = avgA - turtleangle;
			double theta = alignmentCoef*thetaA;
			//return the turning angle
			return (theta);
		}
		return NO_CHANGE;
	}

	/**
	 * @param boids , list of boids on canvas
	 * @param detectionRadius , radius defined by the slider, visual range of a boid
	 * @return returns the angle by which the boid will turn
	 */
	public List<Boid> checkInRadius(List<Boid> boids, int detectionRadius) {

		List<Boid> boidsInRadius = new ArrayList<>();
		synchronized (boids) {
			for (Boid boid : boids) {
				LineSegment distance = new LineSegment(this.turtleloc, boid.turtleloc);
				if ((distance.length() < detectionRadius) && (distance.length() > NULL)) {
					boidsInRadius.add(boid);
				}
			}
			return boidsInRadius;
		}
	}
}
