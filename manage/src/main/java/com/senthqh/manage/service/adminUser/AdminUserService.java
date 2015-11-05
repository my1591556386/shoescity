package com.senthqh.manage.service.adminUser;


import com.senthqh.manage.entity.adminUser.AdminUserEntity;

import java.util.Map;

/**
 * Created Author fengye
 * Created Date 2015/11/4
 * Created Time 17:42
 */
public interface AdminUserService {
    /**
     * 添加账号
     * @param adminUserEntity
     * @return
     */
    public Map<String,Object> saveAdminUser(AdminUserEntity adminUserEntity);

    /**
     * 修改账号
     * @param adminUserEntity
     * @return
     */
    public Map<String,Object> updateAdminUser(AdminUserEntity adminUserEntity);

    /**
     * 根据用户编号,查询数据
     * @param adminCode
     * @return
     */
    public AdminUserEntity selectAdminUserByAdminCode(String adminCode);

    /**
     * 条件分页查询账号
     * @param adminCode 编号
     * @param adminAccount 账号
     * @param adminName 姓名
     * @param adminEnglish 英文名称
     * @param adminPhone 手机号码
     * @param adminEmail 电子邮箱
     * @param adminBirthday 出生日期
     * @param adminSex 性别
     * @param adminStatus 状态
     * @param pageNumber 页码
     * @param pageSize 每页显示多少条数据
     * @return
     */
    public Map<String,Object> searchAdminUser(String adminCode, String adminAccount, String adminName, String adminEnglish, String adminPhone, String adminEmail, String adminBirthday, String adminSex, String adminStatus, Integer pageNumber, Integer pageSize);
}
