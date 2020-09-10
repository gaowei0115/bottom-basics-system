package com.mmc.concurrent.thread.vola;

/**
 * @packageName：com.mmc.concurrent.thread.vola
 * @desrciption: 双重检验锁实现懒加载模式
 * @author: GW
 * @date： 2020-09-10 10:48
 * @history: (version) author date desc
 */
public class LazyInitInstance {

    private static LazyInitInstance instance;

    private final int i;
    private int j;

    private LazyInitInstance(int i) {
        this.i = i;
        j = i;
    }

    public static LazyInitInstance getInstance() {
        if (instance == null) {
            synchronized (LazyInitInstance.class) {
                if (instance == null) {
                    instance = new LazyInitInstance(10);
                }
            }
        }
        return instance;
    }
}
