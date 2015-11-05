package com.senthqh.global.utils.pinyin4j;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * Created Author fengye
 * Created Date 2015/10/25
 * Created Time 14:47
 * 获取中文对应的英文名称工具类
 */
public class PinYin {

    /**
     * 获取全部拼音
     *
     * @param src
     *            原字符串
     * @return
     */
    public static String getPinYin(String src) {
        char[] srcChar = src.toCharArray();
        String[] srcArry = new String[srcChar.length];
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();

        // 设置格式
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_V);
        String result = "";
        try {
            for (int i = 0; i < srcChar.length; i++) {
                // 判断是否为汉字字符
                if (Character.toString(srcChar[i])
                        .matches("[\\u4E00-\\u9FA5]+")) {
                    srcArry = PinyinHelper.toHanyuPinyinStringArray(srcChar[i],
                            format);
                    result += srcArry[0];
                } else{
                    if(i==0){
                        result +=Character.toString(srcChar[i])+"_";
                    }else{
                        result += Character.toString(srcChar[i]);
                    }
                }
            }
            return result.toUpperCase();
        } catch (BadHanyuPinyinOutputFormatCombination e1) {
            e1.printStackTrace();
        }
        return result.toUpperCase();
    }

    /**
     * 获取首字母拼音
     *
     * @param str
     *            原字符串
     * @return
     */
    public static String getFirst(String str) {

        String result = "";
        for (int j = 0; j < str.length(); j++) {
            char word = str.charAt(j);
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
            if (pinyinArray != null) {
                result += pinyinArray[0].charAt(0);
            } else {
                result += word;
            }
        }
        return result.toUpperCase();
    }
    public static void main(String[] args){
        System.out.print(getPinYin("三123"));
    }
}
