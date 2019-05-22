package com.fish.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by mingkunyu on 2019-05-21
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDTD {

    /**
     *   商品ID
     */
    private String productId;
    /**
     *   商品数量
     */
    private Integer productQuantity;
}
