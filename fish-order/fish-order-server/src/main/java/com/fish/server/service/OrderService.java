package com.fish.server.service;


import com.fish.server.dto.OrderDTO;
import org.apache.commons.lang.StringUtils;

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

    /**
     * 完结订单（ 只能卖家操作）
     * @param orderId
     * @return
     */
    OrderDTO finish(String orderId);
}
