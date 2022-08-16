package shape;

import drawing.Canvas;
import geometry.CartesianCoordinate;

public class Square extends Shape {
	
	public Square(Canvas canvas, CartesianCoordinate cartesianCoordinate) {
		super(canvas, cartesianCoordinate);
		
	}
	
	@Override
	public void draw() {
		turtle.move(size);
		turtle.turn(90);
		turtle.move(size);
		turtle.turn(90);
		turtle.move(size);
		turtle.turn(90);
		turtle.move(size);
		turtle.turn(90);
	}
	

	

}
