package com.mmc.concurrent.thread.vola;

/**
 * @packageName：com.mmc.concurrent.thread.vola
 * @desrciption: volatile 修饰变量 查看汇编码
 * @author: GW
 * @date： 2020-09-09 12:44
 * @history: (version) author date desc
 */
public class VolatileTest {

    private volatile int state;

    public void count() {
        state += 1;
    }

    public static void main(String[] args) {
        VolatileTest volatileTest = new VolatileTest();
        volatileTest.count();
    }
}
