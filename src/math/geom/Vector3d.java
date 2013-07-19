package math.geom;

public class Vector3d {
	protected double i, j, k;
	protected double alpha, beta, gamma;
	protected double magnitude;
	
	public Vector3d(double i, double j, double k) {
		this.i=i; this.j=j; this.k=k;
		calc(i, j, k);
	}
	public Vector3d(double alpha, double beta, double gamma, double magnitude) {
		this.alpha=alpha; this.beta=beta; this.gamma=gamma; this.magnitude=magnitude;
		calc(alpha, beta, gamma, magnitude);
	}
	
	protected void calc(double i, double j, double k) {
		magnitude = (double)Math.sqrt(i*i + j*j + k*k);
		alpha = (double) Math.acos(i/magnitude);
		beta = (double) Math.acos(j/magnitude);
		gamma = (double) Math.acos(k/magnitude);
	}
	protected void calc(double alpha, double beta, double gamma, double magnitude) {
		i = (double) (magnitude * Math.cos(alpha));
		j = (double) (magnitude * Math.cos(beta));
		k = (double) (magnitude * Math.cos(gamma));
	}
	
	public double geti() {return i;}
	public double getj() {return j;}
	public double getk() {return k;}
	public double getAlpha() {return alpha;}
	public double getBeta() {return beta;}
	public double getGamma() {return gamma;}
	public double getMagnitude() {return magnitude;}
	
	public void seti(double i) {
		this.i=i;
		calc(i, j, k);
	}
	public void setj(double j) {
		this.j=j;
		calc(i, j, k);
	}
	public void setk(double k) {
		this.k=k;
		calc(i, j, k);
	}
	public void setAlpha(double a) {
		alpha=a;
		calc(alpha, beta, gamma, magnitude);
	}
	public void setBeta(double b) {
		beta=b;
		calc(alpha, beta, gamma, magnitude);
	}
	public void setGamma(double g) {
		gamma=g;
		calc(alpha, beta, gamma, magnitude);
	}
	public void setMagnitude(double m) {
		magnitude=m;
		calc(alpha, beta, gamma, magnitude);
	}
	
	public double dot(Vector3f v) {
		return geti()*v.geti() + getj()*v.getj() + getk()*v.getk();
	}
	
	public Vector3d cross(Vector3f v) {
		return new Vector3d((getj()*v.getk() - v.getj()*getk()), 
							 (getk()*v.geti() - v.getk()*geti()), 
							 (geti()*v.getj() - v.geti()*getj()));
	}
	
	public Vector3d project(Vector3f v) {
		double s = dot(v) / v.dot(v);
		return new Vector3d(s*v.geti(), s*v.getj(), s*v.getk());
	}
	
	public double angle(Vector3f v) {
		return (double) Math.acos(dot(v) / (getMagnitude()*v.getMagnitude()));
	}
	
	public Vector3d getUnitVector() {
		return new Vector3d(i/magnitude, j/magnitude, k/magnitude);
	}
}
