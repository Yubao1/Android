package com.example.administrator.androidart.activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.administrator.R;
import com.example.administrator.myapplication.XActivity;

public class DrawableActivity extends XActivity {

    @Override
    public void init() {

    }

    @Override
    public int getId() {
        return R.layout.activity_drawable;
    }
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_bitmap_drawable:
                startActivity(BitmapDrawableActivity.class);
                break;
            case R.id.btn_shape_drawable:
                startActivity(ShapeDrawableActivity.class);
                break;
            case R.id.btn_nine_patch_drawable:
                startActivity(NinePatchDrawableActivity.class);
                break;
            case R.id.btn_layer_drawable:
                startActivity(LayoutDrawableActivity.class);
                break;
            case R.id.btn_state_list_drawable:
                startActivity(StateListDrawableActivity.class);
                break;
            case R.id.btn_level_list_drawable:
                startActivity(LevelListDrawableActivity.class);
                break;
            case R.id.btn_transition_drawable:
                startActivity(TransitionDrawableActivity.class);
                break;
            case R.id.btn_insert_drawable:
                startActivity(InsertDrawableActivity.class);
                break;
            case R.id.btn_scale_drawable:
                startActivity(ScaleDrawableActivity.class);
                break;
            case R.id.btn_clip_drawable:
                startActivity(ClipDrawableActivity.class);
                break;
            case R.id.btn_custom_drawable:
                startActivity(CustomDrawableActivity.class);
                break;
            case R.id.btn_custom_drawable_2:
                startActivity(CustomDrawable2Activity.class);
                break;
        }
    }
}
