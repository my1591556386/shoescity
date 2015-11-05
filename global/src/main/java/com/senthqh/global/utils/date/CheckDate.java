package com.senthqh.global.utils.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created Author fengye
 * Created Date 2015/10/23
 * Created Time 18:28
 * 日期工具类
 */
public class CheckDate {

    private final static SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");

    private final static SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");

    private final static SimpleDateFormat sdfDays = new SimpleDateFormat("yyyyMMdd");

    private final static SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 获取YYYY格式
     * @return YYYY
     */
    public static String getYear() {
        return sdfYear.format(new Date());
    }

    /**
     * 获取YYYY-MM-DD格式
     * @return YYYY-MM-DD
     */
    public static String getDay() {
        return sdfDay.format(new Date());
    }

    /**
     * 获取yyyyMMdd格式
     * @return yyyyMMdd
     */
    public static String getDays() {
        return sdfDays.format(new Date());
    }

    /**
     * 获取YYYY-MM-DD HH:mm:ss格式
     * @return YYYY-MM-DD HH:mm:ss
     */
    public static String getTime() {
        return sdfTime.format(new Date());
    }

    /**
     * @Title: compareDate
     * @Description: TODO(日期比较，如果s>=e 返回true 否则返回false)
     * @param s 开始日期
     * @param e 结束日期
     * @return boolean true表示开始日期大于结束日期,false表示开始日期小于结束日期
     * @throws
     * @author luguosui
     */
    public static boolean compareDate(String s, String e) {
        if (fomatDate(s) == null || fomatDate(e) == null) {
            return false;
        }
        return fomatDate(s).getTime() >= fomatDate(e).getTime();
    }

    /**
     * 格式化日期 yyyy-MM-dd
     * @return  yyyy-MM-dd
     */
    public static Date fomatDate(String date) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return fmt.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * @Title: compareDate
     * @Description: TODO(日期比较，如果s>=e 返回true 否则返回false)
     * @param s 开始日期
     * @param e 结束日期
     * @return boolean true表示开始日期大于结束日期,false表示开始日期小于结束日期
     * @throws
     * @author luguosui
     */
    public static boolean compareDateTime(String s, String e) {
        if (fomatDateTime(s) == null || fomatDateTime(e) == null) {
            return false;
        }
        return fomatDateTime(s).getTime() >= fomatDateTime(e).getTime();
    }

    /**
     * 格式化日期 yyyy-MM-dd
     * @return  yyyy-MM-dd
     */
    public static Date fomatDateTime(String date) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return fmt.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 校验日期是否合法
     * @return true代表合法,false代表不合法
     */
    public static boolean isValidDate(String s) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            fmt.parse(s);
            return true;
        } catch (Exception e) {
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            return false;
        }
    }
    /**
     * 校验日期是否合法
     * @return true代表合法,false代表不合法
     */
    public static boolean isValidDateTime(String s) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            fmt.parse(s);
            return true;
        } catch (Exception e) {
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            return false;
        }
    }
    /**
     * 日期之间相处N年
     * @param startTime 开始日期
     * @param endTime 结束日期
     * @return int
     */
    public static int getDiffYear(String startTime, String endTime) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        int years=0;
        try {
            years = (int) (((fmt.parse(endTime).getTime() - fmt.parse(startTime).getTime()) / (1000 * 60 * 60 * 24)) / 365);
            return years;
        } catch (Exception e) {
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            return years;
        }
    }

    /**
     * 功能描述：时间相减得到天数
     * @param beginDateStr 开始日期
     * @param endDateStr 结束日期
     * @return long
     * @author Administrator
     */
    public static long getDaySub(String beginDateStr, String endDateStr) {
        long day = 0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate = null;
        Date endDate = null;

        try {
            beginDate = format.parse(beginDateStr);
            endDate = format.parse(endDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        day = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
        //System.out.println("相隔的天数="+day);
        return day;
    }

    /**
     * 得到N天之后的日期(如果是正数,向后推N天,如果是负数，向前推N天)
     * @param days
     * @return
     */
    public static String getAfterDayDate(String days) {
        int daysInt = Integer.parseInt(days);

        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();

        SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = sdfd.format(date);

        return dateStr;
    }
    /**
     * 得到N天之后的日期(如果是正数,向后推N天,如果是负数，向前推N天)
     * @param days
     * @return
     */
    public static String getAfterDayDateTime(String days) {
        int daysInt = Integer.parseInt(days);

        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();

        SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdfd.format(date);

        return dateStr;
    }
    /**
     * 得到n天之后是周几
     * @param days
     * @return
     */
    public static String getAfterDayWeek(String days) {
        int daysInt = Integer.parseInt(days);

        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("E");
        String dateStr = sdf.format(date);

        return dateStr;
    }

    /**
     * 获取两者的时间差,精确到时分秒
     * @param StrDate1、StrDate2  时间格式YYYY-MM-DD HH:mm:ss
     * @return
     */
    public static Map<String,Object> getTimeI(String StrDate1,String StrDate2) {
        Map<String,Object> map=new HashMap<String, Object>();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date2 = df.parse(StrDate2);
            Date date1 = df.parse(StrDate1);
            long times = date2.getTime()-date1.getTime() ;
            long day = times / (24 * 60 * 60 * 1000);
            long hour = (times / (60 * 60 * 1000) - day * 24);
            long min = ((times / (60 * 1000)) - day * 24 * 60 - hour * 60);
            long sec = (times / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);

           /* StringBuffer stringBuffer = new StringBuffer();
            if(day>0) {
                stringBuffer.append(day+"天");
            }else{
                stringBuffer.append("0天");
            }
            if (hour > 0) {
                stringBuffer.append(hour+"小时");
            }else{
                stringBuffer.append("0小时");
            }
            if (min > 0) {
                stringBuffer.append(min+"分");
            }else{
                stringBuffer.append("0分");
            }
            map.put("day", day);
            map.put("hour", hour);
            map.put("min", min);
            map.put("sec", sec);
            map.put("resultTimes", stringBuffer.toString());*/
        } catch (ParseException e) {
            e.printStackTrace();
            map.put("day", 0);
            map.put("hour", 0);
            map.put("min", 0);
            map.put("sec", 0);
            map.put("resultTimes", 0);
        }
        return map;
    }

}
