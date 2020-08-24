package com.mmc.concurrent.thread;

import com.mmc.concurrent.thread.utils.TimeUtils;

/**
 * @packageName：com.mmc.concurrent.thread
 * @desrciption: 线程中断 interrupt
 * @author: GW
 * @date： 2020/8/22 23:26
 * @history: (version) author date desc
 */
public class Interrupted {

    public static void main(String[] args) {
        // sleepThread 不停尝试睡眠
        Thread sleepThread = new Thread(new SleepRunner(), "sleep-thread");
        sleepThread.setDaemon(true);
        // busyThread 不停的运行
        Thread busyThread = new Thread(new BusyRunner(), "busy-thread");
        busyThread.setDaemon(true);

        sleepThread.start();
        busyThread.start();

        TimeUtils.sleepSecond(5);
        sleepThread.interrupt();;
        busyThread.interrupt();

        System.out.println("Sleep Thread in interrupt is " + sleepThread.isInterrupted());

        System.out.println("Busy Thread in interrupt is " + busyThread.isInterrupted());

        TimeUtils.sleepSecond(2);
    }

    static class SleepRunner implements Runnable {

        @Override
        public void run() {
            while (true) {
                TimeUtils.sleepSecond(10);
            }
        }
    }

    static class BusyRunner implements Runnable {

        @Override
        public void run() {
            while (true) {

            }

        }
    }
}
