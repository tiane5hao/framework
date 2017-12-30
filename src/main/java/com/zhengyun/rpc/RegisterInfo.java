package com.zhengyun.rpc;

import java.io.Serializable;

/**
 * Created by 听风 on 2017/12/28.
 */
public class RegisterInfo implements Serializable{

    private String id;

    private String msg;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
