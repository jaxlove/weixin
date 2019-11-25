package com.weixin.weixinutil;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.weixin.common.HttpUtil;
import com.weixin.info.AccessToken;
import com.weixin.info.ApiTicket;
import com.weixin.util.Sha1Util;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class WeiXinAuthUtil {
    public static Logger logger = LoggerFactory.getLogger(WeiXinAuthUtil.class);

    //从微信后台拿到APPID和APPSECRET 并封装为常量
    private static final String APPID = "wx783d24219059d6d4";
    private static final String APPSECRET = "6acb2c28fb7c89068f07fb3004bcf7ba";

    private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    private static final String JSAPI_TICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
    private static final String API_TICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=wx_card";

    private static final String TOKEN_CACHE_KEY = "accessjs_token";
    private static final String JSAPI_TICKET_CACHE_KEY = "jsapi_ticket";
    private static final String API_TICKET_CACHE_KEY = "api_ticket";
    //accessToken缓存，微信accessToken 120分钟过期，这里设置110分钟过期
    private static Cache<String, Object> weixinCache = CacheBuilder.newBuilder()
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
        AccessToken accessToken = (AccessToken) weixinCache.getIfPresent(TOKEN_CACHE_KEY);
        if (accessToken == null) {
            synchronized (TOKEN_CACHE_KEY) {
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
                        weixinCache.put(TOKEN_CACHE_KEY, token);
                    }
                }
            }
        }
        return (AccessToken) weixinCache.getIfPresent(TOKEN_CACHE_KEY);
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

    public static ApiTicket getJsapiTicket() {
        ApiTicket apiTicket = (ApiTicket) weixinCache.getIfPresent(JSAPI_TICKET_CACHE_KEY);
        if (apiTicket == null) {
            synchronized (JSAPI_TICKET_CACHE_KEY) {
                if (apiTicket == null) {
                    ApiTicket ticket = new ApiTicket();
                    String ticketUrl = JSAPI_TICKET_URL.replace("ACCESS_TOKEN", getAccessToken().getToken());
                    //使用刚刚写的doGet方法接收结果
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = HttpUtil.doGetStr(ticketUrl);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //如果返回不为空，将返回结果封装进AccessToken实体类
                    if (jsonObject != null) {
                        ticket.setTicket(jsonObject.getString("ticket"));
                        ticket.setErrmsg(jsonObject.getString("errmsg"));
                        ticket.setErrcode(jsonObject.getInt("errcode"));
                        ticket.setExpiresIn(jsonObject.getInt("expires_in"));
                        weixinCache.put(JSAPI_TICKET_CACHE_KEY, ticket);
                    }
                }
            }
        }
        return (ApiTicket) weixinCache.getIfPresent(JSAPI_TICKET_CACHE_KEY);
    }

    public static ApiTicket getApiTicket() {
        ApiTicket apiTicket = (ApiTicket) weixinCache.getIfPresent(API_TICKET_CACHE_KEY);
        if (apiTicket == null) {
            synchronized (API_TICKET_CACHE_KEY) {
                if (apiTicket == null) {
                    ApiTicket ticket = new ApiTicket();
                    String ticketUrl = API_TICKET_URL.replace("ACCESS_TOKEN", getAccessToken().getToken());
                    //使用刚刚写的doGet方法接收结果
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = HttpUtil.doGetStr(ticketUrl);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //如果返回不为空，将返回结果封装进AccessToken实体类
                    if (jsonObject != null) {
                        ticket.setTicket(jsonObject.getString("ticket"));
                        ticket.setErrmsg(jsonObject.getString("errmsg"));
                        ticket.setErrcode(jsonObject.getInt("errcode"));
                        ticket.setExpiresIn(jsonObject.getInt("expires_in"));
                        weixinCache.put(API_TICKET_CACHE_KEY, ticket);
                    }
                }
            }
        }
        return (ApiTicket) weixinCache.getIfPresent(API_TICKET_CACHE_KEY);
    }

    public static Map generateJsSdkSign(String url) throws Exception {
        Map map = new HashMap();
        String nonceStr = create_nonce_str();
        String currentTimeMillis = create_timestamp();
        ApiTicket ticket = getJsapiTicket();
        String[] arr = {"jsapi_ticket=" + ticket.getTicket(), "noncestr=" + nonceStr, "timestamp=" + currentTimeMillis, "url=" + url};
        String join = StringUtils.join(arr, "&");
        logger.info("signature:{}", join);
        String signature = Sha1Util.decode(join);
        map.put("appId", APPID);
        map.put("timestamp", currentTimeMillis);
        map.put("jsapi_ticket", ticket.getTicket());
        map.put("nonceStr", nonceStr);
        map.put("signature", signature);
        return map;
    }

    public static Map generateApiSign() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Map map = new HashMap();
        String nonceStr = create_nonce_str();
        String currentTimeMillis = create_timestamp();
        ApiTicket ticket = getJsapiTicket();
        String[] arr = {"jsapi_ticket=" + ticket.getTicket(), "noncestr=" + nonceStr, "timestamp=" + currentTimeMillis};
        String join = StringUtils.join(arr, "&");
        logger.info("signature:{}", join);
        String signature = Sha1Util.decode(join);
        map.put("appId", APPID);
        map.put("timestamp", currentTimeMillis);
        map.put("jsapi_ticket", ticket.getTicket());
        map.put("nonceStr", nonceStr);
        map.put("signature", signature);
        return map;
    }


    private static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }

    private static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }
}