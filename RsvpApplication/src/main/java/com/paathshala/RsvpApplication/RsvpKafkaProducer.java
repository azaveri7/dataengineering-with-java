package com.paathshala.RsvpApplication;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketMessage;

@Component
@EnableBinding(Source.class)
public class RsvpKafkaProducer {

    private static final int SENDING_MESSAGE_TIMEOUT_MS = 100000;

    private final Source source;

    public RsvpKafkaProducer(Source source){
        this.source = source;
    }

    public void sendRsvpsMessage(WebSocketMessage<?> message){
        source.output()
                .send(MessageBuilder.withPayload(message.getPayload()).build(),
                        SENDING_MESSAGE_TIMEOUT_MS);
    }


}
