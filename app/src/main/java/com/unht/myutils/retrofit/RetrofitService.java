package com.unht.myutils.retrofit;


import com.unht.myutils.base.BaseEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 为Retrofit框架提供接口请求注解
 * @author 康龙
 * @date 2017/5/9
 */

public interface RetrofitService {

    /**
     * 获取短信验证码
     *
     * @param mobile 手机号
     * @return
     */
    @GET("/fitness/coach/login/code.jhtml")
    Observable<BaseEntity<String>> getVerificatCode(@Query("mobile") String mobile);


}



