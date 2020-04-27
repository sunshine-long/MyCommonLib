package com.marlon.module.common.base;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;


/**
 * @author kanglong
 * @date 17/8/11
 * 无MVP的activity基类
 */
public abstract class DataBindingBaseActivity<D extends ViewDataBinding> extends BaseActivity {
    protected D mBinding;

    @Override
    protected void setView() {
        mBinding = DataBindingUtil.setContentView(this, getLayout());
        mBinding.setLifecycleOwner(this);
    }
}
