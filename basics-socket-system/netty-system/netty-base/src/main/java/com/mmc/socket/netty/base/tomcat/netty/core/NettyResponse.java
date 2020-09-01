package com.mmc.socket.netty.base.tomcat.netty.core;

import com.mmc.socket.netty.base.tomcat.Response;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;

import java.nio.charset.Charset;

/**
 * @packageName：com.mmc.socket.netty.base.tomcat.netty.core
 * @desrciption: 模拟实现Netty response对象
 * @author: GW
 * @date： 2020/9/1 21:42
 * @history: (version) author date desc
 */
public class NettyResponse implements Response {

    private ChannelHandlerContext ctx;
    private HttpRequest request;

    public NettyResponse(ChannelHandlerContext ctx, HttpRequest request) {
        this.ctx = ctx;
        this.request = request;
    }

    @Override
    public void write(String content) {
        try {
            if (content == null || content.length() == 0) {
                return;
            }
            // 设置http及请求头信息
            // 设置Http版本1.1
            // 设置Http 状态码 200
            // 将输出内容编码格式设置UTF-8
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK,
                    Unpooled.wrappedBuffer(content.getBytes(Charset.forName("UTF-8"))));

            response.headers().set("Content-Type", "text/html");
            ctx.write(response);
        } finally {
            ctx.flush();
            ctx.close();
        }
    }
}
