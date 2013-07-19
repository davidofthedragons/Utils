package math.geom;

public class Vector3i {

	protected int i, j, k;
	protected double alpha, beta, gamma;
	protected int magnitude;
	
	public Vector3i(int i, int j, int k) {
		this.i=i; this.j=j; this.k=k;
		calc(i, j, k);
	}
	public Vector3i(int alpha, int beta, int gamma, int magnitude) {
		this.alpha=alpha; this.beta=beta; this.gamma=gamma; this.magnitude=magnitude;
		calc(alpha, beta, gamma, magnitude);
	}
	
	protected void calc(int i, int j, int k) {
		magnitude = (int)Math.sqrt(i*i + j*j + k*k);
		alpha = (int) Math.acos(i/magnitude);
		beta = (int) Math.acos(j/magnitude);
		gamma = (int) Math.acos(k/magnitude);
	}
	protected void calc(double alpha, double beta, double gamma, int magnitude) {
		i = (int) (magnitude * Math.cos(alpha));
		j = (int) (magnitude * Math.cos(beta));
		k = (int) (magnitude * Math.cos(gamma));
	}
	
	public int geti() {return i;}
	public int getj() {return j;}
	public int getk() {return k;}
	public double getAlpha() {return alpha;}
	public double getBeta() {return beta;}
	public double getGamma() {return gamma;}
	public int getMagnitude() {return magnitude;}
	
	public void seti(int i) {
		this.i=i;
		calc(i, j, k);
	}
	public void setj(int j) {
		this.j=j;
		calc(i, j, k);
	}
	public void setk(int k) {
		this.k=k;
		calc(i, j, k);
	}
	public void setAlpha(int a) {
		alpha=a;
		calc(alpha, beta, gamma, magnitude);
	}
	public void setBeta(int b) {
		beta=b;
		calc(alpha, beta, gamma, magnitude);
	}
	public void setGamma(int g) {
		gamma=g;
		calc(alpha, beta, gamma, magnitude);
	}
	public void setMagnitude(int m) {
		magnitude=m;
		calc(alpha, beta, gamma, magnitude);
	}
	
	public int dot(Vector3i v) {
		return geti()*v.geti() + getj()*v.getj() + getk()*v.getk();
	}
	
	public Vector3f cross(Vector3i v) {
		return new Vector3f((getj()*v.getk() - v.getj()*getk()), 
							 (getk()*v.geti() - v.getk()*geti()), 
							 (geti()*v.getj() - v.geti()*getj()));
	}
	
	public Vector3i project(Vector3i v) {
		int s = dot(v) / v.dot(v);
		return new Vector3i(s*v.geti(), s*v.getj(), s*v.getk());
	}
	
	public int angle(Vector3i v) {
		return (int) Math.acos(dot(v) / (getMagnitude()*v.getMagnitude()));
	}
	
	public Vector3i getUnitVector() {
		return new Vector3i(i/magnitude, j/magnitude, k/magnitude);
	}
}
