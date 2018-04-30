/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package badcompressor;
public class Test {
	public static void main(String a[]) {
		byte b = -1; // b = 0xFF = 11111111 (8 bits)
		char c = (char)b; // c = 0xFFFF = 1111111111111111 (16 bits sign extension)
		c = (char)(c & 0xFF); // c = 0x00FF = 0000000011111111 (remove the extra bits)
		int t = c; // t = 0x000000FF = 0000-000011111111 (32 bits)
		System.out.println(t); // display 255
		b = (byte)255; // b = 0xFF = 11111111 (8 bits)
		c = (char)b; // c = 0xFFFF = 1111111111111111 (16 bits sign extension)
		c = (char)(c & 0xFF); // c = 0x00FF = 0000000011111111 (remove extra bits)
		t = c; // t = 0xFF = 000-000011111111 (32 bits)
		System.out.println(t); // displays 255
		System.out.println();
		// In the for loop below, the 16 bits in ch are not converted to an ASCII integer.
		// For example, when ch == 0000000000000000, there is no attempt to convert to ASCII 0
		// The screen may misbehave because some bit sequences are not printable.
		for(char ch = 0; ch <= 255; ch++) {
			System.out.print("" + ch);
		}
		System.out.println();
		// In this for loop, however, the integer is converted to an ASCII representation.
		// For example, when smallInt == 65, two ASCII characters are generated: '6' and '5'.
		// No misbehavior will occur here.
		for(int smallInt = 0; smallInt <= 255; smallInt++) {
			System.out.print("" + smallInt);
		}
		System.out.println();
		// Place a char into a String with no conversion.
		String test = "" + (char)0;
		System.out.println("Test = " + test);
		// Place an int into a String with conversion to ASCII.
		String test2 = "" + 0;
		System.out.println("Test2 = " + test2);
		// So, String objects may hold non-printable sequences of bits.
	}
}