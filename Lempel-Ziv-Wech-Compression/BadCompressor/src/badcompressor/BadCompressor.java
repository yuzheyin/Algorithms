/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package badcompressor;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author Yuzhe Yin <yuzhey@andrew.cmu.edu>
 */
public class BadCompressor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        DataInputStream in = new DataInputStream(
                                            new BufferedInputStream(
                                                            //new FileInputStream(new File("ascii.txt"))));
                                                            new FileInputStream(new File("test.mp4"))));
                                                            //new FileInputStream(new File("1.csv"))));
        DataOutputStream out = new DataOutputStream(
                                            new BufferedOutputStream(
                                                            //new FileOutputStream(new File("compressed.txt"))));
                                                            new FileOutputStream(new File("compressBinary"))));
                                                            //new FileOutputStream(new File("compressCSV.csv"))));
        byte[] byteBuffer = new byte[3];
        byte byteIn1, byteIn2;
        
        int i = 0;
        //When there are more than one byte left, take two bytes out and compress them to three bytes
        while(in.available() > 1) {
                byteIn1 = in.readByte();
                byteIn2 = in.readByte();
                byteBuffer[0] = (byte) (byteIn1 >>> 4);
                byteBuffer[1] = (byte) (byteIn1 << 4);
                byteBuffer[2] = byteIn2;
                out.writeByte(byteBuffer[0]);
                out.writeByte(byteBuffer[1]);
                out.writeByte(byteBuffer[2]);
        }
        //When there is still one byte left, take it out and compress it to two bytes
        if(in.available() > 0){
                byteIn1 = in.readByte();
                byteBuffer[0] = (byte) (byteIn1 >>> 4);
                byteBuffer[1] = (byte) (byteIn1 << 4);
                out.writeByte(byteBuffer[0]);
                out.writeByte(byteBuffer[1]);
        }

        in.close();
        out.close();
        
    }
    
}
