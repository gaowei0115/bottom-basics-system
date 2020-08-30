package com.mmc.socket.netty.base.buffer;

import io.netty.buffer.ByteBuf;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;

/**
 * @packageName：com.mmc.socket.netty.base.buffer
 * @desrciption: 直接缓冲区， 读取数据直接从内核缓冲区中读取，避免从中间缓冲区过渡数据
 * @author: GW
 * @date： 2020/8/30 13:09
 * @history: (version) author date desc
 */
public class DirectBuffer {

    public static void main(String[] args) throws IOException {
        String filePath = DirectBuffer.class.getResource("/").getPath() + "bufferPositionChange.txt";

        FileInputStream fis = new FileInputStream(filePath);
        FileChannel channel = fis.getChannel();

        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

        FileOutputStream fos = new FileOutputStream(DirectBuffer.class.getResource("/").getPath() + "writer.txt");
        FileChannel outC = fos.getChannel();

        while (true){
            buffer.clear();
            int r = channel.read(buffer);
            System.out.println(r);
            while (r == -1) {
                break;
            }

            buffer.flip();
            outC.write(buffer);
        }
    }
}
