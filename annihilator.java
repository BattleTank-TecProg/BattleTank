public class Annihilator extends SolidObject {

	private int bodyAngle = 0;

	private int turretAngle = 0;

	private int bodyAngleDelta = 0;

	private int turretAngleDelta = 0;

	private int position = 0;

	private int desiredPosition = 0;

	private int coolDownShell = 33;

	private int coolDownRocket = 33;

	private int targetAngle = 0;

	private int targetAngleBody = 0;

	private int previousTargetAngleBody = 0;

	private int countDownToDeath = 0;

	private int stuckCount = 0;

	private int randomNumber1 = 0;

	private int randomNumber2 = 0;

	private double distance = 0;

	private boolean forward;

	private boolean aimRight;

	private boolean aimLeft;

	private boolean firingShell;

	private boolean firingRocket;

	private boolean isVisiblePreviousFrame;

	public boolean active = true;

	private boolean engaged;

	private boolean clearToShoot;

	private vector bodyCenter;

	private polygon3D[] turret;

	private polygon3D shadowBody;

	private polygon3D shadowTurret;

	private vector turretCenter;

	private polygon3D[] body;

	private vector displacement = new vector(0, 0, 0);

	private vector tempVector1 = new vector(0, 0, 0);

	private vector tempVector2 = new vector(0, 0, 0);

	private smoke Smoke;

	public Annihilator(double x, double y, double z, int angle) {
		start = new vector(x, y, z);
		iDirection = new vector(1, 0, 0);
		jDirection = new vector(0, 1, 0);
		kDirection = new vector(0, 0, 1);

		modelType = 2;
		makeBoundary(0.1, 0.25, 0.1);

		boundary2D = new rectangle2D(x - 0.115, z + 0.115, 0.23, 0.23);
		position = (int) (x * 4) + (129 - (int) (z * 4)) * 80;
		desiredPosition = position;
		obstacleMap.registerObstacle2(this, position);

		findCentre();

		bodyCenter = centre;
		bodyAngle = angle;
		turretAngle = angle;

		makeBody();
		makeTurret();

		randomNumber1 = gameData.getRandom();

		HP = 400;

		lifeSpan = 1;
	}

	private void makeBody() {
		vector[] v;
		start = bodyCenter.myClone();

		iDirection = new vector(0.95, 0, 0);
		jDirection = new vector(0, 1, 0);
		kDirection = new vector(0, 0, 1);

		iDirection.rotate_XZ(bodyAngle);
		kDirection.rotate_XZ(bodyAngle);

		body = new polygon3D[19];

		v = new vector[] { put(0.1, 0, 0.15), put(0.06, 0, 0.15),
				put(0.06, -0.04, 0.14), put(0.1, -0.04, 0.14) };
		body[0] = new polygon3D(v, v[0], v[1], v[3], main.textures[12], 1, 0.5,
				6);

		v = new vector[] { put(-0.1, -0.04, 0.14), put(-0.06, -0.04, 0.14),
				put(-0.06, 0, 0.15), put(-0.1, 0, 0.15) };
		body[1] = new polygon3D(v, v[0], v[1], v[3], main.textures[12], 1, 0.5,
				6);

		v = new vector[] { put(0.06, 0, -0.14), put(0.1, 0, -0.14),
				put(0.1, -0.04, -0.12), put(0.06, -0.04, -0.12) };
		body[2] = new polygon3D(v, v[0], v[1], v[3], main.textures[12], 1, 0.5,
				6);

		v = new vector[] { put(-0.06, -0.04, -0.12), put(-0.1, -0.04, -0.12),
				put(-0.1, 0, -0.14), put(-0.06, 0, -0.14) };
		body[3] = new polygon3D(v, v[0], v[1], v[3], main.textures[12], 1, 0.5,
				6);

		int i = 4;

		v = new vector[] { put(0.06, 0.06, 0.13), put(0.06, 0.06, 0.08),
				put(0.06, -0.01, 0.08), put(0.06, -0.01, 0.15) };
		body[0 + i] = new polygon3D(v, v[0], v[1], v[3], main.textures[58],
				0.8, 1.1, 6);

		v = new vector[] { put(-0.06, -0.01, 0.15), put(-0.06, -0.01, 0.08),
				put(-0.06, 0.06, 0.08), put(-0.06, 0.06, 0.13) };
		body[1 + i] = new polygon3D(v, v[0], v[1], v[3], main.textures[58],
				0.8, 1.1, 6);

		v = new vector[] { put(-0.06, 0.06, 0.09), put(0.06, 0.06, 0.09),
				put(0.06, 0.06, -0.13), put(-0.06, 0.06, -0.13) };
		body[2 + i] = new polygon3D(v, v[0], v[1], v[3], main.textures[58],
				0.8, 1.1, 6);

		v = new vector[] { put(0.06, 0.06, 0.09), put(-0.06, 0.06, 0.09),
				put(-0.06, 0, 0.15), put(0.06, 0, 0.15) };
		body[3 + i] = new polygon3D(v, v[0], v[1], v[3], main.textures[58],
				0.8, 0.4, 6);

		v = new vector[] { put(-0.1, 0.06, -0.13), put(0.1, 0.06, -0.13),
				put(0.1, 0, -0.14), put(-0.1, 0, -0.14) };
		body[4 + i] = new polygon3D(v, v[0], v[1], v[3], main.textures[58],
				0.8, 0.3, 6);

		v = new vector[] { put(0.06, 0.06, 0.13), put(0.1, 0.06, 0.13),
				put(0.1, 0.06, -0.13), put(0.06, 0.06, -0.13) };
		body[5 + i] = new polygon3D(v, v[0], v[1], v[3], main.textures[58],
				0.3, 0.8, 6);

		v = new vector[] { put(-0.06, 0.06, -0.13), put(-0.1, 0.06, -0.13),
				put(-0.1, 0.06, 0.13), put(-0.06, 0.06, 0.13) };
		body[6 + i] = new polygon3D(v, v[0], v[1], v[3], main.textures[58],
				0.3, 0.8, 6);

		v = new vector[] { put(0.1, 0.06, 0.13), put(0.06, 0.06, 0.13),
				put(0.06, 0., 0.15), put(0.1, 0., 0.15) };
		body[7 + i] = new polygon3D(v, v[0], v[1], v[3], main.textures[58],
				0.8, 1.1, 6);

		v = new vector[] { put(-0.1, 0., 0.15), put(-0.06, 0., 0.15),
				put(-0.06, 0.06, 0.13), put(-0.1, 0.06, 0.13) };
		body[8 + i] = new polygon3D(v, v[0], v[1], v[3], main.textures[58],
				0.8, 1.1, 6);

		v = new vector[] { put(0.1, 0.06, -0.13), put(0.1, 0.06, 0.13),
				put(0.1, 0, 0.15), put(0.1, 0, -0.14) };
		body[9 + i] = new polygon3D(v, v[0], v[1], v[3], main.textures[58],
				0.8, 0.2, 6);

		v = new vector[] { put(-0.1, 0, -0.14), put(-0.1, 0, 0.15),
				put(-0.1, 0.06, 0.13), put(-0.1, 0.06, -0.13) };
		body[10 + i] = new polygon3D(v, v[0], v[1], v[3], main.textures[58],
				0.8, 0.2, 6);

		v = new vector[] { put(0.1, 0, 0.01), put(0.1, 0, 0.15),
				put(0.1, -0.04, 0.14), put(0.1, -0.04, 0.03) };
		body[11 + i] = new polygon3D(v, put(0.1, 0.1, 0.03),
				put(0.1, 0.1, 0.13), put(0.1, -0.04, 0.03), main.textures[12],
				1, 0.5, 6);

		v = new vector[] { put(0.1, 0, -0.14), put(0.1, 0, -0.01),
				put(0.1, -0.04, -0.03), put(0.1, -0.04, -0.12) };
		body[12 + i] = new polygon3D(v, put(0.1, 0.1, -0.15), put(0.1, 0.1,
				-0.01), put(0.1, -0.04, -0.15), main.textures[12], 1, 0.5, 6);

		v = new vector[] { put(-0.1, -0.04, 0.03), put(-0.1, -0.04, 0.14),
				put(-0.1, 0, 0.15), put(-0.1, 0, 0.01) };
		body[13 + i] = new polygon3D(v, put(-0.1, 0.1, 0.03), put(-0.1, 0.1,
				0.13), put(-0.1, -0.04, 0.03), main.textures[12], 1, 0.5, 6);

		v = new vector[] { put(-0.1, -0.04, -0.12), put(-0.1, -0.04, -0.03),
				put(-0.1, 0, -0.01), put(-0.1, 0, -0.14) };
		body[14 + i] = new polygon3D(v, put(-0.1, 0.1, -0.15), put(-0.1, 0.1,
				-0.01), put(-0.1, -0.04, -0.15), main.textures[12], 1, 0.5, 6);

		turretCenter = put(0, 0.07, -0);

		start.add(-0.015, 0, -0.015);
		start.y = -1;
		v = new vector[] { put(-0.3, 0, 0.3), put(0.3, 0, 0.3),
				put(0.3, 0, -0.3), put(-0.3, 0, -0.3) };
		shadowBody = new polygon3D(v, v[0], v[1], v[3], main.textures[14], 1,
				1, 2);

	}

	private void makeTurret() {
		start = turretCenter.myClone();
		vector[] v;

		iDirection = new vector(1.6, 0, 0);
		jDirection = new vector(0, 1.4, 0);
		kDirection = new vector(0, 0, 1.4);

		iDirection.rotate_XZ(turretAngle);
		kDirection.rotate_XZ(turretAngle);

		turret = new polygon3D[23];

		v = new vector[] { put(0.04, 0.035, 0.06), put(-0.04, 0.035, 0.06),
				put(-0.04, 0, 0.06), put(0.04, 0, 0.06) };
		turret[0] = new polygon3D(v, v[0], v[1], v[3], main.textures[59], 0.6,
				0.3, 6);

		v = new vector[] { put(0.02, 0.025, 0.18), put(0.026, 0.015, 0.18),
				put(0.028, 0.015, 0.06), put(0.02, 0.025, 0.06) };
		turret[1] = new polygon3D(v, v[0], v[1], v[3], main.textures[60], 0.1,
				1, 6);

		v = new vector[] { put(0.02, 0.025, 0.06),
				put(-0.008 + 0.02, 0.015, 0.06),
				put(-0.006 + 0.02, 0.015, 0.18), put(0.02, 0.025, 0.18) };
		turret[2] = new polygon3D(v, v[0], v[1], v[3], main.textures[60], 0.1,
				1, 6);

		v = new vector[] { put(-0.02, 0.025, 0.18),
				put(0.006 - 0.02, 0.015, 0.18), put(0.008 - 0.02, 0.015, 0.06),
				put(-0.02, 0.025, 0.06) };
		turret[3] = new polygon3D(v, v[0], v[1], v[3], main.textures[60], 0.1,
				1, 6);

		v = new vector[] { put(-0.02, 0.025, 0.06), put(-0.028, 0.015, 0.06),
				put(-0.026, 0.015, 0.18), put(-0.02, 0.025, 0.18) };
		turret[4] = new polygon3D(v, v[0], v[1], v[3], main.textures[60], 0.1,
				1, 6);

		v = new vector[] { put(-0.04, 0.035, 0.06), put(0.04, 0.035, 0.06),
				put(0.05, 0.035, 0.04), put(0.05, 0.035, -0.03),
				put(0.03, 0.035, -0.07), put(-0.03, 0.035, -0.07),
				put(-0.05, 0.035, -0.03), put(-0.05, 0.035, 0.04) };
		turret[5] = new polygon3D(v, put(-0.04, 0.035, 0.19), put(0.04, 0.035,
				0.19), put(-0.04, 0.035, 0.09), main.textures[59], 0.6, 0.6, 6);

		v = new vector[] { put(0.03, 0, -0.07), put(-0.03, 0, -0.07),
				put(-0.03, 0.035, -0.07), put(0.03, 0.035, -0.07) };
		turret[6] = new polygon3D(v, v[0], v[1], v[3], main.textures[59], 0.4,
				0.2, 6);

		v = new vector[] { put(0.03, 0.035, -0.07), put(0.05, 0.035, -0.03),
				put(0.05, 0, -0.03), put(0.03, 0, -0.07) };
		turret[7] = new polygon3D(v, v[0], v[1], v[3], main.textures[59], 0.4,
				0.2, 6);

		v = new vector[] { put(-0.03, 0, -0.07), put(-0.05, 0, -0.03),
				put(-0.05, 0.035, -0.03), put(-0.03, 0.035, -0.07) };
		turret[8] = new polygon3D(v, v[0], v[1], v[3], main.textures[59], 0.4,
				0.2, 6);

		v = new vector[] { put(0.05, 0.035, -0.03), put(0.05, 0.035, 0.04),
				put(0.05, 0, 0.04), put(0.05, 0, -0.03) };
		turret[9] = new polygon3D(v, v[0], v[1], v[3], main.textures[59], 0.5,
				0.3, 6);

		v = new vector[] { put(-0.05, 0, -0.03), put(-0.05, 0, 0.04),
				put(-0.05, 0.035, 0.04), put(-0.05, 0.035, -0.03) };
		turret[10] = new polygon3D(v, v[0], v[1], v[3], main.textures[59], 0.5,
				0.3, 6);

		v = new vector[] { put(0.05, 0.035, 0.04), put(0.04, 0.035, 0.06),
				put(0.04, 0, 0.06), put(0.05, 0, 0.04) };
		turret[11] = new polygon3D(v, v[0], v[1], v[3], main.textures[59], 0.3,
				0.3, 6);

		v = new vector[] { put(-0.05, 0, 0.04), put(-0.04, 0, 0.06),
				put(-0.04, 0.035, 0.06), put(-0.05, 0.035, 0.04) };
		turret[12] = new polygon3D(v, v[0], v[1], v[3], main.textures[59], 0.3,
				0.3, 6);

		v = new vector[] { put(-0.075, 0.05, 0.02), put(-0.05, 0.05, 0.02),
				put(-0.05, 0.05, -0.04), put(-0.075, 0.05, -0.04) };
		turret[13] = new polygon3D(v, v[0], v[1], v[3], main.textures[35], 0.5,
				0.5, 6);

		v = new vector[] { put(-0.075, 0.05, 0.02), put(-0.075, 0.05, -0.04),
				put(-0.075, 0.02, -0.04), put(-0.075, 0.02, 0.02) };
		turret[14] = new polygon3D(v, v[0], v[1], v[3], main.textures[35], 0.5,
				0.5, 6);

		v = new vector[] { put(-0.075, 0.05, -0.04), put(-0.05, 0.05, -0.04),
				put(-0.05, 0.02, -0.04), put(-0.075, 0.02, -0.04) };
		turret[15] = new polygon3D(v, v[0], v[1], v[3], main.textures[35], 0.5,
				0.5, 6);

		v = new vector[] { put(-0.05, 0.05, -0.04), put(-0.05, 0.05, 0.02),
				put(-0.05, 0.035, 0.02), put(-0.05, 0.035, -0.04) };
		turret[16] = new polygon3D(v, v[0], v[1], v[3], main.textures[35], 0.5,
				0.5, 6);

		int r = 150 / 8;
		int g = 150 / 8;
		int b = 150 / 8;
		short color = (short) ((int) r << 10 | (int) g << 5 | (int) b);

		v = new vector[] { put(-0.075, 0.02, 0.02), put(-0.05, 0.02, 0.02),
				put(-0.05, 0.05, 0.02), put(-0.075, 0.05, 0.02) };
		turret[17] = new polygon3D(v, v[0], v[1], v[3], null, 0.5, 0.5, 7);
		turret[17].color = color;

		v = new vector[] { put(0.075, 0.05, -0.04), put(0.05, 0.05, -0.04),
				put(0.05, 0.05, 0.02), put(0.075, 0.05, 0.02) };
		turret[18] = new polygon3D(v, v[0], v[1], v[3], main.textures[35], 0.5,
				0.5, 6);

		v = new vector[] { put(0.075, 0.02, 0.02), put(0.075, 0.02, -0.04),
				put(0.075, 0.05, -0.04), put(0.075, 0.05, 0.02) };
		turret[19] = new polygon3D(v, v[0], v[1], v[3], main.textures[35], 0.5,
				0.5, 6);

		v = new vector[] { put(0.075, 0.02, -0.04), put(0.05, 0.02, -0.04),
				put(0.05, 0.05, -0.04), put(0.075, 0.05, -0.04) };
		turret[20] = new polygon3D(v, v[0], v[1], v[3], main.textures[35], 0.5,
				0.5, 6);

		v = new vector[] { put(0.05, 0.035, -0.04), put(0.05, 0.035, 0.02),
				put(0.05, 0.05, 0.02), put(0.05, 0.05, -0.04) };
		turret[21] = new polygon3D(v, v[0], v[1], v[3], main.textures[35], 0.5,
				0.5, 6);

		v = new vector[] { put(0.075, 0.05, 0.02), put(0.05, 0.05, 0.02),
				put(0.05, 0.02, 0.02), put(0.075, 0.02, 0.02) };
		turret[22] = new polygon3D(v, v[0], v[1], v[3], null, 0.5, 0.5, 7);
		turret[22].color = color;

		start.add(-0.03, 0, -0.04);
		start.y = -1;
		v = new vector[] { put(-0.18, 0, 0.18), put(0.18, 0, 0.18),
				put(0.18, 0, -0.18), put(-0.18, 0, -0.18) };
		shadowTurret = new polygon3D(v, v[0], v[1], v[3], main.textures[61], 1,
				1, 2);

	}

	public void update() {
		if ((main.timer + randomNumber1 * 3) % 1000 == 0) {
			if (randomNumber2 > 50) {
				randomNumber2 = 50;
			} else {
				randomNumber2 = 51;
			}
		}

		if (countDownToDeath <= 0 && active && !main.gamePaused) {
			processAI();
		}

		if (aimLeft) {
			if (Math.abs(turretAngle - targetAngle) <= 3) {
				turretAngleDelta = targetAngle - turretAngle;
				turretAngle += turretAngleDelta;
				if (turretAngleDelta < 0) {
					turretAngleDelta += 360;
				} else {
					// Dont doing nothing.
				}
			} else {
				turretAngleDelta = 3;
				turretAngle += 3;
			}
			if (turretAngle >= 360) {
				turretAngle -= 360;
			} else {
				// Dont doing nothing.
			}
		} else if (aimRight) {
			if (Math.abs(turretAngle - targetAngle) <= 3) {
				turretAngleDelta = targetAngle - turretAngle;
				turretAngle += turretAngleDelta;
				if (turretAngleDelta < 0) {
					turretAngleDelta += 360;
				} else {
					// Dont doing nothing.
				}
			} else {
				turretAngleDelta = 357;
				turretAngle -= 3;
			}
			if (turretAngle < 0) {
				turretAngle += 360;
			} else {
				// Dont doing nothing.
			}
		}

		if (forward) {
			int delta = targetAngleBody - bodyAngle;
			if (Math.abs(delta) < 5 || Math.abs(delta) > 355) {
				bodyAngle = targetAngleBody;
				bodyAngleDelta = (delta + 720) % 360;
				displacement.set(0, 0, 0.01);
				displacement.rotate_XZ(bodyAngle);
			} else {
				displacement.set(0, 0, 0);
				if (delta > 0) {
					if (delta < 180) {
						bodyAngleDelta = 5;
					} else {
						bodyAngleDelta = 355;
					}
				}
				if (delta < 0) {
					if (delta > -180) {
						bodyAngleDelta = 355;
					} else {
						bodyAngleDelta = 5;
					}
				}

				bodyAngle = (bodyAngle + bodyAngleDelta) % 360;
			}
		}

		centre.add(displacement);

		boundary2D.update(displacement);

		int newPosition = (int) (boundary2D.xPos * 4)
				+ (129 - (int) (boundary2D.yPos * 4)) * 80;
		if (!obstacleMap.isOccupied(newPosition)) {
			obstacleMap.removeObstacle2(position);
			obstacleMap.registerObstacle2(this, newPosition);
			position = newPosition;
			desiredPosition = newPosition;
		} else if (!obstacleMap.isOccupied(desiredPosition)) {
			obstacleMap.removeObstacle2(position);
			obstacleMap.registerObstacle2(this, desiredPosition);
			position = desiredPosition;
		} else {
			desiredPosition = newPosition;
		}

		tempCentre.set(centre);
		tempCentre.y = -1;
		tempCentre.subtract(camera.position);
		tempCentre.rotate_XZ(camera.XZ_angle);
		tempCentre.rotate_YZ(camera.YZ_angle);
		tempCentre.updateLocation();

		visible = true;

		if (tempCentre.z < 0.9 || tempCentre.screenY < -10
				|| tempCentre.screenX < -400 || tempCentre.screenX > 800) {
			visible = false;
			isVisiblePreviousFrame = false;
		} else {
			// Dont doing nothing
		}

		if (visible) {
			if (isVisiblePreviousFrame == false) {
				makeBody();
				makeTurret();
				isVisiblePreviousFrame = true;
			} else {
				// Dont doing nothing
			}
		} else {
			// Dont doing nothing
		}

		if (visible) {
			modelDrawList.register(this);

			if (countDownToDeath < 3) {

				for (int i = 0; i < body.length; i++) {
					body[i].origin.add(displacement);
					body[i].origin.subtract(centre);
					body[i].origin.rotate_XZ(bodyAngleDelta);
					body[i].origin.add(centre);

					body[i].bottomEnd.add(displacement);
					body[i].bottomEnd.subtract(centre);
					body[i].bottomEnd.rotate_XZ(bodyAngleDelta);
					body[i].bottomEnd.add(centre);

					body[i].rightEnd.add(displacement);
					body[i].rightEnd.subtract(centre);
					body[i].rightEnd.rotate_XZ(bodyAngleDelta);
					body[i].rightEnd.add(centre);

					for (int j = 0; j < body[i].vertex3D.length; j++) {
						body[i].vertex3D[j].add(displacement);
						body[i].vertex3D[j].subtract(centre);
						body[i].vertex3D[j].rotate_XZ(bodyAngleDelta);
						body[i].vertex3D[j].add(centre);
					}

					body[i].findRealNormal();
					body[i].findDiffuse();

					body[i].update();
				}

				tempVector1.set(centre);
				tempVector1.add(-0.03, 0, -0.04);
				shadowBody.origin.add(displacement);
				shadowBody.origin.subtract(tempVector1);
				shadowBody.origin.rotate_XZ(bodyAngleDelta);
				shadowBody.origin.add(tempVector1);

				shadowBody.bottomEnd.add(displacement);
				shadowBody.bottomEnd.subtract(tempVector1);
				shadowBody.bottomEnd.rotate_XZ(bodyAngleDelta);
				shadowBody.bottomEnd.add(tempVector1);

				shadowBody.rightEnd.add(displacement);
				shadowBody.rightEnd.subtract(tempVector1);
				shadowBody.rightEnd.rotate_XZ(bodyAngleDelta);
				shadowBody.rightEnd.add(tempVector1);

				for (int j = 0; j < shadowBody.vertex3D.length; j++) {
					shadowBody.vertex3D[j].add(displacement);
					shadowBody.vertex3D[j].subtract(tempVector1);
					shadowBody.vertex3D[j].rotate_XZ(bodyAngleDelta);
					shadowBody.vertex3D[j].add(tempVector1);
				}

				shadowBody.update();
				rasterizer.rasterize(shadowBody);

				turretCenter.add(displacement);

				for (int i = 0; i < turret.length; i++) {
					turret[i].origin.add(displacement);
					turret[i].origin.subtract(turretCenter);
					turret[i].origin.rotate_XZ(turretAngleDelta);
					turret[i].origin.add(turretCenter);

					turret[i].bottomEnd.add(displacement);
					turret[i].bottomEnd.subtract(turretCenter);
					turret[i].bottomEnd.rotate_XZ(turretAngleDelta);
					turret[i].bottomEnd.add(turretCenter);

					turret[i].rightEnd.add(displacement);
					turret[i].rightEnd.subtract(turretCenter);
					turret[i].rightEnd.rotate_XZ(turretAngleDelta);
					turret[i].rightEnd.add(turretCenter);

					for (int j = 0; j < turret[i].vertex3D.length; j++) {
						turret[i].vertex3D[j].add(displacement);
						turret[i].vertex3D[j].subtract(turretCenter);
						turret[i].vertex3D[j].rotate_XZ(turretAngleDelta);
						turret[i].vertex3D[j].add(turretCenter);
					}

					turret[i].findRealNormal();
					turret[i].findDiffuse();

					turret[i].update();
				}

				tempVector1.set(turretCenter);
				tempVector1.add(-0.03, 0, -0.04);

				shadowTurret.origin.add(displacement);
				shadowTurret.origin.subtract(tempVector1);
				shadowTurret.origin.rotate_XZ(turretAngleDelta);
				shadowTurret.origin.add(tempVector1);

				shadowTurret.bottomEnd.add(displacement);
				shadowTurret.bottomEnd.subtract(tempVector1);
				shadowTurret.bottomEnd.rotate_XZ(turretAngleDelta);
				shadowTurret.bottomEnd.add(tempVector1);

				shadowTurret.rightEnd.add(displacement);
				shadowTurret.rightEnd.subtract(tempVector1);
				shadowTurret.rightEnd.rotate_XZ(turretAngleDelta);
				shadowTurret.rightEnd.add(tempVector1);

				for (int j = 0; j < shadowTurret.vertex3D.length; j++) {
					shadowTurret.vertex3D[j].add(displacement);
					shadowTurret.vertex3D[j].subtract(tempVector1);
					shadowTurret.vertex3D[j].rotate_XZ(turretAngleDelta);
					shadowTurret.vertex3D[j].add(tempVector1);
				}
				shadowTurret.update();
				rasterizer.rasterize(shadowTurret);
			} else {
				// Dont doing nothing
			}
		} else {
			// Dont doing nothing
		}

		if (coolDownShell > 0 && coolDownShell != 92 && !main.gamePaused) {
			coolDownShell--;
		} else {
			// Dont doing nothing
		}

		if (coolDownRocket > 0 && coolDownRocket != 90 && !main.gamePaused) {
			coolDownRocket--;
		} else {
			// Dont doing nothing
		}

		if (firingShell) {
			if (coolDownShell == 0) {
				coolDownShell = 100;
				vector tempVector1 = new vector(0, 0, 1);
				tempVector1.rotate_XZ((turretAngle + 270) % 360);
				tempVector1.scale(0.035);
				vector direction = new vector(0, 0, 1);
				direction.rotate_XZ(turretAngle);
				direction.scale(0.1);
				direction.add(turretCenter);
				direction.add(tempVector1);
				projectiles.register(new shell(direction.x, direction.y,
						direction.z, turretAngle, true, 1));

			} else {
				// Dont doing nothing
			}

			if (coolDownShell == 92) {
				coolDownShell = 25;
				vector tempVector1 = new vector(0, 0, 1);
				tempVector1.rotate_XZ((turretAngle + 270) % 360);
				tempVector1.scale(-0.035);
				vector direction = new vector(0, 0, 1);
				direction.rotate_XZ(turretAngle);
				direction.scale(0.1);
				direction.add(turretCenter);
				direction.add(tempVector1);
				projectiles.register(new shell(direction.x, direction.y,
						direction.z, turretAngle, true, 1));
			} else {
				// Dont doing nothing
			}
		} else {
			// Dont doing nothing
		}

		if (firingRocket) {

			if (coolDownRocket == 0) {
				coolDownRocket = 100;
				vector tempVector1 = new vector(0, 0, 1);
				tempVector1.rotate_XZ((turretAngle + 270) % 360);
				tempVector1.scale(0.095);
				vector direction = new vector(0, 0, 1);
				direction.rotate_XZ(turretAngle);
				direction.scale(0.05);
				direction.add(turretCenter);
				direction.add(tempVector1);

				rocket r = new rocket(direction.x, direction.y, direction.z,
						turretAngle, true);
				projectiles.register(r);
			} else {
				// Dont doing nothing
			}

			if (coolDownRocket == 90) {
				coolDownRocket = 45;
				vector tempVector1 = new vector(0, 0, 1);
				tempVector1.rotate_XZ((turretAngle + 270) % 360);
				tempVector1.scale(-0.095);
				vector direction = new vector(0, 0, 1);
				direction.rotate_XZ(turretAngle);
				direction.scale(0.05);
				direction.add(turretCenter);
				direction.add(tempVector1);
				rocket r = new rocket(direction.x, direction.y, direction.z,
						turretAngle, true);
				projectiles.register(r);
			} else {
				// Dont doing nothing
			}
		} else {
			// Dont doing nothing
		}

		if (HP <= 200) {
			if (Smoke == null) {
				Smoke = new smoke(this);
			} else {
				if (visible) {
					Smoke.update();
				} else {
					// Dont doing nothing
				}
			}
		} else {
			// Dont doing nothing
		}

		if (HP <= 0) {
			countDownToDeath++;
			if (countDownToDeath >= 3) {
				if (countDownToDeath == 3) {
					projectiles.register(new explosion(centre.x, centre.y,
							centre.z, 2));

				} else {
					// Dont doing nothing
				}
				obstacleMap.removeObstacle2(position);
				Smoke.stopped = true;
			} else {
				// Dont doing nothing
			}
			if (countDownToDeath >= 40) {
				lifeSpan = 0;
			} else {
				// Dont doing nothing
			}
		} else {
			// Dont doing nothing
		}

		forward = false;
		aimRight = false;
		aimLeft = false;
		bodyAngleDelta = 0;
		turretAngleDelta = 0;
		displacement.reset();
		firingRocket = false;
		firingShell = false;
		if (main.timer % 10 == 0) {
			unstuck = false;
		} else {
			// Dont doing nothing
		}
	}

	private void processAI() {
		tempVector1.set(centre);
		tempVector1.subtract(playerTank.bodyCenter);
		distance = tempVector1.getLength();

		if (distance < 2) {
			engaged = true;
		} else {
			// Dont doing nothing
		}

		if (distance > 6) {
			engaged = false;
			targetAngle = bodyAngle;
			int AngleDelta = turretAngle - targetAngle;
			if (AngleDelta > 0) {
				if (AngleDelta < 180) {
					aimRight = true;
				} else {
					aimLeft = true;
				}
			} else if (AngleDelta < 0) {
				if (AngleDelta > -180) {
					aimLeft = true;
				} else {
					aimRight = true;
				}
			}
			return;
		} else {
			// Dont doing nothing
		}

		if (engaged) {
			if ((main.timer) % 5 == 0) {
				obstacleMap.alertNearbyTanks(position);
			} else {
				// Dont doing nothing
			}
			tempVector1.set(bodyCenter);
			tempVector2.set(playerTank.bodyCenter);
			tempVector2.subtract(tempVector1);
			tempVector2.unit();
			tempVector2.scale(0.125);

			clearToShoot = true;
			int obstacleType = -1;
			double d = 0;
			for (int i = 0; (d < distance) && (i < 30); i++, tempVector1
					.add(tempVector2), d += 0.125) {
				model temp = obstacleMap.isOccupied2(tempVector1);
				if (temp == null) {
					continue;
				} else {
					// Dont doing nothing
				}
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
						&& targetAngle <= 180) {
					targetAngle += 180;
				} else {
					// Dont doing nothing
				}

			} else {
				targetAngle = bodyAngle;

			}

			int AngleDelta = turretAngle - targetAngle;
			if (Math.abs(AngleDelta) < 3 && clearToShoot && distance < 1.7) {
				firingShell = true;
			} else {
				// Dont doing nothing
			}
			if (Math.abs(AngleDelta) < 3 && clearToShoot && distance < 3) {
				firingRocket = true;
			} else {
				// Dont doing nothing
			}
			if (AngleDelta > 0) {
				if (AngleDelta < 180) {
					aimRight = true;
				} else {
					aimLeft = true;
				}
			} else if (AngleDelta < 0) {
				if (AngleDelta > -180) {
					aimLeft = true;
				} else {
					aimRight = true;
				}
			}

			forward = true;
			if (clearToShoot && distance < 1.5) {
				if (distance < 1.4) {
					forward = false;
				} else {
					// Dont doing nothing
				}
				if (distance >= 1.4) {
					if (randomNumber2 > 50) {
						forward = false;
					} else {
						// Dont doing nothing
					}
				} else {
					// Dont doing nothing
				}
			} else {
				// Dont doing nothing
			}

			if (unstuck && distance > 0.8) {
				forward = true;
				obstacleMap.giveWay(this, position);
			} else {
				// Dont doing nothing
			}

			if (forward) {
				targetAngleBody = 90 + (int) (180 * Math
						.atan((centre.z - playerTank.bodyCenter.z)
								/ (centre.x - playerTank.bodyCenter.x)) / Math.PI);
				if (playerTank.bodyCenter.x > centre.x
						&& targetAngleBody <= 180) {
					targetAngleBody += 180;
				} else {
					// Dont doing nothing
				}

				if (!clearToShoot
						&& (distance < 1.2 || (obstacleType == 6 && distance < 2.5))
						|| stuckCount == 10) {
					if (stuckCount == 10) {
						if (randomNumber2 > 50) {
							randomNumber2 = 50;
						} else {
							randomNumber2 = 51;
						}
						stuckCount = 0;
					} else {
						// Dont doing nothing
					}

					if (randomNumber2 > 50) {
						targetAngleBody += 90;
					} else {
						targetAngleBody -= 90;
					}

					targetAngleBody = (targetAngleBody + 360) % 360;
				} else {
					// Dont doing nothing
				}

				int newPosition = (int) (boundary2D.xPos * 4)
						+ (129 - (int) (boundary2D.yPos * 4)) * 80;

				displacement.set(0, 0, 0.01);
				displacement.rotate_XZ(targetAngleBody);
				boundary2D.update(displacement);

				boolean canMove = true;
				if (obstacleMap.collideWithObstacle1(this, newPosition)) {
					forward = false;
					canMove = false;
				} else if (obstacleMap.collideWithObstacle2(this, newPosition)) {
					forward = false;
					canMove = false;
				}
				displacement.scale(-1);
				boundary2D.update(displacement);
				displacement.reset();

				if (!canMove) {
					if (unstuck) {
						obstacleMap.giveWay(this, position);
					} else {
						// Dont doing nothing
					}

					targetAngleBody = targetAngle;
					int angle1 = targetAngleBody - targetAngleBody % 90;
					int angle2 = angle1 + 90;

					angle2 = angle2 % 360;

					boolean canMoveAngle1 = true;
					boolean canMoveAngle2 = true;

					displacement.set(0, 0, 0.01);
					displacement.rotate_XZ(angle1);
					boundary2D.update(displacement);
					newPosition = (int) (boundary2D.xPos * 4)
							+ (129 - (int) (boundary2D.yPos * 4)) * 80;
					if (obstacleMap.collideWithObstacle1(this, newPosition)) {
						canMoveAngle1 = false;
					} else if (obstacleMap.collideWithObstacle2(this,
							newPosition)) {
						canMoveAngle1 = false;
					}
					displacement.scale(-1);
					boundary2D.update(displacement);
					displacement.reset();

					displacement.set(0, 0, 0.01);
					displacement.rotate_XZ(angle2);
					boundary2D.update(displacement);
					newPosition = (int) (boundary2D.xPos * 4)
							+ (129 - (int) (boundary2D.yPos * 4)) * 80;
					if (obstacleMap.collideWithObstacle1(this, newPosition)) {
						canMoveAngle2 = false;
					} else if (obstacleMap.collideWithObstacle2(this,
							newPosition)) {
						canMoveAngle2 = false;
					}
					displacement.scale(-1);
					boundary2D.update(displacement);
					displacement.reset();

					if (canMoveAngle1 && !canMoveAngle2) {
						targetAngleBody = angle1;
						forward = true;

						obstacleMap.giveWay(this, position);
					} else if (!canMoveAngle1 && canMoveAngle2) {
						targetAngleBody = angle2;
						forward = true;

						obstacleMap.giveWay(this, position);
					} else if (canMoveAngle1 && canMoveAngle2) {
						if (Math.abs(angle1 - targetAngleBody) < Math
								.abs(angle2 - targetAngleBody)) {
							targetAngleBody = angle1;

						} else {
							targetAngleBody = angle2;

						}
						forward = true;

					} else {

						stuckCount = 10;

						obstacleMap.giveWay(this, position);

					}

					if (Math.abs((previousTargetAngleBody + 180) % 360
							- targetAngleBody) <= 50) {
						targetAngleBody = previousTargetAngleBody;
					} else {
						// Dont doing nothing
					}

				} else {
					// Dont doing nothing
				}
				displacement.set(0, 0, 0.01);
				displacement.rotate_XZ(targetAngleBody);
				boundary2D.update(displacement);
				newPosition = (int) (boundary2D.xPos * 4)
						+ (129 - (int) (boundary2D.yPos * 4)) * 80;

				if (obstacleMap.collideWithObstacle1(this, newPosition)) {
					forward = false;

				} else if (obstacleMap.collideWithObstacle2(this, newPosition)) {
					forward = false;

				}
				displacement.scale(-1);
				boundary2D.update(displacement);
				displacement.reset();
			}
		}
		previousTargetAngleBody = targetAngleBody;
	}

	public void draw() {
		if (countDownToDeath < 3) {
			for (int i = 0; i < body.length; i++) {
				body[i].draw();

			}

			for (int i = 0; i < turret.length; i++) {
				turret[i].draw();
			}
		} else {
			// Dont doing nothing
		}

		if (Smoke != null && visible) {
			Smoke.draw();
		} else {
			// Dont doing nothing
		}
	}

	public void damage(int damagePoint) {
		if (damagePoint == -1) {
			active = true;
			engaged = true;
			return;
		} else {
			// Dont doing nothing
		}
		HP -= damagePoint;
		engaged = true;
	}

	public rectangle2D getBoundary2D() {
		return boundary2D;
	}

}
