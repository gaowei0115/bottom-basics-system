package com.mmc.concurrent.thread.fina;

/**
 * @packageName：com.mmc.concurrent.thread.fina
 * @desrciption: final 内存语义
 * @author: GW
 * @date： 2020-09-10 9:21
 * @history: (version) author date desc
 */
public class FinalTest {

    private final String message;

    public FinalTest() {
        message = "final hello";
    }

    public void testFinal() {
        System.out.println(message);
    }

    public static void main(String[] args) {
        System.out.println("test final");
    }
}
