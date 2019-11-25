package com.weixin.controller;

import com.weixin.weixinutil.WeiXinAuthUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * @author wangdejun
 * @description: TODO description
 * @date 2019/11/20 16:35
 */
@RestController
@RequestMapping("weixinInfo")
public class WeiXinInfoController {

    @GetMapping("jsSdk/sign")
    public Map getJsSdkSign(String url) throws Exception {
        if (StringUtils.isBlank(url)) {
            return null;
        }
        url = url.split("#")[0];
        Map map = WeiXinAuthUtil.generateJsSdkSign(url);
        return map;
    }

}
