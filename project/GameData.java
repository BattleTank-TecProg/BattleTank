package project;

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
	private final static int SCREENWIDHT = 640;
	private final static int SCREENHEIGHT = 480;

	/** Initialize the datas on the game. */
	public static void makeData() {

		// Make a screen table, so a pixel index can be retrived quickly

		screenTable = new int[SCREENHEIGHT];

		for (int i = 0; i < SCREENHEIGHT; i++) {
			screenTable[i] = SCREENWIDHT * i;
		}

		// Make random number table

		random = new int[SIZE_VALUES_RANDOM_VECTOR];

		for (int i = 0; i < SIZE_VALUES_RANDOM_VECTOR; i++) {
			random[i] = (int) (Math.random() * 100);
		}

		random = generateRandomVector(random, 101);

		// Make sin and cos look up tables

		sin = new double[361];
		cos = new double[361];
		for (int i = 0; i < 361; i++) {
			sin[i] = Math.sin(Math.PI * i / 180);
			cos[i] = Math.cos(Math.PI * i / 180);
		}
		/*
		 * Make color palette. The main color palette has 32768 (15bits)
		 * different colors with 64 different intensity levels, The default
		 * intensity is at level 31 .
		 */
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

			// Calculated the intensity from lvl 0 ~ 31

			for (int j = 0; j < 32; j++) {
				r_ = (int) (r - dr * j);
				g_ = (int) (g - dg * j);
				b_ = (int) (b - db * j);
				colorTableTemp[i][31 - j] = b_ + (g_ << 8) + (r_ << 16);
			}

			dr = r * 0.7 / 32;
			dg = g * 0.7 / 32;
			db = b * 0.7 / 32;

			// calculated the intensity from lvl 32 ~ 63

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

		// Free memory used by creating color table
		colorTableTemp = null;

		// Create randomVectors, they will be used in generating smoke particles
		randomVectors = new Vector[SIZE_VALUES_RANDOM_VECTOR];
		for (int i = 0; i < SIZE_VALUES_RANDOM_VECTOR; i++) {
			randomVectors[i] = new Vector(Math.random() * 0.016 - 0.008, 0.01, Math.random() * 0.016 - 0.008);
		}

		// Generate sprites for particles with different size
		size = new int[9][];
		size[0] = new int[] { 0, -1, -SCREENWIDHT };
		size[1] = new int[] { -(SCREENWIDHT + 1), 0, -1, -SCREENWIDHT };
		size[2] = new int[] { 1, 0, -1, -SCREENWIDHT, SCREENWIDHT };
		size[3] = new int[] { -(SCREENWIDHT + 1), -(SCREENWIDHT - 1), 1, 0, -1, -SCREENWIDHT, SCREENWIDHT };
		size[4] = new int[] { -(SCREENWIDHT + 1), -(SCREENWIDHT - 1), 1, 0, -1, -SCREENWIDHT, SCREENWIDHT,
				(SCREENWIDHT - 1), (SCREENWIDHT + 1) };
		size[5] = new int[] { -1280, -1281, -(SCREENWIDHT + 2), -(SCREENWIDHT + 1), -SCREENWIDHT, -(SCREENWIDHT - 1),
				-2, -1, 0, 1, SCREENWIDHT - 1, SCREENWIDHT };
		size[6] = new int[] { -1281, -1279, -(SCREENWIDHT + 2), -(SCREENWIDHT - 2), SCREENWIDHT - 2, SCREENWIDHT + 2,
				1279, 1281, -1280, -(SCREENWIDHT + 1), -2, -(SCREENWIDHT - 1), 1, 2, 0, -1, -SCREENWIDHT, SCREENWIDHT,
				(SCREENWIDHT - 1), SCREENWIDHT + 1, 1280 };
		size[7] = new int[] { -1278, -1282, 1282, 1278, -1920, 1920, -3, 3, -1281, -1279, -(SCREENWIDHT + 2),
				-(SCREENWIDHT - 2), SCREENWIDHT - 2, SCREENWIDHT + 2, 1279, 1281, -1280, -(SCREENWIDHT + 1), -2,
				-(SCREENWIDHT - 1), 1, 2, 0, -1, -SCREENWIDHT, SCREENWIDHT, SCREENWIDHT - 1, SCREENWIDHT + 1, 1280 };
		size[8] = new int[] { 0 };

		// Generate distortion1 map, it solely used by stealth tank
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

	/**
	 * This methods is responsible for generate a random vector
	 * @param int random[]
	 * @param int sizeVector
	 */
	private static int[] generateRandomVector(int random[], int sizeVector) {
		Random generator = new Random();

		for (int i = 0; i < 999; i++) {
			random[i] = generator.nextInt(sizeVector);
		}
		return random;
	}

}
