package com.mmc.jvm.coll.map;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @packageName：com.mmc.jvm.coll.map
 * @desrciption: HashMap多线程导致问题实例演示
 * @author: GW
 * @date： 2020-08-25 9:53
 * @history: (version) author date desc
 */
public class HashMapMultiThreadCause {


    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new HashMapRunner(), "hash-thread-01");
        Thread t2 = new Thread(new HashMapRunner(), "hash-thread-02");
        Thread t3 = new Thread(new HashMapRunner(), "hash-thread-03");
        Thread t4 = new Thread(new HashMapRunner(), "hash-thread-04");
        Thread t5 = new Thread(new HashMapRunner(), "hash-thread-05");
        Thread t6 = new Thread(new HashMapRunner(), "hash-thread-06");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
//        t1.join();
//        t2.join();
//        t3.join();
//        t4.join();
//        t5.join();
//        t6.join();
    }

    public static class HashMapRunner implements Runnable {

        /**
         * 设置初始长度，2的倍数
         * 索引下标 = hash(key) & (n-1)
         * 2的倍数更好散列，减少碰撞几率
         */
        static HashMap<Integer, Integer> map = new HashMap<>(2);

        static AtomicInteger atomicInteger = new AtomicInteger(1);

        @Override
        public void run() {
            while (atomicInteger.get() < 100000) {
                map.put(atomicInteger.get(), atomicInteger.get());
                atomicInteger.incrementAndGet();
            }
        }
    }
}
