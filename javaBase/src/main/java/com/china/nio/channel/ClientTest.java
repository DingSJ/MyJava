package com.china.nio.channel;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

public class ClientTest {
    public static void main(String[] args) throws IOException {

        SocketChannel channel = SocketChannel.open();
        channel.connect(new InetSocketAddress("localhost", 22222));
        InputStream inputStream = channel.socket().getInputStream();
        int len = -1;
        byte[] buff = new byte[128];
        while ((len = inputStream.read(buff)) > 0) {
            System.out.println(new String(buff, 0, len));
        }
    }
}
