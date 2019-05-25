package com.fish.server.controller;

import com.fish.server.dto.OrderDTO;
import com.fish.server.message.StreamClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by mingkunyu on 2019-05-25
 */

@RestController
public class SendMessageController {

    @Autowired
    private StreamClient streamClient;

//    @GetMapping("/sendMessage")
//    public  void process(){
//        streamClient.output().send(MessageBuilder.withPayload("now"+new Date()).build());
//    }

    /**
     *  发送方（传递对象
     */
    @GetMapping("/sendMessage")
    public  void process(){
        OrderDTO orderDTO=new OrderDTO();
        orderDTO.setOrderId("123456");
        streamClient.output().send(MessageBuilder.withPayload("now"+new Date()).build());
    }


}
