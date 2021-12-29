package com.paathshala.RsvpDataStorage.controller;

import com.paathshala.RsvpDataStorage.model.MeetupRSVP;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class RsvpController {

    private final ReactiveMongoTemplate mongoTemplate;

    public RsvpController(ReactiveMongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
    }

    @GetMapping(value = "/meetupRsvps", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<MeetupRSVP> meetupRsvps(){
        return mongoTemplate.tail(
                new Query(), MeetupRSVP.class).share();
    }

}
