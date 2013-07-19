package math.geom;

public class Vector3f {

	protected float i, j, k;
	protected float alpha, beta, gamma;
	protected float magnitude;
	
	public Vector3f(float i, float j, float k) {
		this.i=i; this.j=j; this.k=k;
		calc(i, j, k);
	}
	public Vector3f(float alpha, float beta, float gamma, float magnitude) {
		this.alpha=alpha; this.beta=beta; this.gamma=gamma; this.magnitude=magnitude;
		calc(alpha, beta, gamma, magnitude);
	}
	
	protected void calc(float i, float j, float k) {
		magnitude = (float)Math.sqrt(i*i + j*j + k*k);
		alpha = (float) Math.acos(i/magnitude);
		beta = (float) Math.acos(j/magnitude);
		gamma = (float) Math.acos(k/magnitude);
	}
	protected void calc(float alpha, float beta, float gamma, float magnitude) {
		i = (float) (magnitude * Math.cos(alpha));
		j = (float) (magnitude * Math.cos(beta));
		k = (float) (magnitude * Math.cos(gamma));
	}
	
	public float geti() {return i;}
	public float getj() {return j;}
	public float getk() {return k;}
	public float getAlpha() {return alpha;}
	public float getBeta() {return beta;}
	public float getGamma() {return gamma;}
	public float getMagnitude() {return magnitude;}
	
	public void seti(float i) {
		this.i=i;
		calc(i, j, k);
	}
	public void setj(float j) {
		this.j=j;
		calc(i, j, k);
	}
	public void setk(float k) {
		this.k=k;
		calc(i, j, k);
	}
	public void setAlpha(float a) {
		alpha=a;
		calc(alpha, beta, gamma, magnitude);
	}
	public void setBeta(float b) {
		beta=b;
		calc(alpha, beta, gamma, magnitude);
	}
	public void setGamma(float g) {
		gamma=g;
		calc(alpha, beta, gamma, magnitude);
	}
	public void setMagnitude(float m) {
		magnitude=m;
		calc(alpha, beta, gamma, magnitude);
	}
	
	public float dot(Vector3f v) {
		return geti()*v.geti() + getj()*v.getj() + getk()*v.getk();
	}
	
	public Vector3f cross(Vector3f v) {
		return new Vector3f((getj()*v.getk() - v.getj()*getk()), 
							 (getk()*v.geti() - v.getk()*geti()), 
							 (geti()*v.getj() - v.geti()*getj()));
	}
	
	public Vector3f project(Vector3f v) {
		float s = dot(v) / v.dot(v);
		return new Vector3f(s*v.geti(), s*v.getj(), s*v.getk());
	}
	
	public float angle(Vector3f v) {
		return (float) Math.acos(dot(v) / (getMagnitude()*v.getMagnitude()));
	}
	
	public Vector3f getUnitVector() {
		return new Vector3f(i/magnitude, j/magnitude, k/magnitude);
	}
}
