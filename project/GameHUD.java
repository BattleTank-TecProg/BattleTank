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
 *  along with BattleTank 2. If not, see <http://www.gnu.org/licenses/>
 */
import java.util.logging.Logger;
import java.util.Locale;

public class GameHUD {

	// Background window frame
	private static int[] leftSide;

	private static int[] rightSide;

	// String represented in number format
	private static int[] Health, Saude, Ammo, Municao, BattleTank2, NewGame, NovoJogo, Controls, Controles, AboutAuthor,
			SobreAutor, controlDescription1, controlDescription2, controlDescription3, controlDescription4,
			controlDescription5, controlDescription6, controlDescription7, controlDescription8, controlDescription9,
			author1, author2, author3, author4, author5, author6, author7, author8, author9, author10, game, over, You,
			Won, cheat1, cheat2, cheat3;

	private static int menuOptionStatus = 0;

	private static int gameoverMessagePosition = 0;

	private static int winMessagePosition = 0;

	private static int loadingScreenPosition = 0;

	private static int mouseXpos = 0;

	private static int mouseYpos = 0;

	private static boolean escapePressed = false;

	private static boolean downPressed = false;

	private static boolean upPressed = false;

	private static boolean enterPressed = false;

	private static boolean mousePressed = false;

	private static int[] randomDisplacement = new int[640];

	// X coordinate of the PlayerTank.
	private static final double XCOORDINADE = 10;

	// Y coordinate of the Player Tank.
	private static final double YCOORDINADE = -0.975;

	// Z coordinate of the Player Tank.
	private static final double ZCOORDINADE = 2.5;
	
	private final static int SCREENWIDHT = 640;
	
	private final static int SCREENHEIGHT = 480;

	public static void init() {
		menuOptionStatus = 1;
		gameoverMessagePosition = 0;
		winMessagePosition = 0;
		loadingScreenPosition = 1234567;

		for (int i = 0; i < SCREENWIDHT; i += 2) {
			int random = GameData.getRandom() / 10;
			randomDisplacement[i] = random;
			randomDisplacement[i + 1] = random;

		}

		leftSide = new int[] { 54, 53, 52, 51, 51, 52, 53, 54 };
		rightSide = new int[] { 196, 197, 198, 199, 199, 198, 197, 196 };

		Health = new int[] { 17, 40, 36, 47, 55, 43 };
		Saude = new int[] { 28, 36, 56, 39, 40 };

		Ammo = new int[] { 10, 48, 48, 50 };
		Municao = new int[] { 22, 56, 49, 44, 38, 36, 50 };

		BattleTank2 = new int[] { 11, 36, 55, 55, 47, 40, 29, 36, 49, 46, -1, 2 };

		NewGame = new int[] { 23, 40, 58, -1, 16, 36, 48, 40 };
		NovoJogo = new int[] { 23, 50, 57, 50, -1, 19, 50, 42, 50 };

		Controls = new int[] { 12, 50, 49, 55, 53, 50, 47, 54 };
		Controles = new int[] { 12, 50, 49, 55, 53, 50, 47, 40, 54 };

		AboutAuthor = new int[] { 10, 37, 50, 56, 55, -1, 36, 56, 55, 43, 50, 53 };
		SobreAutor = new int[] { 28, 50, 37, 53, 40, -1, 50, -1, 10, 56, 55, 50, 53 };

		/*
		 * Press w s a d to move around. Change camera view by moving the mouse
		 * cursor. Click mouse button to fire. press 1 2 3 4 to switch guns.
		 */

		controlDescription1 = new int[] { 25, 53, 40, 54, 54, -1, 58, -1, 54, -1, 36, -1, 39, -1, 55, 50, -1 };
		controlDescription2 = new int[] { 48, 50, 57, 40, -1, 36, 53, 50, 56, 49, 39, 63 };
		controlDescription3 = new int[] { 12, 43, 36, 49, 42, 40, -1, 38, 36, 48, 40, 53, 36, -1, 57, 44, 40, 58 };
		controlDescription4 = new int[] { 37, 60, -1, 48, 50, 57, 44, 49, 42, -1, 55, 43, 40, -1, 48, 50, 56, 54, 40 };
		controlDescription5 = new int[] { 38, 56, 53, 54, 50, 53, 63 };
		controlDescription6 = new int[] { 12, 47, 44, 38, 46, -1, 48, 50, 56, 54, 40, -1, 37, 56, 55, 55, 50, 49 };
		controlDescription7 = new int[] { 55, 50, -1, 41, 44, 53, 40, 63 };
		controlDescription8 = new int[] { 25, 53, 40, 54, 54, -1, 1, -1, 2, -1, 3, -1, 4, -1, 55, 50 };
		controlDescription9 = new int[] { 54, 58, 44, 55, 38, 43, -1, 42, 56, 49, 54, 63 };

		author1 = new int[] { 22, 60, -1, 49, 36, 48, 40, -1, 44, 54, -1, 17, 56, -1, 25, 36, 49, 63 };
		author2 = new int[] { 18, -1, 36, 48, -1, 44, 49, 55, 40, 53, 40, 54, 55, 40, 39, -1, 44, 49 };
		author3 = new int[] { 39, 40, 57, 40, 47, 50, 51, 44, 49, 42, -1, 42, 36, 48, 40, 54 };
		author4 = new int[] { 36, 49, 39, -1, 38, 50, 50, 47, -1, 36, 49, 44, 48, 36, 55, 44, 50, 49 };
		author5 = new int[] { 44, 49, -1, 45, 36, 57, 36, -1, 63, 18, 41, -1, 60, 50, 56 };
		author6 = new int[] { 41, 44, 49, 39, -1, 36, 49, 60, -1, 37, 56, 42, 54 };
		author7 = new int[] { 50, 53, -1, 44, 41, -1, 60, 50, 56, -1, 43, 36, 57, 40, -1, 36, 49, 60 };
		author8 = new int[] { 54, 56, 42, 42, 40, 54, 55, 44, 50, 49, 54, -1, 51, 47, 40, 36, 54, 40 };
		author9 = new int[] { 47, 40, 55, -1, 48, 40, -1, 46, 49, 50, 58, 63 };
		author10 = new int[] { 43, 56, 51, 36, 49, 8, 4, 62, 43, 50, 55, 48, 36, 44, 47, 63, 38, 50, 48 };

		// Game Over
		game = new int[] { 16, 36, 48, 40 };
		over = new int[] { 24, 57, 40, 53 };

		// Wining message
		You = new int[] { 34, 50, 56 };
		Won = new int[] { 32, 50, 49 };

		/*
		 * Cheat message Thanks for playing, next time hold I, L, M keys at the
		 * same time for unlimited ammo and health.
		 */
		cheat1 = new int[] { 29, 43, 36, 49, 46, 54, -1, 41, 50, 53, -1, 51, 47, 36, 60, 44, 49, 42, 63, 23, 40, 59, 55,
				-1, 55, 44, 48, 40, -1, 43, 50, 47, 39 };
		cheat2 = new int[] { 18, -1, 21, -1, 22, -1, 46, 40, 60, 54, -1, 36, 55, -1, 55, 43, 40, -1, 54, 36, 48, 40, -1,
				55, 44, 48, 40, -1, 41, 50, 53 };
		cheat3 = new int[] { 56, 49, 47, 44, 48, 44, 55, 40, 39, -1, 36, 48, 48, 50, -1, 36, 49, 39, -1, 43, 40, 36, 47,
				55, 43 };
	}

	/*
	 * Letters are stored in this order: 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15
	 * 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 0 1 2 3 4 5 6
	 * 7 8 9 A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
	 * 
	 * 36 37 38 39 40 41 42 43 44 45 46 47 48 49 50 51 52 53 54 55 56 57 58 59
	 * 60 61 62 63 a b c d e f g h i j k l m n o p q r s t u v w x y z @ .
	 */

	public static int[] getLeftSide() {
		return leftSide;
	}

	public static void setLeftSide(int[] leftSide) {
		GameHUD.leftSide = leftSide;
	}

	public static int[] getRightSide() {
		return rightSide;
	}

	public static void setRightSide(int[] rightSide) {
		GameHUD.rightSide = rightSide;
	}

	public static int[] getHealth() {
		return Health;
	}

	public static void setHealth(int[] health) {
		Health = health;
	}

	public static int[] getSaude() {
		return Saude;
	}

	public static void setSaude(int[] saude) {
		Saude = saude;
	}

	public static int[] getAmmo() {
		return Ammo;
	}

	public static void setAmmo(int[] ammo) {
		Ammo = ammo;
	}

	public static int[] getMunicao() {
		return Municao;
	}

	public static void setMunicao(int[] municao) {
		Municao = municao;
	}

	public static int[] getBattleTank2() {
		return BattleTank2;
	}

	public static void setBattleTank2(int[] battleTank2) {
		BattleTank2 = battleTank2;
	}

	public static int[] getNewGame() {
		return NewGame;
	}

	public static void setNewGame(int[] newGame) {
		NewGame = newGame;
	}

	public static int[] getNovoJogo() {
		return NovoJogo;
	}

	public static void setNovoJogo(int[] novoJogo) {
		NovoJogo = novoJogo;
	}

	public static int[] getControls() {
		return Controls;
	}

	public static void setControls(int[] controls) {
		Controls = controls;
	}

	public static int[] getControles() {
		return Controles;
	}

	public static void setControles(int[] controles) {
		Controles = controles;
	}

	public static int[] getAboutAuthor() {
		return AboutAuthor;
	}

	public static void setAboutAuthor(int[] aboutAuthor) {
		AboutAuthor = aboutAuthor;
	}

	public static int[] getSobreAutor() {
		return SobreAutor;
	}

	public static void setSobreAutor(int[] sobreAutor) {
		SobreAutor = sobreAutor;
	}

	public static int[] getControlDescription1() {
		return controlDescription1;
	}

	public static void setControlDescription1(int[] controlDescription1) {
		GameHUD.controlDescription1 = controlDescription1;
	}

	public static int[] getControlDescription2() {
		return controlDescription2;
	}

	public static void setControlDescription2(int[] controlDescription2) {
		GameHUD.controlDescription2 = controlDescription2;
	}

	public static int[] getControlDescription3() {
		return controlDescription3;
	}

	public static void setControlDescription3(int[] controlDescription3) {
		GameHUD.controlDescription3 = controlDescription3;
	}

	public static int[] getControlDescription4() {
		return controlDescription4;
	}

	public static void setControlDescription4(int[] controlDescription4) {
		GameHUD.controlDescription4 = controlDescription4;
	}

	public static int[] getControlDescription5() {
		return controlDescription5;
	}

	public static void setControlDescription5(int[] controlDescription5) {
		GameHUD.controlDescription5 = controlDescription5;
	}

	public static int[] getControlDescription6() {
		return controlDescription6;
	}

	public static void setControlDescription6(int[] controlDescription6) {
		GameHUD.controlDescription6 = controlDescription6;
	}

	public static int[] getControlDescription7() {
		return controlDescription7;
	}

	public static void setControlDescription7(int[] controlDescription7) {
		GameHUD.controlDescription7 = controlDescription7;
	}

	public static int[] getControlDescription8() {
		return controlDescription8;
	}

	public static void setControlDescription8(int[] controlDescription8) {
		GameHUD.controlDescription8 = controlDescription8;
	}

	public static int[] getControlDescription9() {
		return controlDescription9;
	}

	public static void setControlDescription9(int[] controlDescription9) {
		GameHUD.controlDescription9 = controlDescription9;
	}

	public static int[] getAuthor1() {
		return author1;
	}

	public static void setAuthor1(int[] author1) {
		GameHUD.author1 = author1;
	}

	public static int[] getAuthor2() {
		return author2;
	}

	public static void setAuthor2(int[] author2) {
		GameHUD.author2 = author2;
	}

	public static int[] getAuthor3() {
		return author3;
	}

	public static void setAuthor3(int[] author3) {
		GameHUD.author3 = author3;
	}

	public static int[] getAuthor4() {
		return author4;
	}

	public static void setAuthor4(int[] author4) {
		GameHUD.author4 = author4;
	}

	public static int[] getAuthor5() {
		return author5;
	}

	public static void setAuthor5(int[] author5) {
		GameHUD.author5 = author5;
	}

	public static int[] getAuthor6() {
		return author6;
	}

	public static void setAuthor6(int[] author6) {
		GameHUD.author6 = author6;
	}

	public static int[] getAuthor7() {
		return author7;
	}

	public static void setAuthor7(int[] author7) {
		GameHUD.author7 = author7;
	}

	public static int[] getAuthor8() {
		return author8;
	}

	public static void setAuthor8(int[] author8) {
		GameHUD.author8 = author8;
	}

	public static int[] getAuthor9() {
		return author9;
	}

	public static void setAuthor9(int[] author9) {
		GameHUD.author9 = author9;
	}

	public static int[] getAuthor10() {
		return author10;
	}

	public static void setAuthor10(int[] author10) {
		GameHUD.author10 = author10;
	}

	public static int[] getGame() {
		return game;
	}

	public static void setGame(int[] game) {
		GameHUD.game = game;
	}

	public static int[] getOver() {
		return over;
	}

	public static void setOver(int[] over) {
		GameHUD.over = over;
	}

	public static int[] getYou() {
		return You;
	}

	public static void setYou(int[] you) {
		You = you;
	}

	public static int[] getWon() {
		return Won;
	}

	public static void setWon(int[] won) {
		Won = won;
	}

	public static int[] getCheat1() {
		return cheat1;
	}

	public static void setCheat1(int[] cheat1) {
		GameHUD.cheat1 = cheat1;
	}

	public static int[] getCheat2() {
		return cheat2;
	}

	public static void setCheat2(int[] cheat2) {
		GameHUD.cheat2 = cheat2;
	}

	public static int[] getCheat3() {
		return cheat3;
	}

	public static void setCheat3(int[] cheat3) {
		GameHUD.cheat3 = cheat3;
	}

	public static int getMenuOptionStatus() {
		return menuOptionStatus;
	}

	public static void setMenuOptionStatus(int menuOptionStatus) {
		GameHUD.menuOptionStatus = menuOptionStatus;
	}

	public static int getGameoverMessagePosition() {
		return gameoverMessagePosition;
	}

	public static void setGameoverMessagePosition(int gameoverMessagePosition) {
		GameHUD.gameoverMessagePosition = gameoverMessagePosition;
	}

	public static int getWinMessagePosition() {
		return winMessagePosition;
	}

	public static void setWinMessagePosition(int winMessagePosition) {
		GameHUD.winMessagePosition = winMessagePosition;
	}

	public static int getLoadingScreenPosition() {
		return loadingScreenPosition;
	}

	public static void setLoadingScreenPosition(int loadingScreenPosition) {
		GameHUD.loadingScreenPosition = loadingScreenPosition;
	}

	public static int getMouseXpos() {
		return mouseXpos;
	}

	public static void setMouseXpos(int mouseXpos) {
		GameHUD.mouseXpos = mouseXpos;
	}

	public static int getMouseYpos() {
		return mouseYpos;
	}

	public static void setMouseYpos(int mouseYpos) {
		GameHUD.mouseYpos = mouseYpos;
	}

	public static boolean isEscapePressed() {
		return escapePressed;
	}

	public static void setEscapePressed(boolean escapePressed) {
		GameHUD.escapePressed = escapePressed;
	}

	public static boolean isDownPressed() {
		return downPressed;
	}

	public static void setDownPressed(boolean downPressed) {
		GameHUD.downPressed = downPressed;
	}

	public static boolean isUpPressed() {
		return upPressed;
	}

	public static void setUpPressed(boolean upPressed) {
		GameHUD.upPressed = upPressed;
	}

	public static boolean isEnterPressed() {
		return enterPressed;
	}

	public static void setEnterPressed(boolean enterPressed) {
		GameHUD.enterPressed = enterPressed;
	}

	public static boolean isMousePressed() {
		return mousePressed;
	}

	public static void setMousePressed(boolean mousePressed) {
		GameHUD.mousePressed = mousePressed;
	}

	public static int[] getRandomDisplacement() {
		return randomDisplacement;
	}

	public static void setRandomDisplacement(int[] randomDisplacement) {
		GameHUD.randomDisplacement = randomDisplacement;
	}

	public static double getXcoordinade() {
		return XCOORDINADE;
	}

	public static double getYcoordinade() {
		return YCOORDINADE;
	}

	public static double getZcoordinade() {
		return ZCOORDINADE;
	}

	public static Logger getLog() {
		return LOG;
	}

	public static Locale getPtbr() {
		return PTBR;
	}

	public static void draw() {
		if (Main.gameOver) {
			// Draw game over message
			drawGameoverMessage();
		} else {
			// Does nothing.
		}

		if (Main.win) {
			// Draw wining message
			drawWinMessage();
		} else {
			// Does nothing.
		}

		if (Main.gamePaused == false && !Main.gameNotStart) {
			drawInfo();
		} else {
			// Does nothing.
		}

		if (Main.gamePaused == true || (Main.gameNotStart && menuOptionStatus != 0)) {
			drawMenu();
			Main.copyScreen();
		} else {
			// Does nothing.
		}

		if (loadingScreenPosition < 800) {

			int startPosition = loadingScreenPosition * SCREENWIDHT;
			int currentPosition = 0;
			for (int i = 0; i < SCREENWIDHT; i++) {
				for (int j = 0; j < SCREENHEIGHT; j++) {
					currentPosition = startPosition + (j + randomDisplacement[i]) * SCREENWIDHT + i;
					if (currentPosition >= SCREENWIDHT * SCREENHEIGHT) {
						currentPosition = SCREENWIDHT * 479 + i;
					} else {
						// Does nothing.
					}

					Main.screen[currentPosition] = Main.stencilBuffer2[j * SCREENWIDHT + i];
				}
			}

			loadingScreenPosition += 15;
		} else {
			// Does nothing.
		}

	}

	// Handle game menu interactions
	public static void update() {
		if (escapePressed) {
			if (menuOptionStatus == 0) {
				Main.gamePaused = true;
				menuOptionStatus = 1;
				LOG.info("Menu options opened");
			} else if (menuOptionStatus == 1 || menuOptionStatus == 2 || menuOptionStatus == 3) {
				Main.gamePaused = false;
				menuOptionStatus = 0;
				LOG.info("Menu options closed");
			} else if (menuOptionStatus == 4 || menuOptionStatus == 5) {
				menuOptionStatus = 1;
			} else {
				// Does nothing.
			}

		} else {
			// Does nothing.
		}

		if (downPressed) {
			if (menuOptionStatus == 1) {
				menuOptionStatus = 2;
			} else if (menuOptionStatus == 2) {
				menuOptionStatus = 3;
			} else if (menuOptionStatus == 3) {
				menuOptionStatus = 1;
			} else {
				// Does nothing.
			}
		} else {
			// Does nothing.
		}

		if (upPressed) {
			if (menuOptionStatus == 1) {
				menuOptionStatus = 3;
			} else if (menuOptionStatus == 2) {
				menuOptionStatus = 1;
			} else if (menuOptionStatus == 3) {
				menuOptionStatus = 2;
			} else {
				// Does nothing.
			}
		} else {
			// Does nothing.
		}

		if (enterPressed) {
			if (menuOptionStatus == 1) {

				menuOptionStatus = 0;
				gameoverMessagePosition = 0;
				winMessagePosition = 0;
				loadingScreenPosition = 0;
				ObstacleMap.clear();

				Camera.setViewDirection(new Vector(0, 0, 1));
				Camera.setXZ_angle(0);
				Camera.setRestart(true);

				PowerUps.init();
				Projectiles.init();
				Enemies.init();
				ObstacleMap.removeObstacle2(Main.PT.position);
				Main.PT = new PlayerTank(XCOORDINADE, YCOORDINADE, ZCOORDINADE);
				Main.gameNotStart = false;
				Main.gamePaused = false;
				Main.gameOver = false;
				Main.win = false;

			} else if (menuOptionStatus == 2) {
				menuOptionStatus = 4;
				LOG.info("You selected controls menu with enter button");
			} else if (menuOptionStatus == 3) {
				menuOptionStatus = 5;
				LOG.info("You selected about author menu with enter button");
			} else {
				// Does nothing.
			}
		} else {
			// Does nothing.
		}

		if (mousePressed) {

			if (menuOptionStatus == 4 || menuOptionStatus == 5) {

				menuOptionStatus = 1;

				mouseXpos = -1234567;
				mouseYpos = -1234567;
			} else {
				if (mouseXpos > 264 && mouseXpos < 385 && mouseYpos > 120 && mouseYpos < 144
						&& !(!Main.gamePaused && !Main.gameNotStart)) {
					menuOptionStatus = 0;
					gameoverMessagePosition = 0;
					winMessagePosition = 0;
					loadingScreenPosition = 0;
					ObstacleMap.clear();

					Camera.setViewDirection(new Vector(0, 0, 1));
					Camera.setXZ_angle(0);
					Camera.setRestart(true);

					PowerUps.init();
					Projectiles.init();
					Enemies.init();
					ObstacleMap.removeObstacle2(Main.PT.position);
					Main.PT = new PlayerTank(XCOORDINADE, YCOORDINADE, ZCOORDINADE);
					Main.gameNotStart = false;
					Main.gamePaused = false;
					Main.gameOver = false;
					Main.win = false;
				} else {
					// Does nothing.
				}

				if (mouseXpos > 262 && mouseXpos < 385 && mouseYpos > 161 && mouseYpos < 183) {
					menuOptionStatus = 2;
				} else {
					// Does nothing.
				}

				if (mouseXpos > 234 && mouseXpos < 412 && mouseYpos > 197 && mouseYpos < 222) {
					menuOptionStatus = 3;
				} else {
					// Does nothing.
				}

				if (menuOptionStatus == 2) {
					menuOptionStatus = 4;
					LOG.info("You selected controls menu with mouse");
				} else if (menuOptionStatus == 3) {
					menuOptionStatus = 5;
					LOG.info("You selected about author menu with mouse");
				} else {
					// Does nothing.
				}
			}
		} else {
			// Does nothing.
		}

		escapePressed = false;
		downPressed = false;
		upPressed = false;
		enterPressed = false;
		mousePressed = false;

	}

	public static void drawGameoverMessage() {

		if (gameoverMessagePosition < 500) {
			gameoverMessagePosition += 6;
		} else {
			// Does nothing.
		}

		if (gameoverMessagePosition > 310) {

			TextFactory.draw(game, gameoverMessagePosition - 310, 190, 2, 0x00eec64d);
			TextFactory.draw(over, 527 - gameoverMessagePosition + 310, 190, 2, 0x00eec64d);
		} else {
			// Does nothing.
		}
	}

	public static void drawWinMessage() {
		if (winMessagePosition < 500) {
			winMessagePosition += 6;
		} else {
			TextFactory.draw(cheat1, 80, 220, 1, 0x005d07f7);
			TextFactory.draw(cheat2, 80, 240, 1, 0x005d07f7);
			TextFactory.draw(cheat3, 80, 260, 1, 0x005d07f7);
		}

		if (winMessagePosition > 310) {
			TextFactory.draw(You, winMessagePosition - 310, 170, 2, 0x00ed07b7);
			TextFactory.draw(Won, 527 - winMessagePosition + 310, 170, 2, 0x00ed07b7);
		} else {
			// Does nothing.
		}

	}

	public static void drawMenu() {
		int[] screen = Main.screen;
		int yPosition = 50;
		int index = 0;
		int pixel;
		int red, green, blue;
		// Draw menu back ground
		for (int i = 0; i < 4; i++) {
			for (int j = leftSide[i] + 150; j < rightSide[i] + 290; j++) {
				index = yPosition * SCREENWIDHT + j - 20;
				pixel = screen[index];
				red = (pixel & 0x00ff0000) >> 17;
				green = (pixel & 0x0000ff00) >> 9;
				blue = (pixel & 0x000000ff) >> 1;
				pixel = blue + (green << 8) + (red << 16);
				screen[index] = pixel;
			}
			yPosition++;
		}

		int loweryPos = yPosition + 330;
		for (int i = yPosition; i < loweryPos; i++) {
			for (int j = 200; j < 490; j++) {
				index = yPosition * SCREENWIDHT + j - 20;
				pixel = screen[index];
				red = (pixel & 0x00ff0000) >> 17;
				green = (pixel & 0x0000ff00) >> 9;
				blue = (pixel & 0x000000ff) >> 1;
				pixel = blue + (green << 8) + (red << 16);
				screen[index] = pixel;
			}
			yPosition++;
		}

		for (int i = 4; i < 8; i++) {
			for (int j = leftSide[i] + 150; j < rightSide[i] + 290; j++) {
				index = yPosition * SCREENWIDHT + j - 20;
				pixel = screen[index];
				red = (pixel & 0x00ff0000) >> 17;
				green = (pixel & 0x0000ff00) >> 9;
				blue = (pixel & 0x000000ff) >> 1;
				pixel = blue + (green << 8) + (red << 16);
				screen[index] = pixel;
			}
			yPosition++;
		}

		int color = 0x00d0d0d0;
		TextFactory.draw(BattleTank2, 222, 57, 1, color);

		if (menuOptionStatus > 0 && menuOptionStatus <= 3) {

			if (menuOptionStatus == 1) {
				color = 0x0055ffaa;
			} else {
				color = 0x00d0d0d0;
			}
			TextFactory.draw(NovoJogo, 265, 120, 1, color);

			if (menuOptionStatus == 2) {
				color = 0x0055ffaa;
			} else {
				color = 0x00d0d0d0;
			}
			TextFactory.draw(Controles, 265, 160, 1, color);

			if (menuOptionStatus == 3) {
				color = 0x0055ffaa;
			} else {
				color = 0x00d0d0d0;
			}
			TextFactory.draw(SobreAutor, 235, 200, 1, color);
		} else {
			// Does nothing.
		}

		if (menuOptionStatus == 4) {
			color = 0x0055ffaa;
			TextFactory.draw(controlDescription1, 183, 100, 1, color);
			TextFactory.draw(controlDescription2, 183, 122, 1, color);
			TextFactory.draw(controlDescription3, 183, 160, 1, color);
			TextFactory.draw(controlDescription4, 183, 177, 1, color);
			TextFactory.draw(controlDescription5, 183, 199, 1, color);
			TextFactory.draw(controlDescription6, 183, 237, 1, color);
			TextFactory.draw(controlDescription7, 183, 259, 1, color);
			TextFactory.draw(controlDescription8, 183, 297, 1, color);
			TextFactory.draw(controlDescription9, 183, 319, 1, color);
		} else {
			// Does nothing.
		}

		if (menuOptionStatus == 5) {
			color = 0x0055ffaa;
			TextFactory.draw(author1, 183, 100, 1, color);
			TextFactory.draw(author2, 183, 122, 1, color);
			TextFactory.draw(author3, 183, 144, 1, color);
			TextFactory.draw(author4, 183, 166, 1, color);
			TextFactory.draw(author5, 183, 188, 1, color);
			TextFactory.draw(author6, 183, 210, 1, color);
			TextFactory.draw(author7, 183, 232, 1, color);
			TextFactory.draw(author8, 183, 254, 1, color);
			TextFactory.draw(author9, 183, 276, 1, color);
			TextFactory.draw(author10, 183, 320, 1, color);

		} else {
			// Does nothing.
		}

	}

	public static void drawInfo() {
		int[] screen = Main.screen;
		int yPos = 420;
		int index = 0;
		int pixel;
		int r, g, b;
		// Draw health info
		for (int i = 0; i < 4; i++) {
			for (int j = leftSide[i]; j < rightSide[i]; j++) {
				index = yPos * SCREENWIDHT + j - 20;
				pixel = screen[index];
				r = (pixel & 0x00ff0000) >> 17;
				g = (pixel & 0x0000ff00) >> 9;
				b = (pixel & 0x000000ff) >> 1;
				pixel = b + (g << 8) + (r << 16);
				screen[index] = pixel;
			}
			yPos++;
		}

		int loweryPos = yPos + 30;
		for (int i = yPos; i < loweryPos; i++) {
			for (int j = 50; j < 200; j++) {
				index = yPos * SCREENWIDHT + j - 20;
				pixel = screen[index];
				r = (pixel & 0x00ff0000) >> 17;
				g = (pixel & 0x0000ff00) >> 9;
				b = (pixel & 0x000000ff) >> 1;
				pixel = b + (g << 8) + (r << 16);
				screen[index] = pixel;
			}
			yPos++;
		}

		for (int i = 4; i < 8; i++) {
			for (int j = leftSide[i]; j < rightSide[i]; j++) {
				index = yPos * SCREENWIDHT + j - 20;
				pixel = screen[index];
				r = (pixel & 0x00ff0000) >> 17;
				g = (pixel & 0x0000ff00) >> 9;
				b = (pixel & 0x000000ff) >> 1;
				pixel = b + (g << 8) + (r << 16);
				screen[index] = pixel;
			}
			yPos++;
		}

		Integer integer = new Integer((int) ((double) Main.PT.HP * 100 / 150));
		TextFactory.drawString(integer.toString(), 134, 427, 1, 0x00d0d0d0);
		TextFactory.draw(Saude, 32, 427, 1, 0x00d0d0d0);
		// Draw ammo info
		yPos = 420;
		for (int i = 0; i < 4; i++) {
			for (int j = leftSide[i]; j < rightSide[i]; j++) {
				index = yPos * SCREENWIDHT + j + 415;
				pixel = screen[index];
				r = (pixel & 0x00ff0000) >> 17;
				g = (pixel & 0x0000ff00) >> 9;
				b = (pixel & 0x000000ff) >> 1;
				pixel = b + (g << 8) + (r << 16);
				screen[index] = pixel;
			}
			yPos++;
		}

		loweryPos = yPos + 30;
		for (int i = yPos; i < loweryPos; i++) {
			for (int j = 50; j < 200; j++) {
				index = yPos * SCREENWIDHT + j + 415;
				pixel = screen[index];
				r = (pixel & 0x00ff0000) >> 17;
				g = (pixel & 0x0000ff00) >> 9;
				b = (pixel & 0x000000ff) >> 1;
				pixel = b + (g << 8) + (r << 16);
				screen[index] = pixel;
			}
			yPos++;
		}

		for (int i = 4; i < 8; i++) {
			for (int j = leftSide[i]; j < rightSide[i]; j++) {
				index = yPos * SCREENWIDHT + j + 415;
				pixel = screen[index];
				r = (pixel & 0x00ff0000) >> 17;
				g = (pixel & 0x0000ff00) >> 9;
				b = (pixel & 0x000000ff) >> 1;
				pixel = b + (g << 8) + (r << 16);
				screen[index] = pixel;
			}
			yPos++;
		}

		integer = new Integer(PlayerTank.currentAmmo);
		TextFactory.drawString(integer.toString(), 588, 427, 1, 0x00d0d0d0);
		TextFactory.draw(Municao, 472, 427, 1, 0x00d0d0d0);
		/*
		 * English
		 * 
		 * TextFactory.drawString(integer.toString(), 554, 427, 1, 0x00d0d0d0)
		 * TextFactory.draw(Ammo, 482, 427, 1, 0x00d0d0d0);
		 * 
		 */

	}

	/** Starts a Logger to GameHUD class */
	private static final Logger LOG = Logger.getLogger(GameHUD.class.getName());

	private static final Locale PTBR = new Locale("pt", "BR");

}