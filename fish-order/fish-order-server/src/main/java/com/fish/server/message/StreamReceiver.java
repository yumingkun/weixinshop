package com.fish.server.message;

import com.fish.server.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

/**
 * Created by mingkunyu on 2019-05-24
 */
@Slf4j
@Component
@EnableBinding(StreamClient.class)
public class StreamReceiver {

//    @StreamListener(StreamClient.INPUT)
//    public void process(Object message){
//
//        log.info("StreamReceiver:{}",message);
//    }


    /**
     * 接收方
     * @param message
     */
    @StreamListener(StreamClient.INPUT)
    public void process(OrderDTO message){

        log.info("StreamReceiver:{}",message);
    }
}
