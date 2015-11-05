package com.senthqh.global.utils.idCard;
import com.senthqh.global.utils.date.CheckDate;
import com.senthqh.global.utils.returnUtils.CodeEnum;
import com.senthqh.global.utils.returnUtils.ResultJsonFormat;
import com.senthqh.global.utils.safety.DataEncryption;
import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created Author fengye
 * Created Date 2015/11/5
 * Created Time 15:22
 */
public class IdCardCheck {
    /**
     * 功能：身份证的有效验证
     * @param IDStr 身份证号
     * @return 有效：返回"" 无效：返回String信息
     * @throws ParseException
     */
    @SuppressWarnings({ "rawtypes" })
    public static Map<String,Object> IDCardValidate(String IDStr) throws ParseException {
        ResultJsonFormat ret=new ResultJsonFormat("身份证验证通过!", CodeEnum.SUCCESS.value());
        try{
            String[] ValCodeArr = { "1", "0", "x", "9", "8", "7", "6", "5", "4","3","2" };
            String[] Wi = { "7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7","9", "10", "5", "8", "4", "2" };
            String Ai = "";
            //号码的长度 15位或18位
            if (IDStr.length() != 15 && IDStr.length() != 18) {
                ret.setFlag(CodeEnum.ERROR.value());
                ret.setMsg("身份证号码长度应该为15位或18位。");
                return ret.convertResultJson();
            }
            // ================ 数字 除最后以为都为数字 ================
            if (IDStr.length() == 18) {
                Ai = IDStr.substring(0, 17);
            }else if (IDStr.length() == 15) {
                Ai = IDStr.substring(0, 6) + "19" + IDStr.substring(6, 15);
            }
            if (isNumeric(Ai) == false) {
                ret.setFlag(CodeEnum.ERROR.value());
                ret.setMsg("身份证15位号码都应为数字 ; 18位号码除最后一位外，都应为数字。");
                return ret.convertResultJson();
            }
            // ================ 出生年月是否有效 ================
            String strYear = Ai.substring(6, 10);// 年份
            String strMonth = Ai.substring(10, 12);// 月份
            String strDay = Ai.substring(12, 14);// 月份
            if (isDate(strYear + "-" + strMonth + "-" + strDay) == false) {
                ret.setFlag(CodeEnum.ERROR.value());
                ret.setMsg("身份证的生日无效!");
                return ret.convertResultJson();
            }
            GregorianCalendar gc = new GregorianCalendar();
            SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
            if ((gc.get(Calendar.YEAR) - Integer.parseInt(strYear)) > 150    || (gc.getTime().getTime() - s.parse(strYear + "-" + strMonth + "-" + strDay).getTime()) < 0) {
                ret.setFlag(CodeEnum.ERROR.value());
                ret.setMsg("身份证生日不在有效范围。");
                return ret.convertResultJson();
            }
            if (Integer.parseInt(strMonth) > 12 || Integer.parseInt(strMonth) == 0) {
                ret.setFlag(CodeEnum.ERROR.value());
                ret.setMsg("身份证月份无效。");
                return ret.convertResultJson();
            }
            if (Integer.parseInt(strDay) > 31 || Integer.parseInt(strDay) == 0) {
                ret.setFlag(CodeEnum.ERROR.value());
                ret.setMsg("身份证日期无效。");
                return ret.convertResultJson();
            }
            // ================ 地区码时候有效 ================
            Hashtable h = GetAreaCode();
            if (h.get(Ai.substring(0, 2)) == null) {
                ret.setFlag(CodeEnum.ERROR.value());
                ret.setMsg("身份证地区编码错误。");
                return ret.convertResultJson();
            }
            // ================ 判断最后一位的值 ================
            int TotalmulAiWi = 0;
            for (int i = 0; i < 17; i++) {
                TotalmulAiWi = TotalmulAiWi+ Integer.parseInt(String.valueOf(Ai.charAt(i)))* Integer.parseInt(Wi[i]);
            }
            int modValue = TotalmulAiWi % 11;
            String strVerifyCode = ValCodeArr[modValue];
            Ai = Ai + strVerifyCode;
            if (IDStr.length() == 18) {
                if (Ai.equals(IDStr) == false) {
                    ret.setFlag(CodeEnum.ERROR.value());
                    ret.setMsg("身份证无效，不是合法的身份证号码");
                    return ret.convertResultJson();
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            ret.setFlag(CodeEnum.ERROR.value());
            ret.setMsg("业务发生异常!请联系管理员!");
            return ret.convertResultJson();
        }
        return ret.convertResultJson();
    }
    /**
     * 功能：设置地区编码
     * @return Hashtable 对象
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private static Hashtable GetAreaCode() {
        Hashtable hashtable = new Hashtable();
        hashtable.put("11","北京");
        hashtable.put("12","天津");
        hashtable.put("13","河北");
        hashtable.put("14","山西");
        hashtable.put("21","辽宁");
        hashtable.put("22","吉林");
        hashtable.put("23","辽宁");
        hashtable.put("31","上海");
        hashtable.put("32","江苏");
        hashtable.put("33","浙江");
        hashtable.put("34","安徽");
        hashtable.put("35","福建");
        hashtable.put("36","江西");
        hashtable.put("37","山东");
        hashtable.put("41","河南");
        hashtable.put("42","湖北");
        hashtable.put("43","湖南");
        hashtable.put("44","广东");
        hashtable.put("45","广西");
        hashtable.put("46","海南");
        hashtable.put("50","重庆");
        hashtable.put("51","四川");
        hashtable.put("52","贵州");
        hashtable.put("53","云南");
        hashtable.put("54","西藏");
        hashtable.put("61","陕西");
        hashtable.put("62","甘肃");
        hashtable.put("63","青海");
        hashtable.put("64","宁夏");
        hashtable.put("65","新疆");
        hashtable.put("71","台湾");
        hashtable.put("81","香港");
        hashtable.put("82","澳门");
        hashtable.put("91","国外");
        return hashtable;
    }
    /**
     * 功能：判断字符串是否为数字
     * @param str
     * @return
     */
    private static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (isNum.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 功能：判断字符串是否为日期格式
     * @param strDate
     * @return
     */
    public static boolean isDate(String strDate) {
        Pattern pattern = Pattern     .compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
        Matcher m = pattern.matcher(strDate);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 对身份证进行解密操作,并且是否显示全部身份证号码
     * @param idCard 需要解密的身份证
     * @param isAllShow 是否全部显示
     * @return
     */
    public static String decryptIdCard(String idCard,boolean isAllShow){
        String userIdCard=null;
        try {
            userIdCard= DataEncryption.decrypt(idCard);//对身份进行解密操作
            //解密的身份证不为空,并且为18位身份证
            if(StringUtils.isNotEmpty(userIdCard) && userIdCard.length()==18){
                if(!isAllShow){//对身份证号码做隐藏处理(即不全部显示身份证号码)
                    userIdCard=userIdCard.substring(0,3)+"************"+userIdCard.substring(userIdCard.length()-3);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return userIdCard;
        }
        return userIdCard;
    }

    //根据用户的生日获取用户的年龄（格式为:yyyy-MM-dd）
    public static int getAgeByBirthday(String birthdayValue) {
        Date birthday= CheckDate.fomatDate(birthdayValue);
        Calendar cal = Calendar.getInstance();
        if (cal.before(birthday)) {
            throw new IllegalArgumentException(
                    "The birthDay is before Now.It's unbelievable!");
        }
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH) + 1;
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

        cal.setTime(birthday);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH) + 1;
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

        int age = yearNow - yearBirth;

        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                // monthNow==monthBirth
                if (dayOfMonthNow < dayOfMonthBirth) {
                    age--;
                }
            } else {
                // monthNow>monthBirth
                age--;
            }
        }
        return age;
    }
    //通过用户的身份证号码,获取用户的生日
    public static String getUserBirthdayByIdCard(String idCard){
        String year=idCard.substring(6,10);
        String month=idCard.substring(10, 12);
        String day=idCard.substring(12,14);
        return year+"-"+month+"-"+day;
    }

    /**
     * 通过用户填写的身份证号码，获取用户的性别(默认true/男)
     * @param idCard 身份证号码,只限18位身份证号码
     * @return true/男，false/女
     */
    public static boolean getUserSexByIdCard(String idCard){
        if(StringUtils.isNotEmpty(idCard) && idCard.length()==18){
            String sex=idCard.substring(16,17);
            if(StringUtils.isNotEmpty(sex) && (sex.equals("1") || sex.equals("3") || sex.equals("5") || sex.equals("7") || sex.equals("9"))){
                return true;
            }else if(StringUtils.isNotEmpty(sex) && (sex.equals("0") || sex.equals("2") || sex.equals("4") || sex.equals("6") || sex.equals("8"))){
                return false;
            }else{
                return true;
            }
        }else{
            return true;
        }
    }
}
