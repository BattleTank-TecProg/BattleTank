package test;

import static org.junit.Assert.*;

import org.junit.Test;

import project.Rasterizer;

public class RasterizerTest {

	@Test
	public void testRasterizerAtributs() {
		assertNotNull(Rasterizer.A);
		assertNotNull(Rasterizer.B);
		assertNotNull(Rasterizer.C);
		assertNotNull(Rasterizer.U);
		assertNotNull(Rasterizer.V);
		assertNotNull(Rasterizer.O);
		assertNotNull(Rasterizer.W);
		assertNotNull(Rasterizer.tempVector1);
		assertNotNull(Rasterizer.tempVector2);
		assertNotNull(Rasterizer.tempVector3);
		assertNotNull(Rasterizer.tempVector4);

}
		
}
