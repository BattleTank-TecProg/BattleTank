package project;
public class Stone extends SolidObject {
	private Polygon3D polygons[];

	public Polygon3D shadow;

	private double scale;

	private boolean testCollision;

	public int angle;

	public int type;

	public int textureIndex;

	public Stone(double x, double y, double z, int angle, int type,
			double scale, int textureIndex, boolean testCollision) {
		this.scale = scale;
		this.angle = angle;
		this.type = type;
		this.textureIndex = textureIndex;
		this.testCollision = testCollision;

		start = new Vector(x, y, z);
		xDirection = new Vector(1, 0, 0);
		yDirection = new Vector(0, 1, 0);
		zDirection = new Vector(0, 0, 1);

		xDirection.scale(scale);
		yDirection.scale(scale);
		zDirection.scale(scale);

		modelType = 3;
		makeBoundary(0.25 * scale, 0.5 * scale, 0.25 * scale);

		boundary2D = new Rectangle2D(x - 0.2 * scale, z + 0.2 * scale,
				0.4 * scale, 0.4 * scale);
		if (testCollision) {
			if (scale != 1) {
				ObstacleMap.registerObstacle2(this, (int) (x * 4)
						+ (129 - (int) (z * 4)) * 80);
			} else {
				ObstacleMap.registerObstacle2(this, (int) ((x - 0.125) * 4)
						+ (129 - (int) ((z + 0.125) * 4)) * 80);
				ObstacleMap.registerObstacle2(this, (int) ((x + 0.125) * 4)
						+ (129 - (int) ((z + 0.125) * 4)) * 80);
				ObstacleMap.registerObstacle2(this, (int) ((x - 0.125) * 4)
						+ (129 - (int) ((z - 0.125) * 4)) * 80);
				ObstacleMap.registerObstacle2(this, (int) ((x + 0.125) * 4)
						+ (129 - (int) ((z - 0.125) * 4)) * 80);
			}
		} else {
			//Does nothing.
		}
		
		xDirection.rotate_XZ(angle);
		zDirection.rotate_XZ(angle);

		findCenter();

		makePolygons(type, textureIndex);
	}

	public Rectangle2D getBoundary2D() {
		return boundary2D;
	}

	private void makePolygons(int type, int textureIndex) {
		Vector v[];

		double x = start.x;
		double y = start.y;
		double z = start.z;

		if (type == 1) {
			polygons = new Polygon3D[8];

			v = new Vector[] { put(-0.07, -0.1, 0.01), put(0.085, -0.1, 0.01),
					put(0.125, -0.25, -0.125), put(-0.1, -0.25, -0.125) };
			polygons[0] = new Polygon3D(v, v[0], v[1], v[3],
					Main.textures[textureIndex], 0.5, 0.5, 6);

			v = new Vector[] { put(-0.08, -0.1, 0.07), put(-0.07, -0.1, 0.01),
					put(-0.1, -0.25, -0.125), put(-0.125, -0.25, 0.08) };
			polygons[1] = new Polygon3D(v, v[0], v[1], v[3],
					Main.textures[textureIndex], 0.5, 0.5, 6);

			v = new Vector[] { put(-0.08, -0.1, 0.07), put(0.085, -0.1, 0.01),
					put(-0.07, -0.1, 0.01) };
			polygons[2] = new Polygon3D(v, v[2], v[0], v[1],
					Main.textures[textureIndex], 0.5, 0.5, 6);

			v = new Vector[] { put(-0.08, -0.1, 0.07),
					put(-0.125, -0.25, 0.08), put(-0.05, -0.25, 0.125) };
			polygons[3] = new Polygon3D(v, v[0], v[1], v[2],
					Main.textures[textureIndex], 0.5, 0.5, 6);

			v = new Vector[] { put(0.085, -0.1, 0.01), put(0.085, -0.1, 0.09),
					put(0.125, -0.25, 0.125), put(0.125, -0.25, -0.125) };
			polygons[4] = new Polygon3D(v, v[0], v[1], v[3],
					Main.textures[textureIndex], 0.5, 0.5, 6);

			v = new Vector[] { put(0.085, -0.1, 0.09),
					put(-0.05, -0.25, 0.125), put(0.125, -0.25, 0.125) };
			polygons[5] = new Polygon3D(v, v[0], v[1], v[2],
					Main.textures[textureIndex], 0.5, 0.5, 6);

			v = new Vector[] { put(0.085, -0.1, 0.09), put(0.085, -0.1, 0.01),
					put(-0.08, -0.1, 0.07) };
			polygons[6] = new Polygon3D(v, v[0], v[1], v[2],
					Main.textures[textureIndex], 0.5, 0.5, 6);

			v = new Vector[] { put(-0.08, -0.1, 0.07),
					put(-0.05, -0.25, 0.125), put(0.085, -0.1, 0.09) };
			polygons[7] = new Polygon3D(v, v[0], v[1], v[2],
					Main.textures[textureIndex], 0.5, 0.5, 6);
		} else if (type == 2) {

			polygons = new Polygon3D[24];
			v = new Vector[] { put(-0.1, -0.1, -0.2), put(0.1, -0.1, -0.2),
					put(0.15, -0.25, -0.25), put(-0.15, -0.25, -0.25) };
			polygons[0] = new Polygon3D(v, v[0], v[1], v[3],
					Main.textures[textureIndex], 0.5, 0.5, 6);

			v = new Vector[] { put(-0.1, -0.1, -0.2), put(-0.15, -0.25, -0.25),
					put(-0.22, -0.25, -0.1), put(-0.15, -0.1, -0.1) };
			polygons[1] = new Polygon3D(v, v[3], v[0], v[2],
					Main.textures[textureIndex], 0.5, 0.5, 6);

			v = new Vector[] { put(-0.15, -0.1, -0.1), put(-0.22, -0.25, -0.1),
					put(-0.25, -0.25, 0.1), put(-0.18, -0.1, 0.1) };
			polygons[2] = new Polygon3D(v, v[3], v[0], v[2],
					Main.textures[textureIndex], 0.5, 0.5, 6);

			v = new Vector[] { put(-0.18, -0.1, 0.1), put(-0.25, -0.25, 0.1),
					put(-0.17, -0.25, 0.2), put(-0.12, -0.1, 0.17) };
			polygons[3] = new Polygon3D(v, v[3], v[0], v[2],
					Main.textures[textureIndex], 0.5, 0.5, 6);

			v = new Vector[] { put(-0.12, -0.1, 0.17), put(-0.17, -0.25, 0.2),
					put(0.02, -0.25, 0.25), put(0.02, -0.1, 0.2) };
			polygons[4] = new Polygon3D(v, v[3], v[0], v[2],
					Main.textures[textureIndex], 0.5, 0.5, 6);

			v = new Vector[] { put(0.02, -0.1, 0.2), put(0.02, -0.25, 0.25),
					put(0.2, -0.25, 0.15), put(0.15, -0.1, 0.13) };
			polygons[5] = new Polygon3D(v, v[3], v[0], v[2],
					Main.textures[textureIndex], 0.5, 0.5, 6);

			v = new Vector[] { put(0.15, -0.1, 0.13), put(0.2, -0.25, 0.15),
					put(0.25, -0.25, 0), put(0.2, -0.1, 0) };
			polygons[6] = new Polygon3D(v, v[3], v[0], v[2],
					Main.textures[textureIndex], 0.4, 0.5, 6);

			v = new Vector[] { put(0.2, -0.1, 0), put(0.25, -0.25, 0),
					put(0.22, -0.25, -0.15), put(0.18, -0.1, -0.12) };
			polygons[7] = new Polygon3D(v, v[3], v[0], v[2],
					Main.textures[textureIndex], 0.2, 0.5, 6);

			v = new Vector[] { put(0.18, -0.1, -0.12), put(0.22, -0.25, -0.15),
					put(0.15, -0.25, -0.25), put(0.1, -0.1, -0.2) };
			polygons[8] = new Polygon3D(v, v[3], v[0], v[2],
					Main.textures[textureIndex], 0.4, 0.5, 6);

			v = new Vector[] { put(0, 0, -0.1), put(0.1, -0.1, -0.2),
					put(-0.1, -0.1, -0.2) };
			polygons[9] = new Polygon3D(v, v[0], v[1], v[2],
					Main.textures[textureIndex], 0.4, 0.5, 6);

			v = new Vector[] { put(0, 0, -0.1), put(-0.1, -0.1, -0.2),
					put(-0.15, -0.1, -0.1) };
			polygons[10] = new Polygon3D(v, v[0], v[1], v[2],
					Main.textures[textureIndex], 0.4, 0.5, 6);

			v = new Vector[] { put(-0.15, -0.1, -0.1), put(-0.08, 0, -0),
					put(0, 0, -0.1) };
			polygons[11] = new Polygon3D(v, v[0], v[1], v[2],
					Main.textures[textureIndex], 0.4, 0.5, 6);

			v = new Vector[] { put(-0.08, 0, -0), put(-0.15, -0.1, -0.1),
					put(-0.18, -0.1, 0.1) };
			polygons[12] = new Polygon3D(v, v[0], v[1], v[2],
					Main.textures[textureIndex], 0.4, 0.5, 6);

			v = new Vector[] { put(-0.18, -0.1, 0.1), put(-0.09, 0, 0.1),
					put(-0.08, 0, -0) };
			polygons[13] = new Polygon3D(v, v[0], v[1], v[2],
					Main.textures[textureIndex], 0.4, 0.5, 6);

			v = new Vector[] { put(-0.09, 0, 0.1), put(-0.18, -0.1, 0.1),
					put(-0.12, -0.1, 0.17) };
			polygons[14] = new Polygon3D(v, v[0], v[1], v[2],
					Main.textures[textureIndex], 0.4, 0.5, 6);

			v = new Vector[] { put(-0.09, 0, 0.1), put(-0.12, -0.1, 0.17),
					put(0.02, -0.1, 0.2) };
			polygons[15] = new Polygon3D(v, v[0], v[1], v[2],
					Main.textures[textureIndex], 0.7, 0.7, 6);

			v = new Vector[] { put(0.02, -0.1, 0.2), put(0.06, 0, 0.06),
					put(-0.09, 0, 0.1) };
			polygons[16] = new Polygon3D(v, v[0], v[1], v[2],
					Main.textures[textureIndex], 0.7, 0.7, 6);

			v = new Vector[] { put(0.06, 0, 0.06), put(0.02, -0.1, 0.2),
					put(0.15, -0.1, 0.13) };
			polygons[17] = new Polygon3D(v, v[0], v[1], v[2],
					Main.textures[textureIndex], 0.7, 0.7, 6);

			v = new Vector[] { put(0.06, 0, 0.06), put(0.15, -0.1, 0.13),
					put(0.2, -0.1, 0) };
			polygons[18] = new Polygon3D(v, v[0], v[1], v[2],
					Main.textures[textureIndex], 0.7, 0.7, 6);

			v = new Vector[] { put(0.11, 0, -0.09), put(0.06, 0, 0.06),
					put(0.2, -0.1, 0) };
			polygons[19] = new Polygon3D(v, v[0], v[1], v[2],
					Main.textures[textureIndex], 0.7, 0.7, 6);

			v = new Vector[] { put(0.11, 0, -0.09), put(0.2, -0.1, 0),
					put(0.18, -0.1, -0.12) };
			polygons[20] = new Polygon3D(v, v[0], v[1], v[2],
					Main.textures[textureIndex], 0.7, 0.7, 6);

			v = new Vector[] { put(0.11, 0, -0.09), put(0.18, -0.1, -0.12),
					put(0.1, -0.1, -0.2) };
			polygons[21] = new Polygon3D(v, v[0], v[1], v[2],
					Main.textures[textureIndex], 0.7, 0.7, 6);

			v = new Vector[] { put(0.11, 0, -0.09), put(0.1, -0.1, -0.2),
					put(0, 0, -0.1) };
			polygons[22] = new Polygon3D(v, v[0], v[1], v[2],
					Main.textures[textureIndex], 0.7, 0.7, 6);

			v = new Vector[] { put(0, 0, -0.1), put(-0.08, 0, -0),
					put(-0.09, 0, 0.1), put(0.06, 0, 0.06), put(0.11, 0, -0.09) };
			polygons[23] = new Polygon3D(v, put(-1, 0, 0), put(0, 0, 0), put(
					-1, 0, -1), Main.textures[textureIndex], 3, 3, 6);

			xDirection = new Vector(0.9, 0, 0);
			yDirection = new Vector(0, 1, 0);
			zDirection = new Vector(0, 0, 0.9);

			xDirection.scale(scale);
			yDirection.scale(scale);
			zDirection.scale(scale);

			v = new Vector[] { put(-0.5, -0.25, 0.4), put(0.4, -0.25, 0.4),
					put(0.4, -0.25, -0.5), put(-0.5, -0.25, -0.5) };
			shadow = new Polygon3D(v, v[0], v[1], v[3], Main.textures[8], 1, 1,
					2);
		} else if (type == 4) {
			yDirection.scale(1.3);
			start.add(0, 0.03, 0);

			polygons = new Polygon3D[15];
			v = new Vector[] { put(0, 0, -0.1), put(0.1, -0.1, -0.2),
					put(-0.1, -0.1, -0.2) };
			polygons[0] = new Polygon3D(v, v[0], v[1], v[2],
					Main.textures[textureIndex], 0.4, 0.5, 6);

			v = new Vector[] { put(0, 0, -0.1), put(-0.1, -0.1, -0.2),
					put(-0.15, -0.1, -0.1) };
			polygons[1] = new Polygon3D(v, v[0], v[1], v[2],
					Main.textures[textureIndex], 0.4, 0.5, 6);

			v = new Vector[] { put(-0.15, -0.1, -0.1), put(-0.08, 0, -0),
					put(0, 0, -0.1) };
			polygons[2] = new Polygon3D(v, v[0], v[1], v[2],
					Main.textures[textureIndex], 0.4, 0.5, 6);

			v = new Vector[] { put(-0.08, 0, -0), put(-0.15, -0.1, -0.1),
					put(-0.18, -0.1, 0.1) };
			polygons[3] = new Polygon3D(v, v[0], v[1], v[2],
					Main.textures[textureIndex], 0.4, 0.5, 6);

			v = new Vector[] { put(-0.18, -0.1, 0.1), put(-0.09, 0, 0.1),
					put(-0.08, 0, -0) };
			polygons[4] = new Polygon3D(v, v[0], v[1], v[2],
					Main.textures[textureIndex], 0.4, 0.5, 6);

			v = new Vector[] { put(-0.09, 0, 0.1), put(-0.18, -0.1, 0.1),
					put(-0.12, -0.1, 0.17) };
			polygons[5] = new Polygon3D(v, v[0], v[1], v[2],
					Main.textures[textureIndex], 0.4, 0.5, 6);

			v = new Vector[] { put(-0.09, 0, 0.1), put(-0.12, -0.1, 0.17),
					put(0.02, -0.1, 0.2) };
			polygons[6] = new Polygon3D(v, v[0], v[1], v[2],
					Main.textures[textureIndex], 0.7, 0.7, 6);

			v = new Vector[] { put(0.02, -0.1, 0.2), put(0.06, 0, 0.06),
					put(-0.09, 0, 0.1) };
			polygons[7] = new Polygon3D(v, v[0], v[1], v[2],
					Main.textures[textureIndex], 0.7, 0.7, 6);

			v = new Vector[] { put(0.06, 0, 0.06), put(0.02, -0.1, 0.2),
					put(0.15, -0.1, 0.13) };
			polygons[8] = new Polygon3D(v, v[0], v[1], v[2],
					Main.textures[textureIndex], 0.7, 0.7, 6);

			v = new Vector[] { put(0.06, 0, 0.06), put(0.15, -0.1, 0.13),
					put(0.2, -0.1, 0) };
			polygons[9] = new Polygon3D(v, v[0], v[1], v[2],
					Main.textures[textureIndex], 0.7, 0.7, 6);

			v = new Vector[] { put(0.11, 0, -0.09), put(0.06, 0, 0.06),
					put(0.2, -0.1, 0) };
			polygons[10] = new Polygon3D(v, v[0], v[1], v[2],
					Main.textures[textureIndex], 0.7, 0.7, 6);

			v = new Vector[] { put(0.11, 0, -0.09), put(0.2, -0.1, 0),
					put(0.18, -0.1, -0.12) };
			polygons[11] = new Polygon3D(v, v[0], v[1], v[2],
					Main.textures[textureIndex], 0.7, 0.7, 6);

			v = new Vector[] { put(0.11, 0, -0.09), put(0.18, -0.1, -0.12),
					put(0.1, -0.1, -0.2) };
			polygons[12] = new Polygon3D(v, v[0], v[1], v[2],
					Main.textures[textureIndex], 0.7, 0.7, 6);

			v = new Vector[] { put(0.11, 0, -0.09), put(0.1, -0.1, -0.2),
					put(0, 0, -0.1) };
			polygons[13] = new Polygon3D(v, v[0], v[1], v[2],
					Main.textures[textureIndex], 0.7, 0.7, 6);

			v = new Vector[] { put(0, 0, -0.1), put(-0.08, 0, -0),
					put(-0.09, 0, 0.1), put(0.06, 0, 0.06), put(0.11, 0, -0.09) };
			polygons[14] = new Polygon3D(v, put(-1, 0, 0), put(0, 0, 0), put(
					-1, 0, -1), Main.textures[textureIndex], 3, 3, 6);

			start.add(0, -0.03, 0);
		} else {
			//Does nothing.
		}

		start = new Vector(x, y, z);
		xDirection = new Vector(1, 0, 0);
		yDirection = new Vector(0, 1, 0);
		zDirection = new Vector(0, 0, 1);

		xDirection.scale(scale);
		yDirection.scale(scale);
		zDirection.scale(scale);

		xDirection.rotate_XZ(angle);
		zDirection.rotate_XZ(angle);
	}

	public void update() {
		tempCentre.set(centre);
		tempCentre.y = 0.25;
		tempCentre.subtract(Camera.getAbsolutePosition());
		if (tempCentre.getLength() > 5.5) {
			polygons = null;
			visible = false;
		} else {

			tempCentre.set(centre);
			tempCentre.y = -1;
			tempCentre.subtract(Camera.getPosition());
			tempCentre.rotate_XZ(Camera.getXZ_angle());
			tempCentre.rotate_YZ(Camera.getYZ_angle());
			tempCentre.updateLocation();

			if (tempCentre.z < 0.9 || tempCentre.screenY < -10
					|| (tempCentre.screenX < -60 && tempCentre.z > 3)
					|| (tempCentre.screenX > 700 && tempCentre.z > 3)) {

				visible = false;
			} else {
				visible = true;
				if (testCollision) {
					ModelDrawList.register(this);
				} else {
					//Does nothing.
				}
				
				if (polygons == null) {
					makePolygons(type, textureIndex);

				} else {
					//Does nothing.
				}

				for (int i = 0; i < polygons.length; i++) {
					polygons[i].update();
				}

				if (shadow != null) {
					shadow.update();
					if (shadow.visible) {
						Rasterizer.rasterize(shadow);
					} else {
						//Does nothing.
					}
				} else {
					//Does nothing.
				}
			}
		}
	}

	public void draw() {
		if (visible) {
			for (int i = 0; i < polygons.length; i++) {
				polygons[i].draw();
			}
		} else {
			//Does nothing.
		}
	}
}