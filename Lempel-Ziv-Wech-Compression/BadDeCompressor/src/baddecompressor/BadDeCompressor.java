/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baddecompressor;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author Yuzhe Yin <yuzhey@andrew.cmu.edu>
 */
public class BadDeCompressor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
                DataInputStream in = new DataInputStream(
                                            new BufferedInputStream(
                                                            //new FileInputStream(new File("compressed.txt"))));
                                                            new FileInputStream(new File("compressBinary"))));
                                                            //new FileInputStream(new File("compressCSV.csv"))));
        DataOutputStream out = new DataOutputStream(
                                            new BufferedOutputStream(
                                                            //new FileOutputStream(new File("decompressed.txt"))));
                                                            new FileOutputStream(new File("decompresBinary.mp4"))));
                                                            //new FileOutputStream(new File("deCSV.csv"))));
        byte[] byteBuffer = new byte[2];
        byte byteIn1, byteIn2, byteIn3;
        
        int i = 0;
        //When there are three bytes or more, get three bytes out each time and decompress them to two bytes
        while(in.available() > 2) {
                byteIn1 = in.readByte();
                byteIn2 = in.readByte();
                byteIn3 = in.readByte();
                byteBuffer[0] = (byte) ((byte)(byteIn1 << 4) | ((byte)(byteIn2 >>> 4) & 0xF));
                byteBuffer[1] = byteIn3;
                out.writeByte(byteBuffer[0]);
                out.writeByte(byteBuffer[1]);
        }
        //When there are still bytes in the stream, there must be two bytes. Decompress them to one byte
        if(in.available() > 0){
                byteIn1 = in.readByte();
                byteIn2 = in.readByte();
                byteBuffer[0] =  (byte) ((byte)(byteIn1 << 4) | (byte)(byteIn2 >>> 4));
                out.writeByte(byteBuffer[0]);
        }

        in.close();
        out.close();
    }
    
}
