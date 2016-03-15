import java.awt.*;

public class helix extends solidObject {

	public vector[] particles;

	public vector[] directions;

	public int[] colors;

	public int ALPHA = 0xFF000000;

	public vector temp1 = new vector(0, 0, 0);
	public vector temp2 = new vector(0, 0, 0);

	public helix(vector centre, int angle) {
		start = centre.myClone();
		this.centre = centre;
		angle += 360;
		angle %= 360;

		iDirection = new vector(1, 0, 0);
		jDirection = new vector(0, 1, 0);
		kDirection = new vector(0, 0, 1);

		modelType = 4;
		makeBoundary(0.01, 0.025, 0.01);

		boundary2D = new rectangle2D(start.x - 0.01, start.z + 0.01, 0.02, 0.02);

		particles = new vector[20];
		directions = new vector[20];
		colors = new int[20];
		int zAxisRotation = 0;

		temp1.set(centre);
		temp2.set(kDirection);
		temp2.rotate_XZ(angle);
		temp2.scale(0.05);
		temp1.subtract(temp2);
		temp2.scale(0.1);
		for (int i = 0; i < particles.length; i++) {
			directions[i] = iDirection.myClone();
			directions[i].rotate_XY(zAxisRotation);
			directions[i].rotate_XZ(angle);
			directions[i].scale(0.02);
			particles[i] = temp1.myClone();
			particles[i].add(directions[i]);
			directions[i].scale(0.02);
			colors[i] = new Color(
					(int) (58 - 20 * gameData.sin[zAxisRotation]),
					(int) (130 - 40 * gameData.sin[zAxisRotation]),
					(int) (165 - 40 * gameData.sin[zAxisRotation])).getRGB();
			zAxisRotation += 18;
			temp1.add(temp2);

		}

		lifeSpan = 40;
	}

	public rectangle2D getBoundary2D() {
		return boundary2D;
	}

	public void update() {
		visible = true;

		lifeSpan--;

		if (lifeSpan == 0) {
			lifeSpan = -1;
			return;
		}

		modelDrawList.register(this);

		for (int i = 0; i < 5; i++)
			boundary[i].update();

		for (int i = 0; i < particles.length; i++)
			particles[i].add(directions[i]);

		tempCentre.set(centre);
		tempCentre.y = -1;
		tempCentre.subtract(camera.position);
		tempCentre.rotate_XZ(camera.XZ_angle);
		tempCentre.rotate_YZ(camera.YZ_angle);

	}

	public void draw() {
		int position = 0;
		int color = 0;
		int r = 0;
		int b = 0;
		int g = 0;
		int alpha = 0;

		double size = 1 / tempCentre.z;

		int spriteIndex = 0;
		if (size < 0.3) {
			spriteIndex = 0;
		} else if (size < 0.4 && size >= 0.3) {
			spriteIndex = 1;
		} else if (size < 0.5 && size >= 0.4) {
			spriteIndex = 2;
		} else if (size < 0.6 && size >= 0.5) {
			spriteIndex = 3;
		} else if (size < 0.7 && size >= 0.6) {
			spriteIndex = 4;
		} else if (size < 0.8 && size >= 0.7) {
			spriteIndex = 5;
		} else if (size >= 0.8) {
			spriteIndex = 6;
		}

		for (int i = 19; i >= 0; i--) {
			temp1.set(particles[i]);
			temp1.subtract(camera.position);
			temp1.rotate_XZ(camera.XZ_angle);
			temp1.rotate_YZ(camera.YZ_angle);
			temp1.updateLocation();

			if (temp1.screenX >= 2 && temp1.screenX < 638 && temp1.screenY >= 0
					&& temp1.screenY < 480) {
				int centre = temp1.screenX + temp1.screenY * 640;

				if (lifeSpan > 30)
					alpha = 55;
				else {
					alpha = 200;
					alpha = alpha - alpha * lifeSpan / 30 + 55;
				}

				for (int j = 0; j < gameData.size[spriteIndex].length; j++) {
					position = centre + gameData.size[spriteIndex][j];
					if (position >= 0 && position < 307200) {
						int bkgrd = main.screen[position];

						color = colors[i];
						r = (alpha
								* (((bkgrd >> 16) & 255) - ((color >> 16) & 255)) >> 8)
								+ ((color >> 16) & 255);
						g = (alpha
								* (((bkgrd >> 8) & 255) - ((color >> 8) & 255)) >> 8)
								+ ((color >> 8) & 255);
						b = (alpha * ((bkgrd & 255) - (color & 255)) >> 8)
								+ (color & 255);

						main.screen[position] = ALPHA | (r << 16) | (g << 8)
								| b;

					}
				}

			}
		}
	}
}
