package com.mmc.socket.netty.base.buffer;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @packageName：com.mmc.socket.netty.base.io
 * @desrciption: Buffer 中 position、limit、capacity变化验证
 *          position：当前buffer缓冲区下一个写入或者读取的索引位置
 *              值会随着get或put方法变化。
 *          limit：指定有多少数据需要取出（从缓冲区写入通道时），或者有多少数据需要写入（从通道读取写入缓冲区时）
 *          capacity：缓冲区能承载的最大数据量
 * @author: GW
 * @date： 2020/8/28 23:03
 * @history: (version) author date desc
 */
public class BufferPositionChange {

    public static void main(String[] args) throws IOException {
        System.out.println(BufferPositionChange.class.getResource("/").getPath());
        // 文件路径
        String filePath = BufferPositionChange.class.getResource("/").getPath() + "bufferPositionChange.txt";
        // 文件流IO
        FileInputStream fis = new FileInputStream(filePath);
        // 文件流操作通道
        FileChannel channel = fis.getChannel();
        // 创建ByteBuffer缓冲区，创建10byte字节大小数组
        ByteBuffer buffer = ByteBuffer.allocate(10);

        output("初始化", buffer);

        // 读取数据
        channel.read(buffer);

        output("change.read", buffer);

        // 准备操作数据，先反转游标位置，设置limit
        buffer.flip();

        output("buffer.flip", buffer);

        while (buffer.hasRemaining()) {
            buffer.get();
        }

        output("buffer.get", buffer);

        buffer.clear();

        output("buffer.clear", buffer);

        fis.close();


    }

    private static void output(String remark, Buffer buffer) {
        System.out.print(remark + ":");
        // 容量大小
        System.out.print("capacity: " + buffer.capacity() + ",");
        // 当前操作数据所在位置，或者buffer游标位置
        System.out.print("position: " + buffer.position());
        // 数据操作限制范围limit
        System.out.print("limit: " + buffer.limit());
        System.out.println();
    }
}
