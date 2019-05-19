package com.fish.demo.service;

import com.fish.demo.dto.OrderDTO;

/**
 * Created by mingkunyu on 2019-05-19
 */
public interface OrderService  {
    /**
     * 创建订单
     * @param orderDTO
     * @return
     */
    OrderDTO create(OrderDTO orderDTO);
}
