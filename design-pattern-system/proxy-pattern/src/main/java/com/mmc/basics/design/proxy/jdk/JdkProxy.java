package com.mmc.basics.design.proxy.jdk;

import java.lang.reflect.Proxy;

/**
 * @packageName：com.mmc.basics.design.proxy.jdk
 * @desrciption:
 * @author: GW
 * @date： 2020-09-11 5:51
 * @history: (version) author date desc
 */
public class JdkProxy {

    public static JdkProxyService getProxy() {
        JdkProxyService jdkProxyService = new JdkProxyServiceImpl();
//        return Proxy.newProxyInstance(jdkProxyService.getClass().getClassLoader(),)

        return null;
    }
}
