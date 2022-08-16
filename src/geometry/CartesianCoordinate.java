package geometry;

public class CartesianCoordinate {
		private double xPosition;
		private double yPosition;
		
		public CartesianCoordinate(double xPosition, double yPosition) {
			this.xPosition = xPosition;
			this.yPosition = yPosition;
		}
		
		public double getX() {
			return xPosition;
		}
		
		public double getY() {
			return yPosition;
		}
		
		public String toString() {
			return xPosition+ " " + yPosition;
		}

		public void setx(double xPosition) {
			this.xPosition = xPosition;
		}

		public void sety(double yPosition) {
			this.yPosition = yPosition;
		}
}
