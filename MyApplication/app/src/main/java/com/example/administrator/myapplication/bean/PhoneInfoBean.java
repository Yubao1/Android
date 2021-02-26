package com.example.administrator.myapplication.bean;

/**
 * Created by Administrator on 2020/3/28.
 */

public class PhoneInfoBean {
    private int infoId;
    private int brandId;
    private String brand;
    private int detailId;
    private String model;
    private EstimateOptions estimateOptions;

    public int getInfoId() {
        return infoId;
    }

    public void setInfoId(int infoId) {
        this.infoId = infoId;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getDetailId() {
        return detailId;
    }

    public void setDetailId(int detailId) {
        this.detailId = detailId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public EstimateOptions getEstimateOptions() {
        return estimateOptions;
    }

    public void setEstimateOptions(EstimateOptions estimateOptions) {
        this.estimateOptions = estimateOptions;
    }

    @Override
    public String toString() {
        return "PhoneInfoBean{" +
                "infoId=" + infoId +
                ", brandId=" + brandId +
                ", brand='" + brand + '\'' +
                ", detailId=" + detailId +
                ", model='" + model + '\'' +
                ", estimateOptions=" + estimateOptions +
                '}';
    }
}
