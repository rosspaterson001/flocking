package shape;

import drawing.Canvas;
import geometry.CartesianCoordinate;
import turtle.Turtle;

public abstract class Shape {

	protected Turtle turtle;
	protected double size = 100;
	protected double angle =90;
	protected Canvas canvas;
	protected CartesianCoordinate posCords;

	public Shape(Canvas canvas, CartesianCoordinate cartesianCoordinate) {
		this.posCords = cartesianCoordinate;
		this.canvas = canvas;
		turtle = new Turtle(canvas, cartesianCoordinate);
		turtle.putPenDown();
	}

	public CartesianCoordinate getPosCords() {
		return posCords;
	}
	
	
	public abstract void draw();
	
	public double getSize() {
		return size;
	}
	public void setSize(double size) {
		this.size = size;
	}
	public double getAngle() {
		return angle;
	}
	public void setAngle(double angle) {
		this.angle = angle;
	}

		
}
