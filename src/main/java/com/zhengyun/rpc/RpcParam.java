package com.zhengyun.rpc;

import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * Created by 听风 on 2017/12/29.
 */
public class RpcParam implements Serializable {

    private String faceName;

    private String method;

    private Object[] args;

    private Class<?>[] parameterTypes;

    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public String getFaceName() {
        return faceName;
    }

    public void setFaceName(String faceName) {
        this.faceName = faceName;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }
}
