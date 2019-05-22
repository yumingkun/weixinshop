package com.fish.server.enums;

import lombok.Getter;

/**
 * Created by mingkunyu on 2019-05-19
 */

@Getter
public enum ProductStatus {
    UP(0, "在架"),
    DOWN(1, "下架");

    private Integer code;
    private String message;

    ProductStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
