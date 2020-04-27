package com.marlon.retrofitclent.exception;

/**
 * @author Marlon
 */
public class ResponeException extends Exception {

    /**
     * 定义网络异常码
     */

    public static final int NOTEXISTTOKEN = 400;
    public static final int UNAUTHORIZED = 401;
    public static final int AUTHORIZEDTIMEOUT = 402;
    public static final int FORBIDDEN = 403;
    public static final int NOT_FOUND = 404;
    public static final int INTERNAL_SERVER_ERROR = 500;
    public static final int BAD_GATEWAY = 502;
    public static final int SERVICE_UNAVAILABLE = 503;
    public static final int GATEWAY_TIMEOUT = 504;

    public String message;
    public int code;

    public ResponeException(Throwable throwable, int resultCode) {
        super(throwable);
        this.code = resultCode;
        this.message = throwable.getMessage();
    }

    public ResponeException(int code, String detailMessage) {
        super(detailMessage);
        this.code = code;
        this.message = detailMessage;
    }
}
