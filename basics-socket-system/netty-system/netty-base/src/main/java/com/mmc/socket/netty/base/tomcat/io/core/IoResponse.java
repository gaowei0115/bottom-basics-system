package com.mmc.socket.netty.base.tomcat.io.core;

import com.mmc.socket.netty.base.tomcat.Response;

import javax.xml.transform.OutputKeys;
import java.io.OutputStream;

/**
 * @packageName：com.mmc.socket.netty.base.tomcat.io.core
 * @desrciption: 基于Socket io Response对象
 * @author: GW
 * @date： 2020/8/31 22:11
 * @history: (version) author date desc
 */
public class IoResponse implements Response {

    private static final long serialVersionUID = -3384883143206657894L;

    private OutputStream os;

    public IoResponse(OutputStream os) {
        this.os = os;
    }

    public void write(String content) throws Exception {
        // 输出也要遵守Http
        // 状态码200
        StringBuilder sb = new StringBuilder();
        sb.append("HTTP/1.1 200 OK \n")
                .append("Content-Type: text/html;\n")
                .append("\r\n")
                .append(content);
        os.write(sb.toString().getBytes());
    }
}
