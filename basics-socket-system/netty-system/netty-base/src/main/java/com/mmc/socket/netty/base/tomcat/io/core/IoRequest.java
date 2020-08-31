package com.mmc.socket.netty.base.tomcat.io.core;

import com.mmc.socket.netty.base.tomcat.Request;

import java.io.InputStream;

/**
 * @packageName：com.mmc.socket.netty.base.io.tomcat.domain
 * @desrciption: 基于Socket IO Request对象
 * @author: GW
 * @date： 2020/8/31 21:58
 * @history: (version) author date desc
 */
public class IoRequest implements Request {

    private static final long serialVersionUID = 5153180025893199106L;

    private String method;
    private String url;

    public IoRequest(InputStream is) {
        try {
            // 获取http内容
            String content = "";
            byte[] buffer = new byte[1024];
            int len = 0;
            if ((len = is.read(buffer)) > 0) {
                content = new String(buffer, 0, len);
            }
            String line = content.split("\\n")[0];
            String[] arr = line.split("\\s");
            this.method = arr[0];
            this.url = arr[1].split("\\?")[0];
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
