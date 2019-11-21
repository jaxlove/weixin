package com.weixin.controller;

import com.weixin.weixinutil.WeiXinAuthUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
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
    public Map getJsSdkSign(HttpServletRequest request){
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()){
            String s = headerNames.nextElement();
            System.out.println(s+":"+request.getHeader(s));
        }
        Map map = WeiXinAuthUtil.generateJsSdkSign("http://wdj.free.idcfengye.com/page/test.html");
        return map;
    }

}
