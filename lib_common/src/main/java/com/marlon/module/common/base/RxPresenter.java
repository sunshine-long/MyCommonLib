package com.marlon.module.common.base;


import com.marlon.module.common.network.ApiService;
import com.marlon.module.common.network.BaseObserver;
import com.marlon.module.common.network.RxHelper;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


/**
 * @desc RxPresenter
 * @author Marlon
 * @date 2019/1/10
 */
public class RxPresenter<V extends BaseView> implements BasePresenter<V> {

    protected ApiService apiService ;
    protected BaseApplication mContext ;
    protected V mView;
    private CompositeDisposable mCompositeDisposable;
    @Inject
    public RxPresenter(ApiService apiService, BaseApplication mContext) {
        this.apiService = apiService;
        this.mContext = mContext;
    }

    /**
     * 取消注册 中断请求
     */
    protected void unSubscribe() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
            mCompositeDisposable.clear();
        }
    }

    //注册
    protected void addSubscribe(Disposable subscription) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(subscription);
    }

    protected void addSubscribe(Observable<?> observable, BaseObserver observer) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(observable.compose(RxHelper.applySchedulers(mContext)).subscribeWith(observer));
    }

    @Override
    public void attachView(V view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        this.mView = null;
        unSubscribe();
    }
}
