package com.marlon.module.common.network;


import com.marlon.module.common.base.BaseResponse;
import com.marlon.module.common.javabean.Record;
import com.marlon.module.common.javabean.UserInfo;
import com.marlon.module.common.javabean.db.GridInfo;
import com.marlon.module.common.javabean.db.PeopleInfo;
import com.marlon.module.common.javabean.db.TobaccoField;
import com.marlon.module.common.javabean.db.VillageGroup;
import com.marlon.module.common.javabean.request.GridRequest;
import com.marlon.module.common.javabean.request.HistoryFieldRequest;
import com.marlon.module.common.javabean.request.LoginRequset;
import com.marlon.module.common.javabean.request.TokenRequest;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;


/**
 * 为Retrofit框架提供接口请求注解
 *
 * @author 康龙
 * @date 2017/5/9
 */

public interface BaseApiService {
    String BASE_URL = " http://news-at.zhihu.com/";
    //    通用的
    String OUTSIDE_BASIC_ULR = "http://uwonders.ticp.net:9090/";
    String INSIDE_BASIC_ULR = "10.92.236.211:8089";

    //用户登录
    String LOGIN1_URL = "uavgis/app/user/login";
    //二、查询所有的行政区域
    String ADMINISTRATIVE_AREA = "uavgis/app/administrativeArea/findAll.check";
    //修改用户密码
    String MODIFY_PASSWORD = "uavgis/app/user/updatePassword";
    //上传烟田信息
    String UPDATE_TOBACCO_FIELDS_MSG = "uavgis/app/tobacco/pushTobaccoField";
    //下载烟田信息
    String DOWNLOAD_TOBACCO_FIELDS_MSG = "uavgis/app/tobacco/pullTobaccoField";
    //上传烟农信息
    String UPDATE_TOBACCO_FARMER_MSG = "uavgis/app/tobacco/pushTobaccoFarmer";
    //下载烟农信息
    String DOWNLOAD_TOBACCO_FARMER_MSG = "uavgis/app/tobacco/pullTobaccoFarmer";
    //下载历史烟田
    String DOWNLOAD_HISTORY_TOBACCO_FIELDS = "uavgis/app/field/findArchives.check";
    //获取网格概况
    String GRID_INFO = "uavgis/app/tobacco/getGridInfo";
    //获取田块的基本属性（田块类型、土壤类型及地形地貌的编码名称，这些都是常量，一般不会变）
    String FIELD_PROPERTY_CONSTANT = "uavgis/app/field/findNature.check";
    //获取生产阶段的编码名称（常量，一般不会变）
    String PRODUCE_PROGRES_CONSTANT = "uavgis/app/produceProgress/findStage.check";
    //查询瓦片图的日期列表（通过此日期列表的日期可以下载不同日期的瓦片图）
    String MAP_GRID_IMAG_DATES = "uavgis/app/mapimg/findGridImgDates.check";
    //删除烟田
    String DELETE_FIELD_URL = "uavgis/app/tobacco/deleteTobaccoField";
    //推送APP的崩溃信息
    String PUSH_APP_ERRO_INFO = "uavgis/app/phoneError/pushPhoneErrorInfo";
    //    String PUSH_APP_ERRO_INFO = "file";
    //APP 版本更新
    String APP_VERSION_CHECK = "uavgis/app/apk/selectNowVersion";


    @POST(LOGIN1_URL)
    Observable<BaseResponse<UserInfo>> login(@Body LoginRequset loginRequset);


    @POST(DOWNLOAD_TOBACCO_FIELDS_MSG)
    Observable<BaseResponse<Record<TobaccoField>>> getFields(@Body GridRequest gridRequest);

    @POST(GRID_INFO)
    Observable<BaseResponse<GridInfo>> getGridInfo(@Body GridRequest GridRequest);

    @POST(DOWNLOAD_TOBACCO_FARMER_MSG)
    Observable<BaseResponse<Record<PeopleInfo>>> getFarmers(@Body GridRequest GridRequest);

    @POST(DOWNLOAD_HISTORY_TOBACCO_FIELDS)
    Observable<BaseResponse<String>> getHistoryFields(@Body HistoryFieldRequest request);

    @POST(ADMINISTRATIVE_AREA)
    Observable<BaseResponse<Record<VillageGroup>>>getVillagGroup(@Body TokenRequest request);

}



