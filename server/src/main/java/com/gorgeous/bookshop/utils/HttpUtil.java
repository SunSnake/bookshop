package com.gorgeous.bookshop.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class HttpUtil {
    /**
     * java代码发送get请求
     * @param url
     * @return
     */
    public static String get(String url){
        // 获取httpClient对象
        DefaultHttpClient httpClient = new DefaultHttpClient();
        // 获取请求对象
        HttpGet httpGet = new HttpGet(url);
        String result = null;
        // 通过client发送get请求
        try {
            HttpResponse response = httpClient.execute(httpGet);
            // 获得状态码  200 成功      404 为找到路径         500 后台程序错误  。。。。
            int statusCode = response.getStatusLine().getStatusCode();
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity,"UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     *
     * @param url
     * @return
     */
    public static String post(String url, String params){
        // 获取httpClient对象
        DefaultHttpClient httpClient = new DefaultHttpClient();
        // 获取请求对象
        HttpPost httpPost = new HttpPost(url);
        String result = null;
        // 通过client发送post请求
        try {
            // 设置将参数传递过去
            httpPost.setEntity(new StringEntity(params,"UTF-8"));
            HttpResponse response = httpClient.execute(httpPost);
            // 获得状态码  200 成功      404 为找到路径         500 后台程序错误  。。。。
            int statusCode = response.getStatusLine().getStatusCode();
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
