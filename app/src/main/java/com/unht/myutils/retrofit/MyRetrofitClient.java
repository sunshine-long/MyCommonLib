package com.unht.myutils.retrofit;

import android.content.Context;

import com.unht.myutils.app.ApiConfig;
import com.unht.myutils.utils.LogUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.CookieJar;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author KangLong
 *         date 2017/5/8
 *         description 采用快速 工厂模式/单例 构建Retrofit请求类
 */
public class MyRetrofitClient {


    private static final long TIMEOUT = 300;
    private static final String TAG = "MyRetrofitClient";
    private static MyRetrofitClient myRetrofitClient;
    private Retrofit retrofit;
    private OkHttpClient mOkHttpClient;
    private HttpCommonInterceptor mCommonInterceptor;
    private CookieJar mCookieJar;

    public static MyRetrofitClient getInstance(Context context) {
        if (myRetrofitClient == null) {
            synchronized (MyRetrofitClient.class) {
                if (myRetrofitClient == null) {
                    myRetrofitClient = new MyRetrofitClient(context);
                }
            }
        }
        return myRetrofitClient;
    }


    private MyRetrofitClient(Context context) {
        initOkhttp();

    }

    /**
     * 初始化 OkHttpClient
     *
     */
    private void initOkhttp() {
        mCookieJar = new CookieManger();
        //添加token 在header中
        mCommonInterceptor = new HttpCommonInterceptor.Builder()
                .addHeaderParams("token", "android")
                .build();
        mOkHttpClient = new OkHttpClient.Builder()
                .cookieJar(mCookieJar)
                .addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
                        LogUtils.d(TAG, message);
                    }
                }).setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .build();
        initRetrofit();
    }

    /**
     * 初始化retrofit
     */
    private void initRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(ApiConfig.BASE_URL)
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public <T> T createService(Class<T> service) {
        return retrofit.create(service);
    }
}
