package com.mmc.concurrent.thread.utils;

import java.util.concurrent.TimeUnit;

/**
 * @packageName：com.mmc.concurrent.thread.utils
 * @desrciption:
 * @author: GW
 * @date： 2020/8/21 22:42
 * @history: (version) author date desc
 */
public class TimeUtils {

    public static void sleepSecond(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
