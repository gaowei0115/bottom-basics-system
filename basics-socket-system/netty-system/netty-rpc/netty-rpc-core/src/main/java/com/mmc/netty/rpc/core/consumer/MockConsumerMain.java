package com.mmc.netty.rpc.core.consumer;

import com.mmc.netty.rpc.core.api.IHelloRpcService;
import com.mmc.netty.rpc.core.api.IRpcMathService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @packageName：com.mmc.netty.rpc.core.consumer
 * @desrciption:
 * @author: GW
 * @date： 2020/9/6 22:35
 * @history: (version) author date desc
 */
public class MockConsumerMain {

    private static final Logger logger = LoggerFactory.getLogger(MockConsumerMain.class);

    public static void main(String[] args) {

        IHelloRpcService helloRpcService = RpcProxy.proxy(IHelloRpcService.class);
        String result = helloRpcService.hello("netty rpc");
        logger.info("helloRpcService.hello(string) result {}", result);

        logger.info("--------------------------------------------------------");

        IRpcMathService mathService = RpcProxy.proxy(IRpcMathService.class);

        int add = mathService.add(12, 34);
        logger.info("match add {}", add);


        int multi = mathService.multi(12, 34);
        logger.info("match multi {}", multi);


        int sub = mathService.sub(12, 34);
        logger.info("match sub {}", sub);


        int div = mathService.div(84, 21);
        logger.info("match div {}", div);
    }
}
