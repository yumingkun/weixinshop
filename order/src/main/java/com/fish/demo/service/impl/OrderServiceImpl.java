package com.fish.demo.service.impl;

import com.fish.demo.dto.OrderDTO;
import com.fish.demo.entity.OrderMaster;
import com.fish.demo.enums.OrderStatusEnum;
import com.fish.demo.enums.PayStatusEnum;
import com.fish.demo.repository.OrderDetailRepository;
import com.fish.demo.repository.OrderMaterRepository;
import com.fish.demo.service.OrderService;
import com.fish.demo.untils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Created by mingkunyu on 2019-05-19
 */
@Service
public class OrderServiceImpl  implements OrderService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private OrderMaterRepository orderMaterRepository;


    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        /**
         * 1:参数校验
         * 2：查询商品信息（调用商品服务）
         * 3；计算总价
         * 4：扣库存
         * 5：订单入库
         */

        //订单入库
        OrderMaster orderMaster=new OrderMaster();
        orderDTO.setOrderId(KeyUtil.genUniqueKey());
        BeanUtils.copyProperties(orderDTO,orderMaster);//拷贝到orderMaster

        orderMaster.setOrderAmount(new BigDecimal(5));//用来对超过16位有效位的数进行精确的运算
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());

        orderMaterRepository.save(orderMaster);

        return orderDTO;
    }
}
