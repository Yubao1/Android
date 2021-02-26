package com.example.administrator.myapplication.bean;

import java.util.List;

/**
 * Created by Administrator on 2020/3/28.
 */

public class ManualTypeDataBean {

    private int id;
    private String title;
    private int sort;
    private List<OptionBean> options;

    private String resultTitle;
    private int resultImg;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<OptionBean> getOptions() {
        return options;
    }

    public void setOptions(List<OptionBean> options) {
        this.options = options;
    }

    public String getResultTitle() {
        return resultTitle;
    }

    public void setResultTitle(String resultTitle) {
        this.resultTitle = resultTitle;
    }

    public int getResultImg() {
        return resultImg;
    }

    public void setResultImg(int resultImg) {
        this.resultImg = resultImg;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return "ManualTypeDataBean{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", sort=" + sort +
                ", options=" + options +
                ", resultTitle='" + resultTitle + '\'' +
                ", resultImg=" + resultImg +
                '}';
    }
}
