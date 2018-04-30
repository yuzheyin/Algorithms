package compression;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


import cmu.andrew.yuzhey.HashTable;
import cmu.andrew.yuzhey.SinglyLinkedList;

public class LZWCompression {
	
	public static void main(String args[]) throws IOException{
		DataInputStream in = new DataInputStream(
								new BufferedInputStream(
										new FileInputStream(new File(args[0]))));
										//new FileInputStream(new File("test.mp4"))));
										//new FileInputStream(new File("shortwords.txt"))));
										//new FileInputStream(new File("words.html"))));
		DataOutputStream out = new DataOutputStream(
									new BufferedOutputStream(
											new FileOutputStream(new File(args[1]))));
											//new FileOutputStream(new File("comMp4"))));
											//new FileOutputStream(new File("comAscii.txt"))));
											//new FileOutputStream(new File("words"))));

		HashTable hash = new HashTable();
		int tableIndex = 256;
		for(char ch = 0; ch <= 255; ch++){
			hash.put(String.valueOf(ch), (int)ch);
		}
		
		String current = "";
		current += (char)(in.readByte() & 0xFF);
		char c;
		long byteCount = 0;
		long writenCount = 0;
		long readCount = 1;
		char temp1 = '0', temp2 = '0';
		byte[] byteBuffer = new byte[3];
		
		//Compression
		while(in.available() > 0){
			c = (char)(in.readByte() & 0xFF);
			readCount++;
			if(hash.containsKey(current+c)){
				current += c;
			}
			else{
				byteCount++;
				if(byteCount%2 == 0){
					temp2 = (char)hash.get(current);
	                byteBuffer[0] = (byte) (temp1 >>> 4 & 0xFF);
	                byteBuffer[1] = (byte) ((temp1 << 4 & 0xF0) | (temp2 >>> 8 & 0xF));
	                byteBuffer[2] = (byte) (temp2 & 0xFF);
	                out.write(byteBuffer);
	                writenCount += 3;
				}
				else
					temp1 = (char)hash.get(current);
				hash.put(current+c, tableIndex++);
				current = String.valueOf(c);
			}
			
			//When the index reaches the maximum 12 digits, create a new table and start anew. 
			if(tableIndex >= 4096){
				hash = new HashTable();
				for(char ch = 0; ch <= 255; ch++){
					hash.put(String.valueOf(ch), (int)ch);
				}
				tableIndex = 256;
			}
		}
		
		byteCount++;
		if(byteCount%2 == 0){
			temp2 = (char)hash.get(current);
            byteBuffer[0] = (byte) (temp1 >>> 4 & 0xFF);
            byteBuffer[1] = (byte) ((temp1 << 4 & 0xFF) | (temp2 >>> 8 & 0xF));
            byteBuffer[2] = (byte) (temp2 & 0xFF);
            out.write(byteBuffer);
            writenCount += 3;
		}
		else{
			temp1 = (char)hash.get(current);
            byteBuffer[0] = (byte) (temp1 >>> 4 & 0xFF);
            byteBuffer[1] = (byte) (temp1 << 4 & 0xFF);
            out.writeByte(byteBuffer[0]);
            out.writeByte(byteBuffer[1]);
            writenCount += 2;
		}
		
		System.out.println("bytes read = "+readCount+", bytes written = "+writenCount);
		
		in.close();
        out.close();

	}
}
