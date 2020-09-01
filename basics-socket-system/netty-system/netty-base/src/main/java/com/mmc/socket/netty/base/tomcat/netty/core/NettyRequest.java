package com.mmc.socket.netty.base.tomcat.netty.core;

import com.mmc.socket.netty.base.tomcat.Request;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @packageName：com.mmc.socket.netty.base.tomcat.netty.core
 * @desrciption: 模拟nettyRequest对象
 * @author: GW
 * @date： 2020/9/1 21:03
 * @history: (version) author date desc
 */
public class NettyRequest implements Request {

    private static final long serialVersionUID = -4597693825393810557L;

    /**
     * 通道控制上下文
     */
    private ChannelHandlerContext ctx;

    private HttpRequest request;

    public NettyRequest(ChannelHandlerContext ctx, HttpRequest request) {
        this.ctx = ctx;
        this.request = request;
    }

    @Override
    public String getMethod() {
        return this.request.method().name();
    }

    @Override
    public String getUrl() {
        return this.request.uri();
    }

    public Map<String, List<String>> getParameters() {
        QueryStringDecoder decoder = new QueryStringDecoder(request.uri());
        return decoder.parameters();
    }

    public String getParameter(String name) {
        Map<String, List<String>> map = getParameters();
        List<String> list = map.get(name);
        if (list == null) {
            return null;
        } else {
            return list.get(0);
        }
    }
}
