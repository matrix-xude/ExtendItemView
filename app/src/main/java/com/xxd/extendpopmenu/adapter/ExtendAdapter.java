package com.xxd.extendpopmenu.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xxd.extendpopmenu.R;
import com.xxd.extendpopmenu.entity.ExtendItem;
import com.xxd.extendpopmenu.utils.ViewHolderUtil;

import java.util.List;

/**
 * Created by xxd on 2017/7/29.
 */

public class ExtendAdapter extends BaseAdapter {

    private Context context;
    private List<ExtendItem> list;

    public ExtendAdapter(Context context, List<ExtendItem> list) {
        this.context = context;
        this.list = list;
    }

    public List<ExtendItem> getList() {
        return list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.extend_item, null);
        }
        TextView tv_name = ViewHolderUtil.get(convertView, R.id.tv_extend_item);

        ExtendItem item = list.get(position);

        tv_name.setText(item.getName());
        if (item.isChoice()) {
            tv_name.setBackgroundColor(Color.YELLOW);
        } else {
            tv_name.setBackgroundColor(Color.WHITE);
        }
        return convertView;
    }
}
