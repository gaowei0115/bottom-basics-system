package com.mmc.socket.netty.base.buffer;

import java.nio.IntBuffer;

/**
 * @packageName：com.mmc.socket.netty.base.io
 * @desrciption: Java NIO Buffer
 *              以IntBuffer示例展示
 * @author: GW
 * @date： 2020/8/28 22:47
 * @history: (version) author date desc
 */
public class IntBufferDemo  {

    public static void main(String[] args) {
        // 分配新的int缓冲区，并设定缓冲区容量
        // 新增缓冲区的当前位置为0，其限制位置为缓冲区容量，底层数组实现，数组偏移量为0
        IntBuffer buffer = IntBuffer.allocate(8);

        for (int i = 0; i < buffer.capacity(); i++) {
            // 将给定的整数写入缓冲区当前位置，当前位置递增
            buffer.put((i + 1) << 1);
        }
        // 反转缓冲区（重置缓冲区），缓冲区限制位置（limit）为当前位置，设置缓冲区当前位置为0
        buffer.flip();
        // 循环缓冲区
        while (buffer.hasRemaining()) {
            // 调用get方法，缓冲区当前位置递增
            System.out.print(buffer.get() + " ");
        }
    }
}
