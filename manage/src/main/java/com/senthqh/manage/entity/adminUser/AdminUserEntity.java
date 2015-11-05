package com.senthqh.manage.entity.adminUser;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created Author fengye
 * Created Date 2015/11/4
 * Created Time 16:40
 * 后台管理人员实体
 */
@Table(name="sys_admin")
public class AdminUserEntity implements Serializable{
    @Column(name="admin_id")
    private String adminId;//ID
    @Column(name="admin_code")
    private String adminCode;//编号(组成:姓名首字母_手机号码)
    @Column(name = "admin_account")
    private String adminAccount;//账号
    @Column(name="admin_password")
    private String adminPassword;//登录密码
    @Column(name="admin_name")
    private String adminName;//姓名
    @Column(name="admin_english")
    private String adminEnglish;//英文名称
    @Column(name="admin_idCard")
    private String adminIdCard;//身份证号码
    @Column(name="admin_sex")
    private boolean adminSex;//性别(true/男,false/女)
    @Column(name="admin_age")
    private int adminAge;//年龄
    @Column(name="admin_birthday")
    private String adminBirthday;//出生日期
    @Column(name="admin_photo")
    private String adminPhoto;//头像
    @Column(name="admin_phone")
    private String adminPhone;//手机号码
    @Column(name="admin_email")
    private String adminEmail;//电子邮箱
    @Column(name="admin_address")
    private String adminAddress;//家庭地址
    @Column(name="admin_status")
    private boolean adminStatus;//状态(true/正常,false/锁定)
    @Column(name="create_time")
    private String createTime;//创建时间
    @Column(name="delete_data")
    private boolean deleteData;//是否删除数据

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getAdminCode() {
        return adminCode;
    }

    public void setAdminCode(String adminCode) {
        this.adminCode = adminCode;
    }

    public String getAdminAccount() {
        return adminAccount;
    }

    public void setAdminAccount(String adminAccount) {
        this.adminAccount = adminAccount;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminEnglish() {
        return adminEnglish;
    }

    public void setAdminEnglish(String adminEnglish) {
        this.adminEnglish = adminEnglish;
    }

    public String getAdminIdCard() {
        return adminIdCard;
    }

    public void setAdminIdCard(String adminIdCard) {
        this.adminIdCard = adminIdCard;
    }

    public boolean isAdminSex() {
        return adminSex;
    }

    public void setAdminSex(boolean adminSex) {
        this.adminSex = adminSex;
    }

    public int getAdminAge() {
        return adminAge;
    }

    public void setAdminAge(int adminAge) {
        this.adminAge = adminAge;
    }

    public String getAdminBirthday() {
        return adminBirthday;
    }

    public void setAdminBirthday(String adminBirthday) {
        this.adminBirthday = adminBirthday;
    }

    public String getAdminPhoto() {
        return adminPhoto;
    }

    public void setAdminPhoto(String adminPhoto) {
        this.adminPhoto = adminPhoto;
    }

    public String getAdminPhone() {
        return adminPhone;
    }

    public void setAdminPhone(String adminPhone) {
        this.adminPhone = adminPhone;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public String getAdminAddress() {
        return adminAddress;
    }

    public void setAdminAddress(String adminAddress) {
        this.adminAddress = adminAddress;
    }

    public boolean getAdminStatus() {
        return adminStatus;
    }

    public void setAdminStatus(boolean adminStatus) {
        this.adminStatus = adminStatus;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public boolean isDeleteData() {
        return deleteData;
    }

    public void setDeleteData(boolean deleteData) {
        this.deleteData = deleteData;
    }
}
