package com.mmc.netty.rpc.core.provide;

import com.mmc.netty.rpc.core.api.IRpcMathService;

/**
 * @packageName：com.mmc.netty.rpc.core.provide
 * @desrciption:
 * @author: GW
 * @date： 2020/9/4 23:18
 * @history: (version) author date desc
 */
public class RpcMatchService implements IRpcMathService {
    @Override
    public int add(int a, int b) {
        return a + b;
    }

    @Override
    public int sub(int a, int b) {
        return a - b;
    }

    @Override
    public int multi(int a, int b) {
        return a * b;
    }

    @Override
    public int div(int a, int b) {
        if (a == 0 || b == 0) {
            return 0;
        }
        return a / b;
    }
}
