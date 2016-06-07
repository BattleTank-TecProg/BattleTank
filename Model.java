public interface Model {
	public void update();

	public Polygon3D[] getBoundary();

	public Rectangle2D getBoundary2D();

	public Vector getCentre();

	public Vector getRealCentre();

	public void draw();

	public int getType();

	public double getZDepth();

	public int getLifeSpan();

	public void damage(int damagePoint);

	public void unstuck();
}