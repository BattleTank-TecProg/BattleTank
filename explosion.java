public class explosion extends solidObject {

	double size;

	public int spriteIndex;

	public int frameIndex = 0;

	public int auraIndex = 0;

	public int damage = 5;

	public int type;

	public int groundZero;

	public boolean explicitDrawing;

	public polygon3D explosionAura;

	public explosion(double x, double y, double z, double size) {
		start = new vector(x, y, z);
		iDirection = new vector(1, 0, 0);
		jDirection = new vector(0, 1, 0);
		kDirection = new vector(0, 0, 1);

		int random = gameData.getRandom();
		if (random >= 75)
			spriteIndex = 17;
		else if (random >= 50)
			spriteIndex = 18;
		else if (random >= 25)
			spriteIndex = 19;
		else
			spriteIndex = 20;

		if (size > 1)
			spriteIndex = 18;

		vector[] v = new vector[] { put(-0.3, 0, 0.3), put(0.3, 0, 0.3),
				put(0.3, 0, -0.3), put(-0.3, 0, -0.3) };
		if (size > 3)
			v = new vector[] { put(-0.12, 0, 0.12), put(0.12, 0, 0.12),
					put(0.12, 0, -0.12), put(-0.12, 0, -0.12) };

		explosionAura = new polygon3D(v, v[0], v[1], v[3], main.textures[21],
				1, 1, 2);

		this.size = size;

		modelType = 4;
		makeBoundary(0.001, 0.001, 0.001);

		boundary2D = new rectangle2D(x - 0.1, z + 0.1, 0.2, 0.2);

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
				rasterizer.rasterize(explosionAura);
			}
		}
		auraIndex++;

		if (!explicitDrawing)
			modelDrawList.register(this);

		tempCentre.set(centre);
		tempCentre.subtract(camera.position);
		tempCentre.rotate_XZ(camera.XZ_angle);
		tempCentre.rotate_YZ(camera.YZ_angle);

		if (lifeSpan == 15 && damage != 0) {
			obstacleMap.damageType2Obstacles(damage, boundary2D, groundZero);
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

		rasterizer.temp = this.type;
		rasterizer.renderExplosionSprite(
				main.textures[spriteIndex].explosions[frameIndex], ratio,
				tempCentre.screenX, tempCentre.screenY, 64, 64);

		frameIndex++;

	}

	public rectangle2D getBoundary2D() {
		return boundary2D;
	}
}
