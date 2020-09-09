package com.mmc.concurrent.thread.sync;

/**
 * @packageName：com.mmc.concurrent.thread.sync
 * @desrciption: synchronized 同步
 * @author: GW
 * @date： 2020-09-09 15:11
 * @history: (version) author date desc
 */
public class SynchronizedTest {

    public synchronized void test() {
        System.out.println("你好");
    }


    public void test1() {
        synchronized (Object.class) {
            System.out.println("hello");
        }
    }


}
