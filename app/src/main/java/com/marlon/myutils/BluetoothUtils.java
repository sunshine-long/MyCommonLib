package com.marlon.myutils;

import android.bluetooth.BluetoothAdapter;
/**
 * @desc BluetoothUtils 用于蓝牙使用的相关工具类
 * @author Marlon
 * @date 2018/8/23
 */
public class BluetoothUtils {

    private static BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

    /**
     * 判断是否支持蓝牙
     *
     * @return
     */
    public static boolean supportBluetooth() {
        if (mBluetoothAdapter == null) {
            return false;
        }
        return true;
    }


    public static boolean enableBluetooth() {
        if (mBluetoothAdapter == null) {
            return false;
        }
        return mBluetoothAdapter.enable();
    }

    public static boolean disableBluetooth() {
        if (mBluetoothAdapter == null) {
            return false;
        }
        return mBluetoothAdapter.disable();
    }

    public static boolean isEnable() {
        if (mBluetoothAdapter == null) {
            return false;
        }
        return mBluetoothAdapter.isEnabled();
    }

}
