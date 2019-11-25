package com.weixin.test;

import com.weixin.util.QrCodeUtil;

/**
 * @author wangdejun
 * @description: TODO description
 * @date 2019/11/25 18:02
 */
public class QrCodeTest {

    public static void main(String[] args) throws Exception {
        // 存放在二维码中的内容
        String text = "https://www.cnblogs.com/jaxlove-it/";
        // 嵌入二维码的图片路径
        String imgPath = "C:\\Users\\wangdejun\\Desktop\\Z5NWS5400I0$@4_DKI%99WI.png";
        // 生成的二维码的路径及名称
        String destPath = "C:\\Users\\wangdejun\\Desktop\\testQrcode\\jam.jpg";
        //生成二维码
        QrCodeUtil.encode(text, imgPath, destPath, true);
        // 解析二维码
        String str = QrCodeUtil.decode(destPath);
        // 打印出解析出的内容
        System.out.println(str);

    }

}
