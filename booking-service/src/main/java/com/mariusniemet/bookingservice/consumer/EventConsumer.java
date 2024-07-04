package com.mariusniemet.bookingservice.consumer;


import com.mariusniemet.bookingservice.events.CustomEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
public class EventConsumer {
    @KafkaListener(topics = "event-created", groupId = "tickets-group")
    public void listen(Object message) {
        if(message instanceof CustomEvent) {
            CustomEvent event = (CustomEvent) message;
            System.out.println("Received message: " + event.getName());
        }

    }
}
