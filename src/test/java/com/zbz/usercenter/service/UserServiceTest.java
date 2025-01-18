package com.zbz.usercenter.service;

import com.zbz.usercenter.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * Classname: UserServiceTest
 * Package: com.zbz.data_manager_center_server.service
 * Decription:
 *
 * @Author: 爱可尼科
 * @Create: 2025/1/18 - 18:57
 * @Version: v1.0
 */
@SpringBootTest
@Slf4j
class UserServiceTest {
    @Resource
    private UserService userService;
    @Test
    public void userServiceTest() {
        List<User> list = userService.list();
        log.info("执行成功");
        System.out.println(list);
    }

    @Test
    void doLogin() {
        User user = userService.doLogin("yupi", "12345678", null);
        System.out.println(user);
    }

    @Test
    void userRegister() {
        Long userId = userService.userRegister("survival", "12345678", "12345678");
        System.out.println("userId = " + userId);
    }
}