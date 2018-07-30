package com.jaxloveweixin.common;

import com.jaxloveweixin.entity.menu.Button;
import com.jaxloveweixin.entity.menu.ClickButton;
import com.jaxloveweixin.entity.menu.Menu;
import com.jaxloveweixin.entity.menu.ViewButton;

/**
 * description
 *
 * @author wdj on 2018/7/28
 */
public class WeiXinUtil2 {

    /**

     * 组装菜单

     * @return

     */

    public static Menu initMenu(){

        Menu menu = new Menu();

        ClickButton button11 = new ClickButton();

        button11.setName("啥都不是");

        button11.setType("click");

        button11.setKey("nothing");



        ClickButton button12 = new ClickButton();

        button12.setName("了解我们");

        button12.setType("click");

        button12.setKey("abountus");



        ViewButton button21 = new ViewButton();

        button21.setName("军爱夏的小屋");

        button21.setType("view");

        button21.setUrl("https://jaxlove.github.io/christmas/index.html");



        ViewButton button22 = new ViewButton();

        button22.setName("百度");

        button22.setType("view");

        button22.setUrl("https://www.baidu.com");



        ClickButton button31 = new ClickButton();

        button31.setName("军爱夏");

        button31.setType("click");

        button31.setKey("dontkown");



        Button button1 = new Button();

        button1.setName("about us"); //将11/12两个button作为二级菜单封装第一个一级菜单

        button1.setSub_button(new Button[]{button11,button12});



        Button button2 = new Button();

        button2.setName("相关网址"); //将21/22两个button作为二级菜单封装第二个二级菜单

        button2.setSub_button(new Button[]{button21,button22});



        menu.setButton(new Button[]{button1,button2,button31});// 将31Button直接作为一级菜单

        return menu;

    }
}
