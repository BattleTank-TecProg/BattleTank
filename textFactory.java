
public class textFactory {

	public static boolean[][] letters;
	public static boolean[] spaceCharacter;

	public static void init() {
		letters = new boolean[64][1024];
		spaceCharacter = new boolean[1024];

		short[] texture = main.textures[62].Texture;

		int startIndex = 0;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 1024; j++) {
				int x = j % 32;
				int y = j / 32;
				short color = texture[startIndex + y * 256 + i * 32 + x];
				if (color < 10000)
					letters[i][j] = false;
				else
					letters[i][j] = true;
			}
			if (i == 7) {
				startIndex = 8192;
			}
		}

		startIndex = 16384;
		for (int i = 0; i < 26; i++) {
			for (int j = 0; j < 1024; j++) {
				int x = j % 32;
				int y = j / 32;

				short color = texture[startIndex + y * 256 + ((i + 1) % 8) * 32
						+ x];
				if (color < 10000)
					letters[i + 10][j] = false;
				else
					letters[i + 10][j] = true;
			}

			if (i == 6) {
				startIndex = 24576;
			}
			if (i == 14) {
				startIndex = 32768;
			}
			if (i == 22) {
				startIndex = 40960;
			}
		}

		startIndex = 49152;
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 1024; j++) {
				int x = j % 32;
				int y = j / 32;

				short color = texture[startIndex + y * 256 + ((i + 1) % 8) * 32
						+ x];
				if (color < 10000)
					letters[i + 36][j] = false;
				else
					letters[i + 36][j] = true;
			}
			if (i == 6)
				startIndex = 57344;
		}

		startIndex = 40960;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 1024; j++) {
				int x = j % 32;
				int y = j / 32;

				short color = texture[startIndex + y * 256 + ((i + 3) % 8) * 32
						+ x];
				if (color < 10000)
					letters[i + 51][j] = false;
				else
					letters[i + 51][j] = true;
			}
		}

		startIndex = 8192;
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 1024; j++) {
				int x = j % 32;
				int y = j / 32;

				short color = texture[startIndex + y * 256 + ((i + 2) % 8) * 32
						+ x];
				if (color < 10000)
					letters[i + 56][j] = false;
				else
					letters[i + 56][j] = true;
			}
		}

		for (int j = 0; j < 1024; j++) {
			int x = j % 32;
			int y = j / 32;

			short color = texture[16384 + y * 256 + x];
			if (color < 10000)
				letters[62][j] = false;
			else
				letters[62][j] = true;
		}

		for (int j = 0; j < 1024; j++) {
			int x = j % 32;
			int y = j / 32;

			short color = texture[49152 + y * 256 + x];
			if (color < 10000)
				letters[63][j] = false;
			else
				letters[63][j] = true;
		}

	}

	public static void draw(int[] text, int xPos, int yPos, int size, int color) {
		for (int i = 0; i < text.length; i++) {
			if (text[i] == -1)
				rasterizer.renderText(spaceCharacter, xPos, yPos, size, color);
			else
				rasterizer
						.renderText(letters[text[i]], xPos, yPos, size, color);
			if (size == 1)
				xPos += 15;
			if (size == 2)
				xPos += 30;
		}
	}

	public static void drawString(String text, int xPos, int yPos, int size,
			int color) {
		int length = text.length();
		int[] text_ = new int[length];

		for (int i = 0; i < length; i++) {
			text_[i] = translate(text.substring(i, i + 1));
		}

		for (int i = 0; i < length; i++) {
			if (text_[i] == -1)
				rasterizer.renderText(spaceCharacter, xPos, yPos, size, color);
			else
				rasterizer.renderText(letters[text_[i]], xPos, yPos, size,
						color);

			if (size == 1)
				xPos += 15;
			if (size == 2)
				xPos += 30;
		}
	}

	public static int translate(String s) {
		if (s.equals("0"))
			return 0;
		if (s.equals("1"))
			return 1;
		if (s.equals("2"))
			return 2;
		if (s.equals("3"))
			return 3;
		if (s.equals("4"))
			return 4;
		if (s.equals("5"))
			return 5;
		if (s.equals("6"))
			return 6;
		if (s.equals("7"))
			return 7;
		if (s.equals("8"))
			return 8;
		if (s.equals("9"))
			return 9;

		return -1;
	}

}
