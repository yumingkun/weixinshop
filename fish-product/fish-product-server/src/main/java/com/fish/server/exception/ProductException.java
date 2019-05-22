package com.fish.server.exception;

import com.fish.server.enums.ResultEnum;

/**
 * Created by mingkunyu on 2019-05-22
 */
public class ProductException extends RuntimeException {
    private Integer code;

    public ProductException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public ProductException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}
