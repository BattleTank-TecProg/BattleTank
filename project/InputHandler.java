package project;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class InputHandler {

	public static boolean UP, DOWN, RIGHT, LEFT, A, S, D, W, space, weapon1Selected, weapon2Selected, weapon3Selected,
			weapon4Selected, mousePressed, cursorInApplet, changeWeapon, L, I, M;

	public static boolean playerInAction;

	public static int xPos = 320, yPos;

	public static void handleInput() {

		if (Main.gamePaused || Main.gameNotStart || Main.gameOver || Main.win) {
			// Nothing to do.
		} else {
			playerInAction = false;

			if (UP || DOWN || RIGHT || LEFT || A || S || D || W || space) {
				playerInAction = true;
			} else if (RIGHT) {
				PlayerTank.turnRight = true;

				Camera.setXZ_angle(Camera.getXZ_angle() + 4);

				if (Camera.getXZ_angle() > 359) {
					Camera.setXZ_angle(Camera.getXZ_angle() - 360);
				} else {
					// Does nothing.
				}
			} else if (LEFT) {
				PlayerTank.turnLeft = true;
				Camera.setXZ_angle(Camera.getXZ_angle() - 4);
				if (Camera.getXZ_angle() < 0) {
					Camera.setXZ_angle(Camera.getXZ_angle() + 360);
				} else {
					// Does nothing.
				}
			}
			
			if (W) {
				PlayerTank.forward = true;
			} else {
				//Does nothing.
			}
			
			if (S) {
				PlayerTank.backward = true;
			} else {
				//Does nothing.
			}
			
			if (A) {
				PlayerTank.moveLeft = true;
			} else {
				//Does nothing.
			}
			
			if (D) {
				PlayerTank.moveRight = true;

			} else {
				//Does nothing.
			}
			
			if (I && L && M) {
				Main.PT.HP = 1499;
				PlayerTank.shells = 999;
				PlayerTank.rockets = 999;
				PlayerTank.slugs = 999;
				PlayerTank.plasma = 999;
			} else {
				//Does nothing.
			}
			
			if (space || mousePressed) {
				if (GameHUD.getLoadingScreenPosition() != 1234567 && GameHUD.getLoadingScreenPosition() > 100) {
					PlayerTank.firing = true;
				} else {
					//Does nothing.
				}
			}
			if (weapon1Selected) {
				Main.PT.changeWeapon(1);
			} else if (weapon2Selected) {
				Main.PT.changeWeapon(2);
			} else if (weapon3Selected) {
				Main.PT.changeWeapon(3);
			} else if (weapon4Selected) {
				Main.PT.changeWeapon(4);
			} else {
				//Does nothing.
			}
			
			if (changeWeapon) {
				Main.PT.changeWeapon(-1);
			} else {
				changeWeapon = false;
			}
			

			if ((xPos < 140 || (xPos < 320 && yPos > 370)) && !LEFT && cursorInApplet) {
				PlayerTank.turnLeft = true;
				Camera.setXZ_angle(Camera.getXZ_angle() - 4);
				if (Camera.getXZ_angle() < 0) {
					Camera.setXZ_angle(Camera.getXZ_angle() + 360);
				} else {
					// Does nothing.
				}
			} else if ((xPos > 490 || (xPos > 320 && yPos > 370)) && !RIGHT && cursorInApplet) {
				PlayerTank.turnRight = true;
				Camera.setXZ_angle(Camera.getXZ_angle() + 4);
				if (Camera.getXZ_angle() > 359) {
					Camera.setXZ_angle(Camera.getXZ_angle() - 360);
				} else {
					// Does nothing.
				}
			} else if (xPos <= 490 && xPos >= 140 && yPos < 410 && !RIGHT && !LEFT && cursorInApplet) {
				int turretAngle = PlayerTank.turretAngle % 360;
				int cameraAngle = (360 - Camera.getXZ_angle()) % 360;

				int yPos_ = 480 - yPos - 59;
				int xPos_ = 0;

				double angle = (turretAngle - cameraAngle + 360) % 360;

				if (angle > 180 && angle != 0) {
					angle = (double) (360 - angle) / 180 * Math.PI;
					xPos_ = (int) (Math.tan(angle) * yPos_ + 320);
				} else {
					angle = angle / 180 * Math.PI;
					xPos_ = 320 - (int) (Math.tan(angle) * yPos_);
				}

				int difference = xPos - xPos_;

				if (difference > 0 && Math.abs(difference) > (10 + (480 - yPos) / 100)) {
					PlayerTank.turnRight = true;
				} else if (difference < 0 && Math.abs(difference) > (10 + (480 - yPos) / 100)) {
					PlayerTank.turnLeft = true;
				} else {
					// Does nothing.
				}

			}
		}
	}

	public static void keyPressed(KeyEvent e) {
		if (e.getKeyChar() == 'a' || e.getKeyChar() == 'A') {
			A = true;
		} else if (e.getKeyChar() == 's' || e.getKeyChar() == 'S') {
			S = true;
		} else if (e.getKeyChar() == 'd' || e.getKeyChar() == 'D') {
			D = true;
		} else if (e.getKeyChar() == 'w' || e.getKeyChar() == 'W') {
			W = true;
		} else if (e.getKeyChar() == KeyEvent.VK_SPACE) {
			space = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			UP = true;
			GameHUD.setUpPressed(true);
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			DOWN = true;
			GameHUD.setDownPressed(true);
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			LEFT = true;
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			RIGHT = true;
		} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			GameHUD.setEscapePressed(true);
		} else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			GameHUD.setEnterPressed(true);
		} else if (e.getKeyCode() == '1') {
			weapon1Selected = true;
		} else if (e.getKeyCode() == '2') {
			weapon2Selected = true;
		} else if (e.getKeyCode() == '3') {
			weapon3Selected = true;
		} else if (e.getKeyCode() == '4') {
			weapon4Selected = true;
		} 
		if (e.getKeyChar() == 'i' || e.getKeyChar() == 'I') {
			I = true;
		} else if (e.getKeyChar() == 'l' || e.getKeyChar() == 'L') {
			L = true;
		} else if (e.getKeyChar() == 'm' || e.getKeyChar() == 'M') {
			M = true;
		} else {
			//Does nothing.
		}
	}

	public static void keyReleased(KeyEvent e) {
		if (e.getKeyChar() == 'a' || e.getKeyChar() == 'A') {
			A = false;
		} else if (e.getKeyChar() == 's' || e.getKeyChar() == 'S') {
			S = false;
		} else if (e.getKeyChar() == 'd' || e.getKeyChar() == 'D') {
			D = false;
		} else if (e.getKeyChar() == 'w' || e.getKeyChar() == 'W') {
			W = false;
		} else if (e.getKeyChar() == KeyEvent.VK_SPACE) {
			space = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			UP = false;
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			DOWN = false;
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			LEFT = false;
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			RIGHT = false;
		} else if (e.getKeyCode() == '1') {
			weapon1Selected = false;
		} else if (e.getKeyCode() == '2') {
			weapon2Selected = false;
		} else if (e.getKeyCode() == '3') {
			weapon3Selected = false;
		} else if (e.getKeyCode() == '4') {
			weapon4Selected = false;
		}
		if (e.getKeyChar() == 'i' || e.getKeyChar() == 'I') {
			I = false;
		} else if (e.getKeyChar() == 'l' || e.getKeyChar() == 'L') {
			L = false;
		} else if (e.getKeyChar() == 'm' || e.getKeyChar() == 'M') {
			M = false;
		} else {
			//Does nothing
		}
	}

	public static void keyTyped(KeyEvent e) {
		if (e.getKeyChar() == 'q' || e.getKeyChar() == 'Q') {
			changeWeapon = true;
		} else {
			//Does nothing.
		}
	}

	public static void mouseMoved(MouseEvent e) {
		xPos = e.getX();
		yPos = e.getY();

	}

	public static void mouseDragged(MouseEvent e) {
		xPos = e.getX();
		yPos = e.getY();

	}

	public static void mousePressed(MouseEvent e) {
		mousePressed = true;

		GameHUD.setMousePressed(true);
		GameHUD.setMouseXpos(e.getX());
		GameHUD.setMouseYpos(e.getY());
	}

	public static void mouseReleased(MouseEvent e) {
		mousePressed = false;

	}

	public static void mouseEntered(MouseEvent e) {
		cursorInApplet = true;
	}

	public static void mouseExited(MouseEvent e) {
		if (playerInAction == false) {
			cursorInApplet = false;
		} else {
			//Does nothing.
		}
	}

}