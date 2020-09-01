package com.mmc.socket.netty.base.tomcat.netty.servlet;

import com.mmc.socket.netty.base.tomcat.Request;
import com.mmc.socket.netty.base.tomcat.Response;
import com.mmc.socket.netty.base.tomcat.netty.core.NettyServlet;

/**
 * @packageName：com.mmc.socket.netty.base.tomcat.netty.servlet
 * @desrciption:
 * @author: GW
 * @date： 2020/9/1 22:23
 * @history: (version) author date desc
 */
public class TradeServlet extends NettyServlet {
    @Override
    protected void doGet(Request request, Response response) throws Exception {
        System.out.println("处理Trade请求...");
        response.write("process trade success");
    }
}
