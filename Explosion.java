//This class is responsible for explosion in the game.

public class Explosion extends SolidObject {
	
	double size = 0;

	public int spriteIndex = 0;

	public int frameIndex = 0;

	public int auraIndex = 0;

	public int damage = 5;

	public int type = 0;

	public int groundZero = 0;

	public boolean explicitDrawing;

	public Polygon3D explosionAura;

	public Explosion(double x, double y, double z, double size) {
		
		final double LENGHT = 0.001;
		
		final double HEIGHT = 0.001;
		
		final double WIDTH = 0.001;
		
		final double XPOS = x - 0.1;
		
		final double YPOS = z + 0.1;
		
		final double LENGHTRECTANGLE = 0.2;
		
		final double HEIGHTRECTANGLE = 0.2;		
		
		start = new Vector(x, y, z);
		iDirection = new Vector(1, 0, 0);
		jDirection = new Vector(0, 1, 0);
		kDirection = new Vector(0, 0, 1);

		int random = GameData.getRandom();
		if (random >= 75) {
			spriteIndex = 17;
		} else if (random >= 50) {
			spriteIndex = 18;
		} else if (random >= 25) {
			spriteIndex = 19;
		} else {
			spriteIndex = 20;
		}
		if (size > 1) {
			spriteIndex = 18;
		}
		Vector[] v = new Vector[] { put(-0.3, 0, 0.3), put(0.3, 0, 0.3),
				put(0.3, 0, -0.3), put(-0.3, 0, -0.3) };
		if (size > 3) {
			v = new Vector[] { put(-0.12, 0, 0.12), put(0.12, 0, 0.12),
					put(0.12, 0, -0.12), put(-0.12, 0, -0.12) };
		}
		explosionAura = new Polygon3D(v, v[0], v[1], v[3], Main.textures[21],
				1, 1, 2);

		this.size = size;

		modelType = 4;
		makeBoundary(LENGHT, HEIGHT, WIDTH);

		boundary2D = new Rectangle2D(XPOS, YPOS, LENGHTRECTANGLE, HEIGHTRECTANGLE);

		lifeSpan = 16;

		findCentre();

		groundZero = (int) (x * 4) + (129 - (int) (z * 4)) * 80;
	}

	public void update() {

		visible = true;

		if (explosionAura != null && damage != 0) {
			explosionAura.update();
			if (explosionAura.visible) {
				explosionAura.myTexture.Texture = explosionAura.myTexture.lightMapData[auraIndex];
				Rasterizer.rasterize(explosionAura);
			}
		}
		auraIndex++;

		if (!explicitDrawing)
			ModelDrawList.register(this);

		tempCentre.set(centre);
		tempCentre.subtract(Camera.position);
		tempCentre.rotate_XZ(Camera.XZ_angle);
		tempCentre.rotate_YZ(Camera.YZ_angle);

		if (lifeSpan == 15 && damage != 0) {
			ObstacleMap.damageType2Obstacles(damage, boundary2D, groundZero);
		}

		lifeSpan--;

		if (lifeSpan == 0) {
			lifeSpan = -1;
			return;
		}
	}

	public void draw() {

		tempCentre.updateLocation();
		double ratio = size * 2 / tempCentre.z;

		Rasterizer.temp = this.type;
		Rasterizer.renderExplosionSprite(
				Main.textures[spriteIndex].explosions[frameIndex], ratio,
				tempCentre.screenX, tempCentre.screenY, 64, 64);

		frameIndex++;

	}

	public Rectangle2D getBoundary2D() {
		return boundary2D;
	}
}
