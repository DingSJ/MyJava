package com.china.nio.channel;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class ServerTest {
    public static void main(String[] args) throws IOException {

        ServerSocketChannel channel = ServerSocketChannel.open();

        channel.socket().bind(new InetSocketAddress("localhost", 22222));

        while (true) {
            SocketChannel channel1 = channel.accept();
            if (channel1.isConnected()) {
                System.out.println("新连接加入：" + channel1.getRemoteAddress());
                ByteBuffer buffer = ByteBuffer.allocate(128);
                buffer.put("欢迎加入连接".getBytes());

                // 注意处理数据position
                buffer.flip();

                channel1.write(buffer);
            }
        }
    }
}
