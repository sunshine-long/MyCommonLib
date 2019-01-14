package com.marlon.module.common.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.marlon.module.common.di.component.DaggerFragmentComponent;
import com.marlon.module.common.di.component.FragmentComponent;
import com.marlon.module.common.di.module.FragmentModule;

import javax.inject.Inject;


/**
 * @author Marlon
 * @desc BaseDaggerFragment MVP Fragment基类
 * @date 2018/12/4
 */
public abstract class BaseMvpFragment<P extends BasePresenter> extends BaseFragment implements BaseView {

    @Inject
    protected P mPresenter;

    protected FragmentComponent getFragmentComponent() {
        return DaggerFragmentComponent.builder()
                .appComponent(BaseApplication.getAppComponent())
                .fragmentModule(getFragmentModule())
                .build();
    }

    protected FragmentModule getFragmentModule() {
        return new FragmentModule(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initPresenter();
        mPresenter.attachView(this);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroyView();
    }

    protected abstract void initPresenter();


    @Override
    public void gotoLogin() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void showShortToast(String msg) {
        super.showShortToast(msg);
    }

    @Override
    public void showLongToast(String msg) {
        super.showLongToast(msg);
    }
}