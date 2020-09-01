package com.mmc.socket.netty.base.tomcat;

import java.io.Serializable;

/**
 * @packageName：com.mmc.socket.netty.base.io
 * @desrciption: 抽象响应对象
 * @author: GW
 * @date： 2020/8/31 21:57
 * @history: (version) author date desc
 */
public interface Response extends Serializable {

    void write(String content) throws Exception;
}
