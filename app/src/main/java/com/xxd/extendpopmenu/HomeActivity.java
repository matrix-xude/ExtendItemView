package com.xxd.extendpopmenu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xxd.extendpopmenu.entity.BackExtendHeader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv1, tv2, tv3, tv4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initView();

    }

    private void initData() {
        InputStream is = this.getResources().openRawResource(R.raw.filter3);
        Reader reader = new InputStreamReader(is);
//        Toast.makeText(this, "找不到json文件", Toast.LENGTH_SHORT).show();
        Gson gson = new Gson();
        List<BackExtendHeader> back = gson.fromJson(reader, new TypeToken<List<BackExtendHeader>>() {
        }.getType());
        BackExtendHeader header1 = back.get(0);
        String type = header1.getType();
        Log.e("xxd", "当前类型：" + type);
    }

    private void initView() {
        tv1 = (TextView) findViewById(R.id.tv_choice_1);
        tv2 = (TextView) findViewById(R.id.tv_choice_2);
        tv3 = (TextView) findViewById(R.id.tv_choice_3);
        tv4 = (TextView) findViewById(R.id.tv_choice_4);

        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);
        tv4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_choice_1:
                initData();
                break;
            case R.id.tv_choice_2:

                break;
            case R.id.tv_choice_3:

                break;
            case R.id.tv_choice_4:

                break;
        }
    }

    private void popWindow() {
        PopupWindow pop = new PopupWindow();
    }
}
