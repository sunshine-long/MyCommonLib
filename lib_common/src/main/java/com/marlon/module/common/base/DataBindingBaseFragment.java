package com.marlon.module.common.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import org.jetbrains.annotations.NotNull;


/**
 * Created by codeest on 16/8/11.
 * 无MVP的Fragment基类
 */

public abstract class DataBindingBaseFragment<D extends ViewDataBinding> extends BaseFragment {
    protected D mBinding;

    @Nullable
    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mBinding = DataBindingUtil.inflate(
                    inflater
                    , getLayout()
                    , container
                    , false
            );
            mBinding.setLifecycleOwner(this);
            mRootView = mBinding.getRoot();
        }
        initBinding();
        return mRootView;
    }

    protected abstract void initBinding() ;


}
