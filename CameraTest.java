import static org.junit.Assert.*;

import org.junit.Test;

public class CameraTest {

	private static final int XCOORDINADE = 0;

	private static final int YCOORDINADE = 0;

	private final static int WIDTH = 640;

	private final static int HEIGHT = 480;
	
	@Test
	public void testGetXcoordinade() {
		assertEquals(Camera.getXcoordinade(), XCOORDINADE);
	}

	@Test
	public void testGetYcoordinade() {
		assertEquals(Camera.getYcoordinade(), YCOORDINADE);
	}

	@Test
	public void testGetWidth() {
		assertEquals(Camera.getWidth(), WIDTH);
	}

	@Test
	public void testGetHeight() {
		assertEquals(Camera.getHeight(), HEIGHT);

	}

}
