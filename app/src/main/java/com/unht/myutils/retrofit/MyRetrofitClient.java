package com.unht.myutils.retrofit;

import android.content.Context;

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
    //IP地址
    //本地
    public static final String BASE_URL = "http://192.168.2.110:8080/";
    //云上
//    public static final String BASE_URL = "http://www.sichuanxinge.com/";

    private static final long TIMEOUT = 300;
    private static final String TAG = "MyRetrofitClient";
    private static MyRetrofitClient myRetrofitClient;
    private Retrofit retrofit;

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

    private MyRetrofitClient(){

    }


    private MyRetrofitClient(Context context) {
        CookieJar cookieJar = new CookieManger(context);
        //添加token 在header中
        HttpCommonInterceptor commonInterceptor = new HttpCommonInterceptor.Builder()
                .addHeaderParams("token","android")
                .build();
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cookieJar(cookieJar)
                .addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
                        LogUtils.d(TAG, message);
                    }
                }).setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public RetrofitService createService() {
        return retrofit.create(RetrofitService.class);
    }
}
