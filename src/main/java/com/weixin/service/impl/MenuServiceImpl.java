package com.weixin.service.impl;

import com.weixin.weixinutil.WeiXinAuthUtil;
import com.weixin.weixinutil.MenuTestUtil;
import com.weixin.entity.menu.Menu;
import com.weixin.service.MenuService;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service("menuService")
public class MenuServiceImpl implements MenuService {

    public static Logger log = LoggerFactory.getLogger(MenuServiceImpl.class);

    // 菜单创建（POST） 限100（次/天）
    public static String MENU_CREATE = "https://api.weixin.qq.com/cgi-bin/menu/create";
    public static String MENU_DELETE = "https://api.weixin.qq.com/cgi-bin/menu/delete";

    @Override
    public String createMenu(String jsonMenu) {
        String resultStr = "";
        // 调用接口获取token
        String token = WeiXinAuthUtil.getAccessToken().getToken();
        if (token != null) {
            // 调用接口创建菜单
            JSONObject jsonObject = WeiXinAuthUtil.doPostWithToken(jsonMenu, MENU_CREATE);
            if (null != jsonObject) {
                if (0 != jsonObject.getInt("errcode")) {
                    resultStr = "菜单创建失败，错误码：" + jsonObject.getInt("errcode");
                    log.error("创建菜单失败 errcode:" + jsonObject.getInt("errcode") + "，errmsg:" + jsonObject.getString("errmsg"));
                }else {
                    resultStr = "菜单创建成功";
                }
            }
        }

        return resultStr;
    }

    /**
     * 删除所有菜单
     *
     * @return
     */
    @Override
    public String deleteMenu() {
        // 调用接口删除菜单
        JSONObject jsonObject = WeiXinAuthUtil.doPostWithToken(null, MENU_DELETE);
        return jsonObject.toString();
    }


    public static void main(String[] args) {
        Menu menu = MenuTestUtil.initMenu();
        // 这是一个符合菜单的json格式，“\”是转义符
        String jsonMenu = JSONObject.fromObject(menu).toString();
        MenuServiceImpl impl = new MenuServiceImpl();
//        String token = WeiXinUtil.getAccessToken().getToken();
//        System.out.println(impl.deleteMenu(token));
        impl.createMenu(jsonMenu);
    }


}
