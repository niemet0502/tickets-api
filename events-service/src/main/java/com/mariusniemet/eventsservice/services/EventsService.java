package com.mariusniemet.eventsservice.services;

import com.mariusniemet.eventsservice.dtos.EventCreateDto;
import com.mariusniemet.eventsservice.entities.Event;
import com.mariusniemet.eventsservice.repositories.IEventRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

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

//    public Event findById(Long id){
//        Event result = null;
//        result = repository.;
//        try {
//        }catch (Exception e){
////            .orElseThrow(() -> new RuntimeException("Could not found the event"))
//        }
//        return ;
//
//    }
}
