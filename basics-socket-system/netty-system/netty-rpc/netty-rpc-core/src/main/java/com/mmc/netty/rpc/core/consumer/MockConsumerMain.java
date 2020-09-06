package com.mmc.netty.rpc.core.consumer;

import com.mmc.netty.rpc.core.api.IHelloRpcService;

/**
 * @packageName：com.mmc.netty.rpc.core.consumer
 * @desrciption:
 * @author: GW
 * @date： 2020/9/6 22:35
 * @history: (version) author date desc
 */
public class MockConsumerMain {

    public static void main(String[] args) {

        IHelloRpcService helloRpcService = RpcProxy.proxy(IHelloRpcService.class);
        helloRpcService.hello("netty rpc");
    }
}
