/**
 * This class is responsible for the methods and attributes of energy fence.
 * 
 * This class is part of BattleTank 2.
 * 
 * BattleTank 2 is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * BattleTank 2 is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * BattleTank 2. If not, see <http://www.gnu.org/licenses/>
 */

public class Fence extends SolidObject {

	/** Returns the block health length value. */
	static final double LENGHT = 0.125;

	/** This Constant represents the block health height value. */
	static final double HEIGHT = 0.25;

	/** This Constant represents the health block width value. */
	static final double WIDTH = 0.125;

	/** This Constant represents the fence orientation vertical */
	static final int VERTICAL = 0;

	/** This Constant represents the fence orientation horizontal */
	static final int HORIZONTAL = 1;

	/** The polygons of the model. */
	private Polygon3D polygons[];

	/** The fence orientation */
	public int orientation;

	/**
	 * This is a constructor method, which receives as parameters the
	 * coordinates x, y and z and the orientation of Fence.
	 * 
	 * @param x
	 * @param y
	 * @param z
	 * @param orientation
	 */

	public Fence(double x, double y, double z, int orientation) {

		assert ((x > 0 && x < 20000) && y == -0.9 && (z > 0 && z < 25000) && (orientation == VERTICAL || orientation == HORIZONTAL));

		start = new Vector(x, y, z);
		xDirection = new Vector(1, 0, 0);
		yDirection = new Vector(0, 1, 0);
		zDirection = new Vector(0, 0, 1);

		if (orientation == VERTICAL) {
			xDirection.rotate_XZ(90);
			zDirection.rotate_XZ(90);
		}

		modelType = 6;
		makeBoundary(LENGHT, HEIGHT, WIDTH);

		if (orientation == VERTICAL) {
			boundary2D = new Rectangle2D(x - 0.06, z + 0.17, 0.12, 0.34);
			ObstacleMap.registerObstacle2(this, (int) (x * 4)
					+ (129 - (int) (z * 4)) * 80);

		}

		if (orientation == HORIZONTAL) {
			boundary2D = new Rectangle2D(x - 0.17, z + 0.06, 0.34, 0.12);
			ObstacleMap.registerObstacle2(this, (int) (x * 4)
					+ (129 - (int) (z * 4)) * 80);

		}

		findCentre();

		makePolygons();
	}

	/** This method is responsibly of Construct polygons for a fence of energy. */

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

	/**
	 * This method is responsible for return the 2D boundary of the fence.
	 * 
	 * @return boundary2D
	 */

	public Rectangle2D getBoundary2D() {
		return boundary2D;
	}

	/**
	 * This method is responsible for updating the fence in the scenario,
	 * according to the actions in the game.
	 */

	public void update() {
		assert (polygons != null);
		assert (boundary != null);

		tempCentre.set(centre);
		tempCentre.y = -1;
		tempCentre.subtract(Camera.getPosition());
		tempCentre.rotate_XZ(Camera.getXZ_angle());
		tempCentre.rotate_YZ(Camera.getYZ_angle());
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

	/** This method is responsible to eliminate the fence of the scenery. */

	public void destroy() {
		int position = (int) (start.x * 4) + (129 - (int) (start.z * 4)) * 80;
		ObstacleMap.removeObstacle2(position);
	}

	/** This method is responsible for drawing the fence. */

	public void draw() {
		if (visible) {
			for (int i = 0; i < polygons.length; i++) {
				polygons[i].draw();
			}
		}
	}
}