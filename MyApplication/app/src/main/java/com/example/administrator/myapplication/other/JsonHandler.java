package com.example.administrator.myapplication.other;

import com.yanzhenjie.andserver.RequestHandler;
import com.yanzhenjie.andserver.RequestMethod;
import com.yanzhenjie.andserver.annotation.RequestMapping;
import com.yanzhenjie.andserver.util.HttpRequestParser;

import org.apache.httpcore.HttpException;
import org.apache.httpcore.HttpRequest;
import org.apache.httpcore.HttpResponse;
import org.apache.httpcore.entity.StringEntity;
import org.apache.httpcore.protocol.HttpContext;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by Administrator on 2020/8/16.
 */

public class JsonHandler implements RequestHandler {
    @RequestMapping(method = {RequestMethod.POST})
    @Override
    public void handle(HttpRequest httpRequest, HttpResponse httpResponse, HttpContext httpContext) throws HttpException, IOException {
        String content = HttpRequestParser.getContentFromBody(httpRequest);
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(content);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (jsonObject == null) {
            jsonObject = new JSONObject();
        }
        try {
            jsonObject.put("state", "success");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        StringEntity stringEntity = new StringEntity(jsonObject.toString(), "utf-8");
        httpResponse.setStatusCode(200);
        httpResponse.setEntity(stringEntity);
    }
}
