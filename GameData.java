import java.util.Random;

/**
 * This class is responsible for store useful arithmetic data for the game
 * engine such as Cos/Sin, look up table, color palette, etc…
 * 
 * This class is part of BattleTank 2.
 * 
 * BattleTank 2 is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * BattleTank 2 is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * BattleTank 2. If not, see <http://www.gnu.org/licenses/>
 */

public class GameData {
	public static int random[];
	public static int randomIndex;
	public static double sin[];
	public static double cos[];
	public static int colorTable[][];
	public static int screenTable[];
	public static Vector randomVectors[];
	public static int size[][];
	public static int distortion1[];
	public static short distortion2[];
	private static int VALUE_SIMPLE_DISTORTION = 128;
	private static int VALUE_DISTORTION = VALUE_SIMPLE_DISTORTION * VALUE_SIMPLE_DISTORTION;
	private static int SIZE_VALUES_RANDOM_VECTOR = 1000;

	/** Initialize the datas on the game. */
	public static void makeData() {

		screenTable = new int[480];

		for (int i = 0; i < 480; i++) {
			screenTable[i] = 640 * i;
		}

		random = new int[SIZE_VALUES_RANDOM_VECTOR];

		for (int i = 0; i < SIZE_VALUES_RANDOM_VECTOR; i++) {
			random[i] = (int) (Math.random() * 100);
		}

		random = generateRandomVector(random, 101);

		sin = new double[361];
		cos = new double[361];
		for (int i = 0; i < 361; i++) {
			sin[i] = Math.sin(Math.PI * i / 180);
			cos[i] = Math.cos(Math.PI * i / 180);
		}

		colorTable = new int[64][32768];
		int colorTableTemp[][] = new int[32768][64];

		double r, g, b, dr, dg, db;
		int r_, g_, b_;

		for (int i = 0; i < 32768; i++) {
			r = (double) ((i & 31744) >> 10) * 8;
			g = (double) ((i & 992) >> 5) * 8;
			b = (double) ((i & 31)) * 8;

			dr = r * 0.9 / 32;
			dg = g * 0.9 / 32;
			db = b * 0.9 / 32;

			for (int j = 0; j < 32; j++) {
				r_ = (int) (r - dr * j);
				g_ = (int) (g - dg * j);
				b_ = (int) (b - db * j);
				colorTableTemp[i][31 - j] = b_ + (g_ << 8) + (r_ << 16);
			}

			dr = r * 0.7 / 32;
			dg = g * 0.7 / 32;
			db = b * 0.7 / 32;

			for (int j = 1; j <= 32; j++) {
				r_ = (int) (r + dr * j);
				g_ = (int) (g + dg * j);
				b_ = (int) (b + db * j);
				if (r_ > 245) {
					r_ = 245;
				} else {
					// Does nothing.
				}

				if (g_ > 245) {
					g_ = 245;
				} else {
					// Does nothing.
				}
				if (b_ > 245) {
					b_ = 245;
				} else {
					// Does nothing.
				}
				colorTableTemp[i][31 + j] = b_ + (g_ << 8) + (r_ << 16);
			}
		}

		for (int i = 0; i < 64; i++) {
			for (int j = 0; j < 32768; j++) {
				colorTable[i][j] = colorTableTemp[j][i];
			}
		}

		colorTableTemp = null;

		randomVectors = new Vector[SIZE_VALUES_RANDOM_VECTOR];
		for (int i = 0; i < SIZE_VALUES_RANDOM_VECTOR; i++) {
			randomVectors[i] = new Vector(Math.random() * 0.016 - 0.008, 0.01, Math.random() * 0.016 - 0.008);
		}

		size = new int[9][];
		size[0] = new int[] { 0, -1, -640 };
		size[1] = new int[] { -641, 0, -1, -640 };
		size[2] = new int[] { 1, 0, -1, -640, 640 };
		size[3] = new int[] { -641, -639, 1, 0, -1, -640, 640 };
		size[4] = new int[] { -641, -639, 1, 0, -1, -640, 640, 639, 641 };
		size[5] = new int[] { -1280, -1281, -642, -641, -640, -639, -2, -1, 0, 1, 639, 640 };
		size[6] = new int[] { -1281, -1279, -642, -638, 638, 642, 1279, 1281, -1280, -641, -2, -639, 1, 2, 0, -1, -640,
				640, 639, 641, 1280 };
		size[7] = new int[] { -1278, -1282, 1282, 1278, -1920, 1920, -3, 3, -1281, -1279, -642, -638, 638, 642, 1279,
				1281, -1280, -641, -2, -639, 1, 2, 0, -1, -640, 640, 639, 641, 1280 };
		size[8] = new int[] { 0 };

		distortion1 = new int[VALUE_DISTORTION];
		distortion2 = new short[VALUE_DISTORTION];
		for (int i = 0; i < VALUE_DISTORTION; i++) {
			distortion2[i] = (short) ((getRandom() * 1.5) - 75);
		}

		for (int j = 0; j < 2; j++) {

			for (int i = 0; i < VALUE_DISTORTION; i++) {
				distortion1[i] = distortion2[i];
			}

			for (int i = 0; i < VALUE_DISTORTION; i++) {
				int average = 0;

				for (int y = -3; y < 4; y++) {
					for (int x = -3; x < 4; x++) {
						int index = ((i + VALUE_SIMPLE_DISTORTION * y + x) + VALUE_DISTORTION) % VALUE_DISTORTION;

						average += distortion1[index];
					}
				}

				distortion2[i] = (short) (average / 49);

			}
		}

		distortion1 = new int[VALUE_DISTORTION];
		for (int j = 0; j < VALUE_SIMPLE_DISTORTION; j++) {
			for (int k = 0; k < VALUE_SIMPLE_DISTORTION; k++) {
				distortion1[j * VALUE_SIMPLE_DISTORTION
						+ k] = (int) (Math.sin(Math.PI / 32 * k + Math.PI / 8) * 10 * Math.sin(Math.PI / 16 * j));
			}
		}

	}

	/**
	 * Get a random number.
	 * 
	 * @return random[randomIndex]
	 */
	public static int getRandom() {
		randomIndex++;
		if (randomIndex >= SIZE_VALUES_RANDOM_VECTOR) {
			randomIndex = 0;
		} else {
			// Does nothing.
		}
		return random[randomIndex];

	}

	/**
	 * This method is responsible for get a random vector.
	 * 
	 * @return randomVectors[randomIndex]
	 */
	public static Vector getRandomVector() {
		randomIndex++;
		if (randomIndex >= SIZE_VALUES_RANDOM_VECTOR) {
			randomIndex = 0;
		} else {
			// Does nothing.
		}
		return randomVectors[randomIndex];

	}

	/**
	 * This methods is responsible for frees the data stored when the applet is
	 * finished.
	 */
	public static void destroy() {
		random = null;
		randomVectors = null;
		sin = null;
		cos = null;
		colorTable = null;
		screenTable = null;
	}

	private static int[] generateRandomVector(int random[], int sizeVector) {
		Random generator = new Random();

		for (int i = 0; i < 999; i++) {
			random[i] = generator.nextInt(sizeVector);
			System.out.printf("%d", random[i]);

		}
		return random;
	}

}
