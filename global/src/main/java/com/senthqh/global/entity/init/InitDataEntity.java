package com.senthqh.global.entity.init;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created Author fengye
 * Created Date 2015/10/26
 * Created Time 15:35
 * 服务器启动初始数据实体
 */
@Component
public class InitDataEntity {
    private static Map<String,Object> map=new HashMap<String,Object>();

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }
}
