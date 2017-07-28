package com.xxd.extendpopmenu.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by 河图 on 2017/7/28.
 */

public class BackExtendLevel1 {

    @SerializedName("title")
    private String title;
    @SerializedName("content")
    private List<BackExtendLevel2> contentList = null;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<BackExtendLevel2> getContentList() {
        return contentList;
    }

    public void setContentList(List<BackExtendLevel2> contentList) {
        this.contentList = contentList;
    }
}
