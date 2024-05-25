package com.mariusniemet.eventsservice.services;

import com.mariusniemet.eventsservice.dtos.EventCreateDto;
import com.mariusniemet.eventsservice.entities.Event;
import com.mariusniemet.eventsservice.repositories.IEventRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class EventsService {
    private final IEventRepository repository;

    public EventsService(IEventRepository repository) {
        this.repository = repository;
    }

    public Event create(EventCreateDto createDto) throws BadRequestException {
        Event toCreate = new Event(createDto.getName(), createDto.getAddress(), createDto.getDate());

        try{
            Event result = this.repository.save(toCreate);

            // publish the event eventCreated with the ticket data

            return result;
        }catch (Exception e){
            throw new BadRequestException("Failed to create the event", e);
        }

    }

    public List<Event> findAll(){
            return this.repository.findAll();
    }

    public Event update(Long id, EventCreateDto updateDto) throws BadRequestException {

        // check if the event exists
        Optional<Event> toUpdate = this.repository.findById(id);

        if(toUpdate.isEmpty()){
            throw new BadRequestException("Event doesn't exist");
        }
        // update
        Event event = toUpdate.get();

        event.setName(updateDto.getName());
        event.setAddress(updateDto.getAddress());
        event.setDate(updateDto.getDate());

        return this.repository.save(event);
    }

    public Event remove(Long id) throws BadRequestException {

        // check if the event exists
        Optional<Event> toDelete = this.repository.findById(id);

        if(toDelete.isEmpty()){
            throw new BadRequestException("Event doesn't exist");
        }

        try{

            this.repository.delete(toDelete.get());

            // send the event EventCanceled

            return toDelete.get();
        }catch (Exception e){
            throw new
                    BadRequestException("Failed to remove the events", e);
        }
    }
    public Event findOne(@PathVariable Long id) throws BadRequestException {
        Optional<Event> result = this.repository.findById(id);

        if(result.isEmpty()){
            throw new BadRequestException("Event doesn't exist");
        }

        return result.get();
    }
}
