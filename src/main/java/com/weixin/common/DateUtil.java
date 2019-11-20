package com.weixin.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * description
 *
 * @author wdj on 2018/7/28
 */
public class DateUtil {
    public static final Calendar c = Calendar.getInstance();
    public static final String PATTERN_01 = "yyyy-MM-dd HH:mm:ss";
    public static final String PATTERN_02 = "yyyy年MM月dd HH:mm:ss";
    public static final String PATTERN_03 = "yyyy-MM-dd";
    public static final String PATTERN_04 = "HH:mm:ss";

    /**
     * 根据指定格式获取当前时间串
     *
     * @param format 格式，如yyyy-MM-dd HH:mm:ss
     */
    public static String getFormatDate(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date());
    }

    /**
     * 将Date类型转换成yyyy-MM-dd HH:mm:ss格式字符串
     */
    public static String format(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }
    /**
     * 将Date类型转换成指定格式字符串
     */
    public static String formatPattern(Date date,String pattern) {
        return new SimpleDateFormat(pattern).format(date);
    }

    /**
     * 判断日期是否为当前月
     */
    public static boolean isCurMonth(Date date){
        if(date != null
                && DateUtil.getFormatDate("yyyy-MM").equals(DateUtil.formatPattern(date,"yyyy-MM"))){
            return true;
        }
        return false;
    }

    /**
     * 检查当前时间是否为当月最后一天
     * @return
     */
    public static boolean isLastOfDay(){
        if (c.get(Calendar.DATE) == c.getActualMaximum(Calendar.DATE)) {
            return true;
        }
        return true;
    }

}
