package com.marlon.retrofitclent;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;

import com.marlon.retrofitclent.exception.ResponeException;
import com.marlon.retrofitclent.handler.ExceptionHandle;
import com.marlon.retrofitclent.handler.RxErrorHandler;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * @author 康龙
 * @date 2017/5/10
 */
public abstract class BaseObserver<T> implements Observer<BaseResponse<T>> {
    private static final String TAG = "BaseObserver";
    private RxErrorHandler mRxErrorHandler;
    private ProgressDialog mDialog;
    private Disposable mDisposable;

    public BaseObserver() {

    }

    public BaseObserver(Context context, RxErrorHandler rxErrorHandler) {
        initProgressDialog(context);
        this.mRxErrorHandler = rxErrorHandler;
    }

    public BaseObserver(Context context) {
        initProgressDialog(context);
    }

    private void initProgressDialog(Context mContext) {
        if (mDialog == null) {
            mDialog = new ProgressDialog(mContext);
            mDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mDialog.setCancelable(true);
            mDialog.setCanceledOnTouchOutside(false);
            mDialog.setMessage("加载中,请稍后...");
            mDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    if (mDisposable != null) {
                        mDisposable.dispose();
                    }
                }
            });
        }
        mDialog.show();
    }


    @Override
    public void onNext(BaseResponse<T> value) {
        if (mDialog != null) {
            mDialog.dismiss();
        }
        if (value.isSuccess()) {
            if (value.getData() != null) {
                try {
                    onSuccess(value.getData());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                onSuccess(null);
            }
        } else {
            ResponeException exception = new ResponeException(value.getCode(), value.getMessage());
            onFailure(exception);
        }
    }

    @Override
    public void onError(Throwable e) {
        if (mDialog != null) {
            mDialog.dismiss();
        }
        Log.e(TAG, e.getMessage() + "");
        onFailure(ExceptionHandle.handleException(e));

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        this.mDisposable = d;
        onBefore(d);
    }

    /**
     * 请求成功，code == 200；
     *
     * @param value
     */
    protected abstract void onSuccess(T value);

    /**
     * 在回调之前
     *
     * @param d 返回用于全校网络请求
     */
    protected void onBefore(@NonNull Disposable d) {
    }

    /**
     * 请求失败，返回错误/失败信息
     *
     * @param exception
     */
    protected void onFailure(ResponeException exception) {
        if (mRxErrorHandler != null) {
            mRxErrorHandler.handleError(exception);
        }
    }
}
