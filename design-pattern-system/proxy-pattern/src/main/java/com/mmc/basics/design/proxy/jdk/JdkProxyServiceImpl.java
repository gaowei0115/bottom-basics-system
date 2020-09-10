package com.mmc.basics.design.proxy.jdk;

/**
 * @packageName：com.mmc.basics.design.proxy.jdk
 * @desrciption:
 * @author: GW
 * @date： 2020-09-11 5:49
 * @history: (version) author date desc
 */
public class JdkProxyServiceImpl implements JdkProxyService {
    @Override
    public void message() {
        System.out.println("hello jdk proxy");
    }
}
