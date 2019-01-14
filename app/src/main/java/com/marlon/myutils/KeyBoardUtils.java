package com.marlon.myutils;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.marlon.myutils.app.App;


/**
 *
 * @author KangLong
 * @date 2017/7/8
 * 系统键盘的调用和隐藏
 */
public class KeyBoardUtils {
    /**
     * @throws
     * @MethodName:closeInputMethod
     * @Description:关闭系统软键盘
     */
    static public void closeKeyBoard(View view) {

        try {
            //获取输入法的服务
            InputMethodManager imm = (InputMethodManager) App.getInstance().getSystemService(Context.INPUT_METHOD_SERVICE);
            //判断是否在激活状态
            if (imm.isActive()) {
                //隐藏输入法!!,.
                imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }

        } catch (Exception e) {
        } finally {
        }

    }

    /**
     * @throws
     * @MethodName:openInputMethod
     * @Description:打开系统软键盘
     */

    static public void openKeyBoard(EditText editText) {
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        InputMethodManager inputManager = (InputMethodManager) App.getInstance().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.showSoftInput(editText, 0);
    }


}
