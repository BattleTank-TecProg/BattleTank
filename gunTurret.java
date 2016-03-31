public class gunTurret extends SolidObject {
	private polygon3D[] body;

	private polygon3D[] turret;

	private polygon3D shadowBody;

	private polygon3D shadowTurret;

	private vector bodyCenter;

	private vector turretCenter;

	private int turretAngle;

	private int targetAngle;

	private int turretAngleDelta;

	private boolean aimRight, aimLeft, firing;

	private int coolDown = 20;

	private int currentCoolDown = 0;

	private int position;

	private smoke Smoke;

	private boolean isVisiblePreviousFrame;

	private vector tempVector1 = new vector(0, 0, 0);
	private vector tempVector2 = new vector(0, 0, 0);

	private boolean engaged;

	private double distance;

	private boolean clearToShoot;

	private boolean destoried;

	public gunTurret(double x, double y, double z, int angle) {
		start = new vector(x, y, z);
		iDirection = new vector(0.7, 0, 0);
		jDirection = new vector(0, 0.7, 0);
		kDirection = new vector(0, 0, 0.7);

		modelType = 2;
		makeBoundary(0.1, 0.25, 0.1);

		boundary2D = new rectangle2D(x - 0.13, z + 0.13, 0.26, 0.26);
		position = (int) (x * 4) + (129 - (int) (z * 4)) * 80;
		obstacleMap.registerObstacle2(this, position);

		findCentre();

		turretCenter = start.myClone();
		bodyCenter = centre;
		turretAngle = angle;

		makeBody();
		makeTurret();

		HP = 75;

		lifeSpan = 1;
	}

	private void makeBody() {
		vector[] v;

		body = new polygon3D[5];

		v = new vector[] { put(-0.09, 0.09, -0.09), put(0.09, 0.09, -0.09),
				put(0.12, 0, -0.12), put(-0.12, 0, -0.12) };
		body[0] = new polygon3D(v, v[0], v[1], v[3], main.textures[41], 1, 1, 6);

		v = new vector[] { put(-0.09, 0.09, 0.09), put(-0.09, 0.09, -0.09),
				put(-0.12, 0, -0.12), put(-0.12, 0, 0.12) };
		body[1] = new polygon3D(v, v[0], v[1], v[3], main.textures[41], 1, 1, 6);

		v = new vector[] { put(0.12, 0, 0.12), put(0.12, 0, -0.12),
				put(0.09, 0.09, -0.09), put(0.09, 0.09, 0.09) };
		body[2] = new polygon3D(v, v[0], v[1], v[3], main.textures[41], 1, 1, 6);

		v = new vector[] { put(-0.12, 0, 0.12), put(0.12, 0, 0.12),
				put(0.09, 0.09, 0.09), put(-0.09, 0.09, 0.09) };
		body[3] = new polygon3D(v, v[0], v[1], v[3], main.textures[41], 1, 1, 6);

		v = new vector[] { put(-0.09, 0.09, 0.09), put(0.09, 0.09, 0.09),
				put(0.09, 0.09, -0.09), put(-0.09, 0.09, -0.09) };
		body[4] = new polygon3D(v, v[0], v[1], v[3], main.textures[41], 1, 1, 6);

		tempVector1 = start.myClone();

		iDirection.scale(1.2);
		start.add(0, 0, -0.03);
		v = new vector[] { put(-0.2, 0, 0.2), put(0.2, 0, 0.2),
				put(0.2, 0, -0.2), put(-0.2, 0, -0.2) };
		shadowBody = new polygon3D(v, v[0], v[1], v[3], main.textures[50], 1,
				1, 2);

		for (int i = 0; i < body.length; i++) {
			body[i].Ambient_I = 22;
			body[i].diffuse_coefficient = 0.7;
			body[i].findDiffuse();

		}

		start.set(tempVector1);
		iDirection.scale(0.8333333333);

	}

	private void makeTurret() {
		iDirection = new vector(0.7, 0, 0);
		jDirection = new vector(0, 0.7, 0);
		kDirection = new vector(0, 0, 0.7);

		iDirection.rotate_XZ(turretAngle);
		kDirection.rotate_XZ(turretAngle);

		iDirection.rotate_XZ(180);
		kDirection.rotate_XZ(180);

		turret = new polygon3D[22];

		vector[] v;

		v = new vector[] { put(-0.04, 0.16, -0.08), put(0.04, 0.16, -0.08),
				put(0.04, 0.09, -0.08), put(-0.04, 0.09, -0.08) };
		turret[0] = new polygon3D(v, v[0], v[1], v[3], main.textures[51], 1,
				0.5, 6);

		v = new vector[] { put(-0.08, 0.16, 0.08), put(-0.04, 0.16, -0.08),
				put(-0.04, 0.09, -0.08), put(-0.08, 0.09, 0.08) };
		turret[1] = new polygon3D(v, v[0], v[1], v[3], main.textures[51], 1,
				0.5, 6);

		v = new vector[] { put(0.08, 0.16, 0.08), put(-0.08, 0.16, 0.08),
				put(-0.08, 0.09, 0.08), put(0.08, 0.09, 0.08) };
		turret[2] = new polygon3D(v, v[0], v[1], v[3], main.textures[51], 1,
				0.5, 6);

		v = new vector[] { put(0.04, 0.16, -0.08), put(0.08, 0.16, 0.08),
				put(0.08, 0.09, 0.08), put(0.04, 0.09, -0.08) };
		turret[3] = new polygon3D(v, v[0], v[1], v[3], main.textures[51], 1,
				0.5, 6);

		double r = 0.016;
		double r1 = 0.012;
		double theta = Math.PI / 8;
		start.add(0, 0.1, 0);

		for (int i = 0; i < 16; i++) {
			v = new vector[] {
					put(r1 * Math.cos(i * theta), r1 * Math.sin(i * theta),
							-0.22),
					put(r1 * Math.cos((i + 1) * theta),
							r1 * Math.sin((i + 1) * theta), -0.22),
					put(r * Math.cos((i + 1) * theta),
							r * Math.sin((i + 1) * theta), -0.08),
					put(r * Math.cos(i * theta), r * Math.sin(i * theta), -0.08) };
			turret[i + 4] = new polygon3D(v, v[0], v[1], v[3],
					main.textures[23], 0.001, 0.01, 6);
			turret[i + 4].Ambient_I = 18;
			turret[i + 4].diffuse_coefficient = 0.7;
			turret[i + 4].findDiffuse();
		}

		v = new vector[16];
		for (int i = 1; i < 17; i++)
			v[16 - i] = put(r1 * Math.cos(i * theta), r1 * Math.sin(i * theta),
					-0.22);
		turret[21] = new polygon3D(v, v[0], v[1], v[3], main.textures[23], 1,
				1, 6);

		start.add(0, -0.1, 0);

		v = new vector[] { put(-0.08, 0.16, 0.08), put(0.08, 0.16, 0.08),
				put(0.04, 0.16, -0.08), put(-0.04, 0.16, -0.08) };
		turret[20] = new polygon3D(v, v[0], v[1], put(-0.08, 0.16, -0.08),
				main.textures[51], 1, 1, 6);

		start.add(-0.05, 0, -0.05);
		start.y = -1;
		v = new vector[] { put(0.5, 0, -0.27), put(-0.5, 0, -0.27),
				put(-0.5, 0, 0.27), put(0.5, 0, 0.27) };
		shadowTurret = new polygon3D(v, v[0], v[1], v[3], main.textures[15], 1,
				1, 2);
		start.add(0.05, 0, 0.05);

		iDirection.rotate_XZ(180);
		kDirection.rotate_XZ(180);

	}

	public void update() {
		if (HP > 0 && !main.gamePaused)
			processAI();

		if (aimLeft) {
			if (Math.abs(turretAngle - targetAngle) <= 3) {
				turretAngleDelta = targetAngle - turretAngle;
				turretAngle += turretAngleDelta;
				if (turretAngleDelta < 0)
					turretAngleDelta += 360;
			} else {
				turretAngleDelta = 3;
				turretAngle += 3;
			}
			if (turretAngle >= 360)
				turretAngle -= 360;
		} else if (aimRight) {
			if (Math.abs(turretAngle - targetAngle) <= 3) {
				turretAngleDelta = targetAngle - turretAngle;
				turretAngle += turretAngleDelta;
				if (turretAngleDelta < 0)
					turretAngleDelta += 360;
			} else {
				turretAngleDelta = 357;
				turretAngle -= 3;
			}
			if (turretAngle < 0)
				turretAngle += 360;
		}

		tempCentre.set(centre);
		tempCentre.y = -1;
		tempCentre.subtract(Camera.position);
		tempCentre.rotate_XZ(Camera.XZ_angle);
		tempCentre.rotate_YZ(Camera.YZ_angle);
		tempCentre.updateLocation();

		visible = true;
		if (tempCentre.z < 0.9 || tempCentre.screenY < -10
				|| tempCentre.screenX < -400 || tempCentre.screenX > 800) {
			visible = false;
			isVisiblePreviousFrame = false;
		}

		if (visible) {
			if (isVisiblePreviousFrame == false) {

				makeTurret();
				isVisiblePreviousFrame = true;
			}
		}

		if (visible) {
			modelDrawList.register(this);

			for (int i = 0; i < 5; i++)
				boundary[i].update();

			for (int i = 0; i < body.length; i++) {
				body[i].update();
			}

			shadowBody.update();
			if (shadowBody.visible) {
				rasterizer.rasterize(shadowBody);
			}

			if (HP > 0) {

				for (int i = 0; i < turret.length; i++) {

					turret[i].origin.subtract(turretCenter);
					turret[i].origin.rotate_XZ(turretAngleDelta);
					turret[i].origin.add(turretCenter);

					turret[i].bottomEnd.subtract(turretCenter);
					turret[i].bottomEnd.rotate_XZ(turretAngleDelta);
					turret[i].bottomEnd.add(turretCenter);

					turret[i].rightEnd.subtract(turretCenter);
					turret[i].rightEnd.rotate_XZ(turretAngleDelta);
					turret[i].rightEnd.add(turretCenter);

					for (int j = 0; j < turret[i].vertex3D.length; j++) {

						turret[i].vertex3D[j].subtract(turretCenter);
						turret[i].vertex3D[j].rotate_XZ(turretAngleDelta);
						turret[i].vertex3D[j].add(turretCenter);
					}

					turret[i].findRealNormal();
					turret[i].findDiffuse();

					turret[i].update();
				}

				tempVector1.set(turretCenter);
				tempVector1.add(-0.05, 0, -0.05);

				shadowTurret.origin.subtract(tempVector1);
				shadowTurret.origin.rotate_XZ(turretAngleDelta);
				shadowTurret.origin.add(tempVector1);

				shadowTurret.bottomEnd.subtract(tempVector1);
				shadowTurret.bottomEnd.rotate_XZ(turretAngleDelta);
				shadowTurret.bottomEnd.add(tempVector1);

				shadowTurret.rightEnd.subtract(tempVector1);
				shadowTurret.rightEnd.rotate_XZ(turretAngleDelta);
				shadowTurret.rightEnd.add(tempVector1);

				for (int j = 0; j < shadowTurret.vertex3D.length; j++) {

					shadowTurret.vertex3D[j].subtract(tempVector1);
					shadowTurret.vertex3D[j].rotate_XZ(turretAngleDelta);
					shadowTurret.vertex3D[j].add(tempVector1);
				}
				shadowTurret.update();
				rasterizer.rasterize(shadowTurret);

			}
		}

		if (currentCoolDown > 0 && !main.gamePaused)
			currentCoolDown--;

		if (firing) {
			if (currentCoolDown == 0) {
				currentCoolDown = coolDown;

				vector direction = new vector(0, 0, 1);
				direction.rotate_XZ(turretAngle);
				direction.scale(0.06);
				projectiles
						.register(new shell(centre.x + direction.x, centre.y,
								centre.z + direction.z, turretAngle, true, 1));
			}
		}

		if (HP <= 37) {
			if (Smoke == null) {
				tempVector1 = getRealCentre().myClone();
				tempVector1.y += 0.1;
				Smoke = new smoke(tempVector1.myClone());
			} else {
				if (visible)
					Smoke.update();
			}
		}

		if (HP <= 0) {
			if (!destoried) {
				projectiles.register(new Explosion(centre.x, centre.y,
						centre.z, 2));
				modelType = 6;
				Smoke.stopped = true;
			}
			destoried = true;
		}

		aimRight = false;
		aimLeft = false;
		turretAngleDelta = 0;
		firing = false;

	}

	private void processAI() {

		tempVector1.set(centre);
		tempVector1.subtract(playerTank.bodyCenter);
		distance = tempVector1.getLength();

		if (distance < 2.3)
			engaged = true;
		else
			engaged = false;

		if (engaged) {
			if ((main.timer) % 5 == 0)
				obstacleMap.alertNearbyTanks(position);

			tempVector1.set(bodyCenter);
			tempVector2.set(playerTank.bodyCenter);
			tempVector2.subtract(tempVector1);
			tempVector2.unit();
			tempVector2.scale(0.125);

			clearToShoot = true;
			int obstacleType = -1;
			double d = 0;
			for (int i = 0; (d < distance) && (i < 35); i++, tempVector1
					.add(tempVector2), d += 0.125) {
				model temp = obstacleMap.isOccupied2(tempVector1);
				if (temp == null)
					continue;
				obstacleType = temp.getType();
				if (obstacleType == 1) {
					break;
				} else {
					clearToShoot = false;
					break;
				}

			}

			if (clearToShoot) {
				targetAngle = 90 + (int) (180 * Math
						.atan((centre.z - playerTank.bodyCenter.z)
								/ (centre.x - playerTank.bodyCenter.x)) / Math.PI);
				if (playerTank.bodyCenter.x > turretCenter.x
						&& targetAngle <= 180)
					targetAngle += 180;

			}

			int AngleDelta = turretAngle - targetAngle;
			if (Math.abs(AngleDelta) < 3 && clearToShoot && distance < 2.25)
				firing = true;

			if (AngleDelta > 0) {
				if (AngleDelta < 180)
					aimRight = true;
				else
					aimLeft = true;
			} else if (AngleDelta < 0) {
				if (AngleDelta > -180)
					aimLeft = true;
				else
					aimRight = true;
			}

		}
	}

	public void draw() {

		for (int i = 0; i < body.length; i++) {
			body[i].draw();

		}

		if (HP > 0) {
			for (int i = 0; i < turret.length; i++) {
				turret[i].draw();
			}
		}

		if (Smoke != null && visible)
			Smoke.draw();

	}

	public rectangle2D getBoundary2D() {
		return boundary2D;
	}

	public void damage(int damagePoint) {
		HP -= damagePoint;
	}
}
