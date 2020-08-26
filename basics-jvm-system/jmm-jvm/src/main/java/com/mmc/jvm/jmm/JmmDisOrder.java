package com.mmc.jvm.jmm;

import java.util.concurrent.ThreadFactory;

/**
 * @packageName：com.mmc.jvm.jmm
 * @desrciption: 多线程情况下执行乱序问题
 *              处理器和编译器重排序
 * @author: GW
 * @date： 2020/8/26 22:38
 * @history: (version) author date desc
 */
public class JmmDisOrder {

    private static int x, y, a, b;

    /**
     * 当执行结果出现 x y 同时等于0时，说明出现重排序问题
     * @param args
     *          参数
     * @throws InterruptedException
     *          异常
     */
    public static void main(String[] args) throws InterruptedException {

        int i = 0;
        for (;;) {
            i++;
            x = y = 0;
            a = b = 0;

            Thread t1 = new Thread(() -> {
                a = 1;
                x = b;
            }, "thread-01");

            Thread t2 = new Thread(() -> {
                b = 1;
                y = a;
            }, "thread-02");

            t1.start();
            t2.start();
            t1.join();
            t2.join();
            String result = "第 【" + i + "】次 (" + x + ", " + y + ")";
            if (x == 0 && y == 0) {
                System.err.println(result);
                break;
            } else {
                System.out.println(result);
            }
        }
    }
}
