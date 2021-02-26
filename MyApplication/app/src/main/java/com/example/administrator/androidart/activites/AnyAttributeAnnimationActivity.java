package com.example.administrator.androidart.activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.administrator.R;
import com.example.administrator.myapplication.XActivity;
import com.nineoldandroids.animation.ObjectAnimator;

public class AnyAttributeAnnimationActivity extends XActivity {
    Button btn;
    @Override
    public void init() {
        btn = findViewById(R.id.btn);
    }
    public void onClick(View view) {
        ViewWrapper viewWrapper = new ViewWrapper(btn);
        ObjectAnimator objectAnimator = ObjectAnimator.ofInt(viewWrapper,"width",500);
        objectAnimator.setDuration(5000).start();
    }
    @Override
    public int getId() {
        return R.layout.activity_any_attribute_annimation;
    }
    private static class ViewWrapper {
        private Button btn;
        public ViewWrapper(Button btn) {
            this.btn = btn;
        }
        public int getWidth() {
            return btn.getLayoutParams().width;
        }
        public void setWidth(int width) {
            btn.getLayoutParams().width = width;
            btn.requestLayout();
        }
    }
}
