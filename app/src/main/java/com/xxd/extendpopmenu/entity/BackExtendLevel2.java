package com.xxd.extendpopmenu.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by 河图 on 2017/7/28.
 */

public class BackExtendLevel2 {

    @SerializedName("paramCode")
    private String code;
    @SerializedName("paramName")
    private String name;
    @SerializedName("content")
    private List<BackExtendLevel2> contentList;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BackExtendLevel2> getContentList() {
        return contentList;
    }

    public void setContentList(List<BackExtendLevel2> contentList) {
        this.contentList = contentList;
    }
}
