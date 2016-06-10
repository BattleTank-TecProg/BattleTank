import static org.junit.Assert.*;

import org.junit.Test;

public class GameHUDTest {
	public static int cheat1[];
	public static int cheat2[];
	public static int cheat3[];
	public static int you[];
	public static int won[];
	public static int menuOptionStatus = 0;
	public static int gameoverMessagePosition = 0;
	public static int winMessagePosition = 0;
	public static int loadingScreenPosition = 0;
	public static int mouseXpos = 0;
	public static int mouseYpos = 0;
	public static boolean escapePressed = false;
	public static boolean downPressed = false;
	public static boolean upPressed = false;
	public static boolean enterPressed = false;
	public static boolean mousePressed = false;

	@Test
	public void testMenuOptionStatus() {
		assertNotNull(GameHUD.getMenuOptionStatus());
		assertEquals(GameHUD.getMenuOptionStatus(), menuOptionStatus);
	}

	@Test
	public void testGameoverMessagePosition() {
		assertNotNull(GameHUD.getGameoverMessagePosition());
		assertEquals(GameHUD.getGameoverMessagePosition(), gameoverMessagePosition);
	}

	@Test
	public void testWinMessagePosition() {
		assertNotNull(GameHUD.getWinMessagePosition());
		assertEquals(GameHUD.getWinMessagePosition(), winMessagePosition);
	}

	@Test
	public void testLoadingScreenPosition() {
		assertNotNull(GameHUD.getLoadingScreenPosition());
		assertEquals(GameHUD.getLoadingScreenPosition(), loadingScreenPosition);
	}

	@Test
	public void testMouseXpos() {
		assertNotNull(GameHUD.getMouseXpos());
		assertEquals(GameHUD.getMouseXpos(), mouseXpos);
	}

	@Test
	public void testMouseYpos() {
		assertNotNull(GameHUD.getMouseYpos());
		assertEquals(GameHUD.getMouseYpos(), mouseYpos);
	}

	@Test
	public void testEscapePressed() {
		assertNotNull(GameHUD.isEscapePressed());
		assertEquals(GameHUD.isEscapePressed(), escapePressed);
	}

	@Test
	public void testDownPressed() {
		assertNotNull(GameHUD.isDownPressed());
		assertEquals(GameHUD.isDownPressed(), downPressed);
	}

	@Test
	public void testUpPressed() {
		assertNotNull(GameHUD.isUpPressed());
		assertEquals(GameHUD.isUpPressed(), upPressed);
	}

	@Test
	public void testEnterPressed() {
		assertNotNull(GameHUD.isEnterPressed());
		assertEquals(GameHUD.isEnterPressed(), enterPressed);
	}

	@Test
	public void testMousePressed() {
		assertNotNull(GameHUD.isMousePressed());
		assertEquals(GameHUD.isMousePressed(), mousePressed);
	}

	@Test
	public void testDrawMenu() {
		int test[] = null;
		assertArrayEquals(GameHUD.getLeftSide(), test);
		assertArrayEquals(GameHUD.getRightSide(), test);
	}

	@Test
	public void testUpdate() {
		GameHUD.update();
		assertNotNull(GameHUD.isEscapePressed());
		assertNotNull(GameHUD.isUpPressed());
		assertNotNull(GameHUD.isDownPressed());
		assertNotNull(GameHUD.isEnterPressed());
		assertNotNull(GameHUD.isMousePressed());
		assertNotNull(GameHUD.getMenuOptionStatus());
		assertNotNull(GameHUD.getGameoverMessagePosition());
		assertNotNull(GameHUD.getWinMessagePosition());
		assertNotNull(GameHUD.getLoadingScreenPosition());
		assertEquals(GameHUD.isEscapePressed(), false);
		assertEquals(GameHUD.isDownPressed(), false);
		assertEquals(GameHUD.isUpPressed(), false);
		assertEquals(GameHUD.isEnterPressed(), false);
		assertEquals(GameHUD.isMousePressed(), false);

	}

	@Test
	public void testDrawGameOverMessage() {
		GameHUD.drawGameoverMessage();
		assertNotNull(GameHUD.getGameoverMessagePosition());
		assertEquals(GameHUD.getGame(), null);
		assertEquals(GameHUD.getOver(), null);
	}

	@Test
	public void testDrawWinMessage() {
		GameHUD.drawWinMessage();
		assertNotNull(GameHUD.getWinMessagePosition());
		assertArrayEquals(cheat1, GameHUD.getCheat1());
		assertArrayEquals(cheat2, GameHUD.getCheat2());
		assertArrayEquals(cheat3, GameHUD.getCheat3());
		assertEquals(null, GameHUD.getCheat1());
		assertEquals(null, GameHUD.getCheat2());
		assertEquals(null, GameHUD.getCheat3());
		assertEquals(null, GameHUD.getYou());
		assertEquals(null, GameHUD.getWon());
		assertArrayEquals(you, GameHUD.getYou());
		assertArrayEquals(won, GameHUD.getWon());
	}
}
