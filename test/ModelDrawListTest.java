package test;

import static org.junit.Assert.*;

import org.junit.Test;

import project.Geometry;
import project.Model;
import project.ModelDrawList;

public class ModelDrawListTest {

	@Test
	public void testMakeList() {
		Model[] visibleModels1;
		ModelDrawList.makeList();
		visibleModels1 = new Model[300];
		ModelDrawList.visibleModels = new Model[300];
		assertArrayEquals(ModelDrawList.visibleModels, visibleModels1);
		assertNotNull(ModelDrawList.visibleModels);
		
	}

	@Test
	public void testRegister() {

	}

	@Test
	public void testDraw() {
		ModelDrawList.draw();
		assertNotNull(ModelDrawList.modelCount);
	}

	@Test
	public void testSort() {
		ModelDrawList.sort();
		assertNotNull(ModelDrawList.modelCount);
		assertNull(ModelDrawList.visibleModels);

	}

}
