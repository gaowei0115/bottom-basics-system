package com.mmc.socket.netty.base.tomcat;

/**
 * @packageName：com.mmc.socket.netty.base.tomcat
 * @desrciption: 请求方法
 * @author: GW
 * @date： 2020/8/31 23:07
 * @history: (version) author date desc
 */
public enum HttpMethod {
    HTTP_GET("GET", "get请求方法"),
    HTTP_POST("POST", "post请求方法");

    private String method;
    private String remark;

    HttpMethod(String method, String remark) {
        this.method = method;
        this.remark = remark;
    }

    public String getMethod() {
        return method;
    }

    public String getRemark() {
        return remark;
    }
}
