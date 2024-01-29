package com.mariusniemet.bookingservice.repositories;

import com.mariusniemet.bookingservice.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IBooking extends JpaRepository<Booking, Long> {
    List<Booking> findByEventId(Long eventId);
}
