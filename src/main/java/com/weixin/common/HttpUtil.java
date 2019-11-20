package com.weixin.common;

import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @author wangdejun
 * @description: http请求工具类
 * @date 2019/11/18 17:46
 */
public class HttpUtil {

    /**
     * 编写Get请求的方法。但没有参数传递的时候，可以使用Get请求
     *
     * @param url 需要请求的URL
     * @return 将请求URL后返回的数据，转为JSON格式，并return
     */
    public static JSONObject doGetStr(String url) throws ClientProtocolException, IOException {
        //获取DefaultHttpClient请求
        DefaultHttpClient client = new DefaultHttpClient();
        //HttpGet将使用Get方式发送请求URL
        HttpGet httpGet = new HttpGet(url);
        JSONObject jsonObject = null;
        //使用HttpResponse接收client执行httpGet的结果
        HttpResponse response = client.execute(httpGet);
        //从response中获取结果，类型为HttpEntity
        HttpEntity entity = response.getEntity();
        if(entity != null){
            //HttpEntity转为字符串类型
            String result = EntityUtils.toString(entity,"UTF-8");
            //字符串类型转为JSON类型
            jsonObject = JSONObject.fromObject(result);
        }
        return jsonObject;
    }

    /**
     * 编写Post请求的方法。当我们需要参数传递的时候，可以使用Post请求
     *
     * @param url 需要请求的URL
     * @param outStr  需要传递的参数
     * @return 将请求URL后返回的数据，转为JSON格式，并return
     */
    public static JSONObject doPostStr(String url, String outStr){
        //获取DefaultHttpClient请求
        DefaultHttpClient client = new DefaultHttpClient();
        //HttpPost将使用Get方式发送请求URL
        HttpPost httpost = new HttpPost(url);
        JSONObject jsonObject = null;
        //使用setEntity方法，将我们传进来的参数放入请求中
        httpost.setEntity(new StringEntity(outStr,"UTF-8"));
        String result = null;//HttpEntity转为字符串类型
        try {
            //使用HttpResponse接收client执行httpost的结果
            HttpResponse response = client.execute(httpost);
            result = EntityUtils.toString(response.getEntity(),"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //字符串类型转为JSON类型
        jsonObject = JSONObject.fromObject(result);
        return jsonObject;
    }


}
