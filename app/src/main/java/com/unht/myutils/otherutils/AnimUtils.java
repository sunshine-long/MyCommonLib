package com.unht.myutils.otherutils;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;

/**
 * 动画工具类
 * Created by Administrator on 2015/10/10 0010.
 */
public class AnimUtils {

    /**
     * 给View加点击动画，利用开源库nineoldandroids设置动画
     * @param view 要生成动画的view
     */
    public static void addAnimation(View view){
        float [] vaules = new float[]{0.8f, 0.9f, 1.0f, 1.1f,1.2f, 1.2f,1.1f,1.1f,1.0f};
        AnimatorSet set = new AnimatorSet();
        set.playTogether(ObjectAnimator.ofFloat(view, "scaleX", vaules),ObjectAnimator.ofFloat(view, "scaleY", vaules));
        set.setDuration(150);
        LinearInterpolator linearInterpolator = new LinearInterpolator();
        set.setInterpolator(linearInterpolator);
        set.start();
    }


    /**
     * 给View加点击动画，利用开源库nineoldandroids设置动画
     * @param view 要生成动画的view
     */
    public static void breatheAnimation(View view){
        float [] vaules = new float[]{1.0f, 1.1f,1.2f,/*1.3f,1.3f,1.3f,1.2f,*/1.1f,1.0f};
        AnimatorSet set = new AnimatorSet();
        set.playTogether(ObjectAnimator.ofFloat(view, "scaleX", vaules),ObjectAnimator.ofFloat(view, "scaleY", vaules));
        LinearInterpolator linearInterpolator = new LinearInterpolator();
        set.setInterpolator(linearInterpolator);
        set.setDuration(1800);
        set.start();
    }

    /**
     * 获取旋转动画
     *
     * @return 选择动画
     */
    public static RotateAnimation getRotateAnimation(){
        RotateAnimation   ra = new RotateAnimation(0, 359, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        ra.setDuration(2000);
        LinearInterpolator linearInterpolator = new LinearInterpolator();
        ra.setInterpolator(linearInterpolator);
        // 永远不停止 一致运行
        ra.setRepeatCount(Animation.INFINITE);
        return ra;
    }

    /**
     * 隐藏和显示动画
     * @return
     */
    public static TranslateAnimation getVisible(){
        TranslateAnimation mShowAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        mShowAction.setDuration(500);
        return mShowAction;
    }

    public static RotateAnimation  upRtAnimation() {
        RotateAnimation upRt = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        upRt.setDuration(1000);
        upRt.setFillAfter(true);
        return upRt;

    }

    public static RotateAnimation downRtAnimation(){
        RotateAnimation  downRt = new RotateAnimation(-180, -360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        downRt.setDuration(500);
        downRt.setFillAfter(true);
        return downRt;
    }
}
