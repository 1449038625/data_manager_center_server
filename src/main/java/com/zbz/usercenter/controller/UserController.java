package com.zbz.usercenter.controller;

import com.zbz.usercenter.model.User;
import com.zbz.usercenter.model.request.UserLoginRequest;
import com.zbz.usercenter.model.request.UserRegisterRequest;
import com.zbz.usercenter.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Classname: UserController
 * Package: com.zbz.data_manager_center_server.controller
 * Decription:
 *
 * @Author: 爱可尼科
 * @Create: 2025/1/18 - 23:26
 * @Version: v1.0
 */
@RestController
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/user/regist/")
    public Long userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        if(StringUtils.isAllBlank(userAccount, userPassword, checkPassword)){
            return null;
        }
        return userService.userRegister(userAccount, userPassword, checkPassword);
    }

    @PostMapping("/user/login/")
    public User doLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        if(StringUtils.isAllBlank(userAccount, userPassword)){
            return null;
        }
        return userService.doLogin(userAccount, userPassword, request);
    }

}
