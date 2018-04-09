package com.horen.cortp.company.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by David-Notebook on 2017/6/15.
 */

public class DateUtil {

    /**
     * 把时间格式转换为指定格式年月日
     *
     * @param date
     * @return
     */
    public static String getDate(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    /**
     * 把时间格式转换为指定格式年月日
     *
     * @param date
     * @return
     */
    public static String getDateYear(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    /**
     * 把时间格式转换
     *
     * @param dateStr
     * @return
     */
    public static Date strDate(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 根据当前时间得到下一天或者上一天的时间
     * <p>
     * +下一天-上一天
     *
     * @return
     */
    public static String getTime() {
        Calendar c = Calendar.getInstance();
//        c.add(Calendar.YEAR, -1);//当前时间减去一年，即一年前的时间
        c.add(Calendar.MONTH, -1);//当前时间前去一个月，即一个月前的时间
//        c.add(Calendar.DAY_OF_MONTH, +1);//设置日期为今天+1，即等于第二天
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(c.getTime());
        return date;
    }

    /**
     * 年月日转换为标准时间格式
     */
    public static String switchDate(int year, int monthOfYear, int dayOfMonth) {
        String monthOfYearStr;
        String dayOfMonthStr;
        //--------------设置标准的日期格式-----------------
        if (monthOfYear < 9) {
            monthOfYearStr = "0" + (++monthOfYear);
        } else {
            monthOfYearStr = "" + (++monthOfYear);
        }
        if (dayOfMonth < 10) {
            dayOfMonthStr = "0" + dayOfMonth;
        } else {
            dayOfMonthStr = "" + dayOfMonth;
        }
        //--------------设置标准的日期格式-----------------
        String date = year + "-" + monthOfYearStr + "-" + dayOfMonthStr;
        return date;
    }
}
