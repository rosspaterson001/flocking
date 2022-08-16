package geometry;

public class LineSegment {

	private CartesianCoordinate startPoint;
	private CartesianCoordinate endPoint;
	
	public LineSegment(CartesianCoordinate startPoint, CartesianCoordinate endPoint) {
		this.endPoint = endPoint;
		this.startPoint = startPoint;
	}

	public CartesianCoordinate getStartPoint() {
		return startPoint;
	}

	public CartesianCoordinate getEndPoint() {
		return endPoint;
	}
	
	public String toString() {
		return "start x y:" + startPoint + " end x y:" + endPoint;
	}

	public double length() {
		//returns the length beween 
		return Math.sqrt(Math.pow((startPoint.getX()-endPoint.getX()),2)+Math.pow((startPoint.getY()-endPoint.getY()),2));
	}
	
}