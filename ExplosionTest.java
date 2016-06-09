import static org.junit.Assert.*;

import org.junit.Test;

public class ExplosionTest {
	
	int varibleForComparateResult = 1;
	Explosion explosion = new Explosion(varibleForComparateResult, varibleForComparateResult, varibleForComparateResult,
			varibleForComparateResult);

	@Test
	public void testGetAuraIndex() {
		explosion.setAuraIndex(varibleForComparateResult);
		assertEquals(explosion.getAuraIndex(), varibleForComparateResult);
	}
	
	@Test
	public void testGetDamage() {
		explosion.setDamage(varibleForComparateResult);
		assertEquals(explosion.getDamage(), varibleForComparateResult);
	}
	
	@Test
	public void testGetFrameIndex() {
		explosion.setFrameIndex(varibleForComparateResult);
		assertEquals(explosion.getFrameIndex(), varibleForComparateResult);
	}
	
	@Test
	public void testGetGroundZero() {
		explosion.setGroundZero(varibleForComparateResult);
		assertEquals(explosion.getGroundZero(), varibleForComparateResult);
	}
	
	@Test
	public void testGetSpriteIndex() {
		explosion.setSpriteIndex(varibleForComparateResult);
		assertEquals(explosion.getSpriteIndex(), varibleForComparateResult);
	}
	
}
