package com.marlon.module.common.base;

import android.util.Log;

import io.reactivex.observers.DisposableObserver;

/**
 * @author 康龙
 * @date 2017/5/10
 */
public abstract class BaseObserver<T> extends DisposableObserver<BaseResponse<T>> {
    private static final String TAG = "BaseObserver";

    @Override
    public void onNext(BaseResponse<T> value) {
        if (value.isSuccess() && (value.getData() != null)) {
            try {
                onSuccess(value.getData());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            if (value.getCode() == 401) {
                //未登录或者登录过期进行处理
                /*PreferencesUtils.clear();
                App.getInstance().startActivity(new Intent(App.getInstance(), LoginActivity.class));*/

            } else {
                //返回错误信息
                onFailure(value.getMessage());
            }
        }
    }

    @Override
    public void onError(Throwable e) {
        Log.e(TAG, e.toString());
        onFailure(e.toString());
        e.printStackTrace();
       /* try {
            if (e instanceof ConnectException
                    || e instanceof TimeoutException
                    || e instanceof NetworkErrorException
                    || e instanceof UnknownHostException) {
                onFailure(e.getMessage());
                e.printStackTrace();
            } else {
                onFailure(e.getMessage());
                e.printStackTrace();
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }*/
    }

    @Override
    public void onComplete() {

    }

    /**
     * 请求成功，code == 200；
     *
     * @param value
     */
    protected abstract void onSuccess(T value);

    /**
     * 请求失败，返回错误/失败信息
     *
     * @param message
     */
    protected abstract void onFailure(String message);


}
