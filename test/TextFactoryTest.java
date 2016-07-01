package test;

import static org.junit.Assert.*;

import org.junit.Test;

import project.TextFactory;

public class TextFactoryTest {

	@Test
	public void testTextFactoryAtributs() {
		assertNull(TextFactory.letters);
		assertNull(TextFactory.spaceCharacter);
	}
	
	public void testTranslate() {
		assertEquals(TextFactory.translate("0"),0);
		assertEquals(TextFactory.translate("1"),1);
		assertEquals(TextFactory.translate("2"),2);
		assertEquals(TextFactory.translate("3"),3);
		assertEquals(TextFactory.translate("4"),4);
		assertEquals(TextFactory.translate("5"),5);
		assertEquals(TextFactory.translate("6"),6);
		assertEquals(TextFactory.translate("7"),7);
		assertEquals(TextFactory.translate("8"),8);
		assertEquals(TextFactory.translate("9"),9);
	}
}