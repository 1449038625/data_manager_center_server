package com.zbz.usercenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zbz.usercenter.model.User;

import javax.servlet.http.HttpServletRequest;

/**
* @author 14490
* @description 针对表【user(用户)】的数据库操作Service
* @createDate 2025-01-18 18:46:11
*/
public interface UserService extends IService<User> {

    /**
     * 用户登录
     * @param userAccount
     * @param userPassword
     * @return 脱敏后的用户信息
     */
    User doLogin(String userAccount, String userPassword, HttpServletRequest request);
    /**
     * 用户注册
     * @param userAccount
     * @param userPassword
     * @param checkPassword
     * @return 注册用户id
     */
    Long userRegister(String userAccount, String userPassword, String checkPassword);
}
