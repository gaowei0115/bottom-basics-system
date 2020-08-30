package com.mmc.socket.netty.base.selector;

import io.netty.buffer.ByteBuf;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @packageName：com.mmc.socket.netty.base.selector
 * @desrciption: 简单Selector 注册事件
 * @author: GW
 * @date： 2020/8/30 13:28
 * @history: (version) author date desc
 */
public class SimpleSelector {

    // 注册器
    private Selector selector;
    private final int port;

    private ByteBuffer buffer;

    public SimpleSelector(int port) {
        this.port = port;
        this.buffer = ByteBuffer.allocate(2048);
    }

    public void run() {
        try {
            this.selector = getSelector();
            this.listener();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Selector getSelector() throws IOException {
        // 开启Selector 注册监听对象
        Selector selector = Selector.open();
        // 创建Socket server可用通道，并设置非阻塞模式
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);

        // 绑定特定端口 IP
        InetSocketAddress address = new InetSocketAddress(port);
        ssc.bind(address);

        // 向Selector注册accept事件
        ssc.register(selector, SelectionKey.OP_ACCEPT);

        return selector;
    }

    public void listener() throws IOException {
        System.out.println("listen port : " + port);
        try {
            while (true) {
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    iterator.remove();
                    process(key);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理selector key
     * @param key
     *          注册监听事件key
     */
    private void process(SelectionKey key) throws IOException {
        // 接收请求 - 一个连接
        if (key.isAcceptable()) {
            ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
            SocketChannel accept = ssc.accept();
            accept.configureBlocking(false);
            accept.register(selector, SelectionKey.OP_READ);
        } else if (key.isReadable()) {
            SocketChannel sc = (SocketChannel) key.channel();
            int readLen = sc.read(buffer);
            if (readLen > 0) {
                buffer.flip();
                String content = new String(buffer.array(), 0, readLen);
                System.out.println("server read content > " + content);
                SelectionKey register = sc.register(selector, SelectionKey.OP_WRITE);
                register.attach(content);
            } else {
                sc.close();
            }
            buffer.clear();
        } else if (key.isWritable()) {
            SocketChannel sc = (SocketChannel) key.channel();
            String content = (String) key.attachment();
            content = "ss writer " + content;
            System.out.println("写入content ： " + content);
            buffer.clear();
            buffer = ByteBuffer.wrap(content.getBytes());
            if (buffer != null) {
                sc.write(buffer);
            } else {
                sc.close();
            }
            buffer.clear();
        }
    }

    public static void main(String[] args) {
        SimpleSelector simpleSelector = new SimpleSelector(9091);
        simpleSelector.run();
    }
}
