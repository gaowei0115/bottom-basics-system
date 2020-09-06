package com.mmc.netty.rpc.core.api;

/**
 * @packageName：com.mmc.netty.rpc.core.api
 * @desrciption:
 * @author: GW
 * @date： 2020/9/4 23:08
 * @history: (version) author date desc
 */
public interface IHelloRpcService {

    /**
     * hello方法
     * @param message
     *      发送消息
     * @return
     *      返回
     */
    String hello(String message);
}
