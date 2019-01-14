package com.marlon.module.common.base;

/**
 * Created by 康龙 on 2017/5/22.
 */

public interface BaseView {
    void gotoLogin();
    void showLoading();
    void dismissLoading();
    void showShortToast(String msg);
    void showLongToast(String msg);
}
