package com.paathshala.RsvpApplication;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class RsvpWebSocketHandler extends AbstractWebSocketHandler {

    private static final Logger logger =
            Logger.getLogger(RsvpWebSocketHandler.class.getName());

    private final RsvpKafkaProducer rsvpKafkaProducer;

    public RsvpWebSocketHandler(RsvpKafkaProducer rsvpKafkaProducer){
        this.rsvpKafkaProducer = rsvpKafkaProducer;
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message){
        logger.log(Level.INFO,
                "New RSVP: \n {0}",
                message.getPayload());

        rsvpKafkaProducer.sendRsvpsMessage(message);
    }

}
