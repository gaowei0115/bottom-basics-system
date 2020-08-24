package com.mmc.jvm.cpu.cache;

/**
 * @packageName：com.mmc.jvm.cpu.cache
 * @desrciption: cache line 缓存一致性
 *
 * @author: GW
 * @date： 2020-08-24 15:45
 * @history: (version) author date desc
 */
public class CacheLinePadding01 {

    /**
     * 占位符类
     * 声明一个long类型 volatile修饰
     */
    private static class Placeholder {
        public volatile long point = 0L;
    }

    public static Placeholder[] arr = new Placeholder[2];

    static {
        arr[0] = new Placeholder();
        arr[1] = new Placeholder();
    }

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        long len = 1000_0000L;
        Thread t1 = new Thread(() -> {
           for (long i = 0; i < len; i++) {
               arr[0].point = i;
           }
        });

        Thread t2 = new Thread(() -> {
            for (long i = 0; i < len; i++) {
                arr[1].point = i;
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("cost time " + (System.currentTimeMillis() - startTime) + " ms");
    }
}
