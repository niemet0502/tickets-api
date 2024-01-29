package com.mariusniemet.bookingservice.controllers;

import com.mariusniemet.bookingservice.entities.Booking;
import com.mariusniemet.bookingservice.repositories.IBooking;
import com.mariusniemet.bookingservice.services.BookingService;
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

    private BookingService service;

    public BookingController(IBooking repository, BookingService service) {
        this.repository = repository;
        this.restTemplate = new RestTemplate();
        this.service = service;
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
        Event event = service.getEvent(newBooking.getEventId());

        int remaingQuantity = event.getQuantityTotal() - event.getQuantityBooked();

        if(remaingQuantity >= newBooking.getTotal()){
            // process the booking
            newBooking.setCreatedAt(new Date());
            newBooking.setTotalPrice(event.getPrice() * newBooking.getTotal());

            Booking createdBooking = repository.save(newBooking);

            // call the event service to decrement the number of places
            event.setQuantityBooked(event.getQuantityBooked() + newBooking.getTotal());
            service.updateEvent(newBooking.getEventId(), event);

            return createdBooking;
        }else {
            throw new RuntimeException("The quantity asked isn't available !!");
        }

    }

    @DeleteMapping("/{id}")
    public Booking delete(@PathVariable Long id){

        Booking toDelete = repository.findById(id).orElseThrow();

        // get the event
        Event event = service.getEvent(toDelete.getEventId());

        repository.delete(toDelete);

        // call the event service the restore the booking canceled
        event.setQuantityBooked(event.getQuantityBooked() - toDelete.getTotal());
        service.updateEvent(toDelete.getEventId(), event);

        return toDelete;
    }
}
