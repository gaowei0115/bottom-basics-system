package com.mmc.concurrent.thread;

import com.mmc.concurrent.thread.utils.TimeUtils;

/**
 * @packageName：com.mmc.concurrent.thread
 * @desrciption: 安全终止线程
 *                  1. 设置线程interrupt标志
 *                  2. 线程任务中设置一个暂停标志
 *
 * @author: GW
 * @date： 2020/8/23 11:21
 * @history: (version) author date desc
 */
public class ShutDownThread {

    public static void main(String[] args) {
        Runner run1 = new Runner();
        Thread countThread = new Thread(run1, "count-thread01");
        countThread.start();

        TimeUtils.sleepSecond(1);
        countThread.interrupt();

        Runner run2 = new Runner();
        countThread = new Thread(run2, "count-thread02");
        countThread.start();

        TimeUtils.sleepSecond(1);
        run2.cancel();
    }

    static class Runner implements Runnable {

        private long i;
        private volatile boolean on = true;

        @Override
        public void run() {
            while (on && !Thread.currentThread().isInterrupted()) {
                i++;
            }

            System.out.println(Thread.currentThread().getName() + " > Count i = " + i);
        }

        public void cancel() {
            this.on = false;
        }
    }
}
