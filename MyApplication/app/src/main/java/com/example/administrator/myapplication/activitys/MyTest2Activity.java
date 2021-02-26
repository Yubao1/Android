package com.example.administrator.myapplication.activitys;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.administrator.R;
import com.example.administrator.myapplication.XActivity;
import com.example.administrator.myapplication.tool.MyLogcat;

public class MyTest2Activity extends XActivity {
    @Override
    public void init() {

    }

    @Override
    public int getId() {
        return R.layout.activity_my_test2;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_1:
                performAsyncTask();
                break;
        }
    }

    private void performAsyncTask() {
        for (int i = 0; i < 15; i++) {
            MyAsyncTask myAsyncTask = new MyAsyncTask("-------MyAsyncTask[" + i + "]");
            myAsyncTask.execute(new String[0]);
        }
    }

    class MyAsyncTask extends AsyncTask<String,Void,String> {
        private String name;
        public MyAsyncTask(String name) {
            this.name = name;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            new MyLogcat().d(getClass(),"----------doInBackground----" + Thread.currentThread().getName() + "-----ThreadId" + Thread.currentThread().getId());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);

        }
    }
}
