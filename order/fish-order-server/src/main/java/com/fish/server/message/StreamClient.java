package com.fish.server.message;

        import org.springframework.cloud.stream.annotation.Input;
        import org.springframework.messaging.MessageChannel;
        import org.springframework.messaging.SubscribableChannel;

/**
 * Created by mingkunyu on 2019-05-24
 */
public interface StreamClient {

    String INPUT="myMessage";


    @Input(StreamClient.INPUT)
    SubscribableChannel input();


    @Input(StreamClient.INPUT)
    MessageChannel output();

}
