package com.unht.myutils.base;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.unht.myutils.activity.MainActivity;
import com.unht.myutils.app.App;
import com.unht.myutils.myutils.ActivityCollector;
import com.unht.myutils.myutils.PreferencesUtils;
import com.unht.myutils.myutils.ScreenAdaptationUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.annotations.NonNull;


/**
 * @author kanglong
 * @date 17/8/11
 * 无MVP的activity基类
 */
public abstract class SimpleActivity extends AppCompatActivity {
    protected final String TAG = this.getClass().getSimpleName();
    protected Activity mContext;
    private Unbinder mUnBinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        ScreenAdaptationUtil.setCustomDensity(this, App.getInstance());
        if (getLayout() != 0) {
            setContentView(getLayout());
        }
        mUnBinder = ButterKnife.bind(this);
        mContext = this;
        onViewCreated();
        ActivityCollector.addActivity(this);
        initEventAndData();

    }

    protected void showToast(String meg) {
        Toast.makeText(App.getInstance(), meg, Toast.LENGTH_LONG).show();
    }

    protected void onViewCreated() {

    }

    protected abstract int getLayout();

    protected abstract void initEventAndData();

    protected abstract void showLoading();
    protected abstract void dismissLoading();

    //跳转到登录页面
    protected void gotoLoginAct(){
        PreferencesUtils.clear();
//        startActivity(LoginActivity.class);
    }

    protected void gotoMainAct(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    /**
     * [页面跳转]
     *
     * @param clz
     */
    protected void startActivity(@NonNull Class<?> clz) {
        startActivity(new Intent(this, clz));
    }

    /**
     * [携带数据的页面跳转]
     *
     * @param clz
     * @param bundle
     */
    protected void startActivity(@NonNull Class<?> clz, @NonNull Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, clz);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * [含有Bundle通过Class打开编辑界面]
     *
     * @param cls
     * @param bundle
     * @param requestCode
     */
    protected void startActivityForResult(@NonNull Class<?> cls, @NonNull Bundle bundle,
                                          @NonNull int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        intent.putExtras(bundle);
        startActivityForResult(intent, requestCode);
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
        mUnBinder.unbind();
    }


}
