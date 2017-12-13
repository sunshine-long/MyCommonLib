package com.unht.myutils.base;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by 康龙 on 2017/5/10.
 */

public class BaseResponse<E> implements Serializable {
    @SerializedName("code")
    private int code = 200;
    @SerializedName("msg")
    private String msg;
    @SerializedName("response")
    private E response;

    public boolean isSuccess() {
        return code == 200;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return msg;
    }

    public void setMessage(String message) {
        this.msg = message;
    }

    public E getData() {
        return response;
    }

    public void setData(E data) {
        this.response = data;
    }
}
