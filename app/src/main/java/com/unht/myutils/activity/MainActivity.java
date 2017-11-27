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

        LitePrefUtils.getInstance(this).putValue("test", "llalalala");
        String string = (String) LitePrefUtils.getInstance(this).getValue("test", null);
        LitePrefUtils.getInstance(this).setFileName("userInfo").putValue("text2", "这是测试有文件名的情况！");
        Toast.makeText(this, string, Toast.LENGTH_LONG).show();
        test1();
    }

    public void toLiteSP(View view) {
        startActivity(new Intent(this, TestLiteSPUtilActivity.class));
    }


    private void test1() {
        String[] arrayA = new String[]{"a", "b"};
        String[] arrayB = arrayA.clone();
        arrayB[0] = "c";
        pllaytlnArray(arrayA);
        pllaytlnArray(arrayB);

        arrayB = null;

        pllaytlnArray(arrayA);
        pllaytlnArray(arrayB);
    }

    private void pllaytlnArray(String[] array) {
        if (array == null) {
            return;
        } else {
            for (int i = 0; i < array.length;i++ ){
                System.out.print(array[i]);
            }
        }
        System.out.println();
    }


}
