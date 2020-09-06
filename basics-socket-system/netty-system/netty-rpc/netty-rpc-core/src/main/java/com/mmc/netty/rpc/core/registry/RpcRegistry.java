package com.mmc.netty.rpc.core.registry;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolver;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @packageName：com.mmc.netty.rpc.core.registry
 * @desrciption: 服务注册中心
 * @author: GW
 * @date： 2020/9/4 23:20
 * @history: (version) author date desc
 */
public class RpcRegistry {

    private static Logger logger = LoggerFactory.getLogger(RpcRegistry.class);

    private final int port;

    public RpcRegistry(int port) {
        this.port = port;
    }

    public void start() {
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup work = new NioEventLoopGroup();
        try {
            ServerBootstrap boot = new ServerBootstrap();
            boot.group(boss, work).channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            /**
                             * 自定义解码器
                             * maxFrameLength
                             *      解码器支持最大数据帧
                             * lengthFieldOffset
                             *      长度偏移量，消息的位置
                             * lengthFieldLength
                             *      长度字段的长度（占用最大字节数）
                             * lengthAdjustment
                             *      补偿值
                             * initialBytesToStrip
                             *      解码帧中剥离的第一个字节
                             */
                            pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
                            // 自定义协议解码器
                            pipeline.addLast(new LengthFieldPrepender(4));
                            // 对象参数类型编码器
                            pipeline.addLast("encoder", new ObjectEncoder());
                            // 对象参数类型解码器
                            pipeline.addLast("decoder", new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.cacheDisabled(null)));

                            // 自定义注册解码器
                            pipeline.addLast(new RegistryHandler());
                        }
                    }).option(ChannelOption.SO_BACKLOG, 128)
            .childOption(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture future = boot.bind(port).sync();
            logger.info("auto rpc registry start listener port {}", port);
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            logger.error("启动注册中心服务异常", e);
        } finally {
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        RpcRegistry rpc = new RpcRegistry(9091);
        rpc.start();
    }
}
