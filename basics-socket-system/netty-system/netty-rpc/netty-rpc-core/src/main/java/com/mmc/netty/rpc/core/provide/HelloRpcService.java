package com.mmc.netty.rpc.core.provide;

import com.mmc.netty.rpc.core.api.IHelloRpcService;

/**
 * @packageName：com.mmc.netty.rpc.core.provide
 * @desrciption:
 * @author: GW
 * @date： 2020/9/4 23:17
 * @history: (version) author date desc
 */
public class HelloRpcService implements IHelloRpcService {
    @Override
    public String hello(String message) {
        return "RPC ->> " + message;
    }
}
