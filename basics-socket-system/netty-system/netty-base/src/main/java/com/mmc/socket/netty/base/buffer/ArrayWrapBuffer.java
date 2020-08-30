package com.mmc.socket.netty.base.buffer;

import java.nio.Buffer;
import java.nio.ByteBuffer;

/**
 * @packageName：com.mmc.socket.netty.base.buffer
 * @desrciption: 数组包装成Buffer对象
 * @author: GW
 * @date： 2020/8/30 10:56
 * @history: (version) author date desc
 */
public class ArrayWrapBuffer {

    public static void main(String[] args) {
        // 定义byte数组，并赋值
        byte[] array = new byte[10];
        array[0] = 1;

        Buffer buffer = ByteBuffer.wrap(array);
        System.out.println("before:::");
        System.out.println(buffer.capacity());
        System.out.println(buffer.position());
        System.out.println(buffer.limit());

        array[0] = 4;

        System.out.println("---------------------------------");
        System.out.println("after:::");
        System.out.println(buffer.capacity());
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
    }
}
