package com.example.administrator.androidart.activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.administrator.R;
import com.example.administrator.myapplication.XActivity;
import com.nineoldandroids.animation.IntEvaluator;
import com.nineoldandroids.animation.ValueAnimator;

public class ValueAnimationActivity extends XActivity {
    private Button btn;
    @Override
    public void init() {
        btn = findViewById(R.id.btn);
    }

    @Override
    public int getId() {
        return R.layout.activity_value_animation;
    }
    public void onClick(View view) {
        performAnimation(view,view.getWidth(),500);
    }
    private void performAnimation(final View target, final int start, final int end) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(1,100);
         valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
             @Override
             public void onAnimationUpdate(ValueAnimator animation) {
                 IntEvaluator intEvaluator = new IntEvaluator();
                 int currentValue = (Integer)animation.getAnimatedValue();
                 float fraction = animation.getAnimatedFraction();
                 target.getLayoutParams().width = intEvaluator.evaluate(fraction,start,end);
                 target.requestLayout();
             }
         });
         valueAnimator.setDuration(5000).start();
    }
}
