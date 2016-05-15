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

import java.awt.*;
import java.util.logging.*;

/** This class is responsible for the camera for the game. */

public class Camera {
	
	public static final double LARGER_SIZE_FOR_A_DOUBLE_VARIABLE = 321321.3123435;
	public static final double MINIMUM_MOST_FOR_A_DOUBLE_VARIABLE = -3123.434354;
	/** X coordinate of the rectangle. */
	public static final int XCOORDINADE = 0;

	/** Y coordinate of the rectangle. */
	static final int YCOORDINADE = 0;

	/** Width of rectangle. */
	final static int WIDTH = 640;

	/** Weight of rectangle. */
	final static int HEIGHT = 480;

	/** This vector represents the position of the camera (third person view). */
	public static Vector position = new Vector(10, 0.25, 1.5);

	/** This vector represents the absolute position of the camera */
	public static Vector absolutePosition = new Vector(10, 0.25, 1.5);

	/** The vector represents the displacement for creating third person effect. */
	public Vector thirdPersonDisplacement = new Vector(0, 0, 0);

	/** The vector represents the direction of the view. */
	public static Vector viewDirection = new Vector(0, 0, 1);

	/**
	 * The angle that camera has rotated from the default view direction, this
	 * angle is 315 degrees.
	 */
	public static int XZ_angle = 0;

	/**
	 * The angle that camera has rotated from the default view direction, this
	 * angle is 315 degrees.
	 */
	public static int YZ_angle = 319;

	/**
	 * This object is a large rectangle that represents the area of the game on
	 * the screen.
	 */
	public static final Rectangle screen = new Rectangle(XCOORDINADE,
			YCOORDINADE, WIDTH, HEIGHT);

	/**
	 * Variable that indicates whether the camera is positioned at the starting
	 * position.
	 */
	public static boolean restart;

	/** This variable holds the flight time of the camera. */
	public int flyThroughTimer;

	/** This is a constructor method, which init camera with default values. */
	public Camera() {
		thirdPersonDisplacement.set(viewDirection.x, 0, -viewDirection.z);
	}

	private String erroDividedByZero() {
		return "No is possible divided by zero";
	}

	public double divided(double divider,
			double dividend) {
		// -1 is value for initializable variable
		double quotient= -1;
		try {
			quotient = divider / dividend;
		} catch (ArithmeticException ImpossibleDividedByZero) {
			LOG.severe(erroDividedByZero());
			System.err.println(erroDividedByZero());
			divided(divider, dividend);
		} 
		return quotient;
	}
	
	public int centreZLessBodyCenterZPlayerTank(int dividerInt, int dividendInt) {
		double dividerDouble = (double) dividerInt;
		double dividendDouble = (double) dividendInt;
		int quotientInt = (int) divided(dividerDouble, dividendDouble);
		return quotientInt;
	}
	
	private double updateDirectionX() {
		double tankPositionMinusTheCameraPositionInX = PlayerTank.bodyCenter.x
				- position.x;
		return divided(tankPositionMinusTheCameraPositionInX, 5);
	}

	private double updateDirectionZ() {
		double tankPositionMinusTheCameraPositionInZ = PlayerTank.bodyCenter.z
				- position.z;
		return divided(tankPositionMinusTheCameraPositionInZ, 5);
	}

	/**
	 * This method is responsible for updating the camera in the scenario,
	 * according to the actions in the game.
	 */
	public void update() {
		// Stop updating camera when game is finished

		if (Main.gameOver) {
			return;
		}

		if (Main.gameNotStart) {
			flyThroughTimer++;
		}

		// Move the camera to the player's position

		if (!Main.gameNotStart) {

			// Update position

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

			// Update view direction

			viewDirection.set(0, 0, 1);
			viewDirection.rotate_YZ(YZ_angle);
			viewDirection.rotate_XZ(XZ_angle);
			viewDirection.unit();

			/*
			 * Move the camera back a little bit, so the view becomes more like
			 * third person rather than first person
			 */

			thirdPersonDisplacement.set(viewDirection.x * 0.9, 0,
					-viewDirection.z * 0.9);
			position.add(thirdPersonDisplacement);

		} else {

			// Fly through this entire level when the game isn't started

			if (flyThroughTimer <= 60) {
				position.add(0, 0, 0.01);
				absolutePosition.set(position);
			} else if (flyThroughTimer <= 130) {

				XZ_angle -= 1;
				XZ_angle = (XZ_angle + 360) % 360;

				viewDirection.set(0, 0, 1);
				viewDirection.rotate_YZ(YZ_angle);
				viewDirection.rotate_XZ(XZ_angle);
				viewDirection.unit();
				position.add(0, 0, 0.01);
				absolutePosition.set(position);
			} else if (flyThroughTimer <= 430) {
				position.add(-0.01, 0, 0.01);
				absolutePosition.set(position);
			} else if (flyThroughTimer <= 480) {
				XZ_angle += 1;
				XZ_angle = (XZ_angle + 360) % 360;

				viewDirection.set(0, 0, 1);
				viewDirection.rotate_YZ(YZ_angle);
				viewDirection.rotate_XZ(XZ_angle);
				viewDirection.unit();
				position.add(0, 0, 0.01);
				absolutePosition.set(position);
			} else if (flyThroughTimer <= 800) {
				position.add(-0.005, 0, 0.01);
				absolutePosition.set(position);
			} else if (flyThroughTimer <= 825) {
				XZ_angle += 1;
				XZ_angle = (XZ_angle + 360) % 360;

				viewDirection.set(0, 0, 1);
				viewDirection.rotate_YZ(YZ_angle);
				viewDirection.rotate_XZ(XZ_angle);
				viewDirection.unit();
				position.add(-0.005, 0, 0.01);
				absolutePosition.set(position);
			} else if (flyThroughTimer <= 1100) {
				position.add(0, 0, 0.01);
				absolutePosition.set(position);
			} else if (flyThroughTimer <= 1130) {
				XZ_angle += 1;
				XZ_angle = (XZ_angle + 360) % 360;

				viewDirection.set(0, 0, 1);
				viewDirection.rotate_YZ(YZ_angle);
				viewDirection.rotate_XZ(XZ_angle);
				viewDirection.unit();
				position.add(0, 0, 0.01);
				absolutePosition.set(position);
			} else if (flyThroughTimer <= 1250) {
				position.add(0.005, 0, 0.01);
				absolutePosition.set(position);
			} else if (flyThroughTimer <= 1290) {
				XZ_angle += 1;
				XZ_angle = (XZ_angle + 360) % 360;

				viewDirection.set(0, 0, 1);
				viewDirection.rotate_YZ(YZ_angle);
				viewDirection.rotate_XZ(XZ_angle);
				viewDirection.unit();
				position.add(0, 0, 0.01);
				absolutePosition.set(position);
			} else if (flyThroughTimer <= 1550) {
				position.add(0.01, 0, 0.005);
				absolutePosition.set(position);
			} else if (flyThroughTimer <= 1567) {
				XZ_angle += 1;
				XZ_angle = (XZ_angle + 360) % 360;

				viewDirection.set(0, 0, 1);
				viewDirection.rotate_YZ(YZ_angle);
				viewDirection.rotate_XZ(XZ_angle);
				viewDirection.unit();
				position.add(0.01, 0, 0.005);
				absolutePosition.set(position);
			} else if (flyThroughTimer <= 1867) {
				position.add(0.012, 0, 0);
				absolutePosition.set(position);
			} else if (flyThroughTimer <= 1900) {
				XZ_angle += 1;
				XZ_angle = (XZ_angle + 360) % 360;

				viewDirection.set(0, 0, 1);
				viewDirection.rotate_YZ(YZ_angle);
				viewDirection.rotate_XZ(XZ_angle);
				viewDirection.unit();
				position.add(0.01, 0, 0.005);
				absolutePosition.set(position);
			} else if (flyThroughTimer <= 2100) {
				position.add(0.007, 0, -0.01);
				absolutePosition.set(position);
			}else if (flyThroughTimer <= 2130) {
				XZ_angle += 1;
				XZ_angle = (XZ_angle + 360) % 360;

				viewDirection.set(0, 0, 1);
				viewDirection.rotate_YZ(YZ_angle);
				viewDirection.rotate_XZ(XZ_angle);
				viewDirection.unit();
				position.add(0.007, 0, -0.01);
				absolutePosition.set(position);
			}else if (flyThroughTimer <= 2330) {
				position.add(0.003, 0, -0.01);
				absolutePosition.set(position);
			}else if (flyThroughTimer <= 2360) {
				XZ_angle += 1;
				XZ_angle = (XZ_angle + 360) % 360;

				viewDirection.set(0, 0, 1);
				viewDirection.rotate_YZ(YZ_angle);
				viewDirection.rotate_XZ(XZ_angle);
				viewDirection.unit();
				position.add(0.003, 0, -0.01);
				absolutePosition.set(position);
			}else if (flyThroughTimer <= 2560) {
				position.add(0, 0, -0.01);
				absolutePosition.set(position);
			}else if (flyThroughTimer <= 2590) {
				XZ_angle += 1;
				XZ_angle = (XZ_angle + 360) % 360;

				viewDirection.set(0, 0, 1);
				viewDirection.rotate_YZ(YZ_angle);
				viewDirection.rotate_XZ(XZ_angle);
				viewDirection.unit();
				position.add(0, 0, -0.01);
				absolutePosition.set(position);
			} else if (flyThroughTimer <= 2900) {
				position.add(-0.005, 0, -0.01);
				absolutePosition.set(position);
			} else if (flyThroughTimer <= 2920) {
				XZ_angle += 1;
				XZ_angle = (XZ_angle + 360) % 360;

				viewDirection.set(0, 0, 1);
				viewDirection.rotate_YZ(YZ_angle);
				viewDirection.rotate_XZ(XZ_angle);
				viewDirection.unit();
				position.add(-0.007, 0, -0.01);
				absolutePosition.set(position);
			} else if (flyThroughTimer <= 3255) {
				position.add(-0.009, 0, -0.011);
				absolutePosition.set(position);
			} else if (flyThroughTimer <= 3380) {
				XZ_angle += 1;
				XZ_angle = (XZ_angle + 360) % 360;

				viewDirection.set(0, 0, 1);
				viewDirection.rotate_YZ(YZ_angle);
				viewDirection.rotate_XZ(XZ_angle);
				viewDirection.unit();
				position.set(10, 0.25, 1.5);
				absolutePosition.set(position);
				flyThroughTimer = 0;
			} else if (flyThroughTimer == 3380) {
				flyThroughTimer = 0;

			}

		}

	}

	private static final Logger LOG = Logger.getLogger(Annihilator.class
			.getName());

}
