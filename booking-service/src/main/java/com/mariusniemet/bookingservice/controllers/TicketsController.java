package com.mariusniemet.bookingservice.controllers;

import com.mariusniemet.bookingservice.entities.Ticket;
import com.mariusniemet.bookingservice.services.TicketsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketsController {
    private final TicketsService service;

    public TicketsController(TicketsService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Ticket>> finAll(@RequestParam int eventId){
        List<Ticket> result = this.service.findAll(eventId);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
