package com.mmc.concurrent.thread.vola;

/**
 * @packageName：com.mmc.concurrent.thread.vola
 * @desrciption: 双重检验锁+ volatile实现懒加载模式
 * @author: GW
 * @date： 2020-09-10 10:48
 * @history: (version) author date desc
 */
public class LazyInitVolatileInstance {

    private static volatile LazyInitVolatileInstance instance;

    private final int i;
    private int j;

    private LazyInitVolatileInstance(int i) {
        this.i = i;
        j = i;
    }

    public static LazyInitVolatileInstance getInstance() {
        if (instance == null) {
            synchronized (LazyInitVolatileInstance.class) {
                if (instance == null) {
                    instance = new LazyInitVolatileInstance(10);
                }
            }
        }
        return instance;
    }
}
