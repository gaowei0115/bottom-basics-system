package com.mmc.netty.rpc.core.protocol;

import java.io.Serializable;

/**
 * @packageName：com.mmc.netty.rpc.core.protocol
 * @desrciption:
 * @author: GW
 * @date： 2020/9/4 23:14
 * @history: (version) author date desc
 */
public class AutoProtocol implements Serializable {

    private static final long serialVersionUID = -2743059548084682799L;

    /**
     * 发布服务接口类名
     */
    private String className;
    /**
     * 发布服务方法名
     */
    private String methodName;
    /**
     * 方法参数类型
     */
    private Class<?>[] paramTypes;
    /**
     * 方法参数对象
     */
    private Object[] params;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class<?>[] getParamTypes() {
        return paramTypes;
    }

    public void setParamTypes(Class<?>[] paramTypes) {
        this.paramTypes = paramTypes;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }
}
