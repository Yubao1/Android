package com.example.administrator.androidart.activites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

//import com.example.administrator.myapplication.R;
import com.example.administrator.R;
import com.example.administrator.myapplication.XActivity;
import com.example.administrator.myapplication.activitys.JNIActivity;

public class AndroidArtActivity extends XActivity {
    @Override
    public void init() {

    }

    @Override
    public int getId() {
        return R.layout.activity_android_art;
    }
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_android_ipc_activity:
                startActivity(AndroidIpcActivity.class);
                break;
            case R.id.btn_view_event_activity:
                startActivity(ViewEventActivity.class);
                break;
            case R.id.btn_view_event_distribution_activity:
                startActivity(ViewEventDistributionActivity.class);
                break;
            case R.id.btn_view_slide_conflict_activity:
                startActivity(ViewSlideConflictActivity.class);
                break;
            case R.id.btn_remote_views_activity:
                startActivity(RemoteViewsActivity.class);
                break;
            case R.id.btn_drawable_activity:
                startActivity(DrawableActivity.class);
                break;
            case R.id.btn_animation_activity:
                startActivity(AnimationActivity.class);
                break;
            case R.id.btn_window_activity:
                startActivity(WindowActivity.class);
                break;
            case R.id.btn_thread_and_thread_pool_activity:
                startActivity(ThreadAndThreadPooActivity.class);
                break;
            case R.id.btn_bitmap_load_and_cache_activity:
                startActivity(BitmapLoadAndCacheActivity.class);
                break;
            case R.id.btn_comprehensive_activity:
                startActivity(ComprehensiveActivity.class);
                break;
            case R.id.btn_jni_activity:
                startActivity(JNIActivity.class);
                break;
            case R.id.btn_performance_optimize_activity:
                startActivity(PerformanceOptimizeActivity.class);
                break;
        }
    }
}
