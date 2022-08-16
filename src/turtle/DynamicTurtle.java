package turtle;

import drawing.Canvas;
import geometry.CartesianCoordinate;

public class DynamicTurtle extends Turtle {
	protected int speed = 100;

	public DynamicTurtle(Canvas myCanvas, CartesianCoordinate initPos) {
		super(myCanvas, initPos);
		//initial draw to start loop
		draw();
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public void update(int time) {
		move(((double) speed / 1000) * time);

	}

	/**
	 * @param Width , defines the x boundry
	 * @param Height , defines the y boundry
	 */
	public void wrap(int Width, int Height) {
		if (turtleloc.getX() < 0) {
			turtleloc.setx(Width);

		}
		if (turtleloc.getX() > Width) {
			turtleloc.setx(0);

		}

		if (turtleloc.getY() < 0) {
			turtleloc.sety(Height);

		}
		if (turtleloc.getY() > Height) {
			turtleloc.sety(0);

		}

	}

}
