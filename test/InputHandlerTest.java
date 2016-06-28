package test;

import project.InputHandler;
import static org.junit.Assert.*;

import org.junit.Test;

public class InputHandlerTest {

	@Test
	public void testHandleInput() {
		InputHandler.handleInput();
		assertEquals(InputHandler.playerInAction, false);
		assertEquals(InputHandler.UP, false);
		assertEquals(InputHandler.DOWN, false);
		assertEquals(InputHandler.RIGHT, false);
		assertEquals(InputHandler.LEFT, false);
		assertEquals(InputHandler.A, false);
		assertEquals(InputHandler.S, false);
		assertEquals(InputHandler.D, false);
		assertEquals(InputHandler.W, false);
		assertEquals(InputHandler.space, false);
		assertEquals(InputHandler.weapon1Selected, false);
		assertEquals(InputHandler.weapon2Selected, false);
		assertEquals(InputHandler.weapon3Selected, false);
		assertEquals(InputHandler.weapon4Selected, false);
		assertEquals(InputHandler.mousePressedAction, false);
		assertEquals(InputHandler.changeWeapon, false);
		assertEquals(InputHandler.L, false);
		assertEquals(InputHandler.I, false);
		assertEquals(InputHandler.M, false);

	}
	/*
	 * @Test public void testKeyPressed() { fail("Not yet implemented"); }
	 * 
	 * @Test public void testKeyReleased() { fail("Not yet implemented"); }
	 * 
	 * @Test public void testKeyTyped() { fail("Not yet implemented"); }
	 * 
	 * @Test public void testMouseMoved() { fail("Not yet implemented"); }
	 * 
	 * @Test public void testMouseDragged() { fail("Not yet implemented"); }
	 * 
	 * @Test public void testMousePressed() { fail("Not yet implemented"); }
	 * 
	 * @Test public void testMouseReleased() { fail("Not yet implemented"); }
	 * 
	 * @Test public void testMouseEntered() { fail("Not yet implemented"); }
	 * 
	 * @Test public void testMouseExited() { fail("Not yet implemented"); }
	 */
}
