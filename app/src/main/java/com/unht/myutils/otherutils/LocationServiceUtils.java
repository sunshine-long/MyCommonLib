package com.unht.myutils.otherutils;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings;

/**
 * 作者: Sunshine
 * 时间: 2017/6/21.
 * 邮箱: 44493547@qq.com
 * 描述:
 */

public class LocationServiceUtils {
    private static final String TAG = "LocationServiceUtils";

    /**
     * 判断是否启动定位服务
     *
     * @param context 全局信息接口
     * @return 是否启动定位服务
     */
    public static boolean isOpenLocService(final Context context) {

        boolean isGps = false; //判断GPS定位是否启动
        boolean isNetwork = false; //判断网络定位是否启动

        if (context != null) {

            LocationManager locationManager
                    = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

            if (locationManager != null) {
                //通过GPS卫星定位，定位级别可以精确到街（通过24颗卫星定位，在室外和空旷的地方定位准确、速度快）
                isGps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
                //通过WLAN或移动网络(3G/2G)确定的位置（也称作AGPS，辅助GPS定位。主要用于在室内或遮盖物（建筑群或茂密的深林等）密集的地方定位）
                isNetwork = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            }

            if (isGps || isNetwork) {
                return true;
            }

        }

        return false;
    }

    /**
     * 判断是否启动全部网络连接，包括WIFI和流量
     *
     * @param context 全局信息接口
     * @return 是否连接到网络
     */
    public static boolean isNetworkConnected(Context context) {

        if (context != null) {

            ConnectivityManager mConnectivityManager =
                    (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();

            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }

        }
        return false;
    }

    /**
     * 判断是否启动WIFI连接
     *
     * @param context 全局信息接口
     * @return 是否连接到WIFI
     */
    public static boolean isWifiConnected(Context context) {

        if (context != null) {

            WifiManager wifi = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);

            if (wifi != null) {
                return wifi.isWifiEnabled();
            }

        }

        return false;
    }

    /**
     * 跳转定位服务界面
     *
     * @param context 全局信息接口
     */
    public static void gotoLocServiceSettings(Context context) {
        final Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 跳转WIFI服务界面
     *
     * @param context 全局信息接口
     */
    public static void gotoWifiServiceSettings(Context context) {
        final Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

}
