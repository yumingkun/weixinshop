package com.fish.demo.enums;

import lombok.Getter;

/**
 * Created by mingkunyu on 2019-05-19
 */
@Getter
public enum PayStatusEnum  {
    WAIT(0,"等待支付"),
    FINISHED(1,"支付成功");

    private Integer code;
    private String message;

    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
