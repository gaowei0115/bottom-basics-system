package com.mmc.socket.netty.base.buffer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @packageName：com.mmc.socket.netty.base.buffer
 * @desrciption: 内存映射缓冲区，
 * @author: GW
 * @date： 2020/8/30 13:20
 * @history: (version) author date desc
 */
public class MappedBuffer {

    private static final int START = 0;
    private static final int SIZE = 1024;

    public static void main(String[] args) throws IOException {
        RandomAccessFile raf = new RandomAccessFile(MappedBuffer.class.getResource("/").getPath() + "writer.txt", "rw");

        FileChannel fileChannel = raf.getChannel();

        MappedByteBuffer map = fileChannel.map(FileChannel.MapMode.READ_WRITE, START, SIZE);
        map.put(0, (byte)97);
        map.put(1023, (byte)122);

        raf.close();
    }
}
