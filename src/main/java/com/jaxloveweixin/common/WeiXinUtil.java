package com.jaxloveweixin.common;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.jaxloveweixin.info.AccessToken;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class WeiXinUtil {
    public static Logger log = Logger.getLogger(WeiXinUtil.class);

    //从微信后台拿到APPID和APPSECRET 并封装为常量
    private static final String APPID = "wx783d24219059d6d4";
    private static final String APPSECRET = "6acb2c28fb7c89068f07fb3004bcf7ba";
    private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    private static final String CACHE_KEY = "accessToken";
    //accessToken缓存，微信accessToken 120分钟过期，这里设置110分钟过期
    private static Cache<String, AccessToken> tokenCache = CacheBuilder.newBuilder()
            .expireAfterWrite(110L, TimeUnit.MINUTES)
            .concurrencyLevel(6)
            .initialCapacity(100)
            .maximumSize(1000)
            .softValues()
            .build();

    /**
     * 获取AccessToken
     *
     * @return 返回拿到的access_token及有效期
     */
    public static AccessToken getAccessToken() {
        AccessToken accessToken = tokenCache.getIfPresent(CACHE_KEY);
        if (accessToken == null) {
            synchronized (tokenCache) {
                if (accessToken == null) {
                    AccessToken token = new AccessToken();
                    //将URL中的两个参数替换掉
                    String url = ACCESS_TOKEN_URL.replace("APPID", APPID).replace("APPSECRET", APPSECRET);
                    //使用刚刚写的doGet方法接收结果
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = HttpUtil.doGetStr(url);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //如果返回不为空，将返回结果封装进AccessToken实体类
                    if (jsonObject != null) {
                        //取出access_token
                        token.setToken(jsonObject.getString("access_token"));
                        //取出access_token的有效期
                        token.setExpiresIn(jsonObject.getInt("expires_in"));
                        tokenCache.put(CACHE_KEY, token);
                    }
                }
            }
        }
        return tokenCache.getIfPresent(CACHE_KEY);
    }

    /**
     * 0表示成功，其他值表示失败
     *
     * @param data
     * @param url
     * @return
     */
    public static JSONObject doPostWithToken(String data, String url) {
        // 调用接口创建菜单
        if (StringUtils.isNotBlank(url)) {
            if (url.indexOf("?") > -1) {
                url += "&access_token=" + getAccessToken().getToken();
            } else {
                url += "?access_token=" + getAccessToken().getToken();
            }
        }
        JSONObject jsonObject = HttpUtil.doPostStr(url, data);
        return jsonObject;

    }
}