import java.awt.*;

public class Polygon3D {
	public Vector[] vertex3D, tempVertex;

	public Vector[] vertex2D;

	public Vector realNormal, normal;

	public Vector realCentre, centre;

	public int L;

	public Rectangle bound;
	public int xMin, yMin, xMax, yMax;

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

	public Polygon3D(Vector[] vertex3D, Vector origin, Vector rightEnd,
			Vector bottomEnd, Texture myTexture, double scaleX, double scaleY,
			int type) {
		this.type = type;
		this.vertex3D = vertex3D;
		this.myTexture = myTexture;
		L = vertex3D.length;
		bound = new Rectangle(0, 0, 0, 0);
		diffuse_I = 31;

		tempVertex = new Vector[L];
		for (int i = 0; i < L; i++) {
			tempVertex[i] = new Vector(0, 0, 0);
			tempVertex[i].set(vertex3D[i]);
		}

		tempVector1.set(tempVertex[1]);
		tempVector1.subtract(tempVertex[0]);
		tempVector2.set(tempVertex[2]);
		tempVector2.subtract(tempVertex[1]);
		realNormal = tempVector1.cross(tempVector2);
		realNormal.unit();
		normal = new Vector(0, 0, 0);
		normal.set(realNormal);

		if (Math.abs(normal.y) > 0.99)
			faceVerticalPolygon = true;
		else
			faceVerticalPolygon = false;

		realCentre = new Vector(0, 0, 0);
		for (int i = 0; i < tempVertex.length; i++)
			realCentre.add(tempVertex[i]);
		realCentre.scale(1.0 / tempVertex.length);
		centre = new Vector(0, 0, 0);
		centre.set(realCentre);

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

		vertex2D = new Vector[L + 1];
		for (int i = 0; i < vertex2D.length; i++)
			vertex2D[i] = new Vector(0, 0, 0);

		findDiffuse();
	}

	public void update() {
		tempVector1.set(Camera.position);
		tempVector1.subtract(vertex3D[0]);
		if (tempVector1.dot(realNormal) <= 0) {
			visible = false;
			return;
		}

		double x = 0, y = 0, z = 0, camX = Camera.position.x, camY = Camera.position.y, camZ = Camera.position.z, sinXZ = GameData.sin[Camera.XZ_angle], cosXZ = GameData.cos[Camera.XZ_angle], sinYZ = GameData.sin[Camera.YZ_angle], cosYZ = GameData.cos[Camera.YZ_angle];

		for (int i = 0; i < L; i++) {
			x = vertex3D[i].x - camX;
			y = vertex3D[i].y - camY;
			z = vertex3D[i].z - camZ;

			tempVertex[i].x = cosXZ * x - sinXZ * z;
			tempVertex[i].z = sinXZ * x + cosXZ * z;

			z = tempVertex[i].z;

			tempVertex[i].y = cosYZ * y - sinYZ * z;
			tempVertex[i].z = sinYZ * y + cosYZ * z;

			tempVertex[i].updateLocation();
		}

		xMax = tempVertex[0].screenX;
		xMin = xMax;
		yMax = tempVertex[0].screenY;
		yMin = yMax;
		for (int i = 1; i < tempVertex.length; i++) {
			xMax = Math.max(xMax, tempVertex[i].screenX);
			xMin = Math.min(xMin, tempVertex[i].screenX);
			yMax = Math.max(yMax, tempVertex[i].screenY);
			yMin = Math.min(yMin, tempVertex[i].screenY);
		}
		bound.setLocation(xMin, yMin);
		bound.setSize(xMax - xMin + 1, yMax - yMin);

		visible = Camera.screen.intersects(bound);

		if (visible) {
			tempVector1.set(tempVertex[1]);
			tempVector1.subtract(tempVertex[0]);
			tempVector2.set(tempVertex[2]);
			tempVector2.subtract(tempVertex[1]);
			normal = tempVector1.cross(tempVector2);

			centre.reset();
			for (int i = 0; i < L; i++)
				centre.add(tempVertex[i]);
			centre.scale(1.0 / L);

			withinViewScreen = Camera.screen.contains(xMin, yMin)
					&& Camera.screen.contains(xMax, yMax);
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
		tempVector1.set(vertex3D[1]);
		tempVector1.subtract(vertex3D[0]);
		tempVector2.set(vertex3D[2]);
		tempVector2.subtract(vertex3D[1]);
		realNormal = tempVector1.cross(tempVector2);
		realNormal.unit();
	}

	public void draw() {
		if (visible) {
			Main.polyCount++;
			Rasterizer.rasterize(this);
		}
	}
}
