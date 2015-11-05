package com.senthqh.manage.controller.base;

import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2015/1/6.
 */
@Controller
public class BaseController {

    /**项目的根路径*/
    protected String projectPath(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request.getSession().getServletContext().getRealPath("/");
    }

    /**项目的URI地址*/
    protected String basePath(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String path = request.getContextPath();
        return request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    }
}
