public class rasterizer {
	public static int[] xLow = new int[480], xHigh = new int[480];

	public static vector W = new vector(0, 0, 0), O = new vector(0, 0, 0),
			V = new vector(0, 0, 0), U = new vector(0, 0, 0), A = new vector(0,
					0, 0), B = new vector(0, 0, 0), C = new vector(0, 0, 0);

	public static vector tempVector1 = new vector(0, 0, 0),
			tempVector2 = new vector(0, 0, 0),
			tempVector3 = new vector(0, 0, 0),
			tempVector4 = new vector(0, 0, 0);

	public static polygon3D poly;

	public static vector[] tempVertex, vertex2D;
	public static int L, widthMask, heightMask, widthBits, diffuse_I;
	public static double A_offset, B_offset, C_offset;

	public static int alpha;

	public static int visibleCount;

	public static double aDotW, bDotW, cDotW, cDotWInverse;
	public static int BigX, BigY, d_x, d_y, k, X1, Y1, BigDx, BigDy, dx, dy, X,
			Y, textureIndex, temp, temp1, temp2, r, g, b, scale, yOffset,
			xOffset;
	public static short I, variation;

	public static void rasterize(polygon3D polygon) {
		poly = polygon;
		L = poly.L;
		widthMask = poly.widthMask;
		heightMask = poly.heightMask;
		widthBits = poly.widthBits;
		tempVertex = poly.tempVertex;
		vertex2D = poly.vertex2D;
		alpha = poly.alpha;

		if (poly.type != 7)
			findVectorOUV();

		if (poly.withinViewScreen) {
			visibleCount = L;
			for (int i = 0; i < L; i++) {
				vertex2D[i].set(tempVertex[i]);
				vertex2D[i].setScreenLocation(tempVertex[i]);
			}
		} else
			findClipping();

		scanPolygon();

		if (poly.type == 6)
			renderModel();
		else if (poly.type == 1)
			renderTerrain();
		else if (poly.type == 2)
			renderLightMap();
		else if (poly.type == 3)
			renderLightMap2();
		else if (poly.type == 5)
			renderWater();
		else if (poly.type == 7)
			renderSoildPolygon();
		else if (poly.type == 8)
			renderTransparent1();
		else if (poly.type == 9)
			renderTransparent2();
	}

	public static void findVectorOUV() {
		O.set(poly.origin);
		O.subtract(Camera.position);
		O.rotate_XZ(Camera.XZ_angle);
		O.rotate_YZ(Camera.YZ_angle);

		U.set(poly.rightEnd);
		U.subtract(Camera.position);
		U.rotate_XZ(Camera.XZ_angle);
		U.rotate_YZ(Camera.YZ_angle);

		V.set(poly.bottomEnd);
		V.subtract(Camera.position);
		V.rotate_XZ(Camera.XZ_angle);
		V.rotate_YZ(Camera.YZ_angle);

		U.subtract(O);
		U.unit();
		U.scale(poly.textureScaleX);

		V.subtract(O);
		V.unit();
		V.scale(poly.textureScaleY);

		A = V.cross(O);
		B = O.cross(U);
		C = U.cross(V);
	}

	public static void findClipping() {
		visibleCount = 0;

		for (int i = 0; i < L; i++) {
			if (tempVertex[i].z >= 0.01) {
				vertex2D[visibleCount].set(tempVertex[i]);
				vertex2D[visibleCount].setScreenLocation(tempVertex[i]);
				visibleCount++;
			} else {
				int index = (i + L - 1) % L;
				if (tempVertex[index].z >= 0.01) {
					approximatePoint(visibleCount, tempVertex[i],
							tempVertex[index]);
					visibleCount++;
				}
				index = (i + 1) % L;
				if (tempVertex[index].z >= 0.01) {
					approximatePoint(visibleCount, tempVertex[i],
							tempVertex[index]);
					visibleCount++;
				}
			}
		}
	}

	public static void approximatePoint(int index, vector behindPoint,
			vector frontPoint) {
		tempVector1.set(frontPoint.x - behindPoint.x, frontPoint.y
				- behindPoint.y, frontPoint.z - behindPoint.z);
		tempVector1.scale(frontPoint.z / tempVector1.z);
		vertex2D[index].set(frontPoint.x, frontPoint.y, frontPoint.z);
		vertex2D[index].subtract(tempVector1);
		vertex2D[index].add(0, 0, 0.01);
		vertex2D[index].updateLocation();
	}

	public static void scanPolygon() {
		int start = 479;
		int end = 0;
		int temp_ = 0;
		int g = 0;

		for (int i = 0; i < visibleCount; i++) {
			vector v1 = vertex2D[i];
			vector v2;

			if (i == visibleCount - 1) {
				v2 = vertex2D[0];
			} else {
				v2 = vertex2D[i + 1];
			}

			boolean downwards = false;

			if (v1.screenY > v2.screenY) {
				downwards = true;
				vector temp = v1;
				v1 = v2;
				v2 = temp;
			}
			int dy = v2.screenY - v1.screenY;

			if (dy == 0) {
				continue;
			}

			if (poly.withinViewScreen) {
				int startY = Math.max(v1.screenY, 0);
				int endY = Math.min(v2.screenY, 479);
				if (startY < start)
					start = startY;
				if (endY > end)
					end = endY;

				double dx = v2.screenX - v1.screenX;
				g = (int) (dx / dy * 0xff);
				temp_ = v1.screenX << 8;

				for (int y = startY; y <= endY; y++) {
					if (downwards)
						xLow[y] = temp_ >> 8;
					else
						xHigh[y] = (temp_ >> 8) + 1;
					temp_ += g;
				}

				continue;
			}

			int startY = Math.max(v1.screenY, 0);
			int endY = Math.min(v2.screenY, 479);

			if (startY < start)
				start = startY;

			if (endY > end)
				end = endY;

			double dx = v2.screenX - v1.screenX;
			double gradient = dx / dy;

			int temp_x, x;
			temp_ = (int) (v1.screenX + (startY - v1.screenY) * gradient) << 8;
			g = (int) (gradient * 0xff);

			for (int y = startY; y <= endY; y++) {
				temp_x = temp_ >> 8;
				temp_ += g;
				x = Math.min(639 + 1, Math.max(temp_x, 0));
				if (downwards) {
					xLow[y] = x;
				} else {
					if (x < 639)
						x++;
					xHigh[y] = x;
				}
			}
		}
		poly.start = start;
		poly.end = end;
	}

	public static void renderTerrain() {
		int[] screen = main.screen;
		short[] Texture = poly.myTexture.Texture;
		int[][] colorTable = GameData.colorTable;
		short[] lightMap = main.lightMap;
		boolean[] terrainBuffer = main.terrainBuffer;
		boolean flag = main.terrainBufferFlag;

		A_offset = A.x * 2048;
		B_offset = B.x * 2048;
		C_offset = C.x * 2048;

		int start = poly.start;
		int end = poly.end;

		for (int i = start; i <= end; i++) {
			int offset = xHigh[i] - xLow[i];
			W.set(xLow[i] - 320, -i + 240, vector.Z_length);
			aDotW = A.dot(W);
			bDotW = B.dot(W);
			cDotW = C.dot(W);

			cDotWInverse = 1 / cDotW;
			BigX = (int) (aDotW * cDotWInverse * 0xffff);
			BigY = (int) (bDotW * cDotWInverse * 0xffff);
			aDotW += A_offset;
			bDotW += B_offset;
			cDotW += C_offset;
			cDotWInverse = 1 / cDotW;
			X1 = (int) (aDotW * cDotWInverse * 0xffff);
			Y1 = (int) (bDotW * cDotWInverse * 0xffff);
			BigDx = X1 - BigX;
			BigDy = Y1 - BigY;
			dx = BigDx >> 16;
			dy = BigDy >> 16;

			int temp = GameData.screenTable[i];
			int index = xLow[i] + temp;

			X = BigX >> 16;
			Y = BigY >> 16;

			for (k = offset, d_x = 0, d_y = 0; k > 0; k--, d_x += dx, d_y += dy, index++) {
				if (flag != terrainBuffer[index]) {
					textureIndex = (((d_x >> 11) + X) & widthMask)
							+ ((((d_y >> 11) + Y) & heightMask) << widthBits);
					screen[index] = colorTable[lightMap[index]][Texture[textureIndex]];
					lightMap[index] = 35;
					terrainBuffer[index] = flag;
				}
			}
		}
	}

	public static void renderLightMap() {
		short[] lightMap = main.lightMap;
		short[] Texture = poly.myTexture.Texture;
		variation = poly.variation;

		A_offset = A.x * 1024;
		B_offset = B.x * 1024;
		C_offset = C.x * 1024;

		int start = poly.start;
		int end = poly.end;

		for (int i = start; i <= end; i++) {
			int offset = xHigh[i] - xLow[i];
			W.set(xLow[i] - 320, -i + 240, vector.Z_length);
			aDotW = A.dot(W);
			bDotW = B.dot(W);
			cDotW = C.dot(W);

			cDotWInverse = 1 / cDotW;
			BigX = (int) (aDotW * cDotWInverse * 0xffff);
			BigY = (int) (bDotW * cDotWInverse * 0xffff);
			aDotW += A_offset;
			bDotW += B_offset;
			cDotW += C_offset;
			cDotWInverse = 1 / cDotW;
			X1 = (int) (aDotW * cDotWInverse * 0xffff);
			Y1 = (int) (bDotW * cDotWInverse * 0xffff);
			BigDx = X1 - BigX;
			BigDy = Y1 - BigY;
			dx = BigDx >> 16;
			dy = BigDy >> 16;

			int temp = GameData.screenTable[i];
			int index = xLow[i] + temp;

			X = BigX >> 16;
			Y = BigY >> 16;

			if (index < 0)
				index = 0;

			for (k = offset, d_x = 0, d_y = 0; k > 0; k--, d_x += dx, d_y += dy, index++) {
				textureIndex = (((d_x >> 10) + X) & widthMask)
						+ ((((d_y >> 10) + Y) & heightMask) << widthBits);
				I = lightMap[index];
				temp = Texture[textureIndex];
				if ((temp == 0) || (temp <= 1 && temp >= -1))
					continue;
				temp += variation;
				if (variation > 0) {
					if (temp > 0)
						temp = 0;
				}

				I += temp;

				if (I <= 63) {
					if (I < 13) {
						lightMap[index] = 13;
						continue;
					}
					lightMap[index] = I;
				} else
					lightMap[index] = 63;
			}
		}
	}

	public static void renderLightBlue() {

	}

	public static void renderWater() {
		int[] screen = main.screen;
		int[] stencilBuffer = main.stencilBuffer;
		byte[] Texture = poly.myTexture.waterWave[poly.myTexture.waveIndex];
		int temp;
		double scale;

		A_offset = A.x * 2048;
		B_offset = B.x * 2048;
		C_offset = C.x * 2048;

		int start = poly.start;
		int end = poly.end;

		for (int i = start; i <= end; i++) {
			int offset = xHigh[i] - xLow[i];
			W.set(xLow[i] - 320, -i + 240, vector.Z_length);
			scale = Math.min(1400.0 / (i * 1.1 + 1), 15);

			aDotW = A.dot(W);
			bDotW = B.dot(W);
			cDotW = C.dot(W);

			cDotWInverse = 1 / cDotW;
			BigX = (int) (aDotW * cDotWInverse * 0xffff);
			BigY = (int) (bDotW * cDotWInverse * 0xffff);
			aDotW += A_offset;
			bDotW += B_offset;
			cDotW += C_offset;
			cDotWInverse = 1 / cDotW;
			X1 = (int) (aDotW * cDotWInverse * 0xffff);
			Y1 = (int) (bDotW * cDotWInverse * 0xffff);
			BigDx = X1 - BigX;
			BigDy = Y1 - BigY;
			dx = BigDx >> 16;
			dy = BigDy >> 16;

			temp = GameData.screenTable[i];
			int index = xLow[i] + temp;

			X = BigX >> 16;
			Y = BigY >> 16;

			for (k = offset, d_x = 0, d_y = 0; k > 0; k--, d_x += dx, d_y += dy, index++) {
				textureIndex = (((d_x >> 11) + X) & widthMask)
						+ ((((d_y >> 11) + Y) & heightMask) << widthBits);
				temp = Texture[textureIndex];
				temp = index + (int) (temp / scale) * 640;

				if (temp < 0 || temp >= 307200)
					temp = index;
				stencilBuffer[index] = screen[temp] + 6;
			}
		}

		for (int i = start; i <= end; i++) {
			int offset = xHigh[i] - xLow[i];
			temp = GameData.screenTable[i];
			int index = xLow[i] + temp;
			for (k = offset; k > 0; k--, index++)
				screen[index] = stencilBuffer[index];
		}
	}

	public static void renderTransparent1() {
		int[] screen = main.screen;
		int[] stencilBuffer = main.stencilBuffer;

		int start = poly.start;
		int end = poly.end;

		for (int i = start; i <= end; i++) {
			int temp = GameData.screenTable[i];
			int index;
			for (int j = xLow[i]; j < xHigh[i]; j++) {
				index = j + temp;

				X = (j + xOffset) % 128;
				Y = i % 128;

				temp1 = GameData.distortion1[X + Y * 128];
				temp1 = index + (int) (temp1 / scale) * 640;
				if (temp1 < 0 || temp1 >= 307200)
					temp1 = index;

				stencilBuffer[index] = screen[temp1];
			}
		}

		for (int i = start; i <= end; i++) {
			int offset = xHigh[i] - xLow[i];
			temp = GameData.screenTable[i];
			int index = xLow[i] + temp;
			for (k = offset; k > 0; k--, index++)
				screen[index] = stencilBuffer[index];
		}

	}

	public static void renderTransparent2() {
		int[] screen = main.screen;
		int[] stencilBuffer = main.stencilBuffer;

		int start = poly.start;
		int end = poly.end;
		double distanceScale = poly.centre.z / 2;
		if (distanceScale < 1)
			distanceScale = 1;

		for (int i = start; i <= end; i++) {
			int temp = GameData.screenTable[i];
			int index;

			for (int j = xLow[i]; j < xHigh[i]; j++) {
				index = j + temp;

				X = (j * 2 / 3 + xOffset + 128);
				Y = i * 2 / 3;

				X *= distanceScale;
				Y *= distanceScale;
				X %= 128;
				Y %= 128;

				temp1 = GameData.distortion2[X + Y * 128];
				temp1 = index + (int) (temp1 / (distanceScale * 1.2)) * 640
						+ (int) (temp1 / (distanceScale * 1.2));
				if (temp1 < 0 || temp1 >= 307200)
					temp1 = index;

				stencilBuffer[index] = screen[temp1];
			}
		}

		for (int i = start; i <= end; i++) {
			int offset = xHigh[i] - xLow[i];
			temp = GameData.screenTable[i];
			int index = xLow[i] + temp;
			for (k = offset; k > 0; k--, index++) {
				temp1 = stencilBuffer[index];
				temp2 = -16741234;
				r = (210 * (((temp1 >> 16) & 255) - ((temp2 >> 16) & 255)) >> 8)
						+ ((temp2 >> 16) & 255);
				g = (210 * (((temp1 >> 8) & 255) - ((temp2 >> 8) & 255)) >> 8)
						+ ((temp2 >> 8) & 255);
				b = (210 * ((temp1 & 255) - (temp2 & 255)) >> 8)
						+ (temp2 & 255);
				screen[index] = (r << 16) | (g << 8) | b;

			}
		}

	}

	public static void renderModel() {
		int[] screen = main.screen;
		short[] Texture = poly.myTexture.Texture;
		diffuse_I = poly.diffuse_I;
		int[] colorTable = GameData.colorTable[diffuse_I];

		A_offset = A.x * 16;
		B_offset = B.x * 16;
		C_offset = C.x * 16;

		double Aoffset, Boffset, Coffset;

		int start = poly.start;
		int end = poly.end;

		for (int i = start; i <= end; i++) {
			W.set(xLow[i] - 320, -i + 240, vector.Z_length);
			aDotW = A.dot(W);
			bDotW = B.dot(W);
			cDotW = C.dot(W);

			cDotWInverse = 1 / cDotW;
			X = (int) (aDotW * cDotWInverse);
			Y = (int) (bDotW * cDotWInverse);
			X1 = X;
			Y1 = Y;

			int temp = GameData.screenTable[i];
			int index;
			for (int j = xLow[i]; j < xHigh[i]; j += 16) {
				X = X1;
				Y = Y1;

				index = j + temp;
				if (xHigh[i] - j > 15) {
					aDotW += A_offset;
					bDotW += B_offset;
					cDotW += C_offset;
					cDotWInverse = 1 / cDotW;
					X1 = (int) (aDotW * cDotWInverse);
					Y1 = (int) (bDotW * cDotWInverse);
					dx = X1 - X;
					dy = Y1 - Y;

					if (alpha <= 0) {
						for (k = 16, d_x = 0, d_y = 0; k > 0; k--, d_x += dx, d_y += dy, index++) {
							textureIndex = (((d_x >> 4) + X) & widthMask)
									+ ((((d_y >> 4) + Y) & heightMask) << widthBits);
							screen[index] = colorTable[Texture[textureIndex]];
						}
					} else {
						for (k = 16, d_x = 0, d_y = 0; k > 0; k--, d_x += dx, d_y += dy, index++) {
							textureIndex = (((d_x >> 4) + X) & widthMask)
									+ ((((d_y >> 4) + Y) & heightMask) << widthBits);
							temp1 = screen[index];
							temp2 = colorTable[Texture[textureIndex]];
							r = (alpha
									* (((temp1 >> 16) & 255) - ((temp2 >> 16) & 255)) >> 8)
									+ ((temp2 >> 16) & 255);
							g = (alpha
									* (((temp1 >> 8) & 255) - ((temp2 >> 8) & 255)) >> 8)
									+ ((temp2 >> 8) & 255);
							b = (alpha * ((temp1 & 255) - (temp2 & 255)) >> 8)
									+ (temp2 & 255);

							screen[index] = (r << 16) | (g << 8) | b;
						}
					}
					continue;
				}

				int offset = xHigh[i] - j;
				Aoffset = A.x * offset;
				Boffset = B.x * offset;
				Coffset = C.x * offset;

				aDotW += Aoffset;
				bDotW += Boffset;
				cDotW += Coffset;
				cDotWInverse = 1 / cDotW;
				X1 = (int) (aDotW * cDotWInverse);
				Y1 = (int) (bDotW * cDotWInverse);
				dx = X1 - X;
				dy = Y1 - Y;

				if (alpha <= 0) {
					for (k = offset, d_x = 0, d_y = 0; k > 0; k--, d_x += dx, d_y += dy, index++) {
						textureIndex = (((d_x / offset) + X) & widthMask)
								+ ((((d_y / offset) + Y) & heightMask) << widthBits);

						screen[index] = colorTable[Texture[textureIndex]];
					}
				} else {
					for (k = offset, d_x = 0, d_y = 0; k > 0; k--, d_x += dx, d_y += dy, index++) {
						textureIndex = (((d_x / offset) + X) & widthMask)
								+ ((((d_y / offset) + Y) & heightMask) << widthBits);
						temp1 = screen[index];
						temp2 = colorTable[Texture[textureIndex]];
						r = (alpha
								* (((temp1 >> 16) & 255) - ((temp2 >> 16) & 255)) >> 8)
								+ ((temp2 >> 16) & 255);
						g = (alpha
								* (((temp1 >> 8) & 255) - ((temp2 >> 8) & 255)) >> 8)
								+ ((temp2 >> 8) & 255);
						b = (alpha * ((temp1 & 255) - (temp2 & 255)) >> 8)
								+ (temp2 & 255);

						screen[index] = (r << 16) | (g << 8) | b;
					}
				}
				break;
			}
		}
	}

	public static void renderLightMap2() {
		int[] screen = main.screen;
		short[] Texture = poly.myTexture.Texture;

		A_offset = A.x * 16;
		B_offset = B.x * 16;
		C_offset = C.x * 16;

		double Aoffset, Boffset, Coffset;

		int start = poly.start;
		int end = poly.end;

		for (int i = start; i <= end; i++) {
			W.set(xLow[i] - 320, -i + 240, vector.Z_length);
			aDotW = A.dot(W);
			bDotW = B.dot(W);
			cDotW = C.dot(W);

			cDotWInverse = 1 / cDotW;
			X = (int) (aDotW * cDotWInverse);
			Y = (int) (bDotW * cDotWInverse);
			X1 = X;
			Y1 = Y;

			int temp = GameData.screenTable[i];
			int index;

			for (int j = xLow[i]; j < xHigh[i]; j += 16) {
				X = X1;
				Y = Y1;

				index = j + temp;
				if (xHigh[i] - j > 15) {
					aDotW += A_offset;
					bDotW += B_offset;
					cDotW += C_offset;
					cDotWInverse = 1 / cDotW;
					X1 = (int) (aDotW * cDotWInverse);
					Y1 = (int) (bDotW * cDotWInverse);
					dx = X1 - X;
					dy = Y1 - Y;

					for (k = 16, d_x = 0, d_y = 0; k > 0; k--, d_x += dx, d_y += dy, index++) {
						textureIndex = (((d_x / 16) + X) & widthMask)
								+ ((((d_y / 16) + Y) & heightMask) << widthBits);
						temp1 = screen[index];

						temp2 = Texture[textureIndex];
						if ((temp2 == 0) || (temp2 <= 1 && temp2 >= -1))
							continue;

						r = (((temp1 >> 16) & 255) * 164) >> 8;
						g = (((temp1 >> 8) & 255) * 164) >> 8;
						b = ((temp1 & 255) * 164) >> 8;

						screen[index] = (r << 16) | (g << 8) | b;
					}

					continue;
				}

				int offset = xHigh[i] - j;
				Aoffset = A.x * offset;
				Boffset = B.x * offset;
				Coffset = C.x * offset;

				aDotW += Aoffset;
				bDotW += Boffset;
				cDotW += Coffset;
				cDotWInverse = 1 / cDotW;
				X1 = (int) (aDotW * cDotWInverse);
				Y1 = (int) (bDotW * cDotWInverse);
				dx = X1 - X;
				dy = Y1 - Y;

				for (k = offset, d_x = 0, d_y = 0; k > 0; k--, d_x += dx, d_y += dy, index++) {
					textureIndex = (((d_x / offset) + X) & widthMask)
							+ ((((d_y / offset) + Y) & heightMask) << widthBits);
					temp1 = screen[index];

					temp2 = Texture[textureIndex];
					if ((temp2 == 0) || (temp2 <= 1 && temp2 >= -1))
						continue;

					r = (((temp1 >> 16) & 255) * 164) >> 8;
					g = (((temp1 >> 8) & 255) * 164) >> 8;
					b = ((temp1 & 255) * 164) >> 8;

					screen[index] = (r << 16) | (g << 8) | b;
				}

				break;
			}

		}

	}

	public static void renderSoildPolygon() {
		int soildColor = GameData.colorTable[poly.diffuse_I][poly.color];
		int[] screen = main.screen;

		int start = poly.start;
		int end = poly.end;

		for (int i = start; i <= end; i++) {
			int temp = GameData.screenTable[i];
			int index;
			for (int j = xLow[i]; j < xHigh[i]; j++) {
				index = j + temp;
				temp1 = screen[index];
				temp2 = soildColor;
				r = (alpha * (((temp1 >> 16) & 255) - ((temp2 >> 16) & 255)) >> 8)
						+ ((temp2 >> 16) & 255);
				g = (alpha * (((temp1 >> 8) & 255) - ((temp2 >> 8) & 255)) >> 8)
						+ ((temp2 >> 8) & 255);
				b = (alpha * ((temp1 & 255) - (temp2 & 255)) >> 8)
						+ (temp2 & 255);

				screen[index] = (r << 16) | (g << 8) | b;
			}
		}
	}

	public static void renderExplosionSprite(int[] sprite, double ratio,
			int xPos, int yPos, int width, int height) {
		int[] screen = main.screen;

		int originalWidth = width;

		double ratioInverse = 1 / ratio;

		width = (int) (ratio * width);
		height = (int) (ratio * height);

		int xTop = xPos - width / 2;
		int yTop = yPos - height / 2;
		int xBot = xPos + width / 2;
		int yBot = yPos + height / 2;

		boolean withinScreen = true;
		if (xTop < 0 || yTop < 0 || xBot > 639 || yBot > 479)
			withinScreen = false;

		int screenIndex = 0;
		int SpriteValue = 0;
		int screenValue = 0;
		int MASK7Bit = 0xFEFEFF;
		int overflow = 0;
		int pixel = 0;

		if (withinScreen) {
			for (int i = yTop, y = 0; i < yBot; i++, y++) {

				int ratioInverseY = (int) (ratioInverse * y);
				for (int j = xTop, x = 0; j < xBot; j++, x++) {
					screenIndex = j + i * 640;
					SpriteValue = sprite[(int) (ratioInverse * x)
							+ ratioInverseY * originalWidth];

					if (SpriteValue != 0)
						screenValue = (screen[screenIndex] & 0xFEFEFE) >> 1;
					else
						screenValue = screen[screenIndex];

					if (temp == 1) {
						SpriteValue = (SpriteValue & 0x0000ff00)
								| ((SpriteValue & 0x00ff0000) >> 17);

					}

					pixel = (SpriteValue & MASK7Bit) + (screenValue & MASK7Bit);
					overflow = pixel & 0x1010100;
					overflow = overflow - (overflow >> 8);
					screen[screenIndex] = overflow | pixel;

				}
			}
		} else {

			for (int i = yTop, y = 0; i < yBot; i++, y++) {

				if (i < 0 || i >= 480)
					continue;
				int ratioInverseY = (int) (ratioInverse * y);
				for (int j = xTop, x = 0; j < xBot; j++, x++) {
					screenIndex = j + i * 640;
					if (j < 0 || j >= 640)
						continue;
					SpriteValue = sprite[(int) (ratioInverse * x)
							+ ratioInverseY * originalWidth];
					if (SpriteValue != 0)
						screenValue = (screen[screenIndex] & 0xFEFEFE) >> 1;
					else
						screenValue = screen[screenIndex];

					if (temp == 1) {
						SpriteValue = (SpriteValue & 0x0000ff00)
								| ((SpriteValue & 0x00ff0000) >> 17);

					}

					pixel = (SpriteValue & MASK7Bit) + (screenValue & MASK7Bit);
					overflow = pixel & 0x1010100;
					overflow = overflow - (overflow >> 8);
					screen[screenIndex] = overflow | pixel;

				}
			}
		}
	}

	public static void renderSmokSprite(int[] sprite, double ratio, int xPos,
			int yPos, int width, int height, int alpha) {
		int[] screen = main.screen;
		int originalWidth = width;

		double ratioInverse = 1 / ratio;

		width = (int) (ratio * width);
		height = (int) (ratio * height);

		int xTop = xPos - width / 2;
		int yTop = yPos - height / 2;
		int xBot = xPos + width / 2;
		int yBot = yPos + height / 2;

		boolean withinScreen = true;
		if (xTop < 0 || yTop < 0 || xBot > 639 || yBot > 479)
			withinScreen = false;

		int screenIndex = 0;
		int SpriteValue = 0;

		if (withinScreen) {
			for (int i = yTop, y = 0; i < yBot; i++, y++) {
				int ratioInverseY = (int) (ratioInverse * y);
				for (int j = xTop, x = 0; j < xBot; j++, x++) {
					screenIndex = j + i * 640;
					SpriteValue = sprite[(int) (ratioInverse * x)
							+ ratioInverseY * originalWidth];

					if (SpriteValue == 0)
						continue;

					temp1 = screen[screenIndex];
					temp2 = SpriteValue;
					r = (alpha
							* (((temp1 >> 16) & 255) - ((temp2 >> 16) & 255)) >> 8)
							+ ((temp2 >> 16) & 255);
					g = (alpha * (((temp1 >> 8) & 255) - ((temp2 >> 8) & 255)) >> 8)
							+ ((temp2 >> 8) & 255);
					b = (alpha * ((temp1 & 255) - (temp2 & 255)) >> 8)
							+ (temp2 & 255);
					screen[screenIndex] = (r << 16) | (g << 8) | b;

				}
			}
		} else {
			for (int i = yTop, y = 0; i < yBot; i++, y++) {
				if (i < 0 || i >= 480)
					continue;
				int ratioInverseY = (int) (ratioInverse * y);
				for (int j = xTop, x = 0; j < xBot; j++, x++) {
					screenIndex = j + i * 640;
					if (j < 0 || j >= 640)
						continue;
					SpriteValue = sprite[(int) (ratioInverse * x)
							+ ratioInverseY * originalWidth];

					if (SpriteValue == 0)
						continue;

					temp1 = screen[screenIndex];
					temp2 = SpriteValue;
					r = (alpha
							* (((temp1 >> 16) & 255) - ((temp2 >> 16) & 255)) >> 8)
							+ ((temp2 >> 16) & 255);
					g = (alpha * (((temp1 >> 8) & 255) - ((temp2 >> 8) & 255)) >> 8)
							+ ((temp2 >> 8) & 255);
					b = (alpha * ((temp1 & 255) - (temp2 & 255)) >> 8)
							+ (temp2 & 255);
					screen[screenIndex] = (r << 16) | (g << 8) | b;

				}
			}
		}
	}

	public static void renderText(boolean[] pixels, int xPos, int yPos,
			int size, int color) {
		int[] screen = main.screen;
		for (int i = 0; i < 1024; i++) {
			int x = i % 32;
			int y = i / 32;
			int start = xPos + yPos * 640;
			if (pixels[i]) {
				if (size == 2) {
					screen[start + x * 2 + y * 2 * 640] = color;
					screen[start + x * 2 + 1 + y * 2 * 640] = color;
					screen[start + x * 2 + (y * 2 + 1) * 640] = color;
					screen[start + x * 2 + 1 + (y * 2 + 1) * 640] = color;
				}

				if (size == 1) {
					screen[start + x + y * 640] = color;
					screen[start + x + 1 + y * 640] = color;

				}
			}

		}
	}
}
