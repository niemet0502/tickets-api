package com.mariusniemet.eventsservice.producers;

import com.mariusniemet.eventsservice.events.CustomEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class EventsProducer {
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessage(String topic, String message) {

        kafkaTemplate.send(topic, new CustomEvent(message) );
    }
}
