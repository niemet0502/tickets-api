package com.mariusniemet.eventsservice.services;

import com.mariusniemet.eventsservice.dtos.EventCreateDto;
import com.mariusniemet.eventsservice.entities.Event;
import com.mariusniemet.eventsservice.repositories.IEventRepository;
import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class EventsService {
    private final IEventRepository repository;
    private final Logger logger = LoggerFactory.getLogger(EventsService.class);

    public EventsService(IEventRepository repository) {
        this.repository = repository;
    }

    public Event create(EventCreateDto createDto) throws BadRequestException {
        this.logger.info("Creating an event... [{}]", createDto);

        Event toCreate = new Event(createDto.getName(), createDto.getAddress(), createDto.getDate());

        try{
            Event result = this.repository.save(toCreate);

            // send EVENT eventCreated with the ticket data
            this.logger.info("Event created {}", result);
            return result;
        }catch (Exception e){
            System.out.println(e.getCause());
            this.logger.error("Failed to create the event [{}]", e.getCause());
            throw new BadRequestException("Failed to create the event", e);
        }

    }

    public List<Event> findAll(){
        this.logger.info("Fetching all the events");
        return this.repository.findAll();
    }

    public Event update(Long id, EventCreateDto updateDto) throws BadRequestException {
        logger.info("Updating the event {} with data {}", id, updateDto);

        // check if the event exists
        Optional<Event> toUpdate = this.repository.findById(id);

        if(toUpdate.isEmpty()){
            logger.error("Failed to fetch the event {}", id);
            throw new BadRequestException("Event doesn't exist");
        }
        // update
        Event event = toUpdate.get();

        event.setName(updateDto.getName());
        event.setAddress(updateDto.getAddress());
        event.setDate(updateDto.getDate());

        this.logger.info("Event updated");
        return this.repository.save(event);
    }

    public Event remove(Long id) throws BadRequestException {
        this.logger.info("Canceling an event... {}", id);
        // check if the event exists
        Optional<Event> toDelete = this.repository.findById(id);

        if(toDelete.isEmpty()){
            this.logger.error("Failed to fetch the event {}", id);
            throw new BadRequestException("Event doesn't exist");
        }

        try{

            this.repository.delete(toDelete.get());
            this.logger.info("Event canceled {}", id);
            // send EVENT EventCanceled

            return toDelete.get();
        }catch (Exception e){
            this.logger.error("Failed to cancel the event {}", id);
            throw new
                    BadRequestException("Failed to remove the events", e);
        }
    }
    public Event findOne(@PathVariable Long id) throws BadRequestException {
        this.logger.info("Fetching a single event {}", id);
        Optional<Event> result = this.repository.findById(id);

        if(result.isEmpty()){
            this.logger.error("Failed to fetch the event {}", id);
            throw new BadRequestException("Event doesn't exist");
        }

        return result.get();
    }

    public void removeAll(){
        this.repository.deleteAll();
    }
}
