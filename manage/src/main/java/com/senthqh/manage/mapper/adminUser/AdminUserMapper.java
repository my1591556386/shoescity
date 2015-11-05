package com.senthqh.manage.mapper.adminUser;

import com.github.abel533.mapper.Mapper;
import com.senthqh.manage.entity.adminUser.AdminUserEntity;

import java.util.List;
import java.util.Map;

/**
 * Created Author fengye
 * Created Date 2015/11/4
 * Created Time 17:06
 */
public interface AdminUserMapper extends Mapper<AdminUserEntity>{
    /**
     * 通过编号,查询编号是否存在
     * @param adminCode 编号
     * @return
     */
    public int searchAdminUserByAdminCode(String adminCode);

    /**
     * 通过账号,查询账号是否存在
     * @param adminAccount 账号
     * @return
     */
    public int searchAdminUserByAdminAccount(String adminAccount);

    /**
     * 通过手机号码,查询号码是否存在
     * @param adminPhone 手机号码
     * @return
     */
    public int searchAdminUserByAdminPhone(String adminPhone);

    /**
     * 通过电子邮箱，查询邮箱是否存在
     * @param adminEmail 电子邮箱
     * @return
     */
    public int searchAdminUserByAdminEmail(String adminEmail);

    /**
     * 通过身份证号码,查询身份证号码是否存在
     * @param adminIdCard 身份证号码
     * @return
     */
    public int searchAdminUserByAdminIdCard(String adminIdCard);

    /**
     * 分页查询管理员账号
     * @param map
     * @return
     */
    public List<AdminUserEntity> searchAdminUser(Map<String, Object> map);

    /**
     * 查询用户详细信息
     * @param adminCode
     * @return
     */
    public AdminUserEntity selectAdminUserByAdminCode(String adminCode);

}
