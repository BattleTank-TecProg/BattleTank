public class Fence extends SolidObject {

	static final double LENGHT = 0.125;
	
	static final double HEIGHT = 0.25;
	
	static final double WIDTH = 0.125; 
	
	private Polygon3D polygons[];

	public int orientation;

	public Fence(double x, double y, double z, int orientation) {
		
	assert( (x > 0 && x < 20000) && y == -0.9 && (z > 0 && z < 25000) && (orientation == 0 || orientation == 1));
		
		start = new Vector(x, y, z);
		iDirection = new Vector(1, 0, 0);
		jDirection = new Vector(0, 1, 0);
		kDirection = new Vector(0, 0, 1);

		if (orientation == 0) {
			iDirection.rotate_XZ(90);
			kDirection.rotate_XZ(90);
		}

		modelType = 6;
		makeBoundary(LENGHT, HEIGHT, WIDTH);

		if (orientation == 0) {
			boundary2D = new Rectangle2D(x - 0.06, z + 0.17, 0.12, 0.34);
			ObstacleMap.registerObstacle2(this, (int) (x * 4)
					+ (129 - (int) (z * 4)) * 80);

		}

		if (orientation == 1) {
			boundary2D = new Rectangle2D(x - 0.17, z + 0.06, 0.34, 0.12);
			ObstacleMap.registerObstacle2(this, (int) (x * 4)
					+ (129 - (int) (z * 4)) * 80);

		}

		findCentre();

		makePolygons();
	}

	public void makePolygons() {
		Vector v[];

		polygons = new Polygon3D[2];
		v = new Vector[] { put(-0.125, 0.14, 0), put(0.125, 0.14, 0),
				put(0.125, -0.1, 0), put(-0.125, -0.1, 0) };
		polygons[0] = new Polygon3D(v, new Vector(-1, -1, 1), new Vector(1, -1,
				1), new Vector(-1, -1, -1), null, 1, 1, 9);

		v = new Vector[] { put(-0.125, -0.1, 0), put(0.125, -0.1, 0),
				put(0.125, 0.14, 0), put(-0.125, 0.14, 0) };
		polygons[1] = new Polygon3D(v, new Vector(-1, -1, 1), new Vector(1, -1,
				1), new Vector(-1, -1, -1), null, 1, 1, 9);
	}

	public Rectangle2D getBoundary2D() {
		return boundary2D;
	}

	public void update() {

		tempCentre.set(centre);
		tempCentre.y = -1;
		tempCentre.subtract(Camera.position);
		tempCentre.rotate_XZ(Camera.XZ_angle);
		tempCentre.rotate_YZ(Camera.YZ_angle);
		tempCentre.updateLocation();

		if (tempCentre.z < 0.5 || tempCentre.screenY < -30
				|| tempCentre.screenX < -400 || tempCentre.screenX > 800) {
			visible = false;
			return;
		}
		visible = true;

		ModelDrawList.register(this);

		for (int i = 0; i < 5; i++)
			boundary[i].update();

		for (int i = 0; i < polygons.length; i++) {
			polygons[i].update();
		}
	}

	public void destory() {
		int position = (int) (start.x * 4) + (129 - (int) (start.z * 4)) * 80;
		ObstacleMap.removeObstacle2(position);
	}

	public void draw() {
		if (visible) {
			for (int i = 0; i < polygons.length; i++) {
				polygons[i].draw();
			}
		}
	}
}
