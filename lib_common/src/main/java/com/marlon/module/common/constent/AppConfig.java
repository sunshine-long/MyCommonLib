package com.marlon.module.common.constent;

import android.os.Environment;

import com.marlon.module.common.base.BaseApplication;
import com.marlon.module.common.utils.AppUtils;

import java.io.File;

/**
 * @author Marlon
 * @desc
 * @date 2018/12/6
 */
public class AppConfig {

    public static final String SDCARDPATH = Environment.getExternalStorageDirectory().getAbsolutePath();
    public static final String APP_SAVE_PATH = SDCARDPATH + File.separator + AppUtils.getAppInfo(BaseApplication.getInstance()).getName();
    public static final String COMMON_FILE = File.separator + "common";
    public static final String NCNN_MODEL_FILE = File.separator + "model";
    public static final String IMAGE_SAVE_FILE = File.separator + "image";
    public static final String EXCEL_FILES = File.separator + "/excels";
    /**
     * 启动照相Intent的RequestCode.自定义相机.
     */
    public static final int TAKE_PHOTO_CUSTOM = 100;
    //相机权限
    public static final int PERMISSION_STORAGE_RESQUEST_CODE = 200;
    public static final int PERMISSION_CAMERA_RESQUEST_CODE = 300;
    public static final int PERMISSION_CAMERA = 500;
    //相机
    public static final int GET_IMAGE_BY_CAMERA = 1000;
    //本地图片
    public static final int GET_IMAGE_FROM_PHONE = 1001;
    //剪切图片
    public static final int CROP_IMAGE = 1002;
    //打开视频
    public static final int OPEN_VIDEO = 1003;


    public static final int RECOGNISE_INPUT_WIDTH = 96;
    public static final int RECOGNISE_INPUT_HEIGHT = 112;

    public static final String PathFieldFragment = "net.uwonders.module.field";

    public static final String PathFarmerFragment = "net.uwonders.module.farmer";

    public static final String PathUserFragment = "net.uwonders.module.user";

    public static final String PathProgressFragment = "net.uwonders.module.progress";

    public static final String PathHomeFragment = "net.uwonders.module.progress";

    public static String TOKEN = null;
    public static Integer GRIDID = null;


}
