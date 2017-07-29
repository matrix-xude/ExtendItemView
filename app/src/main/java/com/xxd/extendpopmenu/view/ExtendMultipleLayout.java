package com.xxd.extendpopmenu.view;

import android.content.Context;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by xxd on 2017/7/28.
 */

public class ExtendMultipleLayout extends LinearLayout{

    public ExtendMultipleLayout(Context context) {
        this(context,null);
    }

    public ExtendMultipleLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ExtendMultipleLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(LinearLayout.HORIZONTAL);
    }
}
