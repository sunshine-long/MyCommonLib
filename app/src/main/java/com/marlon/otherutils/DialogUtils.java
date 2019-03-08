package com.marlon.otherutils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.marlon.myutils.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author Sunshine
 * @Description dialog相关工具类
 */
public class DialogUtils {

    public static Uri imageUri = null;
    /**
     * 加载对话框
     *
     * @param context 上下文
     * @param message 文本信息
     * @return dialog对象
     */
    public static Dialog getLoadingDialog(Context context, String message) {
        Dialog dialog = new Dialog(context, R.style.no_title);
        View view = View.inflate(context, R.layout.dialog_load, null);
        if (!TextUtils.isEmpty(message)){
            ((TextView) view.findViewById(R.id.tv_message)).setText(message);
        }
        ImageView imageView = (ImageView) view.findViewById(R.id.image);
        imageView.startAnimation(AlertAnimateUtil.getRotateAnimation());
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(view);
        return dialog;
    }

    public static void cropPhoto(Context context, Uri uri,int requestCode) {
        // 裁剪图片意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);
        // 图片格式
        intent.putExtra("outputFormat", "JPEG");
        // 取消人脸识别
        intent.putExtra("noFaceDetection", true);
        intent.putExtra("return-data", true);
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CUT
        ((Activity) context).startActivityForResult(intent, requestCode);
    }


    public  static Dialog getDilog(Context context,View view){
        final Dialog dialog = new Dialog(context, com.fitsleep.sunshinelibrary.R.style.no_title);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams windowparams = window.getAttributes();
        window.setGravity(Gravity.BOTTOM);
        Rect rect = new Rect();
        View view1 = window.getDecorView();
        view1.getWindowVisibleDisplayFrame(rect);
        windowparams.width = ScreenUtils.getScreenWidth(context);
        window.setBackgroundDrawableResource(android.R.color.transparent);
        window.setAttributes((WindowManager.LayoutParams) windowparams);
        // 设置显示动画
        window.setWindowAnimations(com.fitsleep.sunshinelibrary.R.style.main_menu_animstyle);
        dialog.setContentView(view);
        return dialog;
    }



    public static void photoAndCamera(final Context context){
        new AlertView("上传头像", null, "取消", null,new String[]{"拍照", "从相册中选择"},context, AlertView.Style.ActionSheet, new OnItemClickListener() {
            @Override
            public void onItemClick(Object o, int position) {
                if (position==1){
                    Intent intent;
                    if (Build.VERSION.SDK_INT < 19) {
                        intent = new Intent(Intent.ACTION_GET_CONTENT);
                        intent.setType("image/*");
                    } else {
                        intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                    }
                    ((Activity) context).startActivityForResult(intent, 0x009);
                }else if (position==0){
                    imageUri = null;
                    getPhoto(context);
                }
            }
        }).setCancelable(true).show();
    }

    /**
     * 获取相片
     */
    protected static void getPhoto(Context context) {
        if (!SDCardUtils.isSDCardEnable()) {
            Toast.makeText(context, "sDCard不可用!", Toast.LENGTH_SHORT).show();
        } else {
            try {
                String filePath = SDCardUtils.getSDCardPath();
                String imagePath = getPhotoFileName();
                // 下面这句指定调用相机拍照后的照片存储的路径
                File dir = new File(filePath + "/NokeLockCach");
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                File tmpFile = new File(dir, imagePath);
                if (!tmpFile.exists()) {
                    tmpFile.createNewFile();
                }
                if (Build.VERSION.SDK_INT >= 24) {
                    imageUri = FileProvider.getUriForFile(context, "com.nokelock.bike.m.fileprovider", tmpFile);//通过FileProvider创建一个content类型的Uri
                } else {
                    imageUri = Uri.fromFile(tmpFile);
                }
                Intent intent = new Intent();
                if (Build.VERSION.SDK_INT >= 24) {
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); //添加这一句表示对目标应用临时授权该Uri所代表的文件
                }
                intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);//设置Action为拍照
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);//将拍取的照片保存到指定URI
                ((Activity) context).startActivityForResult(intent, 0x586);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void cropPhoto(Context context, Uri uri) {
        Logger.e("photo","uri:"+uri);
        // 裁剪图片意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        if (Build.VERSION.SDK_INT >= 24) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);
        // 图片格式
        intent.putExtra("outputFormat", "JPEG");
        // 取消人脸识别
        intent.putExtra("noFaceDetection", true);
        intent.putExtra("return-data", true);
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CUT
        ((Activity) context).startActivityForResult(intent, 0x587);

    }

    // 使用系统当前日期加以调整作为照片的名称
    public static String getPhotoFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("'IMG'_yyyyMMdd_HHmmss");
        return dateFormat.format(date) + ".jpg";
    }
}
