package com.marlon.module.common.base;

/**
 * Created by 康龙 on 2017/5/9.
 */

public interface BasePresenter<V extends BaseView> {
    void attachView(V view);

    void detachView();
}
