public abstract class SolidObject implements model {
	protected Vector start;

	protected Vector xDirection, yDirection, zDirection;

	protected Polygon3D boundary[];

	protected Rectangle2D boundary2D;

	protected boolean visible;

	protected Vector centre;

	protected Vector tempCentre = new Vector(0, 0, 0);

	protected int lifeSpan;

	protected int HP;

	protected boolean unstuck;

	protected int modelType;

	public final Polygon3D[] getBoundary() {
		return boundary;
	}

	public final Vector getCentre() {
		return tempCentre;
	}

	public final Vector getRealCentre() {
		return centre;
	}

	public int getType() {
		return modelType;
	}

	public final void unstuck() {
		unstuck = true;

	}

	public final void makeBoundary(double l, double h, double w) {
		boundary = new Polygon3D[5];
		Vector[] front = new Vector[] { put(l, h, w), put(-l, h, w),
				put(-l, -h, w), put(l, -h, w) };
		boundary[0] = new Polygon3D(front, null, null, null, null, 0, 0, 0);
		Vector[] right = new Vector[] { put(l, h, -w), put(l, h, w),
				put(l, -h, w), put(l, -h, -w) };
		boundary[1] = new Polygon3D(right, null, null, null, null, 0, 0, 0);
		Vector[] back = new Vector[] { put(-l, h, -w), put(l, h, -w),
				put(l, -h, -w), put(-l, -h, -w) };
		boundary[2] = new Polygon3D(back, null, null, null, null, 0, 0, 0);
		Vector[] left = new Vector[] { put(-l, h, w), put(-l, h, -w),
				put(-l, -h, -w), put(-l, -h, w) };
		boundary[3] = new Polygon3D(left, null, null, null, null, 0, 0, 0);
		Vector[] top = new Vector[] { put(-l, h, w), put(l, h, w),
				put(l, h, -w), put(-l, h, -w) };
		boundary[4] = new Polygon3D(top, null, null, null, null, 0, 0, 0);
	}

	public final boolean testVisibility() {
		for (int i = 0; i < boundary.length; i++) {
			if (boundary[i].visible)
				return true;
		}
		return false;
	}

	public final void findCenter() {
		centre = new Vector(0, 0, 0);
		for (int i = 0; i < 4; i++)
			centre.add(boundary[i].centre);
		centre.scale(1.0 / 4);
	}

	public final Vector put(double i, double j, double k) {
		Vector temp = start.myClone();
		temp.add(xDirection, i);
		temp.add(yDirection, j);
		temp.add(zDirection, k);
		return temp;
	}

	public final void change(double i, double j, double k, Vector v) {
		v.set(start);
		v.add(xDirection, i);
		v.add(yDirection, j);
		v.add(zDirection, k);
	}

	public final double getZDepth() {
		return tempCentre.z;
	}

	public final int getLifeSpan() {
		return lifeSpan;
	}

	public void damage(int damagePoint) {
		HP -= damagePoint;
	}

}
