package com.unht.myutils.base;

import android.content.Context;

import com.unht.myutils.utils.LogUtils;
import com.unht.myutils.utils.ToastUtils;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;


/**
 * Created by 康龙 on 2017/5/10.
 */

/**
 * @param <T>
 * @author KangLong
 * @date 2017/5/10
 */
public abstract class BaseObserver<T> implements Observer<BaseEntity<T>> {
    private static final String TAG = "BaseObserver";
    private Context mContext;
    //    private LoadingDialog mLoadingDialog;
    private Context viewContent;

    protected BaseObserver(Context context) {
        this.mContext = context.getApplicationContext();
        this.viewContent = context;
//        showLoadingDialog(context);
    }

    /*    public void showLoadingDialog(Context context) {
            if (mLoadingDialog == null) {
                mLoadingDialog = new LoadingDialog(context);
            }
            mLoadingDialog.show();
        }

        public void dissmissLoadingDialog() {
            if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
                mLoadingDialog.dismiss();
                mLoadingDialog = null;
            }
        }
    */
    @Override
    public void onNext(BaseEntity<T> value) {
//        dissmissLoadingDialog();
        if (value.isSuccess()) {
            if (value.getData() == null) {
                onSuccess(value.getData());

            } else {
                T t = value.getData();
                onSuccess(t);
            }

        } else {
            onResError(value);
            if (value.getCode() == 5000) {
//                viewContent.startActivity(new Intent(viewContent, LoginActivity.class));
            }
            ToastUtils.show(mContext, value.getMessage());
        }
    }

    @Override
    public void onError(Throwable e) {
//        dissmissLoadingDialog();
        LogUtils.e(TAG, "error:" + e.toString());
        ToastUtils.show(mContext, e.getMessage().toString());
    }

    @Override
    public void onComplete() {
//        dissmissLoadingDialog();
        LogUtils.d(TAG, "onComplete");
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        LogUtils.d(TAG, "onSubscribe   " + d);
//        dissmissLoadingDialog();
    }

    protected abstract void onSuccess(T t);

    protected abstract void onResError(BaseEntity value);


}
