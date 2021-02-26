package com.example.administrator.myapplication.other;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

/**
 * Created by Administrator on 2021/1/9.
 */

public class User extends BaseObservable {
    private String name;
//    private String header;

    public User(String name) {
        this.name = name;
//        this.header = header;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(1);
    }



//    public String getHeader() {
//        return header;
//    }
//
//    public void setHeader(String header) {
//        this.header = header;
//    }


//    @BindingAdapter("bind:header")
//    public static void getImageView(ImageView imageView, String url) {
//        Picasso.with(imageView.getContext()).load(url).into(imageView);
//    }
}
