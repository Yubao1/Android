package com.example.administrator.myapplication.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
//import com.example.administrator.myapplication.R;
import com.example.administrator.R;
import com.example.administrator.myapplication.XActivity;
import com.example.administrator.myapplication.bean.PhoneInfoBean;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class JsonActivity extends XActivity {
    private TextView tv;
    private PhoneInfoBean mPhoneInfoBean = null;
    @Override
    public void init() {
        tv = findViewById(R.id.tv);
        parsingJsonData();
        showJsonData();
    }

    private void showJsonData() {
        tv.setText(mPhoneInfoBean + "");
    }

    private void parsingJsonData() {
        InputStreamReader inputStreamReader = null;
        String  jsonData = "";
        BufferedReader bufferedReader = null;
        try {
            inputStreamReader = new InputStreamReader(getResources().openRawResource(R.raw.testdata), "UTF-8");
            bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            jsonData = stringBuilder.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if (!TextUtils.isEmpty(jsonData)){
           mPhoneInfoBean = JSON.parseObject(jsonData,PhoneInfoBean.class);
        }
    }
    @Override
    public int getId() {
        return R.layout.activity_json;
    }
}
