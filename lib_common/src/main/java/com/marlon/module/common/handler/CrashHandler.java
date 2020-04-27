package com.marlon.module.common.handler;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.autonavi.amap.mapcore.FileUtil;
import com.marlon.module.common.base.ViewManager;
import com.marlon.module.common.javabean.CrashLog;
import com.marlon.module.common.utils.AppUtils;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 康龙 on 2017/11/4.
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private Thread.UncaughtExceptionHandler mDefaultHandler;
    // CrashHandler实例
    private static CrashHandler INSTANCE = new CrashHandler();
    // 程序的Context对象
    private Context mContext;

    private CrashHandler() {
    }

    public static CrashHandler getInstance() {
        return INSTANCE;
    }

    /**
     * 初始化
     *
     * @param context
     */
    public void init(Context context) {
        mContext = context;
        // 获取系统默认的UncaughtException处理
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        // 设置该CrashHandler为程序的默认处理
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 异常捕获
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (!handleException(ex) && mDefaultHandler != null) {
            // 如果用户没有处理则让系统默认的异常处理器来处
            mDefaultHandler.uncaughtException(thread, ex);
        } else {
            // 跳转到崩溃提示Activity
//            Intent intent =
//                    new Intent(mContext, LoginActivity.class);
            ViewManager.getInstance().exitApp(App.app);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            mContext.startActivity(intent);
//            System.exit(0);// 关闭已奔溃的app进程
        }
    }

    /**
     * 自定义错误捕获
     *
     * @param ex
     * @return true:如果处理了该异常信息;否则返回false.
     */
    private boolean handleException(Throwable ex) {
        if (ex == null) {
            return false;
        }
        uploadCrash(ex);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 收集错误信息
        getCrashInfo(ex);
        return true;
    }

    /**
     * 收集要上传的日志存在SharedPreferences
     * @param ex
     */
    private void uploadCrash(Throwable ex) {
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();
        String errorMessage = writer.toString();
        CrashLog crashLog = new CrashLog();
        crashLog.setAppVersion("APP版本:"+ AppUtils.getAppInfo(App.app).getVersionCode());
        crashLog.setCreate_date(getNowTime());
        UserBean user = PreferencesHelper.Companion.getUserInfo();
        if (user != null){
            crashLog.setUserId(user.getLoginCode());
        }
        crashLog.setDeviceType(AppUtils.getDeviceBrand());
        crashLog.setDeviceVendor(AppUtils.getSystemModel());
        crashLog.setSystemVersion(AppUtils.getSystemVersion());
        crashLog.setLog(errorMessage);
        PreferencesHelper.Companion.putCrashLog(crashLog);
    }

    /**
     * 收集错误信息
     *
     * @param ex
     */
    private void getCrashInfo(Throwable ex) {
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        String time = getNowTime();
        printWriter.println(TimeUtil.getNowDate());
        UserBean user = PreferencesHelper.Companion.getUserInfo();
        if (user != null){
            printWriter.println("用户手机号:"+user.getLoginCode());
        }
        printWriter.println("APP版本:"+AppUtils.getAppInfo(App.app).getVersionCode());
        printWriter.println("系统版本:"+AppUtils.getSystemVersion());
        printWriter.println("手机型号:"+AppUtils.getSystemModel());
        printWriter.println("手机厂商:"+AppUtils.getDeviceBrand());
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();
        String errorMessage = writer.toString();
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            String mFilePath = Environment.getExternalStorageDirectory()
                    + File.separator+ AppUtils.getAppInfo(App.app).getName()
                    + File.separator + "errorInfo"
                    + File.separator+time+"error.txt";
            FileUtil.writeFileToSDCardFromString(errorMessage,mFilePath);
            Log.i("msg", "哦豁 存好了...");
        } else {
            //

           Log.i("msg", "哦豁，说好的SD呢...");
        }

    }
    //获取当前时间
    private String getNowTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// HH:mm:ss
        Date date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);
    }


}
