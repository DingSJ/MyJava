package com.china.nio.channel;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ChannelTest {

    @Test
    public void testChannel() throws IOException {

        RandomAccessFile aFile = new RandomAccessFile("E:\\ideaSpace\\MyJava\\javaBase\\src\\main\\java\\com\\china\\nio\\channel\\test.txt", "rw");
        FileChannel inChannel = aFile.getChannel();
        ByteBuffer buf = ByteBuffer.allocate(128);
        int bytesRead = inChannel.read(buf);
        while (bytesRead != -1) {
            System.out.println("Read " + bytesRead);
            buf.flip();
            while(buf.hasRemaining()){
                System.out.print((char) buf.get());
            }
            buf.clear();
            bytesRead = inChannel.read(buf);
        }
        aFile.close();
    }


    @Test
    public void testChannel2() throws IOException {

        String filePath = "E:\\ideaSpace\\MyJava\\javaBase\\src\\main\\java\\com\\china\\nio\\channel\\test.txt";
        FileInputStream inputStream = new FileInputStream(filePath);
        FileChannel inChannel = inputStream.getChannel();
        ByteBuffer buf = ByteBuffer.allocate(128);
        int bytesRead = inChannel.read(buf);
        while (bytesRead != -1) {
            System.out.println("Read " + bytesRead);
            buf.flip();
            while(buf.hasRemaining()){
                System.out.print((char) buf.get());
            }
            buf.clear();
            bytesRead = inChannel.read(buf);
        }
        inputStream.close();
    }
    @Test
    public void testChannel3() throws IOException {

        String filePath = "E:\\ideaSpace\\MyJava\\javaBase\\src\\main\\java\\com\\china\\nio\\channel\\test.txt";
        String newFilePath = "E:\\ideaSpace\\MyJava\\javaBase\\src\\main\\java\\com\\china\\nio\\channel\\test2.txt";
        FileInputStream inputStream = new FileInputStream(filePath);
        FileOutputStream outputStream = new FileOutputStream(newFilePath);
        FileChannel inChannel = inputStream.getChannel();
        FileChannel outChannel = outputStream.getChannel();
        ByteBuffer buf = ByteBuffer.allocate(128);
        int bytesRead = inChannel.read(buf);
        while (bytesRead != -1) {
            buf.flip();
            while(buf.hasRemaining()){
                outChannel.write(buf);
            }
            buf.clear();
            bytesRead = inChannel.read(buf);
        }
        inputStream.close();
        outputStream.close();

        inChannel.close();
        outChannel.close();
    }
}
