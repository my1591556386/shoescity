package com.senthqh.global.utils.email;

import com.senthqh.global.utils.safety.DataEncryption;
import org.apache.commons.lang.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created Author fengye
 * Created Date 2015/11/1
 * Created Time 10:52
 * 邮箱格式验证工具类
 */
public class CheckEmail {
    /**
     * 邮箱验证：
     */
    public static boolean isValidEmail(String email) {
        String regEmail = "^(?:\\w+\\.{1})*\\w+@(\\w+\\.)*\\w+$";
        Pattern pat = Pattern.compile(regEmail);
        Matcher mat = pat.matcher(email);
        if (mat.find()) {
            return true;
        }else{
            return false;
        }
    }

    /**
     * 邮箱是否全部显示
     * @param email
     * @param show
     * @return
     */
    public static String emailIsAllShow(String email,boolean show){
        String emailStr="";
        try{
            emailStr= DataEncryption.decrypt(email);//加密数据库加密的邮箱
            if(StringUtils.isNotBlank(emailStr)&& isValidEmail(emailStr)){//邮箱不为空,并且是邮箱格式
                String emailStrContent=emailStr.substring(0,emailStr.lastIndexOf("@"));
                if(emailStrContent.length()>=6){
                    if(show){
                        emailStr=emailStr;
                    }else if(show==false){
                        emailStr=emailStr.substring(0,3)+"******"+emailStr.substring(emailStr.lastIndexOf("@")-2);
                    }
                }else if(emailStrContent.length()>2 && emailStrContent.length()<6){
                    if(show){
                        emailStr=emailStr;
                    }else if(show==false){
                        emailStr=emailStr.substring(0,1)+"******"+emailStr.substring(emailStr.lastIndexOf("@")-1);
                    }
                }else{
                    emailStr="保密";
                }
            }else{
                emailStr="保密";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return emailStr;
    }
}
