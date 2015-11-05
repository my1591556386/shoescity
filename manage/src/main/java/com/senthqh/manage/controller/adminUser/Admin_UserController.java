package com.senthqh.manage.controller.adminUser;

import com.senthqh.manage.entity.adminUser.AdminUserEntity;
import com.senthqh.manage.service.adminUser.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created Author fengye
 * Created Date 2015/11/4
 * Created Time 17:59
 */
@Controller
@RequestMapping("admin_user/*")
public class Admin_UserController {
    @Autowired
    private AdminUserService adminUserService;

    /**
     * 跳转到后台账号管理页面
     * @return
     */
    @RequestMapping("adminUser")
    public String adminUser(){
        return "adminUser/adminUser";
    }
    @RequestMapping("adminUserList")
    public String adminUserList(
            @RequestParam(value="adminCode",required = false) String adminCode,
            @RequestParam(value="adminAccount",required = false) String adminAccount,
            @RequestParam(value="adminName",required = false) String adminName,
            @RequestParam(value="adminEnglish",required = false) String adminEnglish,
            @RequestParam(value="adminPhone",required = false) String adminPhone,
            @RequestParam(value="adminEmail",required = false) String adminEmail,
            @RequestParam(value="adminBirthday",required = false) String adminBirthday,
            @RequestParam(value="adminSex",required = false ) String adminSex,
            @RequestParam(value="adminStatus",required = false) String adminStatus,
            @RequestParam(value="pageNumber",required = false,defaultValue = "1") Integer pageNumber,
            @RequestParam(value="pageSize",required = false,defaultValue = "10") Integer pageSize,
            Model model
    ){
        model.addAttribute("data",adminUserService.searchAdminUser(adminCode, adminAccount, adminName, adminEnglish, adminPhone, adminEmail, adminBirthday, adminSex, adminStatus, pageNumber, pageSize));
        return "adminUser/adminUserList";
    }

    /**
     * 查看详情
     * @param adminCode
     * @return
     */
    @RequestMapping("showAdminUser")
    public String showAdminUser(@RequestParam(value="adminCode",required = false)String adminCode,Model model){
        model.addAttribute("adminUser",adminUserService.selectAdminUserByAdminCode(adminCode));
        return "adminUser/showAdminUser";
    }

    /**
     * 跳转到添加用户页面
     * @return
     */
    @RequestMapping("addAdminUser")
    public String addAdminUser(){
        return "adminUser/addAdminUser";
    }

    /**
     * 保存用户数据
     * @param adminUserEntity
     * @return
     */
    @RequestMapping("saveAdminUser")
    @ResponseBody
    public Map<String,Object> saveAdminUser(AdminUserEntity adminUserEntity){
        return adminUserService.saveAdminUser(adminUserEntity);
    }

}
