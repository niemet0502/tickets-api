package com.mariusniemet.bookingservice.services;

import com.mariusniemet.bookingservice.dtos.CreateEventDto;
import com.mariusniemet.bookingservice.entities.Ticket;
import com.mariusniemet.bookingservice.repositories.ITicketRepository;
import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class TicketsService {

    private final ITicketRepository repository;

    private static final Logger logger = LoggerFactory.getLogger(TicketsService.class);


    public TicketsService(ITicketRepository repository) {
        this.repository = repository;
    }

    public List<Ticket> findAll(int eventId){
        return this.repository.findByEventId(eventId);
    }

    public Ticket create(CreateEventDto createDto) throws BadRequestException{
        try{
            Ticket ticket = new Ticket(createDto.getEventId(), createDto.getTicketType(), createDto.getPrice(), createDto.getQuantityAvailable());

            return this.repository.save(ticket);
        }catch (Exception e){
            this.logger.error("Failed to create the ticket {}", e.getCause());
            throw new BadRequestException("Failed to create an event");
        }
    }
}
