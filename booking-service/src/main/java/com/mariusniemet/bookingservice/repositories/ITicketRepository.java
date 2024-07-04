package com.mariusniemet.bookingservice.repositories;

import com.mariusniemet.bookingservice.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByEventId(int eventId);
}
