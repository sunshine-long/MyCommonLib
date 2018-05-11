package com.unht.myutils.utils;

import android.bluetooth.BluetoothAdapter;

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
