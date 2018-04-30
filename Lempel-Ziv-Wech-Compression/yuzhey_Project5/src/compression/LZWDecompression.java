package compression;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import cmu.andrew.yuzhey.HashTable2;
import cmu.andrew.yuzhey.SinglyLinkedList;

public class LZWDecompression {
	public static void main(String args[]) throws IOException{
		DataInputStream in = new DataInputStream(
								new BufferedInputStream(
										new FileInputStream(new File(args[0]))));
										//new FileInputStream(new File("comMp4"))));;
										//new FileInputStream(new File("comAscii.txt"))));
										//new FileInputStream(new File("words"))));
		DataOutputStream out = new DataOutputStream(
									new BufferedOutputStream(
										new FileOutputStream(new File(args[1]))));
										//new FileOutputStream(new File("DecomMp4.mp4"))));
										//new FileOutputStream(new File("DecomAscii.txt"))));
										//new FileOutputStream(new File("Dewords.html"))));

		HashTable2 hash = new HashTable2();
		int tableIndex = 256;
		for(char ch = 0; ch <= 255; ch++){
			hash.put((int)ch, String.valueOf(ch));
		}
		
		byte[] byteBuffer = new byte[3];
		byteBuffer[0] = (byte)(in.readByte() & 0xFF);
		byteBuffer[1] = (byte)(in.readByte() & 0xFF);
		char prior = (char)(((byteBuffer[0] << 4) | (byteBuffer[1] >> 4 & 0xF)) & 0xFFF);
		char codeword = 0;
		out.writeBytes(hash.get((int)prior));
		long byteCount = 2;		
		
		//Decompression
		while(in.available() > 0){
			if(byteCount%3 == 2){
				byteCount++;
				byteBuffer[2] = (byte)(in.readByte() & 0xFF);
				codeword = (char)((byteBuffer[1] << 8 & 0xF00) | (byteBuffer[2] & 0xFF) & 0xFFF);
			}
			else if(byteCount%3 == 0){
				byteCount++;
				byteBuffer[0] = (byte)(in.readByte() & 0xFF);
				continue;
			}
			else{
				byteCount++;
				byteBuffer[1] = (byte)(in.readByte() & 0xFF);
				codeword = (char)(((byteBuffer[0] << 4) | (byteBuffer[1] >> 4 & 0xF)) & 0xFFF);
			}
			if(hash.containsKey((int)codeword)){
				hash.put(tableIndex++, hash.get((int)prior)+hash.get((int)codeword).charAt(0));
				out.writeBytes(hash.get((int)codeword));
			}
			else{
				hash.put(tableIndex++, hash.get((int)prior)+hash.get((int)prior).charAt(0));
				out.writeBytes(hash.get((int)prior)+hash.get((int)prior).charAt(0));
			}
			prior = codeword;
			
			//When the index reaches the maximum 12 digits, create a new table and start anew. 
			if(tableIndex >= 4096){
				hash = new HashTable2();
				for(char ch = 0; ch <= 255; ch++){
					hash.put((int)ch, String.valueOf(ch));
				}
				tableIndex = 256;
			}
		}
		
		in.close();
        out.close();

	}
}
