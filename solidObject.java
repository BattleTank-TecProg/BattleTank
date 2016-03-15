public abstract class solidObject implements model {
	protected vector start;

	protected vector iDirection, jDirection, kDirection;

	protected polygon3D[] boundary;

	protected rectangle2D boundary2D;

	protected boolean visible;

	protected vector centre;

	protected vector tempCentre = new vector(0, 0, 0);

	protected int lifeSpan;

	protected int HP;

	protected boolean unstuck;

	protected int modelType;

	public final polygon3D[] getBoundary() {
		return boundary;
	}

	public final vector getCentre() {
		return tempCentre;
	}

	public final vector getRealCentre() {
		return centre;
	}

	public final int getType() {
		return modelType;
	}

	public final void unstuck() {
		unstuck = true;

	}

	public final void makeBoundary(double l, double h, double w) {
		boundary = new polygon3D[5];
		vector[] front = new vector[] { put(l, h, w), put(-l, h, w),
				put(-l, -h, w), put(l, -h, w) };
		boundary[0] = new polygon3D(front, null, null, null, null, 0, 0, 0);
		vector[] right = new vector[] { put(l, h, -w), put(l, h, w),
				put(l, -h, w), put(l, -h, -w) };
		boundary[1] = new polygon3D(right, null, null, null, null, 0, 0, 0);
		vector[] back = new vector[] { put(-l, h, -w), put(l, h, -w),
				put(l, -h, -w), put(-l, -h, -w) };
		boundary[2] = new polygon3D(back, null, null, null, null, 0, 0, 0);
		vector[] left = new vector[] { put(-l, h, w), put(-l, h, -w),
				put(-l, -h, -w), put(-l, -h, w) };
		boundary[3] = new polygon3D(left, null, null, null, null, 0, 0, 0);
		vector[] top = new vector[] { put(-l, h, w), put(l, h, w),
				put(l, h, -w), put(-l, h, -w) };
		boundary[4] = new polygon3D(top, null, null, null, null, 0, 0, 0);
	}

	public final boolean testVisibility() {
		for (int i = 0; i < boundary.length; i++) {
			if (boundary[i].visible)
				return true;
		}
		return false;
	}

	public final void findCentre() {
		centre = new vector(0, 0, 0);
		for (int i = 0; i < 4; i++)
			centre.add(boundary[i].centre);
		centre.scale(1.0 / 4);
	}

	public final vector put(double i, double j, double k) {
		vector temp = start.myClone();
		temp.add(iDirection, i);
		temp.add(jDirection, j);
		temp.add(kDirection, k);
		return temp;
	}

	public final void change(double i, double j, double k, vector v) {
		v.set(start);
		v.add(iDirection, i);
		v.add(jDirection, j);
		v.add(kDirection, k);
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