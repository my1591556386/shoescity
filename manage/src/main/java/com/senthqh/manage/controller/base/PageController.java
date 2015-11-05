package com.senthqh.manage.controller.base;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Administrator on 2015/11/3.
 * 页面控制器
 */
@Controller
public class PageController extends BaseController {
    @RequestMapping("/index")
    public ModelAndView index(){
        return new ModelAndView("index","data",this.basePath());
    }

    @RequestMapping("/main")
    public String main(){
        return "index/indexContent";
    }
}
