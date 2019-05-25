package com.fish.server.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 接收mq消息
 * Created by mingkunyu on 2019-05-23
 */

@Slf4j
@Component
public class MqReceiver  {
//    1：@RabbitListener(queues = "myQueues")
//    2：自动创建消息队列  @RabbitListener(queuesToDeclare = @Queue("myQueue"))
//    3:自动创建消息队列 Exchange绑定queue

    @RabbitListener(bindings =@QueueBinding(
            value = @Queue("myQueue"),
            exchange = @Exchange("myExchange")
    ))
    public void process(String message){
        log.info("myQueues: {}",message);
    }


    /**
     * 水果供应商服务 接收消息
     *
     * @param message
     */

    @RabbitListener(bindings =@QueueBinding(
            exchange = @Exchange("myExchange"),
            key = "fruit",
            value = @Queue("myOrder")
    ))
    public void processFruit(String message){
        log.info("fruit myQueues: {}",message);
    }

    /**
     * 手机供应商服务 接收消息
     *
     * @param message
     */

    @RabbitListener(bindings =@QueueBinding(
            exchange = @Exchange("computerExchange"),
            key = "computer",
            value = @Queue("myOrder")
    ))
    public void processComputer(String message){
        log.info("computer myQueues: {}",message);
    }

}
