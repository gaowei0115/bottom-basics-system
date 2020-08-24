package com.mmc.concurrent.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @packageName：com.mmc.concurrent.thread
 * @desrciption: 线程优先级
 * @author: GW
 * @date： 2020/8/21 22:25
 * @history: (version) author date desc
 */
public class Priority {

    private static volatile boolean noStart = true;

    private static volatile boolean noEnd = true;

    /**
     * 本次实验测试结果来看，线程设置优先级并没有生效，不管是优先级为1或者10最终计算结果非常相近。
     * @param args
     * @throws InterruptedException
     */

    public static void main(String[] args) throws InterruptedException {
        List<Job> jobs = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int priority = i < 5 ? Thread.MIN_PRIORITY : Thread.MAX_PRIORITY;
            Job job = new Job(priority);
            jobs.add(job);

            Thread thread = new Thread(job, "thread - " + i);
            thread.setPriority(priority);
            thread.start();
        }
        noStart = false;
        TimeUnit.SECONDS.sleep(10);
        noEnd = false;
        for (Job job : jobs) {
            System.out.println("job Priority : " + job.priority + " Count: " + job.jobCount);
        }
    }

    static class Job implements Runnable {

        private int priority;
        private long jobCount;

        public Job(int priority) {
            this.priority = priority;
        }

        @Override
        public void run() {
            while(noStart) {
                Thread.yield();
            }

            while (noEnd) {
                Thread.yield();
                jobCount++;
            }
        }
    }
}
