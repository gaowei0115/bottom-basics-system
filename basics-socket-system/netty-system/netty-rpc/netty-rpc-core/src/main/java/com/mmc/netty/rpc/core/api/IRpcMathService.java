package com.mmc.netty.rpc.core.api;

/**
 * @packageName：com.mmc.netty.rpc.core.api
 * @desrciption:
 * @author: GW
 * @date： 2020/9/4 23:09
 * @history: (version) author date desc
 */
public interface IRpcMathService {

    /**
     * two int number add
     * @param a
     *      param a
     * @param b
     *      param b
     * @return
     *      add result
     */
    public int add(int a, int b);

    /**
     * two number sub
     * @param a
     *      param a
     * @param b
     *      param b
     * @return
     *      sub result
     */
    int sub(int a, int b);

    /**
     *  two number multi
     * @param a
     *      param a
     * @param b
     *      param b
     * @return
     *      multi result
     */
    int multi(int a, int b);

    /**
     * two number div
     * @param a
     *      param a
     * @param b
     *      param b
     * @return
     *      div result
     */
    int div(int a, int b);
}
