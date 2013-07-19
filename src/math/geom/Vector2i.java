package math.geom;

public class Vector2i extends Vector3i {

	public Vector2i(int i, int j) {
		super(i, j, 0);
	}
	
	public static Vector2d createVector(double theta, int magnitude) {
		return (Vector2d) (new Vector3d(theta, (double) (Math.PI/2 - theta), 0, magnitude));
	}
	
	public double getTheta() {return alpha;}
	public void setTheta(double theta) {
		alpha = theta;
		super.calc(theta, (double) (Math.PI/2 - theta), 0, magnitude);
	}
	
	@Override
	public void setk(int k) {k=0;}
	
}
