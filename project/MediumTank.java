package project;
/**
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

/**
 * Class to create Mediuns Tanks, which are contained values ​​and
 * methods for this purpose
 */
public class MediumTank extends SolidObject {
	//polygons for tank body
	private Polygon3D body[];
	
	//total angle that the body has rotated from the initial position. (in the x-z plane)
	private int bodyAngle;
	
	//the center of the body in camera coordinate
	private Vector bodyCenter;
	
	//polygons for tank turret
	private Polygon3D turret[];
	
	//the shadow of tank body
	private Polygon3D shadowBody;
	
	//the shadow of tank turret
	private Polygon3D shadowTurret;
	
	//the center of the turret (pivot point for rotation)
	private Vector turretCenter;
	
	//total angle that the turret has rotated from the initial position. (in the x-z plane)
	private int turretAngle;
	
	//movement flag
	private boolean forward;
	
	//movement flag
	private boolean aimRight;
	
	//movement flag
	private boolean aimLeft;
	
	//movement flag
	private boolean firing;
	
	//time needed before a weapon can be fired again
	private int coolDown = 33;

	//current coolDown
	private int currentCoolDown = 0;

	//change in tank's position of each frame
	private Vector displacement = new Vector(0, 0, 0);

	//degrees the tank body has rotated in a frame
	private int bodyAngleDelta;
	
	//degrees the tank turret has rotated in a frame
	private int turretAngleDelta;

	//The position index of the tank in the grid map
	private int position, desiredPosition;

	//whether the tank is visible in the previous frame
	private boolean isVisiblePreviousFrame;
	
	//a smoke tail
	private Smoke Smoke;
	
	//distance from player tank
	private double distance;
	
	//angle between player tank and turret center
	private int targetAngle;

	//angle between a target location and body centre
	private int targetAngleBody;

	//targetAngleBody of the previous frame
	private int previousTargetAngleBody;

	//temporary vectors which will be used for vector arithmetic
	private Vector tempVector1 = new Vector(0, 0, 0);
	
	//temporary vectors which will be used for vector arithmetic
	private Vector tempVector2 = new Vector(0, 0, 0);

	// a flag which indicate whether the take will interact with player at all. (i.e some enemy only get activated at a certain stage of the game)
	public boolean active = true;

	//an AI flag  indicates whether it has engaged with player tank
	private boolean engaged;
	
	//an AI flag indicates whether there is a type 2 obstacle between medium tank and player tank
	private boolean clearToShoot;

	//a count down for death after hp = 0
	private int countDownToDeath;
	
	//represent the time that medium tank has been in stuck status
	private int stuckCount;

	//random numbers 
	private int randomNumber1;
	
	//random numbers 
	private int randomNumber2;

	/**
	 * This is a constructor method. X, y and z are coordinates for construction
	 * of the tank in the space.
	 * 
	 * @param x
	 * @param y
	 * @param y
	 * @param angle
	 */
	public MediumTank(double x, double y, double z, int angle) {

		start = new Vector(x, y, z);
		xDirection = new Vector(1, 0, 0);
		yDirection = new Vector(0, 1, 0);
		zDirection = new Vector(0, 0, 1);

		xDirection.rotate_XZ(angle);
		zDirection.rotate_XZ(angle);

		modelType = 2;
		makeBoundary(0.1, 0.25, 0.1);

		boundary2D = new Rectangle2D(x - 0.1, z + 0.1, 0.2, 0.2);
		position = (int) (x * 4) + (129 - (int) (z * 4)) * 80;
		desiredPosition = position;
		ObstacleMap.registerObstacle2(this, position);

		findCenter();

		bodyCenter = centre;
		bodyAngle = angle;
		turretAngle = angle;

		makeBody();
		makeTurret();

		randomNumber1 = GameData.getRandom();

		HP = 25;

		lifeSpan = 1;
	}

	//create polygons for the tank body
	private void makeBody() {
		Vector v[];

		start = bodyCenter.myClone();
		xDirection = new Vector(1, 0, 0);
		yDirection = new Vector(0, 0.95, 0);
		zDirection = new Vector(0, 0, 1);

		xDirection.rotate_XZ(bodyAngle);
		zDirection.rotate_XZ(bodyAngle);

		body = new Polygon3D[15];
		v = new Vector[] { put(-0.07, 0.025, 0.11), put(-0.07, 0.025, -0.11),
				put(-0.07, 0.005, -0.11), put(-0.07, -0.025, -0.08),
				put(-0.07, -0.025, 0.07), put(-0.07, 0.005, 0.11) };
		body[0] = new Polygon3D(v, put(-0.07, 0.027, 0.11), put(-0.07, 0.027,
				-0.11), put(-0.07, -0.025, 0.11), Main.textures[12], 1, 1, 6);

		v = new Vector[] { put(0.07, 0.005, 0.11), put(0.07, -0.025, 0.07),
				put(0.07, -0.025, -0.08), put(0.07, 0.005, -0.11),
				put(0.07, 0.025, -0.11), put(0.07, 0.025, 0.11) };
		body[1] = new Polygon3D(v, put(0.07, 0.027, -0.11), put(0.07, 0.027,
				0.11), put(0.07, -0.025, -0.11), Main.textures[12], 1, 1, 6);

		v = new Vector[] { put(-0.06, 0.055, 0.05), put(0.06, 0.055, 0.05),
				put(0.06, 0.055, -0.1), put(-0.06, 0.055, -0.1) };
		body[2] = new Polygon3D(v, v[0], v[1], v[3], Main.textures[22], 1, 0.9,
				6);

		v = new Vector[] { put(-0.07, 0.04, 0.11), put(0.07, 0.04, 0.11),
				put(0.06, 0.055, 0.05), put(-0.06, 0.055, 0.05) };
		body[3] = new Polygon3D(v, v[2], v[3], v[1], Main.textures[22], 1, 0.3,
				6);

		v = new Vector[] { put(-0.06, 0.055, 0.05), put(-0.06, 0.055, -0.1),
				put(-0.07, 0.04, -0.11), put(-0.07, 0.04, 0.11) };
		body[4] = new Polygon3D(v, v[2], v[3], v[1], Main.textures[22], 1, 0.3,
				6);

		v = new Vector[] { put(0.07, 0.04, 0.11), put(0.07, 0.04, -0.11),
				put(0.06, 0.055, -0.1), put(0.06, 0.055, 0.05) };
		body[5] = new Polygon3D(v, v[2], v[3], v[1], Main.textures[22], 1, 0.3,
				6);

		v = new Vector[] { put(-0.06, 0.055, -0.1), put(0.06, 0.055, -0.1),
				put(0.07, 0.04, -0.11), put(-0.07, 0.04, -0.11) };
		body[6] = new Polygon3D(v, v[2], v[3], v[1], Main.textures[22], 1, 0.3,
				6);

		v = new Vector[] { put(0.07, 0.04, 0.11), put(-0.07, 0.04, 0.11),
				put(-0.07, 0.01, 0.11), put(0.07, 0.01, 0.11) };
		body[7] = new Polygon3D(v, v[2], v[3], v[1], Main.textures[22], 1, 0.3,
				6);

		v = new Vector[] { put(-0.07, 0.04, 0.11), put(-0.07, 0.04, -0.11),
				put(-0.07, 0.015, -0.11), put(-0.07, 0.005, -0.09),
				put(-0.07, 0.005, 0.09), put(-0.07, 0.015, 0.11) };
		body[8] = new Polygon3D(v, put(-0.07, 0.04, 0.11), put(-0.07, 0.04,
				-0.11), put(-0.07, 0.025, 0.11), Main.textures[22], 1, 0.3, 6);

		v = new Vector[] { put(0.07, 0.015, 0.11), put(0.07, 0.005, 0.09),
				put(0.07, 0.005, -0.09), put(0.07, 0.015, -0.11),
				put(0.07, 0.04, -0.11), put(0.07, 0.04, 0.11) };
		body[9] = new Polygon3D(v, put(0.07, 0.04, 0.11),
				put(0.07, 0.04, -0.11), put(0.07, 0.025, 0.11),
				Main.textures[22], 1, 0.3, 6);

		v = new Vector[] { put(-0.07, 0.04, -0.11), put(0.07, 0.04, -0.11),
				put(0.07, 0.015, -0.11), put(-0.07, 0.015, -0.11) };
		body[10] = new Polygon3D(v, v[0], v[1], v[3], Main.textures[22], 1,
				0.3, 6);

		v = new Vector[] { put(-0.07, 0.005, -0.11), put(-0.04, 0.005, -0.11),
				put(-0.04, -0.025, -0.08), put(-0.07, -0.025, -0.08) };
		body[11] = new Polygon3D(v, v[0], v[1], v[3], Main.textures[12], 1, 1,
				6);

		v = new Vector[] { put(-0.07, 0.015, -0.11), put(-0.04, 0.015, -0.11),
				put(-0.04, 0.005, -0.11), put(-0.07, 0.005, -0.11) };
		body[12] = new Polygon3D(v, v[0], v[1], v[3], Main.textures[12], 1, 1,
				6);

		v = new Vector[] { put(0.04, 0.015, -0.11), put(0.07, 0.015, -0.11),
				put(0.07, 0.005, -0.11), put(0.04, 0.005, -0.11) };
		body[13] = new Polygon3D(v, v[0], v[1], v[3], Main.textures[12], 1, 1,
				6);

		v = new Vector[] { put(0.04, 0.005, -0.11), put(0.07, 0.005, -0.11),
				put(0.07, -0.025, -0.08), put(0.04, -0.025, -0.08) };
		body[14] = new Polygon3D(v, v[0], v[1], v[3], Main.textures[12], 1, 1,
				6);

		turretCenter = put(0, 0.055, -0.0);

		start.add(-0.015, 0, -0.015);
		start.y = -1;
		v = new Vector[] { put(-0.2, 0, 0.2), put(0.2, 0, 0.2),
				put(0.2, 0, -0.2), put(-0.2, 0, -0.2) };
		shadowBody = new Polygon3D(v, v[0], v[1], v[3], Main.textures[14], 1,
				1, 2);

	}
	
	//create polygons for the tank turret
	private void makeTurret() {
		start = turretCenter.myClone();
		Vector v[];
		turret = new Polygon3D[11];

		xDirection = new Vector(0.9, 0, 0);
		yDirection = new Vector(0, 0.95, 0);
		zDirection = new Vector(0, 0, 1);

		xDirection.rotate_XZ(turretAngle);
		zDirection.rotate_XZ(turretAngle);

		v = new Vector[] { put(0.04, 0.035, 0.06), put(-0.04, 0.035, 0.06),
				put(-0.04, 0, 0.06), put(0.04, 0, 0.06) };
		turret[0] = new Polygon3D(v, v[0], v[1], v[3], Main.textures[23], 0.6,
				0.3, 6);

		v = new Vector[] { put(0, 0.025, 0.18), put(0.006, 0.015, 0.18),
				put(0.008, 0.015, 0.06), put(0, 0.025, 0.06) };
		turret[1] = new Polygon3D(v, v[0], v[1], v[3], Main.textures[24], 0.1,
				1, 6);

		v = new Vector[] { put(0, 0.025, 0.06), put(-0.008, 0.015, 0.06),
				put(-0.006, 0.015, 0.18), put(0, 0.025, 0.18) };
		turret[2] = new Polygon3D(v, v[0], v[1], v[3], Main.textures[24], 0.1,
				1, 6);

		v = new Vector[] { put(-0.04, 0.035, 0.06), put(0.04, 0.035, 0.06),
				put(0.05, 0.035, 0.04), put(0.05, 0.035, -0.03),
				put(0.03, 0.035, -0.07), put(-0.03, 0.035, -0.07),
				put(-0.05, 0.035, -0.03), put(-0.05, 0.035, 0.04) };
		turret[3] = new Polygon3D(v, put(-0.04, 0.035, 0.19), put(0.04, 0.035,
				0.19), put(-0.04, 0.035, 0.09), Main.textures[23], 0.6, 0.6, 6);

		v = new Vector[] { put(0.03, 0, -0.07), put(-0.03, 0, -0.07),
				put(-0.03, 0.035, -0.07), put(0.03, 0.035, -0.07) };
		turret[4] = new Polygon3D(v, v[0], v[1], v[3], Main.textures[23], 0.4,
				0.2, 6);

		v = new Vector[] { put(0.03, 0.035, -0.07), put(0.05, 0.035, -0.03),
				put(0.05, 0, -0.03), put(0.03, 0, -0.07) };
		turret[5] = new Polygon3D(v, v[0], v[1], v[3], Main.textures[23], 0.4,
				0.2, 6);

		v = new Vector[] { put(-0.03, 0, -0.07), put(-0.05, 0, -0.03),
				put(-0.05, 0.035, -0.03), put(-0.03, 0.035, -0.07) };
		turret[6] = new Polygon3D(v, v[0], v[1], v[3], Main.textures[23], 0.4,
				0.2, 6);

		v = new Vector[] { put(0.05, 0.035, -0.03), put(0.05, 0.035, 0.04),
				put(0.05, 0, 0.04), put(0.05, 0, -0.03) };
		turret[7] = new Polygon3D(v, v[0], v[1], v[3], Main.textures[23], 0.5,
				0.3, 6);

		v = new Vector[] { put(-0.05, 0, -0.03), put(-0.05, 0, 0.04),
				put(-0.05, 0.035, 0.04), put(-0.05, 0.035, -0.03) };
		turret[8] = new Polygon3D(v, v[0], v[1], v[3], Main.textures[23], 0.5,
				0.3, 6);

		v = new Vector[] { put(0.05, 0.035, 0.04), put(0.04, 0.035, 0.06),
				put(0.04, 0, 0.06), put(0.05, 0, 0.04) };
		turret[9] = new Polygon3D(v, v[0], v[1], v[3], Main.textures[23], 0.3,
				0.3, 6);

		v = new Vector[] { put(-0.05, 0, 0.04), put(-0.04, 0, 0.06),
				put(-0.04, 0.035, 0.06), put(-0.05, 0.035, 0.04) };
		turret[10] = new Polygon3D(v, v[0], v[1], v[3], Main.textures[23], 0.3,
				0.3, 6);

		start.add(-0.03, 0, -0.025);
		start.y = -1;
		v = new Vector[] { put(-0.18, 0, 0.18), put(0.18, 0, 0.18),
				put(0.18, 0, -0.18), put(-0.18, 0, -0.18) };
		shadowTurret = new Polygon3D(v, v[0], v[1], v[3], Main.textures[15], 1,
				1, 2);

	}
	
	//update model 
	public void update() {

		if ((Main.timer + randomNumber1 * 3) % 1000 == 0) {
			if (randomNumber2 > 50) {
				randomNumber2 = 50;
			} else {
				randomNumber2 = 51;
			}
		} else {
			//Does nothing.
		}

		if (countDownToDeath <= 0 && active && !Main.gamePaused) {
			processAI();
		} else {
			//Does nothing.
		}

		if (aimLeft) {
			if (Math.abs(turretAngle - targetAngle) <= 3) {
				turretAngleDelta = targetAngle - turretAngle;
				turretAngle += turretAngleDelta;
				if (turretAngleDelta < 0) {
					turretAngleDelta += 360;
				} else {
					//Does nothing.
				}
			} else {
				turretAngleDelta = 3;
				turretAngle += 3;
			}
			if (turretAngle >= 360) {
				turretAngle -= 360;
			} else {
				//Does nothing.
			}
		} else if (aimRight) {
			if (Math.abs(turretAngle - targetAngle) <= 3) {
				turretAngleDelta = targetAngle - turretAngle;
				turretAngle += turretAngleDelta;
				if (turretAngleDelta < 0) {
					turretAngleDelta += 360;
				} else {
					//Does nothing.
				}
			} else {
				turretAngleDelta = 357;
				turretAngle -= 3;
			}
			if (turretAngle < 0) {
				turretAngle += 360;
			} else {
				//Does nothing.
			}
		} else {
			//Does nothing.
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
				} else {
					//Does nothing.
				}

				bodyAngle = (bodyAngle + bodyAngleDelta) % 360;
			}
		} else {
			//Does nothing.
		}

		centre.add(displacement);

		boundary2D.update(displacement);

		int newPosition = (int) (boundary2D.xPos * 4)
				+ (129 - (int) (boundary2D.yPos * 4)) * 80;
		if (!ObstacleMap.isOccupied(newPosition)) {
			ObstacleMap.removeObstacle2(position);
			ObstacleMap.registerObstacle2(this, newPosition);
			position = newPosition;
			desiredPosition = newPosition;
		} else if (!ObstacleMap.isOccupied(desiredPosition)) {
			ObstacleMap.removeObstacle2(position);
			ObstacleMap.registerObstacle2(this, desiredPosition);
			position = desiredPosition;
		} else {
			desiredPosition = newPosition;
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
			//Does nothing.
		}

		if (visible) {
			if (isVisiblePreviousFrame == false) {
				makeBody();
				makeTurret();
				isVisiblePreviousFrame = true;
			} else {
				//Does nothing.
			}
		} else {
			//Does nothing.
		}

		if (visible) {
			ModelDrawList.register(this);
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

					for (int j = 0; j < body[i].getVertex3D().length; j++) {
						body[i].getVertex3D()[j].add(displacement);
						body[i].getVertex3D()[j].subtract(centre);
						body[i].getVertex3D()[j].rotate_XZ(bodyAngleDelta);
						body[i].getVertex3D()[j].add(centre);
					}

					body[i].findRealNormal();
					body[i].findDiffuse();

					body[i].update();
				}

				tempVector1.set(centre);
				tempVector1.add(-0.03, 0, -0.02);
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

				for (int j = 0; j < shadowBody.getVertex3D().length; j++) {
					shadowBody.getVertex3D()[j].add(displacement);
					shadowBody.getVertex3D()[j].subtract(tempVector1);
					shadowBody.getVertex3D()[j].rotate_XZ(bodyAngleDelta);
					shadowBody.getVertex3D()[j].add(tempVector1);
				}

				shadowBody.update();
				Rasterizer.rasterize(shadowBody);

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

					for (int j = 0; j < turret[i].getVertex3D().length; j++) {
						turret[i].getVertex3D()[j].add(displacement);

						turret[i].getVertex3D()[j].subtract(turretCenter);
						turret[i].getVertex3D()[j].rotate_XZ(turretAngleDelta);
						turret[i].getVertex3D()[j].add(turretCenter);
					}

					turret[i].findRealNormal();
					turret[i].findDiffuse();

					turret[i].update();
				}

				tempVector1.set(turretCenter);
				tempVector1.add(-0.04, 0, -0.04);

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

				for (int j = 0; j < shadowTurret.getVertex3D().length; j++) {
					shadowTurret.getVertex3D()[j].add(displacement);

					shadowTurret.getVertex3D()[j].subtract(tempVector1);
					shadowTurret.getVertex3D()[j].rotate_XZ(turretAngleDelta);
					shadowTurret.getVertex3D()[j].add(tempVector1);
				}
				shadowTurret.update();
				Rasterizer.rasterize(shadowTurret);
			} else {
				//Does nothing.
			}
		} else {
			//Does nothing.
		}

		if (currentCoolDown > 0 && !Main.gamePaused) {
			currentCoolDown--;
		} else {
			//Does nothing.
		}

		if (firing) {
			if (currentCoolDown == 0) {
				currentCoolDown = coolDown;

				Vector direction = new Vector(0, 0, 1);
				direction.rotate_XZ(turretAngle);
				direction.scale(0.06);
				Projectiles
						.register(new Shell(centre.x + direction.x, centre.y,
								centre.z + direction.z, turretAngle, true, 0));
			} else {
				//Does nothing.
			}
		} else {
			//Does nothing.
		}

		if (HP <= 12) {
			if (Smoke == null) {
				Smoke = new Smoke(this);
			} else {
				if (visible) {
					Smoke.update();
				} else {
					//Does nothing.
				}
			}
		} else {
			//Does nothing.
		}

		if (HP <= 0) {
			countDownToDeath++;
			if (countDownToDeath >= 3) {
				if (countDownToDeath == 3) {
					Projectiles.register(new Explosion(centre.x, centre.y,
							centre.z, 1.7));
					PowerUps.register(new PowerUp(centre.x, -0.875, centre.z, 1));
				} else {
					//Does nothing.
				}
				ObstacleMap.removeObstacle2(position);
				Smoke.stopped = true;
			} else {
				//Does nothing.
			}
			if (countDownToDeath >= 40) {
				lifeSpan = 0;
			} else {
				//Does nothing.
			}
		} else {
			//Does nothing.
		}

		forward = false;
		aimRight = false;
		aimLeft = false;
		bodyAngleDelta = 0;
		turretAngleDelta = 0;
		displacement.reset();
		firing = false;
		if (Main.timer % 10 == 0) {
			unstuck = false;
		} else {
			//Does nothing.
		}

	}
	
	//process AI
	private void processAI() {

		tempVector1.set(centre);
		tempVector1.subtract(PlayerTank.bodyCenter);
		distance = tempVector1.getLength();

		if (distance < 2) {
			engaged = true;
		}
		if (distance > 4) {
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
			} else {
				//Does nothing.
			}
		} else {

			if (engaged) {

				if ((Main.timer) % 5 == 0) {
					ObstacleMap.alertNearbyTanks(position);
				} else {
					//Does nothing.
				}
				tempVector1.set(bodyCenter);
				tempVector2.set(PlayerTank.bodyCenter);
				tempVector2.subtract(tempVector1);
				tempVector2.unit();
				tempVector2.scale(0.125);

				clearToShoot = true;
				int obstacleType = -1;
				double d = 0;
				for (int i = 0; (d < distance) && (i < 30); i++, tempVector1
						.add(tempVector2), d += 0.125) {
					Model temp = ObstacleMap.isOccupied2(tempVector1);
					if (temp == null) {
						continue;
					} else {
						//Does nothing.
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
					if (PlayerTank.bodyCenter.x > turretCenter.x && targetAngle <= 180) {
						targetAngle += 180;
					} else {
						//Does nothing.
					}
				} else {
					targetAngle = bodyAngle;

				}

				int AngleDelta = turretAngle - targetAngle;
				if (Math.abs(AngleDelta) < 3 && clearToShoot && distance < 1.7) {
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
				}
				if (AngleDelta < 0) {
					if (AngleDelta > -180) {
						aimLeft = true;
					} else {
						aimRight = true;
					}
				} else {
					//Does nothing.
				}

				forward = true;
				if (clearToShoot && distance < 1.5) {
					if (distance < 1.2) {
						forward = false;
					}
					if (distance >= 1.2) {
						if (randomNumber2 > 50) {
							forward = false;
						} else {
							//Does nothing.
						}
					} else {
						//Does nothing.
					}
				} else {
					//Does nothing.
				}

				if (unstuck && distance > 0.8) {
					forward = true;
					ObstacleMap.giveWay(this, position);
				} else {
					//Does nothing.
				}

				if (forward) {
					targetAngleBody = 90 + (int) (180 * Math
							.atan((centre.z - PlayerTank.bodyCenter.z)
									/ (centre.x - PlayerTank.bodyCenter.x)) / Math.PI);
					if (PlayerTank.bodyCenter.x > centre.x && targetAngleBody <= 180) {
						targetAngleBody += 180;
					} else {
						//Does nothing.
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
							//Does nothing.
						}

						if (randomNumber2 > 50) {
							targetAngleBody += 90;
						} else {
							targetAngleBody -= 90;
						}

						targetAngleBody = (targetAngleBody + 360) % 360;
					} else {
						//Does nothing.
					}

					int newPosition = (int) (boundary2D.xPos * 4)
							+ (129 - (int) (boundary2D.yPos * 4)) * 80;

					displacement.set(0, 0, 0.01);
					displacement.rotate_XZ(targetAngleBody);
					boundary2D.update(displacement);

					boolean canMove = true;

					if (ObstacleMap.collideWithObstacle1(this, newPosition)) {
						forward = false;
						canMove = false;
					} else if (ObstacleMap.collideWithObstacle2(this,
							newPosition)) {
						forward = false;
						canMove = false;
					} else {
						//Does nothing.
					}
					displacement.scale(-1);
					boundary2D.update(displacement);
					displacement.reset();

					if (!canMove) {
						if (unstuck) {
							ObstacleMap.giveWay(this, position);
						} else {
							//Does nothing.
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

						if (ObstacleMap.collideWithObstacle1(this, newPosition)) {
							canMoveAngle1 = false;
						} else if (ObstacleMap.collideWithObstacle2(this,
								newPosition)) {
							canMoveAngle1 = false;
						} else {
							//Does nothing.
						}
						displacement.scale(-1);
						boundary2D.update(displacement);
						displacement.reset();

						displacement.set(0, 0, 0.01);
						displacement.rotate_XZ(angle2);
						boundary2D.update(displacement);
						newPosition = (int) (boundary2D.xPos * 4)
								+ (129 - (int) (boundary2D.yPos * 4)) * 80;

						if (ObstacleMap.collideWithObstacle1(this, newPosition)) {
							canMoveAngle2 = false;
						} else if (ObstacleMap.collideWithObstacle2(this,
								newPosition)) {
							canMoveAngle2 = false;
						} else {
							//Does nothing.
						}
						displacement.scale(-1);
						boundary2D.update(displacement);
						displacement.reset();

						if (canMoveAngle1 && !canMoveAngle2) {
							targetAngleBody = angle1;
							forward = true;

							ObstacleMap.giveWay(this, position);
						} else if (!canMoveAngle1 && canMoveAngle2) {
							targetAngleBody = angle2;
							forward = true;

							ObstacleMap.giveWay(this, position);
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

							ObstacleMap.giveWay(this, position);

						}

						if (Math.abs((previousTargetAngleBody + 180) % 360
								- targetAngleBody) <= 50) {
							targetAngleBody = previousTargetAngleBody;
						} else {
							//Does nothing.
						}

					} else {
						//Does nothing.
					}

					displacement.set(0, 0, 0.01);
					displacement.rotate_XZ(targetAngleBody);
					boundary2D.update(displacement);
					newPosition = (int) (boundary2D.xPos * 4)
							+ (129 - (int) (boundary2D.yPos * 4)) * 80;

					if (ObstacleMap.collideWithObstacle1(this, newPosition)) {
						forward = false;

					} else if (ObstacleMap.collideWithObstacle2(this,
							newPosition)) {
						forward = false;
					} else {
						//Does nothing.
					}
					displacement.scale(-1);
					boundary2D.update(displacement);
					displacement.reset();
				} else {
					//Does nothing
				}
			} else {
				//Does nothing
			}
			previousTargetAngleBody = targetAngleBody;
		}
	}

	//draw model
	public void draw() {

		if (countDownToDeath < 3) {
			for (int i = 0; i < body.length; i++) {
				body[i].draw();
			}

			for (int i = 0; i < turret.length; i++) {
				turret[i].draw();
			}
		}else {
			//Does nothing
		}

		if (Smoke != null && visible) {
			Smoke.draw();
		} else {
			//Does nothing.
		}
	}
	
	//return the 2D boundary of this model
	public Rectangle2D getBoundary2D() {
		return boundary2D;
	}
	
	/**
	 * This is a method to calculate the damage of the tank
	 * 
	 * @param damagePoint
	 */
	public void damage(int damagePoint) {
		if (damagePoint == -1) {
			active = true;
		} else {
			HP -= damagePoint;
			engaged = true;
		}
	}

}