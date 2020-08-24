package com.mmc.jvm.cpu.cache;

/**
 * @packageName：com.mmc.jvm.cpu.cache
 * @desrciption: cache line 缓存一致性
 *              通过添加占位符扩充 cpu三级缓存一次加载字节 64个字节
 * @author: GW
 * @date： 2020-08-24 15:53
 * @history: (version) author date desc
 */
public class CacheLinePadding02 {

    private static class Placeholder {
        public volatile long point;
        public volatile long p1, p2, p3, p4, p5, p6, p7;
    }

    public static Placeholder arr[] = new Placeholder[2];

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
        }, "thread-01");

        Thread t2 = new Thread(() -> {
            for (long i = 0; i < len; i++) {
                arr[1].point = i;
            }
        }, "thread-02");

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("cost time " + (System.currentTimeMillis() - startTime) + " ms");
    }
}
