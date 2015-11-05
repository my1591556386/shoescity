package com.senthqh.global.utils.system;

import java.util.Map;

/**
 * Created Author fengye
 * Created Date 2015/10/23
 * Created Time 18:21
 * 应用程序上下文配置
 */
public class AppConfig {
    public static Map map;

    /** 请求返回的数据中"错误信息"的key */
    public static final String KEY_MSG = "msg";
    /** 请求返回的数据中"数据信息"的key */
    public static final String KEY_ROWS = "rows";
    /** 请求返回的数据中"标识"的key */
    public static final String KEY_FLAG = "flag";
    /** 系统默认的角色code */
    public static final String[] SYSCODELI = {"SYS_MASSES","SYS_TEACHER","SYS_STUDENT","SYS_ADMIN"};//

    public static String get(String key){
        return map.get(key).toString();
    }
}
