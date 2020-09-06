package com.mmc.netty.rpc.core.consumer;

import java.lang.reflect.Proxy;

/**
 * @packageName：com.mmc.netty.rpc.core.consumer
 * @desrciption:
 * @author: GW
 * @date： 2020/9/6 22:06
 * @history: (version) author date desc
 */
public class RpcProxy {

    public static <T> T proxy(Class<?> clazz) {

        ConsumerInvokerHandler handler = new ConsumerInvokerHandler(clazz);
        Class<?>[] interfaces = clazz.isInterface() ? new Class<?>[]{clazz} : clazz.getInterfaces();
        T result = (T) Proxy.newProxyInstance(clazz.getClassLoader(), interfaces, handler);

        return result;
    }
}
