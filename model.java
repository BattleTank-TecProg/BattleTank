public interface model {
	public void update();

	public polygon3D[] getBoundary();

	public rectangle2D getBoundary2D();

	public vector getCentre();

	public vector getRealCentre();

	public void draw();

	public int getType();

	public double getZDepth();

	public int getLifeSpan();

	public void damage(int damagePoint);

	public void unstuck();
}