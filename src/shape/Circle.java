package shape;

import drawing.Canvas;
import geometry.CartesianCoordinate;

public class Circle extends Shape {
	
	private static final double REF_RADIUS = 57.2958;
	private double radius;
	
	/**
	 * @param canvas , canvas to write on
	 * @param cartesianCoordinate , coordinates position of centre
	 * @param radius , radius of circle to be drawn
	 */
	public Circle(Canvas canvas, CartesianCoordinate cartesianCoordinate, double radius) {
		super(canvas, cartesianCoordinate);
		this.radius = radius;
		// as circle that moves 1px and turns 1deg per loop, circ is 360u, therefore use
		// this to find the circles circumference 
		double calcsize = radius/REF_RADIUS; 
		setSize(calcsize);
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double setradius) {
		radius = setradius;
	}

	@Override
	public void draw() {
		//to move to edge of circle
		turtle.turn(270);
		turtle.move(radius);
		turtle.turn(90);
		canvas.removeMostRecentLine();
		//draws circle
		for (int i = 0; i < 360; i++) {
			turtle.move(size);
			turtle.turn(1);
		}
		
	}

}
