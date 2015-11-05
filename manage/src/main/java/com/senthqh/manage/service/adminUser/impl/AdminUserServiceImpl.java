package com.senthqh.manage.service.adminUser.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.senthqh.global.base.BaseService;
import com.senthqh.global.utils.date.CheckDate;
import com.senthqh.global.utils.email.CheckEmail;
import com.senthqh.global.utils.idCard.IdCardCheck;
import com.senthqh.global.utils.phone.CheckPhone;
import com.senthqh.global.utils.pinyin4j.PinYin;
import com.senthqh.global.utils.returnUtils.CodeEnum;
import com.senthqh.global.utils.returnUtils.ResultJsonFormat;
import com.senthqh.manage.entity.adminUser.AdminUserEntity;
import com.senthqh.manage.mapper.adminUser.AdminUserMapper;
import com.senthqh.manage.service.adminUser.AdminUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created Author fengye
 * Created Date 2015/11/4
 * Created Time 17:53
 */
@Service("adminUserService")
public class AdminUserServiceImpl extends BaseService<AdminUserEntity> implements AdminUserService {

    public Map<String, Object> saveAdminUser(AdminUserEntity adminUserEntity) {
        ResultJsonFormat rj=new ResultJsonFormat("恭喜您,用户添加成功!", CodeEnum.SUCCESS.value());
        try{
            //验证名字是否为空
            if(StringUtils.isBlank(adminUserEntity.getAdminName())){
                rj.setFlag(CodeEnum.Failed.value());
                rj.setMsg("温馨提示:用户姓名不能为空!");
                return rj.convertResultJson();
            }
            //判断手机号码是否为空
            if(StringUtils.isBlank(adminUserEntity.getAdminPhone())){
                rj.setFlag(CodeEnum.Failed.value());
                rj.setMsg("温馨提示:用户手机号码不能为空!");
                return rj.convertResultJson();
            }else if(StringUtils.isNotBlank(adminUserEntity.getAdminPhone())){
                if(!CheckPhone.isMobile(adminUserEntity.getAdminPhone())){
                    rj.setFlag(CodeEnum.InvalidParameter.value());
                    rj.setMsg("温馨提示:手机号码格式不符,为13.14.15.17.18开头的11位!");
                    return rj.convertResultJson();
                }else if(CheckPhone.isMobile(adminUserEntity.getAdminPhone())){
                    //判断手机号码是否存在
                    int phoneCount=((AdminUserMapper)mapper).searchAdminUserByAdminPhone(adminUserEntity.getAdminPhone());
                    if(phoneCount!=0){
                        rj.setFlag(CodeEnum.InvalidParameter.value());
                        rj.setMsg("温馨提示:手机号码已经被其他账号绑定!");
                        return rj.convertResultJson();
                    }
                }
            }
            //设置用户的账号(账号格式:姓名首字母-手机号码)
            adminUserEntity.setAdminAccount(PinYin.getFirst(adminUserEntity.getAdminName())+"-"+adminUserEntity.getAdminPhone());
            //设置用户编号(编号组成:admin-手机号码)
            adminUserEntity.setAdminCode("ADMIN-"+adminUserEntity.getAdminPhone());
            //生成用户的英文名称
            adminUserEntity.setAdminEnglish(PinYin.getPinYin(adminUserEntity.getAdminName()));
            //判断邮箱是否符合要求
            if(StringUtils.isBlank(adminUserEntity.getAdminEmail())){
                rj.setFlag(CodeEnum.InvalidParameter.value());
                rj.setMsg("温馨提示:电子邮箱不能为空!");
                return rj.convertResultJson();
            }else if(StringUtils.isNotBlank(adminUserEntity.getAdminEmail())){
                if(!CheckEmail.isValidEmail(adminUserEntity.getAdminEmail())){
                    rj.setFlag(CodeEnum.InvalidParameter.value());
                    rj.setMsg("温馨提示:不是正确的邮箱格式!");
                    return rj.convertResultJson();
                }else if(CheckEmail.isValidEmail(adminUserEntity.getAdminEmail())){//判断邮箱是否存在
                    int emailCount=((AdminUserMapper)mapper).searchAdminUserByAdminEmail(adminUserEntity.getAdminEmail());
                    if(emailCount!=0){
                        rj.setFlag(CodeEnum.InvalidParameter.value());
                        rj.setMsg("温馨提示:电子邮箱已经被其他账号绑定!");
                        return rj.convertResultJson();
                    }
                }
            }
            if(StringUtils.isBlank(adminUserEntity.getAdminIdCard())){
                rj.setFlag(CodeEnum.InvalidParameter.value());
                rj.setMsg("温馨提示:用户身份证号码不能为空!");
                return rj.convertResultJson();
            }else if(StringUtils.isNotBlank(adminUserEntity.getAdminIdCard())){//身份证号码不为空
                //验证身份证号码是否符合要求
                Map<String,Object> idCardMap= IdCardCheck.IDCardValidate(adminUserEntity.getAdminIdCard());
                if(!idCardMap.isEmpty()){
                    if(!idCardMap.get("flag").equals(200)){//身份证号码不符合要求
                        rj.setFlag(Integer.parseInt(idCardMap.get("flag").toString()));
                        rj.setMsg(idCardMap.get("msg").toString());
                        return rj.convertResultJson();
                    }else if(idCardMap.get("flag").equals(200)){
                        //验证身份证号码是否存在
                        int idCardCount=((AdminUserMapper)mapper).searchAdminUserByAdminIdCard(adminUserEntity.getAdminIdCard());
                        if(idCardCount!=0){
                            rj.setFlag(CodeEnum.InvalidParameter.value());
                            rj.setMsg("温馨提示:身份证号码已经被其他账号绑定!");
                        }else if(idCardCount==0){//身份证号码不存在
                            //通过身份证号码,获取用户的生日
                            adminUserEntity.setAdminBirthday(IdCardCheck.getUserBirthdayByIdCard(adminUserEntity.getAdminIdCard()));
                            //通过用户生日,获取用户的年龄
                            adminUserEntity.setAdminAge(IdCardCheck.getAgeByBirthday(adminUserEntity.getAdminBirthday()));
                            //通过身份证号码,获取用户的性别
                            adminUserEntity.setAdminSex(IdCardCheck.getUserSexByIdCard(adminUserEntity.getAdminIdCard()));
                        }
                    }
                }
            }
            //获取用户添加时间
            adminUserEntity.setCreateTime(CheckDate.getTime());
            adminUserEntity.setAdminPassword(adminUserEntity.getAdminIdCard());
            save(adminUserEntity);
        }catch (Exception e){
            e.printStackTrace();
            rj.setFlag(CodeEnum.ERROR.value());
            rj.setMsg("业务发生异常,请联系技术人员!");
        }
        return rj.convertResultJson();
    }

    public Map<String, Object> updateAdminUser(AdminUserEntity adminUserEntity) {
        return null;
    }

    public AdminUserEntity selectAdminUserByAdminCode(String adminCode) {
        return ((AdminUserMapper)mapper).selectAdminUserByAdminCode(adminCode);
    }
    public Map<String, Object> searchAdminUser(String adminCode, String adminAccount, String adminName, String adminEnglish, String adminPhone, String adminEmail, String adminBirthday, String adminSex, String adminStatus, Integer pageNumber, Integer pageSize) {
        Map<String,Object> adminUserMap = new HashMap<String,Object>();
        PageHelper.startPage(pageNumber, pageSize, true);
        Map<String,Object> param=new HashMap<String, Object>();
        if(StringUtils.isNotBlank(adminCode)){
            param.put("adminCode", adminCode);
        }
        if(StringUtils.isNotBlank(adminAccount)){
            param.put("adminAccount",adminAccount);
        }
        if(StringUtils.isNotBlank(adminName)){
            param.put("adminName",adminName);
        }
        if(StringUtils.isNotBlank(adminEnglish)){
            param.put("adminEnglish", adminEnglish);
        }
        if(StringUtils.isNotBlank(adminPhone)){
            param.put("adminPhone", adminPhone);
        }
        if(StringUtils.isNotBlank(adminEmail)){
            param.put("adminEmail", adminEmail);
        }
        if(StringUtils.isNotBlank(adminSex)){
            if(adminSex.equalsIgnoreCase("boy")){
                param.put("adminSex",true);
            }else if(adminSex.equalsIgnoreCase("girl")){
                param.put("adminSex",false);
            }
        }
        if(StringUtils.isNotBlank(adminBirthday)){
            param.put("adminBirthday",adminBirthday);
        }
        if(StringUtils.isNotBlank(adminStatus)){
            if(adminStatus.equalsIgnoreCase("unlock")){
                param.put("adminStatus",true);
            }else if(adminStatus.equalsIgnoreCase("lock")){
                param.put("adminStatus",false);
            }
        }
        Page<AdminUserEntity> page = (Page)((AdminUserMapper)mapper).searchAdminUser(param);
        adminUserMap.put("rows",page);
        adminUserMap.put("records",page.getTotal());
        adminUserMap.put("total",page.getPages());
        adminUserMap.put("page",pageNumber);
        return adminUserMap;
    }

}
