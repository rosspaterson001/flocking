package turtle;

import drawing.Canvas;
import geometry.CartesianCoordinate;

public class Turtle {
	protected Canvas canvas;
	private boolean pendown = false;
	protected CartesianCoordinate turtleloc;
	protected double turtleangle = 0;
	
	public Turtle(Canvas myCanvas, CartesianCoordinate initPos) {
		this.canvas = myCanvas;
		this.turtleloc = initPos;
	}

	/**
	 * The turtle is moved in its current direction for the given number of pixels. 
	 * If the pen is down when the robot moves, a line will be drawn on the floor.
	 * 
	 * @param i The number of pixels to move.
	 */
	public void move(double i) {
		double newx , newy;
		newx = (i*Math.cos(Math.toRadians(turtleangle))+turtleloc.getX());
		newy = (i*Math.sin(Math.toRadians(turtleangle))+turtleloc.getY());
		CartesianCoordinate newxy = new CartesianCoordinate(newx,newy);
		if (pendown) {
			canvas.drawLineBetweenPoints(turtleloc, newxy);
		}
		turtleloc = newxy;
	}

	/**
	 * Rotates the turtle clockwise by the specified angle in degrees.
	 * 
	 * @param i The number of degrees to turn.
	 */
	public void turn(double i) {
		
		turtleangle = (turtleangle + i) % 360;
		
	}
	/**
	 * Moves the pen off the canvas so that the turtle’s route isn’t drawn for any subsequent movements.
	 */
	public void putPenUp() {
		pendown = false;
		
	}

	/**
	 * Lowers the pen onto the canvas so that the turtle’s route is drawn.
	 */
	public void putPenDown() {
		pendown = true;
		
	}
	/**
	 * Draws a simple equilateral triangle shape facing the direction of travel
	 */
	public void draw() {
		putPenDown();
		turn(150);
		move(15);
		turn(120);
		move(15);
		turn(120);
		move(15);
		turn(330);
		putPenUp();
	}

	/**
	 * removes the three most recent lines and repaints the canvas to remove a boid
	 */
	public void undraw() {
		canvas.removeMostRecentLine();
		canvas.removeMostRecentLine();
		canvas.removeMostRecentLine();
		canvas.repaint();
	}

	
	/**
	 * @param x
	 * , double, changes x position of boid
	 * @param y
	 * , double, changes y position of boid
	 */
	public void setPos(double x, double y) {
		CartesianCoordinate newpos = new CartesianCoordinate(x,y);
		this.turtleloc = newpos;
	}
	

}
