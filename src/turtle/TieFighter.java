package turtle;

import drawing.Canvas;
import geometry.CartesianCoordinate;

public class TieFighter extends Boid {

	public TieFighter(Canvas canvas, CartesianCoordinate initPos) {
		super(canvas, initPos);

	}

	@Override
	public void draw() {
		// move to edge of hexagon
		move(6);
		putPenDown();
		// draws wing connector
		move(4);
		turn(90);
		// draws wing
		move(8);
		turn(180);
		move(16);
		turn(180);
		move(8);
		turn(90);
		// moves across to draw other wing
		putPenUp();
		move(16);
		putPenDown();
		// draws connector
		move(4);
		turn(90);
		// draws wing
		move(8);
		turn(180);
		move(16);
		turn(180);
		move(8);
		turn(90);
		move(4);
		turn(300);

		// Draws hexagon used as body

		for (int i = 0; i < 6; i++) {
			move(6);
			turn(60);
		}
		turn(60);
		putPenUp();
		move(6);

	}
	@Override
	public void undraw() {
		for (int i = 0; i < 15; i++) {
			canvas.removeMostRecentLine();
		}
		canvas.repaint();
	}
}
