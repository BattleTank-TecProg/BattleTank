public class rocket extends SolidObject {

	private polygon3D[] polygons;

	private int angle;

	private vector direction;

	private int angleDelta;

	private boolean isHostile;

	private polygon3D rocketAura;

	private vector targetLocation;

	private vector tempVector1 = new vector(0, 0, 0);

	private vector tempVector2 = new vector(0, 0, 0);

	public rocket(double x, double y, double z, int angle, boolean isHostile) {
		start = new vector(x, y, z);
		this.angle = angle;

		iDirection = new vector(1, 0, 0);
		jDirection = new vector(0, 1, 0);
		kDirection = new vector(0, 0, 1);

		this.isHostile = isHostile;

		modelType = 4;
		makeBoundary(0.01, 0.025, 0.01);

		boundary2D = new rectangle2D(x - 0.005, z + 0.005, 0.01, 0.01);

		iDirection.rotate_XZ(angle);
		kDirection.rotate_XZ(angle);

		direction = new vector(0, 0, 0.075);
		direction.rotate_XZ(angle);

		lifeSpan = 38;

		findCentre();

		makePolygons();

		vector[] v = new vector[] { put(-0.15, -0.05, 0.15),
				put(0.15, -0.05, 0.15), put(0.15, -0.05, -0.15),
				put(-0.15, -0.05, -0.15) };
		rocketAura = new polygon3D(v, v[0], v[1], v[3], main.textures[21], 1,
				1, 2);

	}

	public void makePolygons() {
		vector[] v;
		polygons = new polygon3D[17];

		double r = 0.007;
		double theta = Math.PI / 4;

		for (int i = 0; i < 8; i++) {
			v = new vector[] {
					put(r * Math.cos(i * theta), r * Math.sin(i * theta), -0.03),
					put(r * Math.cos((i + 1) * theta),
							r * Math.sin((i + 1) * theta), -0.03),
					put(r * Math.cos((i + 1) * theta),
							r * Math.sin((i + 1) * theta), 0.03),
					put(r * Math.cos(i * theta), r * Math.sin(i * theta), 0.03) };
			polygons[i] = new polygon3D(v, v[0], v[1], v[3], main.textures[25],
					1, 1, 6);
		}

		v = new vector[8];
		for (int i = 1; i < 9; i++)
			v[8 - i] = put(r * Math.cos(i * theta), r * Math.sin(i * theta),
					-0.03);
		polygons[8] = new polygon3D(v, v[0], v[1], v[3], main.textures[16], 1,
				1, 6);
		polygons[8].constantI = true;

		for (int i = 0; i < 8; i++) {
			v = new vector[] {
					put(r * Math.cos(i * theta), r * Math.sin(i * theta), 0.03),
					put(r * Math.cos((i + 1) * theta),
							r * Math.sin((i + 1) * theta), 0.03),
					put(0, 0, 0.05),

			};
			polygons[9 + i] = new polygon3D(v, v[0], v[1], v[2],
					main.textures[26], 1, 1, 6);
		}

	}

	public rectangle2D getBoundary2D() {
		return boundary2D;
	}

	public void update() {
		visible = true;

		lifeSpan--;

		if (rocketAura != null) {
			rocketAura.origin.add(direction);
			rocketAura.bottomEnd.add(direction);
			rocketAura.rightEnd.add(direction);

			for (int i = 0; i < rocketAura.vertex3D.length; i++) {
				rocketAura.vertex3D[i].add(direction);
			}
			rocketAura.update();

			rocketAura.myTexture.Texture = rocketAura.myTexture.lightMapData[1 + (GameData
					.getRandom() % 3) * 2];
			rasterizer.rasterize(rocketAura);

		}

		if (!isHostile) {
			tempVector1.set(playerTank.bodyCenter);
			tempVector2.set(0, 0, 0.15);
			tempVector2.rotate_XZ(playerTank.turretAngle);

			for (int i = 0; i < 20; i++, tempVector1.add(tempVector2)) {
				targetLocation = obstacleMap.isOccupied3(tempVector1);
				if (targetLocation != null)
					break;
			}

			if (targetLocation == null) {
				tempVector2.scale(1000);
				tempVector1.add(tempVector2);
				targetLocation = tempVector1.myClone();
			}
		} else {
			targetLocation = playerTank.bodyCenter;
		}

		int targetAngle = 90 + (int) (180 * Math
				.atan((centre.z - targetLocation.z)
						/ (centre.x - targetLocation.x)) / Math.PI);
		if (targetLocation.x > centre.x && targetAngle <= 180)
			targetAngle += 180;

		angleDelta = targetAngle - angle;

		if (angleDelta > 180) {
			angleDelta -= 360;
		}
		if (angleDelta < -180) {
			angleDelta += 360;
		}

		int angleDeltaAbs = Math.abs(angleDelta);

		if (angleDeltaAbs > 10) {
			angleDelta = angleDelta / angleDeltaAbs;
			angleDelta *= 10;
		}

		angle += angleDelta;

		angleDelta = (angleDelta + 360) % 360;

		direction.rotate_XZ(angleDelta);

		boundary2D.update(direction);

		int position = (int) (boundary2D.xPos * 4)
				+ (129 - (int) (boundary2D.yPos * 4)) * 80;
		if (obstacleMap.projectileCollideObstacle2(this, position, isHostile)) {
			lifeSpan = -1;
			centre.add(direction);
			Explosion theExplosion = new Explosion(centre.x, centre.y,
					centre.z, 1);
			theExplosion.damage = 10;
			projectiles.register(theExplosion);
			return;
		}

		modelDrawList.register(this);

		centre.add(direction);

		tempCentre.set(centre);
		tempCentre.y = -1;
		tempCentre.subtract(Camera.position);
		tempCentre.rotate_XZ(Camera.XZ_angle);
		tempCentre.rotate_YZ(Camera.YZ_angle);

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 4; j++)
				boundary[i].vertex3D[j].add(direction);
			boundary[i].update();
		}

		for (int i = 0; i < polygons.length; i++) {
			for (int j = 0; j < polygons[i].vertex3D.length; j++) {
				polygons[i].vertex3D[j].add(direction);
				polygons[i].vertex3D[j].subtract(centre);
				polygons[i].vertex3D[j].rotate_XZ(angleDelta);
				polygons[i].vertex3D[j].add(centre);
			}

			polygons[i].findRealNormal();
			polygons[i].findDiffuse();

			polygons[i].update();
		}

		if (lifeSpan < 0) {
			Explosion theExplosion = new Explosion(centre.x, centre.y,
					centre.z, 1);
			theExplosion.damage = 10;
			projectiles.register(theExplosion);
			return;
		}
		if (main.timer % 2 == 0) {
			centre.subtract(direction);

			projectiles.register(new rocketTail(centre));

			centre.add(direction);
		}
	}

	public void draw() {
		for (int i = 0; i < polygons.length; i++) {
			polygons[i].draw();
		}
	}

}
