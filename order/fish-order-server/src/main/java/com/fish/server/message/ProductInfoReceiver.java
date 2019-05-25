package com.fish.server.message;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fish.common.ProductInfoOutput;
import com.fish.server.untils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by mingkunyu on 2019-05-25
 */

@Component
@Slf4j
public class ProductInfoReceiver {


    private static final String PRODUCT_STOCK_TEMPLATE="product_stock_%s";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @RabbitListener(queuesToDeclare = @Queue("productInfo"))
    public void process(String message){


        //message=>ProductInfoOutput
        List<ProductInfoOutput> productInfoOutputList=( List<ProductInfoOutput>) JsonUtil.formJson(
                message,
                new TypeReference<List<ProductInfoOutput>>(){}
                );
        log.info("从队列{}接收消息：{}","productInfo",productInfoOutputList);


        //存储在redis中
        for (ProductInfoOutput productInfoOutput : productInfoOutputList) {
            stringRedisTemplate.opsForValue().set(String.format(PRODUCT_STOCK_TEMPLATE,productInfoOutput.getProductId()),String.valueOf(productInfoOutput.getProductStock()));

        }

    }
}
