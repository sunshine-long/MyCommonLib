package com.uwonders.tobaccomangager.customview.dialog

import android.R
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Build


/**
 * @author by kanglong
 * @date on 2020-03-22.
 * 用于快速创建原生AlertDialog
 */
class AlertDialogUtils {
    companion object {
        //创建一个响应确定事件的dialog
        fun showDialog(
            context: Context,
            msgString: String,
            listener: DialogInterface.OnClickListener?
        ) {
            var builder: AlertDialog.Builder?
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                builder = AlertDialog.Builder(
                    context, R.style.Theme_Material_Light_Dialog_Alert
                )
            } else {
                builder = AlertDialog.Builder(context)
            }
            builder.setMessage(msgString)
                .setPositiveButton("确定", listener)
                .setNegativeButton("取消", null)
                .setCancelable(false)
                .show()
        }

        /**
         * 用于快速创建Alerdialog
         */
        fun showDialogAndCancle(
            context: Context,
            msgString: String,
            listener: DialogInterface.OnClickListener?,
            cancleListener: DialogInterface.OnClickListener
        ) {
            var builder: AlertDialog.Builder?
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                builder = AlertDialog.Builder(
                    context, R.style.Theme_Material_Light_Dialog_Alert
                )
            } else {
                builder = AlertDialog.Builder(context)
            }
            builder.setMessage(msgString)
                .setPositiveButton("确定", listener)
                .setNegativeButton("取消", cancleListener)
                .show()
        }

        /**
         *
         * 创建没有点击事件的dialog
         */
        fun showNoticeDialog(
            context: Context,
            msgString: String
        ) {
            var builder: AlertDialog.Builder?
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                builder = AlertDialog.Builder(
                    context, R.style.Theme_Material_Light_Dialog_Alert
                )
            } else {
                builder = AlertDialog.Builder(context)
            }
            builder.setMessage(msgString)
                .setPositiveButton("确定", null)
                .show()
        }

        /**
         * 创建一个有title的dialog
         */
        fun showDialog(
            context: Context,
            title: String,
            msgString: String,
            listener: DialogInterface.OnClickListener?
        ) {
            var builder: AlertDialog.Builder?
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                builder = AlertDialog.Builder(
                    context, R.style.Theme_Material_Light_Dialog_Alert
                )
            } else {
                builder = AlertDialog.Builder(context)
            }
            builder.setMessage(msgString)
                .setTitle(title)
                .setPositiveButton("确定", listener)
                .setNegativeButton("取消", null)
                .setCancelable(false)
                .show()
        }
        //创建一个只响应确定事件的dialog
        fun showOnlySureDialog(
            context: Context,
            msgString: String,
            listener: DialogInterface.OnClickListener?
        ) {
            var builder: AlertDialog.Builder?
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                builder = AlertDialog.Builder(
                    context, R.style.Theme_Material_Light_Dialog_Alert
                )
            } else {
                builder = AlertDialog.Builder(context)
            }
            builder.setMessage(msgString)
                .setPositiveButton("确定", listener)
                .setCancelable(false)
                .show()
        }

    }


}