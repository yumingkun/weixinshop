package com.fish.common;

import lombok.Data;

/**
 * 减库存入参
 * Created by mingkunyu on 2019-05-19
 */
@Data
public class DecreaseStockInput {

    private String productId;

    private Integer productQuantity;

    public DecreaseStockInput() {
    }

    public DecreaseStockInput(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}