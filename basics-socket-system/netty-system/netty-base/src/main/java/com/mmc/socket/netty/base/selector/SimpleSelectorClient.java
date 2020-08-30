package com.mmc.socket.netty.base.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @packageName：com.mmc.socket.netty.base.selector
 * @desrciption:
 * @author: GW
 * @date： 2020/8/30 15:38
 * @history: (version) author date desc
 */
public class SimpleSelectorClient {

    public static void main(String[] args) throws IOException, InterruptedException {
        SocketChannel sc = SocketChannel.open();
        sc.connect(new InetSocketAddress(9091));
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        String message = "selector 多路复用器";
        buffer.put(message.getBytes());
        buffer.flip();
        sc.write(buffer);
        buffer.clear();
        sc.read(buffer);
        System.out.println("read >> " + new String(buffer.array()));

        Thread.sleep(10000);
        sc.close();
    }
}
