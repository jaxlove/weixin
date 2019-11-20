package com.weixin.weixinutil;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.weixin.entity.tomessage.Article;
import com.weixin.entity.tomessage.ToMusicMessage;
import com.weixin.entity.tomessage.ToNewsMessage;
import com.weixin.entity.tomessage.ToTextMessage;
import org.apache.commons.lang.StringEscapeUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 消息回复工具类
 */
public class ResponseMessageFormatUtil {


    /**
     * 解析微信发来的请求（XML）
     *
     * @param request
     * @return
     * @throws Exception
     */
    public static Map<String, String> parseXml(HttpServletRequest request) throws Exception {
        // 将解析结果存储在HashMap中
        Map<String, String> map = new HashMap<String, String>();

        // 从request中取得输入流
        try (InputStream inputStream = request.getInputStream()) {
            // 读取输入流
            SAXReader reader = new SAXReader();
            Document document = reader.read(inputStream);
            // 得到xml根元素
            Element root = document.getRootElement();
            // 得到根元素的所有子节点
            @SuppressWarnings("unchecked")
            List<Element> elementList = root.elements();
            // 遍历所有子节点
            for (Element e : elementList) {
                map.put(e.getName(), e.getText());
            }
        } catch (Exception e) {
            return null;
        }
        return map;
    }

    /**
     * 文本消息对象转换成xml
     *
     * @param toTextMessage 文本消息对象
     * @return xml
     */
    public static String textMessageToXml(ToTextMessage toTextMessage) {
        xstream.alias("xml", toTextMessage.getClass());
        return StringEscapeUtils.unescapeXml(xstream.toXML(toTextMessage));
    }

    /**
     * 音乐消息对象转换成xml
     *
     * @param toMusicMessage 音乐消息对象
     * @return xml
     */
    public static String musicMessageToXml(ToMusicMessage toMusicMessage) {
        xstream.alias("xml", toMusicMessage.getClass());
        return StringEscapeUtils.unescapeXml(xstream.toXML(toMusicMessage));
    }

    /**
     * 图文消息对象转换成xml
     *
     * @param toNewsMessage 图文消息对象
     * @return xml
     */
    public static String newsMessageToXml(ToNewsMessage toNewsMessage) {
        xstream.alias("xml", toNewsMessage.getClass());
        xstream.alias("item", new Article().getClass());
        return StringEscapeUtils.unescapeXml(xstream.toXML(toNewsMessage));
    }

    /**
     * 扩展xstream，使其支持CDATA块
     */
    private static XStream xstream = new XStream(new DomDriver());
//        new XStream(new XppDriver() {
//        @Override
//        public HierarchicalStreamWriter createWriter(Writer out) {
//            return new PrettyPrintWriter(out) {
//                // 对所有xml节点的转换都增加CDATA标记
//                boolean cdata = true;
//                @Override
//                protected void writeText(QuickWriter writer, String text) {
//                    if (cdata) {
//                        writer.write("<![CDATA[");
//                        writer.write(text);
//                        writer.write("]]>");
//                    } else {
//                        writer.write(text);
//                    }
//                }
//            };
//        }
//    });

    public static void main(String[] args) {
        ToTextMessage toTextMessage = new ToTextMessage();
        toTextMessage.setToUserName("111");
        toTextMessage.setFromUserName("222");
        toTextMessage.setMsgType("text");
        toTextMessage.setContent("333");
        String s = ResponseMessageFormatUtil.textMessageToXml(toTextMessage);
        System.out.println(s);
    }

}
