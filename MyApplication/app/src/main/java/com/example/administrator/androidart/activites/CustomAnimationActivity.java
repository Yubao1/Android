package com.example.administrator.androidart.activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import com.example.administrator.R;
import com.example.administrator.androidart.animation.Rotate3dAnimation;
import com.example.administrator.myapplication.XActivity;

/**
 * 参考 https://www.jb51.net/article/140132.htm
 */
public class CustomAnimationActivity extends XActivity {
    private final String TAG="Test3DRotateActivity";
    private ImageView image;
    private Button start ,stop;
    private Rotate3dAnimation rotation;
    private StartNextRotate startNext;
    @Override
    public void init() {
        image = (ImageView) findViewById(R.id.image);
        start=(Button) findViewById(R.id.start);
        stop = (Button) findViewById(R.id.stop);
        start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //进行360度的旋转
                startRotation(0,340);
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                image.clearAnimation();
            }
        });
    }
    private void startRotation(float start, float end) {
        // 计算中心点
        final float centerX = image.getWidth() / 2.0f;
        final float centerY = image.getHeight() / 2.0f;
        Log.d(TAG, "centerX="+centerX+", centerY="+centerY);
        // Create a new 3D rotation with the supplied parameter
        // The animation listener is used to trigger the next animation
        //final Rotate3dAnimation rotation =new Rotate3dAnimation(start, end, centerX, centerY, 310.0f, true);

        /**
         * Z轴的缩放为0
         */
        rotation =new Rotate3dAnimation(start, end, centerX, centerY, 0f, false);
        rotation.setDuration(2000);
        rotation.setFillAfter(false);
        //rotation.setInterpolator(new AccelerateInterpolator());
        //匀速旋转
        rotation.setInterpolator(new LinearInterpolator());
        //设置监听
        startNext = new StartNextRotate();
        rotation.setAnimationListener(startNext);
        image.startAnimation(rotation);
    }
    private class StartNextRotate implements Animation.AnimationListener {
        public void onAnimationEnd(Animation animation) {
            // TODO Auto-generated method stub
            Log.d(TAG, "onAnimationEnd......");
//            image.startAnimation(rotation);
        }
        public void onAnimationRepeat(Animation animation) {
            // TODO Auto-generated method stub
        }
        public void onAnimationStart(Animation animation) {
            // TODO Auto-generated method stub
        }
    }
    @Override
    public int getId() {
        return R.layout.activity_custom_animation;
    }
}
