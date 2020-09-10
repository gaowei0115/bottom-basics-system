package com.mmc.concurrent.thread.vola;

/**
 * @packageName：com.mmc.concurrent.thread.vola
 * @desrciption: 基于类的初始化实现懒加载模式
 * @author: GW
 * @date： 2020-09-10 10:48
 * @history: (version) author date desc
 */
public class LazyInitClassInstance {

    private static volatile LazyInitClassInstance instance;

    private final int i;
    private int j;

    private LazyInitClassInstance(int i) {
        this.i = i;
        j = i;
    }

    public static LazyInitClassInstance getInstance() {
        return InstanceHolder.initClassInstance;
    }

    private static class InstanceHolder{
        public static LazyInitClassInstance initClassInstance = new LazyInitClassInstance(11);
    }
}
