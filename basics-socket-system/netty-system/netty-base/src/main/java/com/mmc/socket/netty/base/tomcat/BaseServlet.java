package com.mmc.socket.netty.base.tomcat;

import com.mmc.socket.netty.base.tomcat.HttpMethod;
import com.mmc.socket.netty.base.tomcat.io.core.IoRequest;
import com.mmc.socket.netty.base.tomcat.io.core.IoResponse;

/**
 * @packageName：com.mmc.socket.netty.base.tomcat.io.core
 * @desrciption: 抽象基础servlet
 * @author: GW
 * @date： 2020/8/31 23:04
 * @history: (version) author date desc
 */
public abstract class BaseServlet {

    public void service(IoRequest request, IoResponse response) throws Exception {
        // service 根据请求方法（get, post） 选择调用doGet or doPost
        if (HttpMethod.HTTP_GET.getMethod().equals(request.getMethod())) {
            doGet(request, response);
        } else if (HttpMethod.HTTP_POST.getMethod().equals(request.getMethod())) {
            doPost(request, response);
        }
    }

    /**
     * post请求处理
     * @param request
     *          请求对象
     * @param response
     *          响应对象
     */
    protected abstract void doPost(IoRequest request, IoResponse response) throws Exception;

    /**
     * get请求处理
     * @param request
     *      请求对象
     * @param response
     *      响应对象
     */
    protected abstract void doGet(IoRequest request, IoResponse response) throws Exception;

}
