package project;
import java.awt.*;

public class Polygon3D {
	private Vector vertex3D[], tempVertex[];

	private Vector vertex2D[];

	private Vector realNormal, normal;

	private Vector realCentre, centre;

	private int L;

	private Rectangle bound;
	
	private int xMin, yMin, xMax, yMax;

	public boolean withinViewScreen;

	public Vector origin, rightEnd, bottomEnd;

	public Texture myTexture;

	public int textureWidth, textureHeight, heightMask, widthMask, widthBits,
			heightBits, bigHeight;

	public double textureScaleX, textureScaleY;

	public int start = 479, end = 0;

	public double A_offset, B_offset, C_offset;

	public int alpha = 0;

	public short variation;

	public static Vector tempVector1 = new Vector(0, 0, 0),
			tempVector2 = new Vector(0, 0, 0),
			tempVector3 = new Vector(0, 0, 0),
			tempVector4 = new Vector(0, 0, 0);

	public boolean faceVerticalPolygon;

	public boolean visible;

	public int type;

	public int diffuse_I;
	public int Ambient_I = 28;

	public double diffuse_coefficient = 1;

	public boolean constantI;

	public short color;

	public Polygon3D(Vector vertex3D[], Vector origin, Vector rightEnd,
			Vector bottomEnd, Texture myTexture, double scaleX, double scaleY,
			int type) {
		this.type = type;
		this.setVertex3D(vertex3D);
		this.myTexture = myTexture;
		setL(vertex3D.length);
		bound = new Rectangle(0, 0, 0, 0);
		diffuse_I = 31;

		setTempVertex(new Vector[getL()]);
		for (int i = 0; i < getL(); i++) {
			getTempVertex()[i] = new Vector(0, 0, 0);
			getTempVertex()[i].set(vertex3D[i]);
		}

		tempVector1.set(getTempVertex()[1]);
		tempVector1.subtract(getTempVertex()[0]);
		tempVector2.set(getTempVertex()[2]);
		tempVector2.subtract(getTempVertex()[1]);
		realNormal = tempVector1.cross(tempVector2);
		realNormal.unit();
		setNormal(new Vector(0, 0, 0));
		getNormal().set(realNormal);

		if (Math.abs(getNormal().y) > 0.99) {
			faceVerticalPolygon = true;
		} else {
			faceVerticalPolygon = false;
		}
		
		setRealCentre(new Vector(0, 0, 0));
		for (int i = 0; i < getTempVertex().length; i++)
			getRealCentre().add(getTempVertex()[i]);
		getRealCentre().scale(1.0 / getTempVertex().length);
		setCentre(new Vector(0, 0, 0));
		getCentre().set(getRealCentre());

		if (origin != null) {
			this.origin = origin.myClone();
			this.rightEnd = rightEnd.myClone();
			this.bottomEnd = bottomEnd.myClone();
		}

		if (myTexture != null) {

			textureWidth = myTexture.width;
			textureHeight = myTexture.height;
			heightMask = myTexture.heightMask;
			widthMask = myTexture.widthMask;
			widthBits = myTexture.widthBits;
			heightBits = myTexture.heightBits;
			bigHeight = textureHeight << 21;

			tempVector1.set(origin);
			tempVector1.subtract(rightEnd);
			double l = tempVector1.getLength();
			textureScaleX = l / myTexture.width;

			tempVector1.set(origin);
			tempVector1.subtract(bottomEnd);
			l = tempVector1.getLength();
			textureScaleY = l / myTexture.height;

			textureScaleX = textureScaleX / scaleX;
			textureScaleY = textureScaleY / scaleY;
		}

		setVertex2D(new Vector[getL() + 1]);
		for (int i = 0; i < getVertex2D().length; i++)
			getVertex2D()[i] = new Vector(0, 0, 0);

		findDiffuse();
	}

	public void update() {
		tempVector1.set(Camera.getPosition());
		tempVector1.subtract(getVertex3D()[0]);
		if (tempVector1.dot(realNormal) <= 0) {
			visible = false;
		} else {

			double x = 0, y = 0, z = 0, camX = Camera.getPosition().x, camY = Camera
					.getPosition().y, camZ = Camera.getPosition().z, sinXZ = GameData.sin[Camera
					.getXZ_angle()], cosXZ = GameData.cos[Camera.getXZ_angle()], sinYZ = GameData.sin[Camera
					.getYZ_angle()], cosYZ = GameData.cos[Camera.getYZ_angle()];

			for (int i = 0; i < getL(); i++) {
				x = getVertex3D()[i].x - camX;
				y = getVertex3D()[i].y - camY;
				z = getVertex3D()[i].z - camZ;

				getTempVertex()[i].x = cosXZ * x - sinXZ * z;
				getTempVertex()[i].z = sinXZ * x + cosXZ * z;

				z = getTempVertex()[i].z;

				getTempVertex()[i].y = cosYZ * y - sinYZ * z;
				getTempVertex()[i].z = sinYZ * y + cosYZ * z;

				getTempVertex()[i].updateLocation();
			}

			xMax = getTempVertex()[0].screenX;
			xMin = xMax;
			yMax = getTempVertex()[0].screenY;
			yMin = yMax;
			for (int i = 1; i < getTempVertex().length; i++) {
				xMax = Math.max(xMax, getTempVertex()[i].screenX);
				xMin = Math.min(xMin, getTempVertex()[i].screenX);
				yMax = Math.max(yMax, getTempVertex()[i].screenY);
				yMin = Math.min(yMin, getTempVertex()[i].screenY);
			}
			bound.setLocation(xMin, yMin);
			bound.setSize(xMax - xMin + 1, yMax - yMin);

			visible = Camera.screen.intersects(bound);

			if (visible) {
				tempVector1.set(getTempVertex()[1]);
				tempVector1.subtract(getTempVertex()[0]);
				tempVector2.set(getTempVertex()[2]);
				tempVector2.subtract(getTempVertex()[1]);
				setNormal(tempVector1.cross(tempVector2));

				getCentre().reset();
				for (int i = 0; i < getL(); i++)
					getCentre().add(getTempVertex()[i]);
				getCentre().scale(1.0 / getL());

				withinViewScreen = Camera.screen.contains(xMin, yMin)
						&& Camera.screen.contains(xMax, yMax);
			}
		}
	}

	public void findDiffuse() {
		double I = realNormal.dot(0.3773502691896258, 0.7773502691896258,
				0.7773502691896258);
		diffuse_I = Ambient_I + (int) (diffuse_coefficient * I * 20);

		if (constantI)
			diffuse_I = 45;
	}

	public void findRealNormal() {
		tempVector1.set(getVertex3D()[1]);
		tempVector1.subtract(getVertex3D()[0]);
		tempVector2.set(getVertex3D()[2]);
		tempVector2.subtract(getVertex3D()[1]);
		realNormal = tempVector1.cross(tempVector2);
		realNormal.unit();
	}

	public void draw() {
		if (visible) {
			Main.polyCount++;
			Rasterizer.rasterize(this);
		}
	}

	public Vector[] getVertex3D() {
		return vertex3D;
	}

	public void setVertex3D(Vector vertex3d[]) {
		vertex3D = vertex3d;
	}

	public Vector[] getTempVertex() {
		return tempVertex;
	}

	public void setTempVertex(Vector tempVertex[]) {
		this.tempVertex = tempVertex;
	}

	public Vector getCentre() {
		return centre;
	}

	public void setCentre(Vector centre) {
		this.centre = centre;
	}

	public Vector getNormal() {
		return normal;
	}

	public void setNormal(Vector normal) {
		this.normal = normal;
	}

	public Vector getRealCentre() {
		return realCentre;
	}

	public void setRealCentre(Vector realCentre) {
		this.realCentre = realCentre;
	}

	public int getL() {
		return L;
	}

	public void setL(int l) {
		L = l;
	}

	public Vector[] getVertex2D() {
		return vertex2D;
	}

	public void setVertex2D(Vector vertex2d[]) {
		vertex2D = vertex2d;
	}
}