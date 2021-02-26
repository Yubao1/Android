package com.example.administrator.androidart.activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

//import com.example.administrator.myapplication.R;
import com.example.administrator.R;
import com.example.administrator.myapplication.XActivity;

public class AndroidIpcActivity extends XActivity {

    @Override
    public void init() {

    }

    @Override
    public int getId() {
        return R.layout.activity_android_ipc;
    }
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_file_public_activity:
                startActivity(FilePublicActivity.class);
                break;
            case R.id.tv_messenger_activity:
                startActivity(MessengerActivity.class);
                break;
            case R.id.tv_aidl_activity:
                startActivity(AidlActivity.class);
                break;
            case R.id.tv_content_provider_activity:
                startActivity(ContentProviderActivity.class);
                break;
            case R.id.tv_socket_activity:
                startActivity(SocketActivity.class);
                break;
            case R.id.tv_binder_activity:
                startActivity(BinderActivity.class);
                break;
        }
    }
}
