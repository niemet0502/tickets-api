package com.mariusniemet.eventsservice.controllers;

import com.mariusniemet.eventsservice.entities.Event;
import com.mariusniemet.eventsservice.repositories.IEventRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {
    private final IEventRepository repository;

    EventController(IEventRepository repository){
        this.repository = repository;
    }

    @GetMapping
    public List<Event> findAll(){
        return repository.findAll();
    }

    @PostMapping
    public Event create(@RequestBody Event newEvent){
        return repository.save(newEvent);
    }

    @GetMapping("/{id}")
    public Event findOne(@PathVariable Long id){
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Could not found event"));
    }

    @PutMapping("/{id}")
    public  Event update(@RequestBody Event toUpdate, @PathVariable Long id){
        return repository.findById(id)
                .map(event -> {
                    event.setName(toUpdate.getName());
                    event.setQuantityBooked(toUpdate.getQuantityBooked());
                    return repository.save(event);
                })
                .orElseGet(() -> {
                    toUpdate.setId(id);
                    return repository.save(toUpdate);
                });
    }
}