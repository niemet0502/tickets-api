package com.mariusniemet.bookingservice.repositories;

import com.mariusniemet.bookingservice.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ITicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByEventId(int eventId);
}
