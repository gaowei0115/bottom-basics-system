package com.mmc.socket.netty.base.tomcat.io.servlet;

import com.mmc.socket.netty.base.tomcat.io.core.IoRequest;
import com.mmc.socket.netty.base.tomcat.io.core.IoResponse;
import com.mmc.socket.netty.base.tomcat.io.core.IoServlet;

/**
 * @packageName：com.mmc.socket.netty.base.tomcat.io.servlet
 * @desrciption: 第一个IO Servlet实现类
 * @author: GW
 * @date： 2020/8/31 23:14
 * @history: (version) author date desc
 */
public class FirstIoServlet extends IoServlet {
    @Override
    protected void doPost(IoRequest request, IoResponse response) throws Exception {
        response.write("This is the io first servlet");
    }

    @Override
    protected void doGet(IoRequest request, IoResponse response) throws Exception {
        this.doPost(request, response);
    }
}
