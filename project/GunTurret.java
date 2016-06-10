package project;
/*
 *	This class is part of BattleTank 2.
 *
 *  BattleTank 2 is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  BattleTank 2 is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with BattleTank 2.  If not, see <http://www.gnu.org/licenses/>
 */

public class GunTurret extends SolidObject {

	public GunTurret(double x, double y, double z, int angle) {

		assert (x >= 7.875 && x <= 16.375);

		assert (y == -1 && z >= 17.25 && z <= 30.875);

		assert (angle >= 0 && angle <= 270);

		final double XPOS = x - 0.13;

		final double YPOS = z + 0.13;

		final double LENGHTRECTANGLE = 0.26;

		final double HEIGHTRECTANGLE = 0.26;

		final double LENGHT = 0.1;

		final double HEIGHT = 0.25;

		final double WIDTH = 0.1;

		start = new Vector(x, y, z);
		xDirection = new Vector(0.7, 0, 0);
		yDirection = new Vector(0, 0.7, 0);
		zDirection = new Vector(0, 0, 0.7);

		modelType = 2;
		makeBoundary(LENGHT, HEIGHT, WIDTH);

		boundary2D = new Rectangle2D(XPOS, YPOS, LENGHTRECTANGLE,
				HEIGHTRECTANGLE);
		position = (int) (x * 4) + (129 - (int) (z * 4)) * 80;
		ObstacleMap.registerObstacle2(this, position);

		findCenter();

		turretCenter = start.myClone();
		bodyCenter = centre;
		turretAngle = angle;

		makeBody();
		makeTurret();

		HP = 75;

		lifeSpan = 1;
	}

	public void update() {
		if (HP > 0 && !Main.gamePaused) {
			processAI();
		} else {
			// Does nothing.
		}

		if (aimLeft) {
			if (Math.abs(turretAngle - targetAngle) <= 3) {
				turretAngleDelta = targetAngle - turretAngle;
				turretAngle += turretAngleDelta;

				if (turretAngleDelta < 0) {
					turretAngleDelta += 360;
				} else {
					// Does nothing.
				}
			} else {
				turretAngleDelta = 3;
				turretAngle += 3;
			}

			if (turretAngle >= 360) {
				turretAngle -= 360;
			} else {
				// Does nothing.
			}

		} else if (aimRight) {
			if (Math.abs(turretAngle - targetAngle) <= 3) {
				turretAngleDelta = targetAngle - turretAngle;
				turretAngle += turretAngleDelta;
				if (turretAngleDelta < 0) {
					turretAngleDelta += 360;
				} else {
					// Does nothing.
				}

			} else {
				turretAngleDelta = 357;
				turretAngle -= 3;
			}
			if (turretAngle < 0)
				turretAngle += 360;
		} else {
			// Does nothing.
		}

		tempCentre.set(centre);
		tempCentre.y = -1;
		tempCentre.subtract(Camera.getPosition());
		tempCentre.rotate_XZ(Camera.getXZ_angle());
		tempCentre.rotate_YZ(Camera.getYZ_angle());
		tempCentre.updateLocation();

		visible = true;
		if (tempCentre.z < 0.9 || tempCentre.screenY < -10
				|| tempCentre.screenX < -400 || tempCentre.screenX > 800) {
			visible = false;
			isVisiblePreviousFrame = false;
		} else {
			// Does nothing.
		}

		if (visible) {
			if (isVisiblePreviousFrame == false) {

				makeTurret();
				isVisiblePreviousFrame = true;
			} else {
				// Does nothing.
			}
		} else {
			// Does nothing.
		}

		if (visible) {
			ModelDrawList.register(this);

			for (int i = 0; i < 5; i++) {
				boundary[i].update();
			}

			for (int i = 0; i < body.length; i++) {
				body[i].update();
			}

			shadowBody.update();
			if (shadowBody.visible) {
				Rasterizer.rasterize(shadowBody);
			} else {
				// Does nothing
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
				Rasterizer.rasterize(shadowTurret);

			} else {
				// Does nothing.
			}
		}

		if (currentCoolDown > 0 && !Main.gamePaused) {
			currentCoolDown--;
		} else {
			// Does nothing.
		}

		if (firing) {
			if (currentCoolDown == 0) {
				currentCoolDown = coolDown;

				Vector direction = new Vector(0, 0, 1);
				direction.rotate_XZ(turretAngle);
				direction.scale(0.06);
				Projectiles
						.register(new Shell(centre.x + direction.x, centre.y,
								centre.z + direction.z, turretAngle, true, 1));
			} else {
				// Does nothing.
			}
		} else {
			// Does nothing.
		}

		if (HP <= 37) {
			if (Smoke == null) {
				tempVector1 = getRealCentre().myClone();
				tempVector1.y += 0.1;
				Smoke = new Smoke(tempVector1.myClone());
			} else {
				if (visible) {
					Smoke.update();
				} else {
					// Does nothing.
				}
			}
		} else if (HP <= 0) {
			if (!destoried) {
				Projectiles.register(new Explosion(centre.x, centre.y,
						centre.z, 2));
				modelType = 6;
				Smoke.stopped = true;
			} else {
				// Does nothing.
			}

			destoried = true;
		} else {
			//Does nothing.
		}

		aimRight = false;
		aimLeft = false;
		turretAngleDelta = 0;
		firing = false;

	}

	public void draw() {

		for (int i = 0; i < body.length; i++) {
			body[i].draw();
		}

		if (HP > 0) {
			for (int i = 0; i < turret.length; i++) {
				turret[i].draw();
			}
		} else {
			// Does nothing.
		}

		if (Smoke != null && visible) {
			Smoke.draw();
		} else {
			// Does nothing.
		}
	}

	public Rectangle2D getBoundary2D() {
		return boundary2D;
	}

	public void damage(int damagePoint) {
		HP -= damagePoint;
	}

	private Polygon3D body[];

	private Polygon3D turret[];

	private Polygon3D shadowBody;

	private Polygon3D shadowTurret;

	private Vector bodyCenter;

	private Vector turretCenter;

	private int turretAngle;

	private int targetAngle;

	private int turretAngleDelta;

	private boolean aimRight, aimLeft, firing;

	private int coolDown = 20;

	private int currentCoolDown = 0;

	private int position;

	private Smoke Smoke;

	private boolean isVisiblePreviousFrame;

	private Vector tempVector1 = new Vector(0, 0, 0);

	private Vector tempVector2 = new Vector(0, 0, 0);

	private boolean engaged;

	private double distance;

	private boolean clearToShoot;

	private boolean destoried;

	private void processAI() {

		tempVector1.set(centre);
		tempVector1.subtract(PlayerTank.bodyCenter);
		distance = tempVector1.getLength();

		if (distance < 2.3) {
			engaged = true;
		} else {
			engaged = false;
		}

		if (engaged) {
			if ((Main.timer) % 5 == 0) {
				ObstacleMap.alertNearbyTanks(position);
			} else {
				// Does nothing.
			}

			tempVector1.set(bodyCenter);
			tempVector2.set(PlayerTank.bodyCenter);
			tempVector2.subtract(tempVector1);
			tempVector2.unit();
			tempVector2.scale(0.125);

			clearToShoot = true;
			int obstacleType = -1;
			double d = 0;
			for (int i = 0; (d < distance) && (i < 35); i++, tempVector1
					.add(tempVector2), d += 0.125) {
				Model temp = ObstacleMap.isOccupied2(tempVector1);
				if (temp == null) {
					continue;
				} else {
					// Does nothing
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
						.atan((centre.z - PlayerTank.bodyCenter.z)
								/ (centre.x - PlayerTank.bodyCenter.x)) / Math.PI);
				if (PlayerTank.bodyCenter.x > turretCenter.x
						&& targetAngle <= 180) {
					targetAngle += 180;
				} else {
					// Does nothing.
				}

			} else {
				// Does nothing.
			}

			int AngleDelta = turretAngle - targetAngle;

			if (Math.abs(AngleDelta) < 3 && clearToShoot && distance < 2.25) {
				firing = true;
			} else {
				//Does nothing.
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
			} else {
				// Does nothing.
			}

		} else {
			//Does nothing.
		}
	}

	private void makeTurret() {
		xDirection = new Vector(0.7, 0, 0);
		yDirection = new Vector(0, 0.7, 0);
		zDirection = new Vector(0, 0, 0.7);

		xDirection.rotate_XZ(turretAngle);
		zDirection.rotate_XZ(turretAngle);

		xDirection.rotate_XZ(180);
		zDirection.rotate_XZ(180);

		turret = new Polygon3D[22];

		Vector v[];

		v = new Vector[] { put(-0.04, 0.16, -0.08), put(0.04, 0.16, -0.08),
				put(0.04, 0.09, -0.08), put(-0.04, 0.09, -0.08) };
		turret[0] = new Polygon3D(v, v[0], v[1], v[3], Main.textures[51], 1,
				0.5, 6);

		v = new Vector[] { put(-0.08, 0.16, 0.08), put(-0.04, 0.16, -0.08),
				put(-0.04, 0.09, -0.08), put(-0.08, 0.09, 0.08) };
		turret[1] = new Polygon3D(v, v[0], v[1], v[3], Main.textures[51], 1,
				0.5, 6);

		v = new Vector[] { put(0.08, 0.16, 0.08), put(-0.08, 0.16, 0.08),
				put(-0.08, 0.09, 0.08), put(0.08, 0.09, 0.08) };
		turret[2] = new Polygon3D(v, v[0], v[1], v[3], Main.textures[51], 1,
				0.5, 6);

		v = new Vector[] { put(0.04, 0.16, -0.08), put(0.08, 0.16, 0.08),
				put(0.08, 0.09, 0.08), put(0.04, 0.09, -0.08) };
		turret[3] = new Polygon3D(v, v[0], v[1], v[3], Main.textures[51], 1,
				0.5, 6);

		double r = 0.016;
		double r1 = 0.012;
		double theta = Math.PI / 8;
		start.add(0, 0.1, 0);

		for (int i = 0; i < 16; i++) {
			v = new Vector[] {
					put(r1 * Math.cos(i * theta), r1 * Math.sin(i * theta),
							-0.22),
					put(r1 * Math.cos((i + 1) * theta),
							r1 * Math.sin((i + 1) * theta), -0.22),
					put(r * Math.cos((i + 1) * theta),
							r * Math.sin((i + 1) * theta), -0.08),
					put(r * Math.cos(i * theta), r * Math.sin(i * theta), -0.08) };
			turret[i + 4] = new Polygon3D(v, v[0], v[1], v[3],
					Main.textures[23], 0.001, 0.01, 6);
			turret[i + 4].Ambient_I = 18;
			turret[i + 4].diffuse_coefficient = 0.7;
			turret[i + 4].findDiffuse();
		}

		v = new Vector[16];

		for (int i = 1; i < 17; i++) {
			v[16 - i] = put(r1 * Math.cos(i * theta), r1 * Math.sin(i * theta),
					-0.22);
		}

		turret[21] = new Polygon3D(v, v[0], v[1], v[3], Main.textures[23], 1,
				1, 6);

		start.add(0, -0.1, 0);

		v = new Vector[] { put(-0.08, 0.16, 0.08), put(0.08, 0.16, 0.08),
				put(0.04, 0.16, -0.08), put(-0.04, 0.16, -0.08) };
		turret[20] = new Polygon3D(v, v[0], v[1], put(-0.08, 0.16, -0.08),
				Main.textures[51], 1, 1, 6);

		start.add(-0.05, 0, -0.05);
		start.y = -1;
		v = new Vector[] { put(0.5, 0, -0.27), put(-0.5, 0, -0.27),
				put(-0.5, 0, 0.27), put(0.5, 0, 0.27) };
		shadowTurret = new Polygon3D(v, v[0], v[1], v[3], Main.textures[15], 1,
				1, 2);
		start.add(0.05, 0, 0.05);

		xDirection.rotate_XZ(180);
		zDirection.rotate_XZ(180);

	}

	private void makeBody() {
		Vector v[];

		body = new Polygon3D[5];

		v = new Vector[] { put(-0.09, 0.09, -0.09), put(0.09, 0.09, -0.09),
				put(0.12, 0, -0.12), put(-0.12, 0, -0.12) };
		body[0] = new Polygon3D(v, v[0], v[1], v[3], Main.textures[41], 1, 1, 6);

		v = new Vector[] { put(-0.09, 0.09, 0.09), put(-0.09, 0.09, -0.09),
				put(-0.12, 0, -0.12), put(-0.12, 0, 0.12) };
		body[1] = new Polygon3D(v, v[0], v[1], v[3], Main.textures[41], 1, 1, 6);

		v = new Vector[] { put(0.12, 0, 0.12), put(0.12, 0, -0.12),
				put(0.09, 0.09, -0.09), put(0.09, 0.09, 0.09) };
		body[2] = new Polygon3D(v, v[0], v[1], v[3], Main.textures[41], 1, 1, 6);

		v = new Vector[] { put(-0.12, 0, 0.12), put(0.12, 0, 0.12),
				put(0.09, 0.09, 0.09), put(-0.09, 0.09, 0.09) };
		body[3] = new Polygon3D(v, v[0], v[1], v[3], Main.textures[41], 1, 1, 6);

		v = new Vector[] { put(-0.09, 0.09, 0.09), put(0.09, 0.09, 0.09),
				put(0.09, 0.09, -0.09), put(-0.09, 0.09, -0.09) };
		body[4] = new Polygon3D(v, v[0], v[1], v[3], Main.textures[41], 1, 1, 6);

		tempVector1 = start.myClone();

		xDirection.scale(1.2);
		start.add(0, 0, -0.03);
		v = new Vector[] { put(-0.2, 0, 0.2), put(0.2, 0, 0.2),
				put(0.2, 0, -0.2), put(-0.2, 0, -0.2) };
		shadowBody = new Polygon3D(v, v[0], v[1], v[3], Main.textures[50], 1,
				1, 2);

		for (int i = 0; i < body.length; i++) {
			body[i].Ambient_I = 22;
			body[i].diffuse_coefficient = 0.7;
			body[i].findDiffuse();

		}

		start.set(tempVector1);
		xDirection.scale(0.8333333333);

	}

}