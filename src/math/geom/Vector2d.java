package math.geom;

public class Vector2d extends Vector3d {

	public Vector2d(double i, double j) {
		super(i, j, 0);
	}
	
	public static Vector2d createVector(double theta, double magnitude) {
		return (Vector2d) (new Vector3d(theta, (double) (Math.PI/2 - theta), 0, magnitude));
	}
	
	public double getTheta() {return alpha;}
	public void setTheta(double theta) {
		alpha = theta;
		super.calc(theta, (double) (Math.PI/2 - theta), 0, magnitude);
	}
	
	@Override
	public void setk(double k) {k=0;}
	
}
