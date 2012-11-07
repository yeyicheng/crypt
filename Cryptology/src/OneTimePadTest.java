import static org.junit.Assert.*;

import org.junit.Test;

public class OneTimePadTest {

	@Test
	public void charToAsciiTest() {
		assertTrue(OneTimePad.charToAscii('a') == 97);
		assertTrue(OneTimePad.charToAscii('z') == 122);
		assertTrue(OneTimePad.charToAscii('A') == 65);
		assertTrue(OneTimePad.charToAscii('Z') == 90);
		assertFalse(OneTimePad.charToAscii('Z') == 91);
	}
	
	@Test
	public void decToHexTest(){
		assertTrue(OneTimePad.decToHex(10).equals("a"));
		assertTrue(OneTimePad.decToHex(22).equals("16"));
		assertTrue(OneTimePad.decToHex(100).equals("64"));
		assertTrue(OneTimePad.decToHex(0).equals("0"));
		assertTrue(OneTimePad.decToHex(31).equals("1f"));
	}
	
	@Test
	public void hexToBinTest(){
		assertTrue(OneTimePad.hexToBin('a').equals("1010"));
		assertTrue(OneTimePad.hexToBin('0').equals("0000"));
		assertTrue(OneTimePad.hexToBin("123abc").equals("000100100011101010111100"));
	}
	
	@Test
	public void stringToAsciiTest(){
		assertTrue(OneTimePad.hexToBin('a').equals("1010"));
		assertTrue(OneTimePad.hexToBin('0').equals("0000"));
		assertTrue(OneTimePad.hexToBin("123abc").equals("000100100011101010111100"));
	}
	
	@Test
	public void decryptTest(){
		assertTrue(OneTimePad.decrypt("1100", "1101").equals("0001"));
		assertTrue(OneTimePad.decrypt("0", "1").equals("1"));
		assertTrue(OneTimePad.decrypt("0", "0").equals("0"));
		assertTrue(OneTimePad.decrypt("1", "1").equals("0"));
		assertTrue(OneTimePad.decrypt("1", "0").equals("1"));
	}
	
	@Test
	public void binToHexTest(){
		assertTrue(OneTimePad.binToHex("1100").equals("c"));
		assertTrue(OneTimePad.binToHex("1101").equals("d"));
		assertTrue(OneTimePad.binToHex("0000").equals("0"));
		assertTrue(OneTimePad.binToHex("1001").equals("9"));
		assertTrue(OneTimePad.binToHex("11000000").equals("c0"));
	}
}
