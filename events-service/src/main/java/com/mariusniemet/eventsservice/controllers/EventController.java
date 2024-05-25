package com.mariusniemet.eventsservice.controllers;

import com.mariusniemet.eventsservice.dtos.EventCreateDto;
import com.mariusniemet.eventsservice.entities.Event;
import com.mariusniemet.eventsservice.services.EventsService;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/events")
public class EventController {

    private final EventsService service;

    public EventController(EventsService service) {
        this.service = service;
    }


    @GetMapping
    public ResponseEntity<List<Event>> findAll(){
        List<Event> result = service.findAll();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Event> create(@RequestBody EventCreateDto eventCreateDto) throws BadRequestException {
        Event result = this.service.create(eventCreateDto);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Event> update(@PathVariable Long id, @RequestBody EventCreateDto updateDto) throws BadRequestException {
        Event result = this.service.update(id, updateDto);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> findOne(@PathVariable Long id) throws BadRequestException{
        Event result = this.service.findOne(id);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Event> remove(@PathVariable  Long id) throws BadRequestException {
        Event result = this.service.remove(id);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}