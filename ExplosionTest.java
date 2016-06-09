import static org.junit.Assert.*;

import org.junit.Test;

public class ExplosionTest {
	int varibleForComparateResult = 1;
	Explosion explosion = new Explosion(varibleForComparateResult, varibleForComparateResult, varibleForComparateResult,
			varibleForComparateResult);

	@Test
	public void testGetSpriteIndex() {
		explosion.setSpriteIndex(varibleForComparateResult);
		assertEquals(explosion.getSpriteIndex(), varibleForComparateResult);
	}
	@Test
	public void testGetAuraIndex() {
		explosion.setAuraIndex(varibleForComparateResult);
		assertEquals(explosion.getAuraIndex(), varibleForComparateResult);
	}
	
	
}
