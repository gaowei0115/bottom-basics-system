package com.mmc.netty.rpc.core.consumer;

import com.mmc.netty.rpc.core.protocol.AutoProtocol;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @packageName：com.mmc.netty.rpc.core.consumer
 * @desrciption:
 * @author: GW
 * @date： 2020/9/6 22:07
 * @history: (version) author date desc
 */
public class ConsumerInvokerHandler implements InvocationHandler {

    private static Logger logger = LoggerFactory.getLogger(ConsumerInvokerHandler.class);

    private Class<?> clazz;

    public ConsumerInvokerHandler(Class clazz) {
        this.clazz = clazz;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            logger.info("参数验证结果 {} ", Object.class.equals(method.getDeclaringClass()));
            if (Object.class.equals(method.getDeclaringClass())) {
                return method.invoke(this, args);
            } else {
                return rpcInvoker(proxy, method, args);
            }
        } catch (Exception e) {
            logger.error("代理执行异常", e);
        }
        return null;
    }

    private Object rpcInvoker(Object proxy, Method method, Object[] args) {
        AutoProtocol protocol = new AutoProtocol();
        protocol.setClassName(this.clazz.getName());
        protocol.setMethodName(method.getName());
        protocol.setParams(args);
        protocol.setParamTypes(method.getParameterTypes());

        EventLoopGroup boss = new NioEventLoopGroup();

        final RpcProxyHandler handler = new RpcProxyHandler();
        try {
            Bootstrap boot = new Bootstrap();
            boot.group(boss).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));

                            pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));

                            pipeline.addLast("encoder", new ObjectEncoder());
                            pipeline.addLast("decoder", new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.cacheDisabled(null)));

                            pipeline.addLast("handler", handler);
                        }
                    });

            ChannelFuture fu = boot.connect("localhost", 9091).sync();
            fu.channel().writeAndFlush(protocol).sync();
            fu.channel().closeFuture().sync();
        } catch (Exception e) {
            logger.error("consumer exception", e);
        } finally {
            boss.shutdownGracefully();
        }

        return handler.getResponse();
    }

    class RpcProxyHandler extends ChannelInboundHandlerAdapter {
        private Object response;

        public Object getResponse() {
            return response;
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            response = msg;
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            logger.error("consumer invoker exception " , cause);
        }
    }
}
