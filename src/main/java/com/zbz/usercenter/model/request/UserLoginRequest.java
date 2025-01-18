package com.zbz.usercenter.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * Classname: UserLoginRequest
 * Package: com.zbz.usercenter.model.request
 * Decription:
 *
 * @Author: 爱可尼科
 * @Create: 2025/1/18 - 23:41
 * @Version: v1.0
 */
@Data
public class UserLoginRequest implements Serializable {
    private static final long serialVersionUID = 3191241716373120793L;
    private String userAccount;
    private String userPassword;

}
