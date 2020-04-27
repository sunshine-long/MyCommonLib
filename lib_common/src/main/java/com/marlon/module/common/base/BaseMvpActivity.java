package com.marlon.module.common.base;


import com.marlon.module.common.di.component.ActivityComponent;
import com.marlon.module.common.di.component.DaggerActivityComponent;
import com.marlon.module.common.di.module.ActivityModule;

import javax.inject.Inject;

/**
 * @desc BaseMvpActivity
 * @author Marlon
 * @date 2018/12/27
 */
public abstract class BaseMvpActivity<P extends RxPresenter> extends BaseActivity implements BaseView {
    @Inject
    protected P mPresenter;

    protected ActivityComponent getActivityComponent() {
        return DaggerActivityComponent.builder()
                .appComponent(BaseApplication.getAppComponent())
                .activityModule(getActivityModule())
                .build();
    }

    public ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    @Override
    protected void onViewCreated() {
        super.onViewCreated();
        initInject();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroy();
    }

    protected abstract void initInject();

    @Override
    public void showToast(String msg) {
        super.showToast(msg);
    }

    @Override
    public void showLongToast(String msg) {
        super.showLongToast(msg);
    }

    @Override
    public void gotoLogin() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }
}
