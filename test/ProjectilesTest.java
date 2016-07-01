package test;

import static org.junit.Assert.*;

import org.junit.Test;

import project.Projectiles;

public class ProjectilesTest {

	@Test
	public void testInit() {
		Projectiles.init();
		assertNotNull(Projectiles.projectilesArray);
		assertEquals(Projectiles.projectilesArray.length, 150);
	}

	@Test
	public void testUpdate() {
		Projectiles.update();
		assertNotNull(Projectiles.projectilesArray);
		assertEquals(Projectiles.projectilesArray.length, 150);

	}

	@Test
	public void testRegister() {

	}

}
