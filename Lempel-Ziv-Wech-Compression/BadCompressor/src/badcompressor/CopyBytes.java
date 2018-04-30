/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package badcompressor;

import java.io.*;
public class CopyBytes {
	public static void main( String args[]) throws IOException {
		DataInputStream in =
				new DataInputStream(
						new BufferedInputStream(
								new FileInputStream(new File("ascii.txt"))));
		DataOutputStream out =
				new DataOutputStream(
						new BufferedOutputStream(
								new FileOutputStream(new File("test2.txt"))));
		byte byteIn;
		try {
			while(true) {
				byteIn = in.readByte();
				out.writeByte(byteIn);
			}
		}
		catch(EOFException e) {
			in.close();
			out.close();
		}
	}
}