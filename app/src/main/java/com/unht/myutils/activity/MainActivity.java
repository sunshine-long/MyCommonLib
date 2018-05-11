package com.unht.myutils.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.unht.myutils.R;
import com.unht.myutils.utils.LitePrefUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textSPutils();
    }

    /**
     * 测试LitePrefUtils 工具类是否可行
     *
     */
    private void textSPutils() {
        LitePrefUtils.getInstance(this).putValue("test", "llalalala");
        String string = (String) LitePrefUtils.getInstance(this).getValue("test", null);
        LitePrefUtils.getInstance(this).setFileName("userInfo").putValue("text2", "这是测试有文件名的情况！");
        Toast.makeText(this, string, Toast.LENGTH_LONG).show();
    }

    public void toLiteSP(View view) {
        startActivity(new Intent(this, TestLiteSPUtilActivity.class));
    }

}
