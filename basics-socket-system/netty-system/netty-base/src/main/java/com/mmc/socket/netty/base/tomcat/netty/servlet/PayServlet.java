package com.mmc.socket.netty.base.tomcat.netty.servlet;

import com.mmc.socket.netty.base.tomcat.Request;
import com.mmc.socket.netty.base.tomcat.Response;
import com.mmc.socket.netty.base.tomcat.netty.core.NettyServlet;

/**
 * @packageName：com.mmc.socket.netty.base.tomcat.netty.servlet
 * @desrciption:
 * @author: GW
 * @date： 2020/9/1 22:21
 * @history: (version) author date desc
 */
public class PayServlet extends NettyServlet {

    @Override
    protected void doGet(Request request, Response response) throws Exception {
        System.out.println("处理pay请求....");
        response.write("process pay success");
    }
}
