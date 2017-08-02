package com.xxd.extendpopmenu;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xxd.extendpopmenu.entity.BackExtendHeader;
import com.xxd.extendpopmenu.entity.BackHotal;
import com.xxd.extendpopmenu.entity.BackHotalBase;
import com.xxd.extendpopmenu.entity.ExtendData;
import com.xxd.extendpopmenu.entity.ExtendItem;
import com.xxd.extendpopmenu.listener.ExtendViewClickListener;
import com.xxd.extendpopmenu.net.RetrofitAssistant;
import com.xxd.extendpopmenu.trans.ExtendDataTrans;
import com.xxd.extendpopmenu.utils.AssertUtil;
import com.xxd.extendpopmenu.utils.ExtendUtil;
import com.xxd.extendpopmenu.view.ExtendControl;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv1, tv2, tv3, tv4;
    private FrameLayout fl_fragment_contain;
    private LinearLayout ll_home_tag;
    private List<BackExtendHeader> data;
    private ExtendControl control;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initView();
        initData();
    }

    private void initData() {
        InputStream is = this.getResources().openRawResource(R.raw.filter3);
        Reader reader = new InputStreamReader(is);
//        Toast.makeText(this, "找不到json文件", Toast.LENGTH_SHORT).show();
        Gson gson = new Gson();
        List<BackExtendHeader> back = gson.fromJson(reader, new TypeToken<List<BackExtendHeader>>() {
        }.getType());
        data = back;
        BackExtendHeader header1 = back.get(0);
        String type = header1.getType();
        Log.e("xxd", "当前类型：" + type);

        translateData();
    }

    private void translateData() {
        List<ExtendData> parse = ExtendDataTrans.parse(data);
        ExtendData data = parse.get(1);
        Log.e("xxd", "总层级数：" + data.getTotalLevel());
        control = new ExtendControl(this, R.id.fl_fragment_contain, parse);
        control.setOnExtendViewClickListener(new ExtendViewClickListener() {
            @Override
            public void onItemClick() {
                showTag();
            }
        });
    }

    private void initView() {
        tv1 = (TextView) findViewById(R.id.tv_choice_1);
        tv2 = (TextView) findViewById(R.id.tv_choice_2);
        tv3 = (TextView) findViewById(R.id.tv_choice_3);
        tv4 = (TextView) findViewById(R.id.tv_choice_4);
        fl_fragment_contain = (FrameLayout) findViewById(R.id.fl_fragment_contain);
        ll_home_tag = (LinearLayout) findViewById(R.id.ll_home_tag);

        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);
        tv4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_choice_1:
                control.showByType("1");
                break;
            case R.id.tv_choice_2:
                control.showByType("2");
                break;
            case R.id.tv_choice_3:
                control.showByType("3");
                break;
            case R.id.tv_choice_4:
                control.showByType("4");
//                Call<BackHotalBase> call = RetrofitAssistant.getGsonService().hotalClassifySearch(8 + "", "亲子", 0 + "", 10 + "");
//                call.enqueue(new Callback<BackHotalBase>() {
//                    @Override
//                    public void onResponse(Call<BackHotalBase> call, Response<BackHotalBase> response) {
//                        Toast.makeText(getApplicationContext(),"请求成功！",Toast.LENGTH_SHORT).show();
//                        BackHotalBase body = response.body();
//                        String s = body.toString();
//                    }
//
//                    @Override
//                    public void onFailure(Call<BackHotalBase> call, Throwable t) {
//                        Toast.makeText(getApplicationContext(),"失败！"+t.getMessage(),Toast.LENGTH_SHORT).show();
//                    }
//                });
//                break;
        }
    }

    private void showTag() {
        List<ExtendData.Tag> tags = control.getTags();
        ll_home_tag.removeAllViews();
        if (!AssertUtil.isEmpty(tags)) {
            for (final ExtendData.Tag tag : tags) {
                final TextView tv = new TextView(this);
                tv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                tv.setPadding(20, 5, 5, 20);
                tv.setTextSize(18);
                tv.setTextColor(Color.BLACK);
                tv.setText(tag.generateTagContent());
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        control.removeTag(tag);
                        ll_home_tag.removeView(tv);
                    }
                });
                ll_home_tag.addView(tv);
            }
        }
    }
}
