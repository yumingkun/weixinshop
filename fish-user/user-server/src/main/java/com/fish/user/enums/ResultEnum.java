package com.fish.user.enums;

import lombok.Getter;

/**
 * Created by mingkunyu on 2019-05-22
 */
@Getter
public enum ResultEnum {

    LOGIN_FAIL(1,"登录失败"),
    POLE_ERROR(2,"角色权限有误")
    ;


    private Integer code;
    private String message;


    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
