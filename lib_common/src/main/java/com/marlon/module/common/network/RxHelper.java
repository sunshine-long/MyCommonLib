package com.marlon.module.common.network;

import android.content.Context;
import android.widget.Toast;

import net.uwonders.module.common.R;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 封装RX线程相关，主要用于控制线程切换，和进行相关预处理
 *
 * @author 康龙
 * @date 2017/5/10
 */

public class RxHelper {
    public static <T> ObservableTransformer<T, T> io_main(final Context context) {
        return observable -> observable
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(disposable -> {
                    if (!NetworkUtil.isNetworkAvailable(context)) {
                        Toast.makeText(context, R.string.toast_network_error, Toast.LENGTH_SHORT).show();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread());
    }

}
