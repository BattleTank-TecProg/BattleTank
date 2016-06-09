import static org.junit.Assert.*;

import java.util.Random;

import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNot;
import org.junit.Assert;
import org.junit.Test;

public class GameDataTest {

	@Test
	public void testMakeData() {
		GameData.makeData();
		assertEquals(GameData.screenTable.length, 480);
		assertEquals(GameData.random.length, 1000);
		assertEquals(GameData.sin.length, 361);
		assertEquals(GameData.cos.length, 361);
		assertEquals(GameData.randomVectors.length, 1000);
		assertEquals(GameData.size.length, 9);
	}

	@Test
	public void testGetRandomVector() {
		GameData.getRandomVector();
		assertNotNull(GameData.randomVectors);
		assertEquals(GameData.randomVectors.length, 1000);

	}

	@Test
	public void testDestroy() {
		GameData.destroy();
		assertEquals(GameData.random, null);
		assertEquals(GameData.sin, null);
		assertEquals(GameData.cos, null);
		assertEquals(GameData.screenTable, null);
		assertSame(GameData.colorTable, null);
	}
	
}
