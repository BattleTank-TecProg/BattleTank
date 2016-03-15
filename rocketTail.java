public class rocketTail extends solidObject {

	public vector[] particles;

	public int ALPHA = 0xFF000000;

	public vector temp;

	public vector[] directions;

	public rocketTail(vector centre) {
		start = centre.myClone();
		this.centre = centre;

		iDirection = new vector(1, 0, 0);
		jDirection = new vector(0, 1, 0);
		kDirection = new vector(0, 0, 1);

		modelType = 4;
		makeBoundary(0.01, 0.025, 0.01);

		particles = new vector[15];
		directions = new vector[15];
		for (int i = 0; i < 15; i++) {
			particles[i] = centre.myClone();
			directions[i] = new vector(0.00005 * gameData.getRandom() - 0.0025,
					0.00005 * gameData.getRandom() - 0.0025,
					0.00005 * gameData.getRandom() - 0.0025);
			directions[i].scale(0.8);
		}

		lifeSpan = 35;

		temp = new vector(0, 0, 0);
	}

	public void update() {
		visible = true;

		lifeSpan--;

		modelDrawList.register(this);

		for (int i = 0; i < 5; i++)
			boundary[i].update();

		for (int i = 0; i < 15; i++)
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
		int factor = 0;

		for (int i = 0; i < particles.length; i++) {
			temp.set(particles[i]);
			temp.subtract(camera.position);
			temp.rotate_XZ(camera.XZ_angle);
			temp.rotate_YZ(camera.YZ_angle);
			temp.updateLocation();

			if (temp.screenX >= 2 && temp.screenX < 638 && temp.screenY >= 0
					&& temp.screenY < 480) {
				int centre = temp.screenX + temp.screenY * 640;

				factor = 200;
				factor = factor - factor * lifeSpan / 50 + 55;

				double size = 1 / temp.z;
				int spriteIndex = 0;
				if (size < 0.3) {
					spriteIndex = 8;
				} else if (size < 0.4 && size >= 0.3) {
					spriteIndex = 2;
				} else if (size < 0.5 && size >= 0.4) {
					spriteIndex = 4;
				} else if (size < 0.6 && size >= 0.5) {
					spriteIndex = 5;
				} else if (size < 0.7 && size >= 0.6) {
					spriteIndex = 5;
				} else if (size < 0.8 && size >= 0.7) {
					spriteIndex = 6;
				} else if (size < 0.9 && size >= 0.8) {
					spriteIndex = 7;
				}

				for (int j = 0; j < gameData.size[spriteIndex].length; j++) {
					position = centre + gameData.size[spriteIndex][j];
					if (position >= 0 && position < 307200) {
						color = main.screen[position];
						r = (((color >> 16) & 255) * factor) >> 8;
						g = (((color >> 8) & 255) * factor) >> 8;
						b = ((color & 255) * factor) >> 8;
						main.screen[position] = ALPHA | (r << 16) | (g << 8)
								| b;
					}
				}
			}
		}
	}

	public rectangle2D getBoundary2D() {
		return boundary2D;
	}

}
