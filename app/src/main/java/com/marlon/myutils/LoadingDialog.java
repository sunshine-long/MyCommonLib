package com.marlon.myutils;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * @author Marlon
 * @date 2017/11/30
 */

public class LoadingDialog extends ProgressDialog{
    public LoadingDialog(Context context) {
        super(context);
    }

    public LoadingDialog(Context context, int theme) {
        super(context, theme);
    }
}
