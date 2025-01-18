package com.zbz.usercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zbz.usercenter.mapper.UserMapper;
import com.zbz.usercenter.model.User;
import com.zbz.usercenter.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
* @author 14490
* @description 针对表【user(用户)】的数据库操作Service实现
* @createDate 2025-01-18 18:46:11
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {

    private static final String USER_LOGIN_STATE = "userLoginState";
    @Override
    public User doLogin(String userAccount, String userPassword, HttpServletRequest request) {
        //1. 检验用户账号和密码是否合法（非空，账号不小于4位，密码不小于8位，帐号不包含特殊字符）
        if (StringUtils.isAllBlank(userAccount, userPassword)){
            return null;
        }
        if(userAccount.length() < 4 || userPassword.length() < 8){
            return null;
        }
        if(!userAccount.matches("^[a-zA-Z0-9]+$")){
            return null;
        }
        //2. 检验密码是否正确
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount", userAccount);
        User user = this.getOne(queryWrapper);
        String s1 = DigestUtils.md5DigestAsHex(userPassword.getBytes());
        if (user==null||!s1.equals(user.getUserpassword())){
            return null;
        }
        //3. 用户信息脱敏处理
        User userTuoMing= new User();
        userTuoMing.setId(user.getId());
        userTuoMing.setUsername(user.getUsername());
        userTuoMing.setUseraccount(user.getUseraccount());
        userTuoMing.setAvatarurl(user.getAvatarurl());
        userTuoMing.setGender(user.getGender());
        userTuoMing.setUserpassword("");
        userTuoMing.setPhone(user.getPhone());
        userTuoMing.setEmail(user.getEmail());
        userTuoMing.setUserstatus(user.getUserstatus());
        userTuoMing.setCreatetime(user.getCreatetime());
        userTuoMing.setUpdatetime(user.getUpdatetime());
        userTuoMing.setIsdelete(user.getIsdelete());
        userTuoMing.setUserrole(user.getUserrole());
        userTuoMing.setPlanetcode(user.getPlanetcode());
        //4. 记录用户的登录态（ Session ），将其存到服务器上（用后端 SpringBoot 框架封装的服务器 Tomcat 记录）
        request.getSession().setAttribute(USER_LOGIN_STATE, userTuoMing);
        //5. 返回脱敏后的用户信息
        return userTuoMing;
    }

    @Override
    public Long userRegister(String userAccount, String userPassword, String checkPassword) {
        //1. 检验用户账号和密码和校验码是否合法（非空，账号不小于4位，密码不小于8位，帐号不包含特殊字符，密码和校验码相同，账号不能重复）
        if (StringUtils.isAllBlank(userAccount, userPassword, checkPassword)){
            return null;
        }
        if(userAccount.length() < 4 || userPassword.length() < 8){
            return null;
        }
        if(!userAccount.matches("^[a-zA-Z0-9]+$")){
            return null;
        }
        if(!userPassword.equals(checkPassword)){
            return null;
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount", userAccount);
        long count = this.count(queryWrapper);
        if (count > 0){
            return null;
        }
        //2. 对密码加密
        String s1 = DigestUtils.md5DigestAsHex(userPassword.getBytes());
        //3. 插入数据库中
        User user = new User();
        user.setUseraccount(userAccount);
        user.setUserpassword(s1);
        user.setCreatetime(new Date());
        boolean save = this.save(user);
        //4. 返回用户id
        if (save){
            return user.getId();
        }else {
            return null;
        }
    }
}




