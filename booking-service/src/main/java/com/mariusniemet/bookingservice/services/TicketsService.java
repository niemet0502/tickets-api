package com.mariusniemet.bookingservice.services;

import com.mariusniemet.bookingservice.entities.Ticket;
import com.mariusniemet.bookingservice.repositories.ITicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketsService {

    private final ITicketRepository repository;

    public TicketsService(ITicketRepository repository) {
        this.repository = repository;
    }

    public List<Ticket> findAll(int eventId){
        return this.repository.findByEventId(eventId);
    }
}
