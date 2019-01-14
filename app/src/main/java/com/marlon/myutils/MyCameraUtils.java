package com.marlon.myutils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.widget.Toast;

import com.yalantis.ucrop.UCrop;
import com.yalantis.ucrop.UCropActivity;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;

import java.io.File;
import java.io.IOException;


/**
 * @author KangLong
 * @date 2017/5/9
 * @description CameraUtils用于调用系统相机 读取图片  剪切 保存到本地 申请相机权限等
 */

public class MyCameraUtils {
    private static final String TAG = "MyCameraUtils";
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
    //相机拍照后的URI
    public static Uri imageUriFromCamera;
    //图片剪切后的URI
    public static Uri cropImageUri;

    private Uri sourceUri;

    /**
     * 打开相机,拍照后返回存入URI
     *
     * @param thiz
     */
    public static void openCameraImage(Activity thiz) {
        imageUriFromCamera = createImagePathUri(thiz);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUriFromCamera);
        // 调用前置摄像头
        intent.putExtra("camerasensortype", 2);
        // 自动对焦
//        intent.putExtra("autofocus", true);

        thiz.startActivityForResult(intent, GET_IMAGE_BY_CAMERA);
    }

    public static void openCameraImage(Fragment thiz) {
        imageUriFromCamera = createImagePathUri(thiz.getActivity());
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUriFromCamera);
        // 调用前置摄像头
        intent.putExtra("camerasensortype", 2);
        // 自动对焦
//        intent.putExtra("autofocus", true);
        thiz.startActivityForResult(intent, GET_IMAGE_BY_CAMERA);
    }

    /**
     * 打开相册,选择图片后返回URI
     *
     * @param thiz
     */
    public static void openLocalImage(Activity thiz) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_PICK);
        thiz.startActivityForResult(intent, GET_IMAGE_FROM_PHONE);
    }

    public static void openLocalImage(Fragment thiz) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_PICK);
        thiz.startActivityForResult(intent, GET_IMAGE_FROM_PHONE);
    }

    /**
     * 剪切图片后存入URI
     *
     * @param thiz
     * @param srcUri
     */
    public static void cropImageSquare(Activity thiz, Uri srcUri, int outputX, int outputY) {
        cropImageUri = createImagePathUri(thiz);
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setDataAndType(srcUri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("outputX", outputX);
        intent.putExtra("outputY", outputY);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, cropImageUri);
        intent.putExtra("return-data", false);
        thiz.startActivityForResult(intent, CROP_IMAGE);
    }

    public static void cropImageSquare(Fragment thiz, Uri srcUri, int outputXY) {
        cropImageUri = createImagePathUri(thiz.getActivity());
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setDataAndType(srcUri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", outputXY);
        intent.putExtra("outputY", outputXY);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, cropImageUri);
        intent.putExtra("return-data", false);
        thiz.startActivityForResult(intent, CROP_IMAGE);
    }


    public static void openViedo(Activity thiz) {
        Intent intent = new Intent();
        intent.setType("video/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        thiz.startActivityForResult(intent, OPEN_VIDEO);
    }

    /**
     * 通过时间创建图片URI
     *
     * @param context
     * @return
     */
    public static Uri createImagePathUri(Context context) {
        Uri imageFilePath = null;
        File file = CommonUtils.createImageFile("IMG" + SystemClock.uptimeMillis() + ".jpg");
        try {
            file.createNewFile();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                String applicationid = AppUtils.getAppInfo(context).getPackageName();
                imageFilePath = FileProvider.getUriForFile(context, applicationid + ".provider", file);
            } else {
                imageFilePath = Uri.fromFile(file);
            }
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(context.getApplicationContext(), "文件存储路径异常", Toast.LENGTH_SHORT).show();
        }
        return imageFilePath;
    }

    /**
     * 获取相机权限
     *
     * @param context
     */
    public static void requestPermission(final Context context, int permission_code, String[]... permission) {
        AndPermission.with(context).requestCode(permission_code)
                .permission(permission)
                .rationale(new RationaleListener() {
                    @Override
                    public void showRequestPermissionRationale(int requestCode, Rationale rationale) {
                        // 此对话框可以自定义，调用rationale.resume()就可以继续申请。
                        AndPermission.rationaleDialog(context, rationale).show();
                    }
                }).callback(context)
                .start();
    }


    /**
     * 读取图片的旋转的角度
     *
     * @param path 图片绝对路径
     * @return 图片的旋转角度
     */
    public static int getBitmapDegree(String path) {
        int degree = 0;
        try {
            // 从指定路径下读取图片，并获取其EXIF信息
            ExifInterface exifInterface = new ExifInterface(path);
            // 获取图片的旋转信息
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
                default:
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }


    /**
     * 将图片按照某个角度进行旋转
     *
     * @param bm     需要旋转的图片
     * @param degree 旋转角度
     * @return 旋转后的图片
     */
    public static Bitmap rotateBitmapByDegree(Bitmap bm, int degree) {
        Bitmap returnBm = null;

        // 根据旋转角度，生成旋转矩阵
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        try {
            // 将原始图片按照旋转矩阵进行旋转，并得到新的图片
            returnBm = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight(), matrix, true);
        } catch (OutOfMemoryError e) {
        }
        if (returnBm == null) {
            returnBm = bm;
        }
        if (bm != returnBm) {
            bm.recycle();
        }
        return returnBm;
    }


    /**
     * 启动Urop剪切图片
     *
     * @param activity
     * @param sourceUri
     * @param requestCode
     * @param aspectRatioX
     * @param aspectRatioY
     * @return
     */
    public static Uri startUCrop(Activity activity, Uri sourceUri, int requestCode, float aspectRatioX, float aspectRatioY) {
        File outFile = CommonUtils.createImageFile("cover.jpg");
        cropImageUri = Uri.fromFile(outFile);
        UCrop uCrop = UCrop.of(sourceUri, cropImageUri);
        UCrop.Options options = new UCrop.Options();
        options.setAllowedGestures(UCropActivity.SCALE, UCropActivity.ROTATE, UCropActivity.ALL);
        options.setCompressionFormat(Bitmap.CompressFormat.JPEG);
        options.setCompressionQuality(100);
        options.setHideBottomControls(true);
        options.setFreeStyleCropEnabled(true);
        uCrop.withOptions(options);
        uCrop.withAspectRatio(aspectRatioX, aspectRatioY);
        uCrop.start(activity, requestCode);
        return cropImageUri;
    }
}
