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

import java.awt.*;
import java.util.logging.*;

//This class is responsible for the game camera.

public class Camera {

	// X coordinate of the rectangle.
	static final int XCOORDINADE = 0;

	// Y coordinate of the rectangle.
	static final int YCOORDINADE = 0;

	// Width of rectangle.
	final static int WIDTH = 640;

	// Weight of rectangle.
	final static int HEIGHT = 480;

	// Position of the camera (third person view).
	public static Vector position = new Vector(10, 0.25, 1.5);

	// Position of the camera (absolute).
	public static Vector absolutePosition = new Vector(10, 0.25, 1.5);

	// The displacement for creating third person effect.
	public Vector thirdPersonDisplacement = new Vector(0, 0, 0);

	// Direction of the view.
	public static Vector viewDirection = new Vector(0, 0, 1);

	// The angle that camera has rotated from the default view direction.
	public static int XZ_angle = 0;

	// The YZ_angle is 315 degrees, and it does not change.
	public static int YZ_angle = 319;

	// A rectangle that represents the screen area.
	public static final Rectangle screen = new Rectangle(XCOORDINADE,
			YCOORDINADE, WIDTH, HEIGHT);

	// A flag which indicates whether the camera should be positioned at initial
	// point.
	public static boolean restart;

	// Fly through timer
	public int flyThroughTimer;

	public Camera() {

		thirdPersonDisplacement.set(viewDirection.x, 0, -viewDirection.z);

	}
	
	private String erroDividedByZero() {
		return "No is possible divided by zero";
	}
	
	private boolean validateParamsForDivided(double numberForDivided, double valueThatWillDivideTheNumber) {
		boolean isZero = true;
		if (numberForDivided != 0 && numberForDivided != 0) {
			isZero = false;
		} else {
			isZero = true;
		}
		return isZero;		
	}
	
	private double dividedByNumber(double numberForDivided, double valueThatWillDivideTheNumber) {
		// -1 is value for initializable variable
		double numberDivided = -1;
		try {
			numberDivided = numberForDivided / valueThatWillDivideTheNumber;
		}
		catch (ArithmeticException ImpossibleDividedByZero) {
			LOG.severe(erroDividedByZero());
			System.err.println(erroDividedByZero());
			update();
		}
		return numberDivided;
	}

	private double updateDirectionX() {
		double tankPositionMinusTheCameraPositionInX = PlayerTank.bodyCenter.x - position.x;
		return dividedByNumber(tankPositionMinusTheCameraPositionInX, 5);
	}

	private double updateDirectionZ() {
		double tankPositionMinusTheCameraPositionInZ = PlayerTank.bodyCenter.z - position.z;
		return dividedByNumber(tankPositionMinusTheCameraPositionInZ, 5);
	}

	public void update() {

		if (Main.gameOver) {
			return;
		}

		if (Main.gameNotStart) {
			flyThroughTimer++;
		}

		if (!Main.gameNotStart) {

			position.subtract(thirdPersonDisplacement);

			absolutePosition.set(position);

			flyThroughTimer = 0;

			if (!restart) {

				double d_x = updateDirectionX();
				double d_z = updateDirectionZ();
				position.x += d_x;
				position.z += d_z;

			} else {
				LOG.info("New game is starting");

				double d_x = (PlayerTank.bodyCenter.x - position.x);
				double d_z = (PlayerTank.bodyCenter.z - position.z);
				position.x += d_x;
				position.z += d_z;

				restart = false;
			}

			viewDirection.set(0, 0, 1);
			viewDirection.rotate_YZ(YZ_angle);
			viewDirection.rotate_XZ(XZ_angle);
			viewDirection.unit();

			thirdPersonDisplacement.set(viewDirection.x * 0.9, 0,
					-viewDirection.z * 0.9);
			position.add(thirdPersonDisplacement);

		} else {

			if (flyThroughTimer > 0 && flyThroughTimer <= 60) {
				position.add(0, 0, 0.01);
				absolutePosition.set(position);
			}

			if (flyThroughTimer > 60 && flyThroughTimer <= 130) {

				XZ_angle -= 1;
				XZ_angle = (XZ_angle + 360) % 360;

				viewDirection.set(0, 0, 1);
				viewDirection.rotate_YZ(YZ_angle);
				viewDirection.rotate_XZ(XZ_angle);
				viewDirection.unit();
				position.add(0, 0, 0.01);
				absolutePosition.set(position);
			}

			if (flyThroughTimer > 130 && flyThroughTimer <= 430) {
				position.add(-0.01, 0, 0.01);
				absolutePosition.set(position);
			}

			if (flyThroughTimer > 430 && flyThroughTimer <= 480) {
				XZ_angle += 1;
				XZ_angle = (XZ_angle + 360) % 360;

				viewDirection.set(0, 0, 1);
				viewDirection.rotate_YZ(YZ_angle);
				viewDirection.rotate_XZ(XZ_angle);
				viewDirection.unit();
				position.add(0, 0, 0.01);
				absolutePosition.set(position);
			}

			if (flyThroughTimer > 480 && flyThroughTimer <= 800) {
				position.add(-0.005, 0, 0.01);
				absolutePosition.set(position);
			}

			if (flyThroughTimer > 800 && flyThroughTimer <= 825) {
				XZ_angle += 1;
				XZ_angle = (XZ_angle + 360) % 360;

				viewDirection.set(0, 0, 1);
				viewDirection.rotate_YZ(YZ_angle);
				viewDirection.rotate_XZ(XZ_angle);
				viewDirection.unit();
				position.add(-0.005, 0, 0.01);
				absolutePosition.set(position);
			}

			if (flyThroughTimer > 825 && flyThroughTimer <= 1100) {
				position.add(0, 0, 0.01);
				absolutePosition.set(position);
			}

			if (flyThroughTimer > 1100 && flyThroughTimer <= 1130) {
				XZ_angle += 1;
				XZ_angle = (XZ_angle + 360) % 360;

				viewDirection.set(0, 0, 1);
				viewDirection.rotate_YZ(YZ_angle);
				viewDirection.rotate_XZ(XZ_angle);
				viewDirection.unit();
				position.add(0, 0, 0.01);
				absolutePosition.set(position);
			}

			if (flyThroughTimer > 1130 && flyThroughTimer <= 1250) {
				position.add(0.005, 0, 0.01);
				absolutePosition.set(position);
			}

			if (flyThroughTimer > 1250 && flyThroughTimer <= 1290) {
				XZ_angle += 1;
				XZ_angle = (XZ_angle + 360) % 360;

				viewDirection.set(0, 0, 1);
				viewDirection.rotate_YZ(YZ_angle);
				viewDirection.rotate_XZ(XZ_angle);
				viewDirection.unit();
				position.add(0, 0, 0.01);
				absolutePosition.set(position);
			}

			if (flyThroughTimer > 1290 && flyThroughTimer <= 1550) {
				position.add(0.01, 0, 0.005);
				absolutePosition.set(position);
			}

			if (flyThroughTimer > 1550 && flyThroughTimer <= 1567) {
				XZ_angle += 1;
				XZ_angle = (XZ_angle + 360) % 360;

				viewDirection.set(0, 0, 1);
				viewDirection.rotate_YZ(YZ_angle);
				viewDirection.rotate_XZ(XZ_angle);
				viewDirection.unit();
				position.add(0.01, 0, 0.005);
				absolutePosition.set(position);
			}

			if (flyThroughTimer > 1567 && flyThroughTimer <= 1867) {
				position.add(0.012, 0, 0);
				absolutePosition.set(position);
			}

			if (flyThroughTimer > 1867 && flyThroughTimer <= 1900) {
				XZ_angle += 1;
				XZ_angle = (XZ_angle + 360) % 360;

				viewDirection.set(0, 0, 1);
				viewDirection.rotate_YZ(YZ_angle);
				viewDirection.rotate_XZ(XZ_angle);
				viewDirection.unit();
				position.add(0.01, 0, 0.005);
				absolutePosition.set(position);
			}

			if (flyThroughTimer > 1900 && flyThroughTimer <= 2100) {
				position.add(0.007, 0, -0.01);
				absolutePosition.set(position);
			}

			if (flyThroughTimer > 2100 && flyThroughTimer <= 2130) {
				XZ_angle += 1;
				XZ_angle = (XZ_angle + 360) % 360;

				viewDirection.set(0, 0, 1);
				viewDirection.rotate_YZ(YZ_angle);
				viewDirection.rotate_XZ(XZ_angle);
				viewDirection.unit();
				position.add(0.007, 0, -0.01);
				absolutePosition.set(position);
			}

			if (flyThroughTimer > 2130 && flyThroughTimer <= 2330) {
				position.add(0.003, 0, -0.01);
				absolutePosition.set(position);
			}

			if (flyThroughTimer > 2330 && flyThroughTimer <= 2360) {
				XZ_angle += 1;
				XZ_angle = (XZ_angle + 360) % 360;

				viewDirection.set(0, 0, 1);
				viewDirection.rotate_YZ(YZ_angle);
				viewDirection.rotate_XZ(XZ_angle);
				viewDirection.unit();
				position.add(0.003, 0, -0.01);
				absolutePosition.set(position);
			}

			if (flyThroughTimer > 2360 && flyThroughTimer <= 2560) {
				position.add(0, 0, -0.01);
				absolutePosition.set(position);
			}

			if (flyThroughTimer > 2560 && flyThroughTimer <= 2590) {
				XZ_angle += 1;
				XZ_angle = (XZ_angle + 360) % 360;

				viewDirection.set(0, 0, 1);
				viewDirection.rotate_YZ(YZ_angle);
				viewDirection.rotate_XZ(XZ_angle);
				viewDirection.unit();
				position.add(0, 0, -0.01);
				absolutePosition.set(position);
			}

			if (flyThroughTimer > 2590 && flyThroughTimer <= 2900) {
				position.add(-0.005, 0, -0.01);
				absolutePosition.set(position);
			}

			if (flyThroughTimer > 2900 && flyThroughTimer <= 2920) {
				XZ_angle += 1;
				XZ_angle = (XZ_angle + 360) % 360;

				viewDirection.set(0, 0, 1);
				viewDirection.rotate_YZ(YZ_angle);
				viewDirection.rotate_XZ(XZ_angle);
				viewDirection.unit();
				position.add(-0.007, 0, -0.01);
				absolutePosition.set(position);
			}

			if (flyThroughTimer > 2920 && flyThroughTimer <= 3255) {
				position.add(-0.009, 0, -0.011);
				absolutePosition.set(position);
			}

			if (flyThroughTimer > 3255 && flyThroughTimer <= 3380) {
				XZ_angle += 1;
				XZ_angle = (XZ_angle + 360) % 360;

				viewDirection.set(0, 0, 1);
				viewDirection.rotate_YZ(YZ_angle);
				viewDirection.rotate_XZ(XZ_angle);
				viewDirection.unit();
				position.set(10, 0.25, 1.5);
				absolutePosition.set(position);
			}

			if (flyThroughTimer == 3380)
				flyThroughTimer = 0;

		}

	}

	private static final Logger LOG = Logger.getLogger(Annihilator.class
			.getName());

}
