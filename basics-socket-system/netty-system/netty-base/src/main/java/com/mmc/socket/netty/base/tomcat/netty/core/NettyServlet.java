package com.mmc.socket.netty.base.tomcat.netty.core;

import com.mmc.socket.netty.base.tomcat.BaseServlet;
import com.mmc.socket.netty.base.tomcat.Request;
import com.mmc.socket.netty.base.tomcat.Response;
import com.mmc.socket.netty.base.tomcat.io.core.IoRequest;
import com.mmc.socket.netty.base.tomcat.io.core.IoResponse;

/**
 * @packageName：com.mmc.socket.netty.base.tomcat.netty.core
 * @desrciption: 基于Netty实现的Servlet基类
 * @author: GW
 * @date： 2020/9/1 21:49
 * @history: (version) author date desc
 */
public abstract class NettyServlet extends BaseServlet {

    @Override
    protected void doPost(Request request, Response response) throws Exception {
        this.doGet(request, response);
    }
}
