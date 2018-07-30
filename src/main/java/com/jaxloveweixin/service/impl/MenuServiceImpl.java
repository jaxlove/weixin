package com.jaxloveweixin.service.impl;

import com.jaxloveweixin.common.WeiXinUtil;
import com.jaxloveweixin.common.WeiXinUtil2;
import com.jaxloveweixin.entity.menu.Menu;
import com.jaxloveweixin.service.MenuService;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;


@Service("menuService")
public class MenuServiceImpl implements MenuService {

    public static Logger log = Logger.getLogger(MenuServiceImpl.class);

    // 菜单创建（POST） 限100（次/天）
    public static String MENU_CREATE = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
    public static String MENU_DELETE = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";

    @Override
    public String createMenu(String jsonMenu) {
        String resultStr = "";
        // 调用接口获取token
        String token = WeiXinUtil.getAccessToken().getToken();
        if (token != null) {
            // 调用接口创建菜单
            int result = createMenu(jsonMenu, token);
            // 判断菜单创建结果
            if (0 == result) {
                resultStr = "菜单创建成功";
                log.info(resultStr);
            } else {
                resultStr = "菜单创建失败，错误码：" + result;
                log.error(resultStr);
            }
        }

        return resultStr;
    }

    @Override
    public String deleteMenu(String accessToken) {

        int result = 0;
        // 拼装创建菜单的url
        String url = MENU_DELETE.replace("ACCESS_TOKEN", accessToken);
        // 调用接口删除菜单
        JSONObject jsonObject = WeiXinUtil.doPostStr(url, "");

        return jsonObject.toString();
    }


    /**
     * 创建菜单
     *
     * @param jsonMenu
     *            菜单的json格式
     * @param accessToken
     *            有效的access_token
     * @return 0表示成功，其他值表示失败
     */
    public static int createMenu(String jsonMenu, String accessToken) {

        int result = 0;
        // 拼装创建菜单的url
        String url = MENU_CREATE.replace("ACCESS_TOKEN", accessToken);
        // 调用接口创建菜单
        JSONObject jsonObject = WeiXinUtil.doPostStr(url, jsonMenu);

        if (null != jsonObject) {
            if (0 != jsonObject.getInt("errcode")) {
                result = jsonObject.getInt("errcode");
                log.error("创建菜单失败 errcode:" + jsonObject.getInt("errcode")
                        + "，errmsg:" + jsonObject.getString("errmsg"));
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Menu menu = WeiXinUtil2.initMenu();
        // 这是一个符合菜单的json格式，“\”是转义符
        String jsonMenu = JSONObject.fromObject(menu).toString();
        MenuServiceImpl impl = new MenuServiceImpl();
//        String token = WeiXinUtil.getAccessToken().getToken();
//        System.out.println(impl.deleteMenu(token));
        impl.createMenu(jsonMenu);
    }



}
