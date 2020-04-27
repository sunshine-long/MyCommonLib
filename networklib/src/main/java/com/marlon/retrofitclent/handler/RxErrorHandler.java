package com.marlon.retrofitclent.handler;


import com.marlon.retrofitclent.exception.ResponeException;

/**
 * @author Marlon
 * @desc
 * @date 2019/5/22
 */
public class RxErrorHandler {
    private ResponseErrorListener mErroListener;

    public RxErrorHandler(ResponseErrorListener listener) {
        this.mErroListener = listener;
    }

    public void handleError(ResponeException e) {
        mErroListener.handleResponseError(e);
    }

    public interface ResponseErrorListener {
        void handleResponseError(ResponeException e);
    }
}
