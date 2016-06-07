public class Smoke {
	public Model emitter;

	public Vector[] particles;

	public Vector temp;

	public int ALPHA = 0xFF000000;

	public boolean stopped;

	public Vector soruceCentre;

	public Smoke(Model emitter) {
		this.emitter = emitter;

		if (emitter != null)
			soruceCentre = emitter.getRealCentre();

		particles = new Vector[40];
		temp = new Vector(0, 0, 0);
	}

	public Smoke(Vector soruceCentre) {
		this.soruceCentre = soruceCentre;

		particles = new Vector[40];
		temp = new Vector(0, 0, 0);
	}

	public void draw() {
		int position = 0;
		int color = 0;
		int r = 0;
		int b = 0;
		int g = 0;
		int factor = 0;

		for (int i = 0; i < particles.length; i++) {
			if (particles[i] == null)
				continue;

			temp.set(particles[i]);
			temp.subtract(Camera.getPosition());
			temp.rotate_XZ(Camera.getXZ_angle());
			temp.rotate_YZ(Camera.getYZ_angle());
			temp.updateLocation();

			if (temp.screenX >= 2 && temp.screenX < 638 && temp.screenY >= 0
					&& temp.screenY < 480) {
				int centre = temp.screenX + temp.screenY * 640;

				factor = 200;
				factor -= (factor * (-0.5 - particles[i].y) / 0.4);
				factor += 55;

				double size = 1 / temp.z;
				int spriteIndex = 0;
				if (size < 0.2) {
					spriteIndex = 8;
				} else if (size < 0.3 && size >= 0.2) {
					spriteIndex = 0;
				} else if (size < 0.4 && size >= 0.3) {
					spriteIndex = 1;
				} else if (size < 0.5 && size >= 0.4) {
					spriteIndex = 3;
				} else if (size < 0.6 && size >= 0.5) {
					spriteIndex = 3;
				} else if (size < 0.7 && size >= 0.6) {
					spriteIndex = 4;
				} else if (size < 0.8 && size >= 0.7) {
					spriteIndex = 5;
				} else if (size < 0.9 && size >= 0.8) {
					spriteIndex = 6;
				} else if (size >= 0.9) {
					spriteIndex = 7;
				}

				for (int j = 0; j < GameData.size[spriteIndex].length; j++) {
					position = centre + GameData.size[spriteIndex][j];
					if (position >= 0 && position < 307200) {
						color = Main.screen[position];
						r = (((color >> 16) & 255) * factor) >> 8;
						g = (((color >> 8) & 255) * factor) >> 8;
						b = ((color & 255) * factor) >> 8;
						Main.screen[position] = ALPHA | (r << 16) | (g << 8)
								| b;
					}
				}

			}
		}
	}

	public void update() {
		for (int i = 0; i < particles.length; i++) {
			if (particles[i] == null && !stopped) {
				particles[i] = soruceCentre.myClone();
				particles[i].y = -0.9;
				break;
			} else {
				if (particles[i] != null) {
					if (Main.timer % 2 == 0)
						particles[i].add(GameData.getRandomVector());
					else
						particles[i].y += 0.01;
					if (particles[i].y >= -0.5) {
						if (stopped) {
							particles[i] = null;
							continue;
						}
						particles[i].set(soruceCentre);
						particles[i].y = -0.9;

					}
				}
			}
		}
	}
}