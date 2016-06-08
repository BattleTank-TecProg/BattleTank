import static org.junit.Assert.*;

import org.junit.Test;

public class GameHUDTest {

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

}
