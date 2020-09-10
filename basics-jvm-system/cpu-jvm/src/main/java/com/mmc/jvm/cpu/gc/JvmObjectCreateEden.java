package com.mmc.jvm.cpu.gc;

/**
 * @packageName：com.mmc.jvm.cpu.gc
 * @desrciption:
 * @author: GW
 * @date： 2020-09-10 20:12
 * @history: (version) author date desc
 */
public class JvmObjectCreateEden {

    /**
     * 分配1MB空间
     */
    private static final int _1MB = 1024 * 1024;


    /**
     * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
     */
    public static void testAllocation() {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation4 = new byte[4 * _1MB];
    }


    public static void main(String[] args) throws InterruptedException {
        testAllocation();

        new Object().wait();
    }

}
