package com.senthqh.global.utils.phone;

import com.senthqh.global.utils.safety.DataEncryption;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created Author fengye
 * Created Date 2015/11/1
 * Created Time 10:33
 * 手机号码验证工具类
 */
public class CheckPhone implements Serializable {
    /**
     * 手机号验证
     *
     * @param str
     * @return 验证通过返回true
     */
    public static boolean isMobile(String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile("^[1][3,4,5,8,7][0-9]{9}$"); // 验证手机号
        m = p.matcher(str);
        b = m.matches();
        return b;
    }

    /**
     * 电话号码验证
     *
     * @param str
     * @return 验证通过返回true
     */
    public static boolean isPhone(String str) {
        Pattern p1 = null, p2 = null;
        Matcher m = null;
        boolean b = false;
        p1 = Pattern.compile("^[0][1-9]{2,3}-[0-9]{5,10}$");  // 验证带区号的
        p2 = Pattern.compile("^[1-9]{1}[0-9]{5,8}$");         // 验证没有区号的
        if (str.length() > 9) {
            m = p1.matcher(str);
            b = m.matches();
        } else {
            m = p2.matcher(str);
            b = m.matches();
        }
        return b;
    }

    /**
     * 电话号码是否全部显示
     * @param phone
     * @param show
     * @return
     */
    public static String phoneIsAllShow(String phone,boolean show){
        String userPhone="";
        if(StringUtils.isNotBlank(phone)){
            try {
                userPhone= DataEncryption.decrypt(phone);
                if(StringUtils.isNotBlank(userPhone)&&userPhone.length()==11 && show==false){//电话号码不全部显示
                    userPhone=userPhone.substring(0,3)+"****"+userPhone.substring(userPhone.length()-4);
                }else if(StringUtils.isNotBlank(userPhone)&& show==true){//电话号码全部显示
                    userPhone=userPhone;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return userPhone;
    }
}
