import java.awt.*;

public class Camera {

	public static vector position;

	public static vector absolutePosition;

	public vector thirdPersonDisplacement;

	public static vector viewDirection;

	public static int XZ_angle, YZ_angle = 319;

	public static final Rectangle screen = new Rectangle(0, 0, 640, 480);

	public static boolean restart;

	public int flyThroughTimer;

	public Camera() {

		XZ_angle = 0;
		position = new vector(10, 0.25, 1.5);
		absolutePosition = new vector(10, 0.25, 1.5);
		viewDirection = new vector(0, 0, 1);
		thirdPersonDisplacement = new vector(0, 0, 0);
		thirdPersonDisplacement.set(viewDirection.x, 0, -viewDirection.z);
	}

	public void update() {

		if (main.gameOver) {
			return;
		}

		if (main.gameNotStart) {
			flyThroughTimer++;
		}

		if (!main.gameNotStart) {

			position.subtract(thirdPersonDisplacement);

			absolutePosition.set(position);

			flyThroughTimer = 0;

			if (!restart) {
				double d_x = (playerTank.bodyCenter.x - position.x) / 5;
				double d_z = (playerTank.bodyCenter.z - position.z) / 5;
				position.x += d_x;
				position.z += d_z;
			} else {
				double d_x = (playerTank.bodyCenter.x - position.x);
				double d_z = (playerTank.bodyCenter.z - position.z);
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
}