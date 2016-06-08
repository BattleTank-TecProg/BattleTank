import static org.junit.Assert.*;

import java.util.Random;

import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNot;
import org.junit.Assert;
import org.junit.Test;

public class GameDataTest {
/*
	@Test
	public void testMakeData() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetRandom() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetRandomVector() {
		fail("Not yet implemented");
	}
*/
	@Test
	public void testDestroy() {
		GameData.destroy();
		assertEquals(GameData.random, null);
		assertEquals(GameData.sin, null);
		assertEquals(GameData.cos, null);
		assertEquals(GameData.screenTable, null);
		assertSame(GameData.colorTable, null);
	}
/*
	@Test
	public void testGenerateRandomVector() {
		fail("Not yet implemented");

	}
*/
}
