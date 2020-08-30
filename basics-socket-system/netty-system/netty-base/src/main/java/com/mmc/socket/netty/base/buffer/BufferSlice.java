package com.mmc.socket.netty.base.buffer;

import java.nio.ByteBuffer;

/**
 * @packageName：com.mmc.socket.netty.base.buffer
 * @desrciption: 缓冲区切分子缓冲区，共享缓冲区内容，只是在原缓冲区基础上切分一小段
 * @author: GW
 * @date： 2020/8/30 12:11
 * @history: (version) author date desc
 */
public class BufferSlice {

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        for (int i = 0; i < 10; i++) {
            buffer.put((byte)i);
        }

        // 构建子缓冲区
        buffer.position(3);
        buffer.limit(7);

        ByteBuffer sliceByte = buffer.slice();

        for (int i = 0; i < sliceByte.capacity(); i++) {
            byte b = sliceByte.get();
            b *= 20;
            sliceByte.put(i, b);
        }

        buffer.position(0);
        buffer.limit(buffer.capacity());
        while (buffer.hasRemaining()) {
            System.out.println(buffer.get());
        }
    }
}
