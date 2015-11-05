package com.senthqh.global.utils.returnUtils;



import com.senthqh.global.utils.system.AppConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * Created Author fengye
 * Created Date 2015/10/23
 * Created Time 18:13
 * 返回的参数信息
 */
public class ResultJsonFormat {

    /** 请求返回的Json中的信息MSG */
    private String msg;
    /** 请求返回数据对象(json || obj) */
    private Object rows;
    /** 是否成功 默认1为成功 */
    private Integer flag = 200;
    public ResultJsonFormat(Integer flag) {
        super();
        this.flag = flag;
    }
    /**
     * 适用于添加、修改、删除
     *
     * @param msg  消息
     * @param flag 是否成功 true成功 false失败
     */
    public ResultJsonFormat(String msg, Integer flag) {
        super();
        this.msg = msg;
        this.flag = flag;
    }

    /**
     * 只返回数据和标记成功和失败
     *
     * @param rows
     * @param flag
     */
    public ResultJsonFormat(Object rows, Integer flag) {
        super();
        this.rows = rows; //用于easyui数据封装
        this.flag = flag;
    }

    /**
     * 适用于添加、修改、删除
     *
     * @param msg 	消息
     * @param rows 	操作后的返回数据
     * @param flag  是否成功
     */
    public ResultJsonFormat(String msg, Object rows, Integer flag) {
        super();
        this.msg = msg;
        this.rows = rows;
        this.flag = flag;
    }
    /**
     * 自动处理需要传递到客户端或则网页的数据(包含rows和data)
     * @return
     */
    public Map<String, Object> convertResultJson() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (msg != null && !"".equals(msg)) {
            resultMap.put(AppConfig.KEY_MSG, msg);
        }
        if (rows != null) {
            resultMap.put(AppConfig.KEY_ROWS, rows);
        }
        resultMap.put(AppConfig.KEY_FLAG, flag);
        return resultMap;
    }
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getRows() {
        return rows;
    }

    public void setRows(Object rows) {
        this.rows = rows;
    }
    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }
}
