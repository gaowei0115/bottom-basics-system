package com.mmc.socket.netty.base.buffer;

import java.nio.ByteBuffer;

/**
 * @packageName：com.mmc.socket.netty.base.buffer
 * @desrciption: 只读buffer，不能进行修改
 * @author: GW
 * @date： 2020/8/30 12:43
 * @history: (version) author date desc
 */
public class ReadOnlyBuffer {

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);

        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put((byte) i);
        }

        ByteBuffer readBuffer = buffer.asReadOnlyBuffer();
        for (int i = 0; i < buffer.capacity(); i++) {
            byte b = buffer.get(i);
            b *= 10;
            buffer.put(i, b);
        }

        readBuffer.position(0);
        readBuffer.limit(buffer.capacity());
        while (readBuffer.remaining() > 0) {
            System.out.println(readBuffer.get());
        }
    }
}
