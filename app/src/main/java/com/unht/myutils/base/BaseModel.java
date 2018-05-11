package com.unht.myutils.base;

import android.content.Context;

import com.google.gson.Gson;
import com.unht.myutils.retrofit.MyRetrofitClient;
import com.unht.myutils.retrofit.RetrofitService;
import com.unht.myutils.utils.LitePrefUtils;

import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;


/**
 * Created by KangLong on 2017/6/22.
 */

public class BaseModel {
    private static final String TAG = "BaseModel";
    protected RetrofitService retrofitServicer;
    protected Map<String, Object> mParams = new HashMap<>();
    protected Gson gson = new Gson();
    protected RequestBody mBody;
    protected Context mContext;
    protected String token;

    public BaseModel(Context context) {
        retrofitServicer = MyRetrofitClient.getInstance(context).createService(RetrofitService.class);
        this.mContext = context;
        token = (String) LitePrefUtils.getInstance(context).getValue("token", "");
    }

}
