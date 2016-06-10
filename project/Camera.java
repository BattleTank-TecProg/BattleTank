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

import java.awt.*;
import java.util.logging.*;

/** This class is responsible for the camera for the game. */

public class Camera {

	public static final double MAX = 321321.3123435;
	public static final double MIN = -3123.434354;
	/** X coordinate of the rectangle. */
	private static final int XCOORDINADE = 0;

	/** Y coordinate of the rectangle. */
	private static final int YCOORDINADE = 0;

	/** Width of rectangle. */
	private final static int WIDTH = 640;

	/** Weight of rectangle. */
	private final static int HEIGHT = 480;

	/** This vector represents the position of the camera (third person view). */
	private static Vector position = new Vector(10, 0.25, 1.5);

	/** This vector represents the absolute position of the camera */
	private static Vector absolutePosition = new Vector(10, 0.25, 1.5);

	/** The vector represents the displacement for creating third person effect. */
	private Vector thirdPersonDisplacement = new Vector(0, 0, 0);

	/** The vector represents the direction of the view. */
	private static Vector viewDirection = new Vector(0, 0, 1);

	/**
	 * The angle that camera has rotated from the default view direction, this
	 * angle is 315 degrees.
	 */
	private static int XZ_angle = 0;

	/**
	 * The angle that camera has rotated from the default view direction, this
	 * angle is 315 degrees.
	 */
	private static int YZ_angle = 319;

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
	private static boolean restart;

	/**
	 * Method that returns a Vector regarding the position of the object.
	 *
	 * @return position
	 */
	public static Vector getPosition() {
		return position;
	}

	/**
	 * Method that set a Vector regarding the position of the object.
	 *
	 * @param position
	 */
	public static void setPosition(Vector position) {
		Camera.position = position;
	}

	/**
	 * Method that return a Vector regarding the absolute position of the
	 * object.
	 *
	 * @return position
	 */
	public static Vector getAbsolutePosition() {
		return absolutePosition;
	}

	/**
	 * Method that set a Vector regarding the absolute position of the object.
	 *
	 * @param position
	 */
	public static void setAbsolutePosition(Vector absolutePosition) {
		Camera.absolutePosition = absolutePosition;
	}

	/**
	 * This method returns a vector referring to the third person displacement.
	 *
	 * @return thirdPersonDisplacement
	 */
	public Vector getThirdPersonDisplacement() {
		return thirdPersonDisplacement;
	}

	/**
	 * This method set a vector referring to the third person displacement.
	 *
	 * @param thirdPersonDisplacement
	 */
	public void setThirdPersonDisplacement(Vector thirdPersonDisplacement) {
		this.thirdPersonDisplacement = thirdPersonDisplacement;
	}

	/**
	 * This method returns an array refers to the vision of direction.
	 *
	 * @return viewDirection
	 */
	public static Vector getViewDirection() {
		return viewDirection;
	}

	/**
	 * This method set an array refers to the vision of direction.
	 *
	 * @param viewDirection
	 */
	public static void setViewDirection(Vector viewDirection) {
		Camera.viewDirection = viewDirection;
	}

	/**
	 * This method returns the angle related to X and Z coordinates.
	 * 
	 * @return XZ_angle
	 */
	public static int getXZ_angle() {
		return XZ_angle;
	}

	/**
	 * This method set the angle related to X and Z coordinates.
	 * 
	 * @param XZ_angle
	 */
	public static void setXZ_angle(int xZ_angle) {
		XZ_angle = xZ_angle;
	}

	/**
	 * This method returns the angle related to Y and Z coordinates.
	 * 
	 * @return YZ_angle
	 */
	public static int getYZ_angle() {
		return YZ_angle;
	}

	/**
	 * This method set the angle related to Y and Z coordinates.
	 * 
	 * @param YZ_angle
	 */
	public static void setYZ_angle(int yZ_angle) {
		YZ_angle = yZ_angle;
	}

	/**
	 * This method boolean indicating whether the user has restarted the game or
	 * not.
	 * 
	 * @return restart
	 */
	public static boolean isRestart() {
		return restart;
	}

	/**
	 * This method boolean set whether the user has restarted the game or not.
	 * 
	 * @param restart
	 */
	public static void setRestart(boolean restart) {
		Camera.restart = restart;
	}

	/**
	 * This method returns the flight through time.
	 * 
	 * @return flyThroughTimer
	 */
	public int getFlyThroughTimer() {
		return flyThroughTimer;
	}

	/**
	 * This method set the flight through time.
	 * 
	 * @param flyThroughTimer
	 */
	public void setFlyThroughTimer(int flyThroughTimer) {
		this.flyThroughTimer = flyThroughTimer;
	}

	/**
	 * This method returns the size larger than a variable type double may have.
	 * 
	 * @return MAX
	 */
	public static double getLargerSizeForADoubleVariable() {
		return MAX;
	}

	/**
	 * This method returns the size smaller than a variable type double may
	 * have.
	 * 
	 * @return MIN
	 */
	public static double getMinimumMostForADoubleVariable() {
		return MIN;
	}
	
	/**
	 * This method returns the max value for type double
	 * have.
	 * 
	 * @return MAX
	 */
	public static double getMaximunMostForADoubleVariable() {
		return MAX;
	}
	
	/**
	 * This method returns the X coordinate of.
	 * 
	 * @return XCOORDINADE
	 */
	public static int getXcoordinade() {
		return XCOORDINADE;
	}

	/**
	 * This method returns the Y coordinate of.
	 * 
	 * @return YCOORDINADE
	 */
	public static int getYcoordinade() {
		return YCOORDINADE;
	}

	/**
	 * This method returns the width.
	 * 
	 * @return WIDTH
	 */
	public static int getWidth() {
		return WIDTH;
	}

	/**
	 * This method returns the height.
	 * 
	 * @return HEIGHT
	 */
	public static int getHeight() {
		return HEIGHT;
	}

	/**
	 * This method returns a rectangle related to screen position.
	 * 
	 * @return screen
	 */
	public static Rectangle getScreen() {
		return screen;
	}

	/**
	 * This method returns the record concerning what the code is doing.
	 * 
	 * @return LOG
	 */
	public static Logger getLog() {
		return LOG;
	}

	/** This variable holds the flight time of the camera. */
	public int flyThroughTimer;

	/**
	 * This is a constructor method, which init camera with default values.
	 */
	public Camera() {
		thirdPersonDisplacement = new Vector(0, 0, 0);
		thirdPersonDisplacement.set(viewDirection.x, 0, -viewDirection.z);
	}

	/**
	 * This method is responsible for updating the camera in the scenario,
	 * according to the actions in the game.
	 */
	public void update() {
		// Stop updating camera when game is finished

		if (Main.gameOver) {
			// Move the camera to the player's position
		} else {
			if (!Main.gameNotStart) {

				// Update position

				position.subtract(thirdPersonDisplacement);

				absolutePosition.set(position);

				flyThroughTimer = 0;

				if (!restart) {

					double d_x = (PlayerTank.bodyCenter.x - position.x) / 5;
					double d_z = (PlayerTank.bodyCenter.z - position.z) / 5;
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
				 * Move the camera back a little bit, so the view becomes more
				 * like third person rather than first person
				 */

				thirdPersonDisplacement.set(viewDirection.x * 0.9, 0,
						-viewDirection.z * 0.9);
				position.add(thirdPersonDisplacement);

			} else {
				flyThroughTimer++;

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
				} else if (flyThroughTimer <= 2130) {
					XZ_angle += 1;
					XZ_angle = (XZ_angle + 360) % 360;

					viewDirection.set(0, 0, 1);
					viewDirection.rotate_YZ(YZ_angle);
					viewDirection.rotate_XZ(XZ_angle);
					viewDirection.unit();
					position.add(0.007, 0, -0.01);
					absolutePosition.set(position);
				} else if (flyThroughTimer <= 2330) {
					position.add(0.003, 0, -0.01);
					absolutePosition.set(position);
				} else if (flyThroughTimer <= 2360) {
					XZ_angle += 1;
					XZ_angle = (XZ_angle + 360) % 360;

					viewDirection.set(0, 0, 1);
					viewDirection.rotate_YZ(YZ_angle);
					viewDirection.rotate_XZ(XZ_angle);
					viewDirection.unit();
					position.add(0.003, 0, -0.01);
					absolutePosition.set(position);
				} else if (flyThroughTimer <= 2560) {
					position.add(0, 0, -0.01);
					absolutePosition.set(position);
				} else if (flyThroughTimer <= 2590) {
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
				} else {
					// Does nothing.
				}
			}
		}
	}

	private static final Logger LOG = Logger.getLogger(Annihilator.class
			.getName());

}
