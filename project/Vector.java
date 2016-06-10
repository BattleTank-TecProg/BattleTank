package project;
public final class Vector {
	public double x, y, z;

	public int screenX, screenY;

	public static final int Z_length = 475;

	public static double old_X, old_Y, old_Z, zInverse;
	private double  lengthInverse;
	
	public Vector(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;

		updateLocation();
	}

	public void add(Vector v) {
		x += v.x;
		y += v.y;
		z += v.z;
	}

	public void add(double a, double b, double c) {
		x += a;
		y += b;
		z += c;
	}

	public void add(Vector v, double scaler) {
		x += v.x * scaler;
		y += v.y * scaler;
		z += v.z * scaler;
	}

	public void subtract(Vector v, double scaler) {
		x -= v.x * scaler;
		y -= v.y * scaler;
		z -= v.z * scaler;
	}

	public void subtract(Vector v) {
		x -= v.x;
		y -= v.y;
		z -= v.z;
	}

	public void scale(double a) {
		x *= a;
		y *= a;
		z *= a;
	}

	public void unit() {
		assigningOneDividedByLenghtInTheVariableLengthInverse();
		x = x * lengthInverse;
		y = y * lengthInverse;
		z = z * lengthInverse;
	}
	
	public void assigningOneDividedByLenghtInTheVariableLengthInverse() {
		lengthInverse = returnOneDividedByLenght();
	}
	
	public double returnOneDividedByLenght() {
		return 1 / getLength();
	}
	
	public double getLength() {
		return Math.sqrt(x * x + y * y + z * z);
	}

	public double dot(Vector v) {
		return x * v.x + y * v.y + z * v.z;
	}

	public double dot(double a, double b, double c) {
		return x * a + y * b + z * c;
	}

	public final Vector cross(Vector v) {
		return new Vector(y * v.z - z * v.y, z * v.x - x * v.z, x * v.y - y
				* v.x);
	}

	public void rotate_XZ(int angle) {
		double sin = GameData.sin[angle];
		double cos = GameData.cos[angle];
		old_X = x;
		old_Z = z;
		x = cos * old_X - sin * old_Z;
		z = sin * old_X + cos * old_Z;
	}

	public void rotate_YZ(int angle) {
		double sin = GameData.sin[angle];
		double cos = GameData.cos[angle];
		old_Y = y;
		old_Z = z;
		y = cos * old_Y - sin * old_Z;
		z = sin * old_Y + cos * old_Z;
	}

	public void rotate_XY(int angle) {
		double sin = GameData.sin[angle];
		double cos = GameData.cos[angle];
		old_X = x;
		old_Y = y;
		x = cos * old_X - sin * old_Y;
		y = sin * old_X + cos * old_Y;
	}

	public void set(Vector v) {
		x = v.x;
		y = v.y;
		z = v.z;
	}

	public void set(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public void reset() {
		x = 0;
		y = 0;
		z = 0;

	}

	public void updateLocation() {
		if (z <= 0.01) {
			screenX = (int) (x * Z_length * 100) + 320;
			screenY = (int) (-y * Z_length * 100) + 240;
		} else {
			zInverse = Z_length / z;
			screenX = (int) (x * zInverse) + 320;
			screenY = (int) (-y * zInverse) + 240;
		}
	}

	public void setScreenLocation(Vector v) {
		screenX = v.screenX;
		screenY = v.screenY;
	}

	public Vector myClone() {
		return new Vector(x, y, z);
	}

	public String toString() {
		return "(" + x + ", " + y + ", " + z + ")";
	}

}