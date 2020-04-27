package com.marlon.module.common.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;

import com.marlon.module.common.utils.PreferencesUtils;
import com.marlon.module.common.utils.Utils;
import com.marlon.retrofitclent.handler.RxErrorHandler;

import org.greenrobot.greendao.annotation.NotNull;

import static com.bumptech.glide.load.HttpException.UNKNOWN;
import static com.marlon.retrofitclent.exception.ResponeException.AUTHORIZEDTIMEOUT;
import static com.marlon.retrofitclent.exception.ResponeException.UNAUTHORIZED;



/**
 * Created by codeest on 16/8/11.
 * 无MVP的Fragment基类
 */

public abstract class BaseFragment extends Fragment {
    public final String TAG = this.getClass().getSimpleName();
    protected View mRootView = null;
    protected BaseActivity mActivity;
    protected Context mContext;
    protected boolean isInited = false;
    protected RxErrorHandler mRxErrorHandler = new RxErrorHandler(e -> {
        if (e.code == UNAUTHORIZED || e.code == AUTHORIZEDTIMEOUT||e.code == UNKNOWN) {
            PreferencesUtils.remove(SESSION_ID);
            Intent  intent = new  Intent(mContext, LoginActivity.class);
            intent.putExtra("isLoginOut", true);
            intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        } else {
            showShortToast(e.getMessage());
        }
    });

    @Override
    public void onAttach(Context context) {
        mActivity = (BaseActivity) context;
        mContext = context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(getLayout(), container, false);
        }
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        init();
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            if (!isInited) {
                isInited = true;
                initData();
            }
        }

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    protected void showShortToast(String msg) {
        Toast.makeText(this.getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    protected void showLongToast(String msg) {
        Toast.makeText(this.getContext(), msg, Toast.LENGTH_LONG).show();
    }

    protected abstract int getLayout();

    protected abstract void init();

    public void initData() {
    }

    /**
     * 获取宿主Activity
     *
     * @return BaseActivity
     */
    protected BaseActivity getHoldingActivity() {
        return mActivity;
    }


    /**
     * 添加fragment
     *
     * @param fragment
     * @param frameId
     */
    protected void addFragment(BaseFragment fragment, @IdRes int frameId) {
        Utils.checkNotNull(fragment);
        getHoldingActivity().addFragment(fragment, frameId);

    }


    /**
     * 替换fragment
     *
     * @param fragment
     * @param frameId
     */
    protected void replaceFragment(BaseFragment fragment, @IdRes int frameId) {
        Utils.checkNotNull(fragment);
        getHoldingActivity().replaceFragment(fragment, frameId);
    }


    /**
     * 隐藏fragment
     *
     * @param fragment
     */
    protected void hideFragment(BaseFragment fragment) {
        Utils.checkNotNull(fragment);
        getHoldingActivity().hideFragment(fragment);
    }


    /**
     * 显示fragment
     *
     * @param fragment
     */
    protected void showFragment(BaseFragment fragment) {
        Utils.checkNotNull(fragment);
        getHoldingActivity().showFragment(fragment);
    }


    /**
     * 移除Fragment
     *
     * @param fragment
     */
    protected void removeFragment(BaseFragment fragment) {
        Utils.checkNotNull(fragment);
        getHoldingActivity().removeFragment(fragment);

    }


    /**
     * 弹出栈顶部的Fragment
     */
    protected void popFragment() {
        getHoldingActivity().popFragment();
    }

}
