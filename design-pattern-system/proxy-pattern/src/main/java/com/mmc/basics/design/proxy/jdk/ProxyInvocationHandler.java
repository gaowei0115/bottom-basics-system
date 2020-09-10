package com.mmc.basics.design.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @packageName：com.mmc.basics.design.proxy.jdk
 * @desrciption:
 * @author: GW
 * @date： 2020-09-11 5:12
 * @history: (version) author date desc
 */
public class ProxyInvocationHandler implements InvocationHandler {


    private final JdkProxyService jdkProxyService;

    public ProxyInvocationHandler(JdkProxyService jdkProxyService) {
        this.jdkProxyService = jdkProxyService;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("process proxy invocation");
        return method.invoke(jdkProxyService, args);
    }
}
