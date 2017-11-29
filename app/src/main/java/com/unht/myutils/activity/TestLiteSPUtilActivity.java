package com.unht.myutils.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.unht.myutils.R;
import com.unht.myutils.utils.LitePrefUtils;

public class TestLiteSPUtilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String string = (String) LitePrefUtils.getInstance(this).getValue("text2",null);
        Toast.makeText(this, string,Toast.LENGTH_LONG).show();
    }
}