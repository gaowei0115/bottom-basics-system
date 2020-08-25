package com.mmc.concurrent.thread;

import com.mmc.concurrent.thread.utils.TimeUtils;

/**
 * @packageName：com.mmc.concurrent.thread
 * @desrciption: 设置daemon属性线程为守护线程，是一种支持类型线程，主要工作被用作程序后台调度以及支持工作。
 *              当一个java虚拟机不存在非daemon线程的时候，Java虚拟机将会退出。
 *              通过Thread.setDaemon(true)将线程设置为daemon线程。
 *
 *              daemon 类型线程会随着虚拟机执行退出而退出
 * @author: GW
 * @date： 2020/8/21 22:58
 * @history: (version) author date desc
 */
public class DaemonThread {

    public static void main(String[] args) {
        Thread thread = new Thread(new DaemonRunner(), "daemon-thread");
        thread.setDaemon(true);
        thread.start();
    }

    static class DaemonRunner implements Runnable {
        @Override
        public void run() {
            try {
                TimeUtils.sleepSecond(100);
            } finally {
                System.out.println("DaemonThread finally run.");
            }
        }
    }
}
