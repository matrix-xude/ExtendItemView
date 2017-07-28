package com.xxd.extendpopmenu.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by 河图 on 2017/7/28.
 */

public class BackExtendHeader {

    @SerializedName("paramType")
    private String type;
    @SerializedName("paramContent")
    private List<BackExtendLevel1> list = null;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<BackExtendLevel1> getList() {
        return list;
    }

    public void setList(List<BackExtendLevel1> list) {
        this.list = list;
    }
}
