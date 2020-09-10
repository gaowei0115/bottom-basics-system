package com.mmc.jvm.cpu.gc;

/**
 * @packageName：com.mmc.jvm.cpu.gc
 * @desrciption: 大对象直接进老年代
 * @author: GW
 * @date： 2020-09-10 20:12
 * @history: (version) author date desc
 */
public class JvmBigObjectOld {

    /**
     * 分配1MB空间
     */
    private static final int _1MB = 1024 * 1024;


    /**
     * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
     *
     * -XX：PretenureSizeThreshold=3145728
     */
    public static void testAllocation() {
        byte[] allocation;
        allocation = new byte[4 * _1MB];
    }


    public static void main(String[] args) throws InterruptedException {
        testAllocation();

        Thread.sleep(100000);
    }

}
