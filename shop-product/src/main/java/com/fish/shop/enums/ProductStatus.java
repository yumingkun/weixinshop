package com.fish.shop.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by mingkunyu on 2019-05-19
 */

@Getter
public enum  ProductStatus {
    UP(0,"在架"),
    DOWN(1,"下架");

    private Integer code;
    private String message;

    ProductStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
