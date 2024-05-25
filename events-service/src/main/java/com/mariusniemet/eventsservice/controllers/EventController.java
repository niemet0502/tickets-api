package com.mariusniemet.eventsservice.controllers;

import com.mariusniemet.eventsservice.dtos.EventCreateDto;
import com.mariusniemet.eventsservice.entities.Event;
import com.mariusniemet.eventsservice.repositories.IEventRepository;
import com.mariusniemet.eventsservice.services.EventsService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
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

//    @GetMapping("/{id}")
//    public Event findOne(@PathVariable Long id){
//        return repository.findById(id).orElseThrow(() -> new RuntimeException("Could not found the event"));
//    }

//    @PutMapping("/{id}")
//    public  Event update(@RequestBody Event toUpdate, @PathVariable Long id){
//        return repository.findById(id)
//                .map(event -> {
//                    event.setName(toUpdate.getName());
//                    return repository.save(event);
//                })
//                .orElseGet(() -> {
//                    toUpdate.setId(id);
//                    return repository.save(toUpdate);
//                });
//    }
}