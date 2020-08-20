package com.mmc.concurrent.thread;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * @packageName：com.mmc.concurrent.thread
 * @desrciption: 使用JMX来查看一个普通的Java程序包包含哪些线程
 * @author: GW
 * @date： 2020/8/20 22:31
 * @history: (version) author date desc
 */
public class MultiThread {

    public static void main(String[] args) {
        // 获取java线程管理MXBean
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        // 获取线程和线程堆栈信息，不需要获取同步的monitor和synchronizer信息
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);

        // 遍历打印信息，线程ID和线程名称
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println("[" + threadInfo.getThreadId() + "]" + threadInfo.getThreadName());
        }

        /**
         * 从运行结果看，一个Java程序的运行不仅仅是main()方法的运行，而是main线程和多个线程的同时运行。
         */
    }
}
