package com.fish.demo.repository;

import com.fish.demo.OrderApplication;
import com.fish.demo.entity.OrderMaster;
import com.fish.demo.enums.OrderStatusEnum;
import com.fish.demo.enums.PayStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by mingkunyu on 2019-05-19
 */
@Component
public class OrderDetailRepositoryTest extends OrderApplication {
    @Autowired
    private OrderMaterRepository orderMaterRepository;

    @Test
    public void OrderMaterRepositoryTestSave(){

        OrderMaster orderMaster=new OrderMaster();
        orderMaster.setOrderId("123456");
        orderMaster.setBuyerName("yu");
        orderMaster.setBuyerPhone("110");
        orderMaster.setBuyerAddress("di");
        orderMaster.setBuyerOpenid("01110");
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMaster.setCreateTime(new Date());
        orderMaster.setUpdateTime(new Date());
        orderMaster.setOrderAmount(new BigDecimal(2.5));

        OrderMaster result=orderMaterRepository.save(orderMaster);
        Assert.assertTrue(result!=null);
    }
}