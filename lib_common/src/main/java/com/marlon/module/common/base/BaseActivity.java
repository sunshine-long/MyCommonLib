package com.marlon.module.common.base;

import android.app.Activity;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.AnimRes;
import androidx.annotation.AnimatorRes;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.marlon.module.common.utils.PreferencesUtils;
import com.marlon.module.common.utils.ScreenAdaptationUtil;
import com.marlon.module.common.utils.Utils;
import com.marlon.retrofitclent.handler.RxErrorHandler;
import com.marlon.utils.R;

import static com.bumptech.glide.load.HttpException.UNKNOWN;
import static com.marlon.retrofitclent.exception.ResponeException.AUTHORIZEDTIMEOUT;
import static com.marlon.retrofitclent.exception.ResponeException.UNAUTHORIZED;



/**
 * @author kanglong
 * @date 17/8/11
 * 无MVP的activity基类
 */
public abstract class BaseActivity extends AppCompatActivity implements LifecycleOwner {
    protected final String TAG = this.getClass().getSimpleName();
    protected Activity mContext;
    protected Bundle savedInstanceState;
    private LifecycleRegistry mLifecycleRegistry;

    /**
     * 封装的findViewByID方法
     */
    @SuppressWarnings("unchecked")
    protected <V extends View> V $(@IdRes int id) {
        return (V) super.findViewById(id);
    }

    protected RxErrorHandler mRxErrorHandler = new RxErrorHandler(e -> {
        if (e.code == UNAUTHORIZED || e.code == AUTHORIZEDTIMEOUT||e.code == UNKNOWN) {
            PreferencesUtils.remove(SESSION_ID);
            Intent  intent = new  Intent(mContext, LoginActivity.class);
            intent.putExtra("isLoginOut", true);
            intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        } else {
            showToast(e.getMessage());
        }
    });

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.savedInstanceState = savedInstanceState;
        ScreenAdaptationUtil.setCustomDensity(this, App.app);
       /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(getResources().getColor(R.color.statusBarColor));
        }*/


        setView();
/*        getLifecycle().addObserver(new GenericLifecycleObserver() {
            @Override
            public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {

            }
        });*/
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mContext = this;
        initView();
        ViewManager.getInstance().addActivity(this);
        initEventAndData();
    }

    protected void setView() {
        if (getLayout() != 0) {
            setContentView(getLayout());
        }
    }


    /**
     * 显示一个短toast
     *
     * @param msg
     */
    public void showToast(CharSequence msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 显示一个长toast
     *
     * @param msg 要显示的字符串
     */
    protected void showLongToast(CharSequence msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ViewManager.getInstance().finishActivity(this);
    }

    protected abstract int getLayout();

    protected abstract void initView();

    protected abstract void initEventAndData();


    /**
     * 添加fragment
     *
     * @param fragment
     * @param frameId
     */
    public void addFragment(BaseFragment fragment, @IdRes int frameId) {
        Utils.checkNotNull(fragment);
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right)
                .add(frameId, fragment, fragment.getClass().getSimpleName())
                .commitAllowingStateLoss();
    }

    /**
     * 添加fragment
     *
     * @param fragment
     * @param frameId
     */
    public void addFragment(BaseFragment fragment, @IdRes int frameId, @AnimatorRes @AnimRes int enter,
                            @AnimatorRes @AnimRes int exit) {
        Utils.checkNotNull(fragment);
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(enter, exit)
                .add(frameId, fragment, fragment.getClass().getSimpleName())
                .commitAllowingStateLoss();
    }


    /**
     * 替换fragment
     *
     * @param fragment
     * @param frameId
     */
    public void replaceFragment(BaseFragment fragment, @IdRes int frameId) {
        Utils.checkNotNull(fragment);
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right)
                .replace(frameId, fragment, fragment.getClass().getSimpleName())
                .commitAllowingStateLoss();

    }


    /**
     * 隐藏fragment
     *
     * @param fragment
     */
    public void hideFragment(BaseFragment fragment, @AnimatorRes @AnimRes int enter,
                                @AnimatorRes @AnimRes int exit) {
        Utils.checkNotNull(fragment);
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(enter, exit)
                .hide(fragment)
                .commitAllowingStateLoss();
    }

    /**
     * 隐藏fragment
     *
     * @param fragment
     */
    public void hideFragment(BaseFragment fragment) {
        Utils.checkNotNull(fragment);
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right)
                .hide(fragment)
                .commitAllowingStateLoss();
    }


    /**
     * 显示fragment
     *
     * @param fragment
     */
    public void showFragment(BaseFragment fragment) {
        showFragment(fragment, R.anim.slide_in_left, R.anim.slide_out_right);
    }

    /**
     * 显示fragment
     *
     * @param fragment
     */
    public void showFragment(BaseFragment fragment, @AnimatorRes @AnimRes int enter,
                                @AnimatorRes @AnimRes int exit) {
        Utils.checkNotNull(fragment);
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(enter, exit)
                .show(fragment)
                .commitAllowingStateLoss();

    }


    /**
     * 移除fragment
     *
     * @param fragment
     */
    public void removeFragment(BaseFragment fragment) {
        Utils.checkNotNull(fragment);
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right)
                .remove(fragment)
                .commitAllowingStateLoss();

    }


    /**
     * 弹出栈顶部的Fragment
     */
    public void popFragment() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
        }
    }


    /**
     * 带参数跳转
     *
     * @param cls
     * @param bundle
     */
    public void goActivity(Class<?> cls, @Nullable Bundle bundle) {
        Intent intent = new Intent(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 不带参数跳转
     *
     * @param cls
     */
    public void goActivity(Class<?> cls) {
        goActivity(cls, null);
    }

    /**
     * [含有Bundle通过Class打开编辑界面]
     *
     * @param cls
     * @param bundle
     * @param requestCode
     */
    public void startActivityForResult(@NonNull Class<?> cls, @NonNull Bundle bundle,
                                       @NonNull int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        intent.putExtras(bundle);
        startActivityForResult(intent, requestCode);
    }


    @Override
    public boolean onSupportNavigateUp() {
        this.finish();
        return super.onSupportNavigateUp();
    }
}
