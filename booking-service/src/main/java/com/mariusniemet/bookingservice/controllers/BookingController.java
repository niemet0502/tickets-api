package com.mariusniemet.bookingservice.controllers;

import com.mariusniemet.bookingservice.entities.Booking;
import com.mariusniemet.bookingservice.repositories.IBooking;
import com.mariusniemet.bookingservice.shared.Event;
import jakarta.ws.rs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.awt.print.Book;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    private IBooking repository;

    private RestTemplate restTemplate;

    public BookingController(IBooking repository) {
        this.repository = repository;
        this.restTemplate = new RestTemplate();
    }

    @GetMapping
    public List<Booking> findAll(@RequestParam(required = false) Long eventId) {

        if( eventId != null){
            return  repository.findByEventId(eventId);
        }else {
            return repository.findAll();
        }
    }

    @PostMapping
    public Booking create(@RequestBody Booking newBooking){
        String url = "http://localhost:7003/events" + "/" + newBooking.getEventId();

        // call the event service to check if the number of places the user wants to buy is still available
        Event event = restTemplate.getForEntity(url, Event.class).getBody();

        System.out.println(event.getQuantityBooked());
        System.out.println(newBooking.getTotal());

        int remaingQuantity = event.getQuantityTotal() - event.getQuantityBooked();

        if(remaingQuantity >= newBooking.getTotal()){
            // process the booking
            newBooking.setCreatedAt(new Date());
            newBooking.setTotalPrice(event.getPrice() * newBooking.getTotal());

            Booking createdBooking = repository.save(newBooking);

            // call the event service to decrement the number of places
            event.setQuantityBooked(event.getQuantityBooked() + newBooking.getTotal());
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Object> requestEntity = new HttpEntity<>(event, headers);

            // Make PUT request
            restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Event.class);

            return createdBooking;
        }else {
            throw new RuntimeException("The quantity asked isn't available !!");
        }

    }

    @DeleteMapping("/{id}")
    public Booking delete(@PathVariable Long id){
        Booking toDelete = repository.findById(id).orElseThrow();

        // call the event service the restore the booking canceled

        repository.delete(toDelete);
        return toDelete;
    }
}
