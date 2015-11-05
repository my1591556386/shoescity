package com.senthqh.manage.init;

import com.senthqh.global.entity.init.InitDataEntity;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by zhaoqian on 2015/5/5.
 */
@Component("applicationContextInit")
public class ApplicationContextInit implements ApplicationListener<ContextRefreshedEvent> {
    public void onApplicationEvent(ContextRefreshedEvent applicationEvent) {
        if (applicationEvent.getApplicationContext().getParent() == null) {
            Resource re = new ClassPathResource("/init.properties");
            Map map = new HashMap();
            try {
                Properties properties = PropertiesLoaderUtils.loadProperties(re);
                for(Object str:properties.keySet()){
                    map.put(str,properties.get(str));
                }
                InitDataEntity initDataEntity=new InitDataEntity();
                initDataEntity.setMap(map);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
