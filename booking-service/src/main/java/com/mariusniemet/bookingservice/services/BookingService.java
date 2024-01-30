package com.mariusniemet.bookingservice.services;

import com.mariusniemet.bookingservice.shared.Event;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BookingService {

    private RestTemplate restTemplate;

    public BookingService() {
        this.restTemplate = new RestTemplate();
    }

    public Event getEvent(int eventID){
        String url = "http://localhost:7003/events" + "/" + eventID;

       return restTemplate.getForEntity(url, Event.class).getBody();
    }

    public Event updateEvent(int eventId, Event toUpdate){
        String url = "http://localhost:7003/events" + "/" + eventId;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> requestEntity = new HttpEntity<>(toUpdate, headers);

        // Make PUT request
        Event response =  restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Event.class).getBody();

        System.out.println(response.getName());
        System.out.println(response.getAddress());
        return  response;
    }
}
