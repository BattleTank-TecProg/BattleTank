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
		assertEquals(GameHUD.mouseXpos, mouseXpos);
	}
	
	@Test
	public void testDrawMenu() {
		int test[] = null;
		assertArrayEquals(GameHUD.leftSide, test);
		assertArrayEquals(GameHUD.rightSide, test);
	}

	@Test
	public void testUpdate() {
		GameHUD.update();
		assertNotNull(GameHUD.escapePressed);
		assertNotNull(GameHUD.upPressed);
		assertNotNull(GameHUD.downPressed);
		assertNotNull(GameHUD.enterPressed);
		assertNotNull(GameHUD.mousePressed);
		assertNotNull(GameHUD.menuOptionStatus);
		assertNotNull(GameHUD.gameoverMessagePosition);
		assertNotNull(GameHUD.winMessagePosition);
		assertNotNull(GameHUD.loadingScreenPosition);
		assertEquals(GameHUD.escapePressed, false);
		assertEquals(GameHUD.downPressed, false);
		assertEquals(GameHUD.upPressed, false);
		assertEquals(GameHUD.enterPressed, false);
		assertEquals(GameHUD.mousePressed, false);

	}

	@Test
	public void testDrawGameOverMessage() {
		GameHUD.drawGameoverMessage();
		assertNotNull(GameHUD.gameoverMessagePosition);
		assertEquals(GameHUD.game, null);
		assertEquals(GameHUD.over, null);
	}
	
	@Test
	public void testDrawWinMessage(){
		GameHUD.drawWinMessage();
		assertNotNull(GameHUD.winMessagePosition);
		assertArrayEquals(cheat1, GameHUD.cheat1);
		assertArrayEquals(cheat2, GameHUD.cheat2);
		assertArrayEquals(cheat3, GameHUD.cheat3);
		assertEquals(null, GameHUD.cheat1);
		assertEquals(null, GameHUD.cheat2);
		assertEquals(null, GameHUD.cheat3);
		assertEquals(null, GameHUD.You);
		assertEquals(null, GameHUD.Won);
		assertArrayEquals(you, GameHUD.You);
		assertArrayEquals(won, GameHUD.Won);	
	}
}
