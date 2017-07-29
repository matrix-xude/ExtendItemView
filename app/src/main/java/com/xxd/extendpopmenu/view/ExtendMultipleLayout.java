package com.xxd.extendpopmenu.view;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.xxd.extendpopmenu.entity.ExtendData;
import com.xxd.extendpopmenu.fragments.ExtendFragment;

import java.util.List;
import java.util.Map;

/**
 * Created by xxd on 2017/7/28.
 */

public class ExtendMultipleLayout extends FrameLayout{


    private Map<String,ExtendFragment> fragmentMap;
    private Activity activity;

    public ExtendMultipleLayout(Context context) {
        this(context,null);
    }

    public ExtendMultipleLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ExtendMultipleLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setData(List<ExtendData> list){

    }

    public void showType(String type){
        ExtendFragment fragment = fragmentMap.get(type);
        FragmentManager fm = activity.getFragmentManager();
        fm.beginTransaction();

        if(fragment == null){

        }else {

        }
    }

}
