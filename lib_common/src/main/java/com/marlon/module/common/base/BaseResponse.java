package com.marlon.module.common.base;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * 网络请求数据基本数据请求
 *
 * @author 康龙
 * @date 2017/5/10
 */

public class BaseResponse<E> implements Serializable {
    @SerializedName("code")
    private int code = 200;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private E data;

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
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
