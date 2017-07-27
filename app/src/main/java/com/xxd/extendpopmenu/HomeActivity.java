package com.xxd.extendpopmenu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv1, tv2, tv3, tv4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initView();
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

                break;
            case R.id.tv_choice_2:

                break;
            case R.id.tv_choice_3:

                break;
            case R.id.tv_choice_4:

                break;
        }
    }
}
