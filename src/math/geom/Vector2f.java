package math.geom;

public class Vector2f extends Vector3f {

	public Vector2f(float i, float j) {
		super(i, j, 0);
	}
	
	public static Vector2f createVector(float theta, float magnitude) {
		return (Vector2f) (new Vector3f(theta, (float) (Math.PI/2 - theta), 0, magnitude));
	}
	
	public float getTheta() {return alpha;}
	public void setTheta(float theta) {
		alpha = theta;
		super.calc(theta, (float) (Math.PI/2 - theta), 0, magnitude);
	}
	
	@Override
	public void setk(float k) {k=0;}
	
	

}
