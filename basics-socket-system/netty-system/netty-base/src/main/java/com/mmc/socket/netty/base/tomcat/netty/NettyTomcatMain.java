package com.mmc.socket.netty.base.tomcat.netty;

import com.mmc.socket.netty.base.tomcat.netty.core.NettyRequest;
import com.mmc.socket.netty.base.tomcat.netty.core.NettyResponse;
import com.mmc.socket.netty.base.tomcat.netty.core.NettyServlet;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @packageName：com.mmc.socket.netty.base.tomcat.netty
 * @desrciption: 基于Netty模拟Tomcat服务启动类
 * @author: GW
 * @date： 2020/9/1 21:50
 * @history: (version) author date desc
 */
public class NettyTomcatMain {

    //netty就是一个同时支持多协议的网络编程框架

    private final int port = 9091;
    private static final String DEFAULT_CONFIG = "web-netty.properties";
    private static final String URL_FIX = ".url";
    private static final String CLASS_NAME_FIX = ".className";
    private ConcurrentMap<String, NettyServlet> proMap = new ConcurrentHashMap<>(10);

    private Properties properties = new Properties();

    public void init() {
        // 加载服务启动配置文件
        try {
            String basePath = this.getClass().getResource("").getPath();
            FileInputStream fis = new FileInputStream(basePath + DEFAULT_CONFIG);
            properties.load(fis);
            Set<Object> proSet = properties.keySet();
            proSet.forEach(k -> {
                String key = k.toString();
                if (key.endsWith(URL_FIX)) {
                    String basePre = key.substring(0, key.lastIndexOf(URL_FIX));
                    String url = properties.getProperty(key);
                    String className = properties.getProperty(basePre + CLASS_NAME_FIX);
                    try {
                        NettyServlet servlet = (NettyServlet) Class.forName(className).newInstance();
                        proMap.put(url, servlet);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            System.out.println("netty tomcat 服务初始化异常 cause : " + e.getMessage());
        }
    }

    public void start() {
        // 初始化服务启动参数
        init();
        // netty 封装NIO reactor模型， Boss，Worker
        // Boss线程
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        // Worker线程
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            // 创建服务启动对象
            ServerBootstrap bootstrap = new ServerBootstrap();

            // 设置服务启动参数
            // 链路式初始化参数
            bootstrap.group(bossGroup, workerGroup)
                    // 主线程处理
                    .channel(NioServerSocketChannel.class)
                    // 子线程处理
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        // 客户端连接Socket处理初始化
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            // 无锁串行话编程
                            // Netty对Http的封装，对顺序有要求
                            // HttpResponseEncoder编码器
                            // 责任链模式，双向链表InBound,OutBound
                            socketChannel.pipeline().addLast(new HttpResponseEncoder());
                            // HttpRequestDecoder解码器
                            socketChannel.pipeline().addLast(new HttpRequestDecoder());

                            // 自定义业务处理
                            socketChannel.pipeline().addLast(new NettyTomcatHandler());
                        }
                    }).option(ChannelOption.SO_BACKLOG, 128)
                    // 配置子线程保持长连接
            .childOption(ChannelOption.SO_KEEPALIVE, true);

            // 启动服务
            ChannelFuture future = bootstrap.bind(port).sync();
            System.out.printf("Netty Tomcat Server Start in port :" + port);
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            System.out.println("Netty Tomcat Server start 异常 cause : " + e.getMessage());
        } finally {
            // 关闭连接，释放资源
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    /**
     * 自定义业务处理handler
     */
    class NettyTomcatHandler extends ChannelInboundHandlerAdapter {

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            if (msg instanceof HttpRequest) {
                System.out.println("处理客户端请求....");
                HttpRequest request = (HttpRequest) msg;
                // 将接收HttpRequest包装给自定义Request对象
                NettyRequest nettyRequest = new NettyRequest(ctx, request);
                // 定义出Response对象
                NettyResponse nettyResponse = new NettyResponse(ctx, request);

                // 获取请求url
                String url = nettyRequest.getUrl();
                if (proMap.containsKey(url)) {
                    proMap.get(url).service(nettyRequest, nettyResponse);
                } else {
                    nettyResponse.write("404 - Not Found");
                }
            }
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            super.exceptionCaught(ctx, cause);
        }
    }

    public static void main(String[] args) {
        NettyTomcatMain main = new NettyTomcatMain();
        main.start();
    }
}
