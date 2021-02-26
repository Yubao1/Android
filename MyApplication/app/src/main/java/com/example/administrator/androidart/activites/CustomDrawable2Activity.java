package com.example.administrator.androidart.activites;

import android.app.ListActivity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.R;
import com.example.administrator.androidart.drawable.CircleImageDrawable;
import com.example.administrator.androidart.drawable.RoundImageDrawable;
import com.example.administrator.androidart.views.MessageListItem;
import com.example.administrator.myapplication.XActivity;

public class CustomDrawable2Activity extends XActivity {
    @Override
    public void init() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.img);
        ImageView iv = (ImageView) findViewById(R.id.id_msg_item_icon);
        iv.setImageDrawable(new CircleImageDrawable(bitmap));
    }

    @Override
    public int getId() {
        return R.layout.activity_custom_drawable2;
    }
}
