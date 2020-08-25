package com.mmc.concurrent.thread;

import com.mmc.concurrent.thread.utils.TimeUtils;

/**
 * @packageName：com.mmc.concurrent.thread
 * @desrciption: 线程状态：new、running、block、wait、time_wait、stop
 * @author: GW
 * @date： 2020/8/21 22:40
 * @history: (version) author date desc
 */
public class ThreadState {

    public static void main(String[] args) {
        new Thread(new TimeWaitJob(), "time-wait-thread").start();

        new Thread(new WaitJob(), "wait-thread").start();

        // 模拟两个阻塞线程，一个获取到锁执行等待，一个获取锁等待
        new Thread(new BlockJob(), "block-thread-01").start();

        new Thread(new BlockJob(), "block-thread-02").start();
    }

    /**
     * 模拟超时等待job
     */
    static class TimeWaitJob implements Runnable {
        @Override
        public void run() {
            while (true) {
                TimeUtils.sleepSecond(100);
            }
        }
    }

    /**
     * 等待job
     */
    static class WaitJob implements Runnable {

        @Override
        public void run() {
            while (true) {
                synchronized (WaitJob.class) {
                    try {
                        WaitJob.class.wait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    /**
     * 阻塞job
     */
    static class BlockJob implements Runnable {

        @Override
        public void run() {
            synchronized (BlockJob.class) {
                while (true) {
                    TimeUtils.sleepSecond(100);
                }
            }
        }
    }
}
