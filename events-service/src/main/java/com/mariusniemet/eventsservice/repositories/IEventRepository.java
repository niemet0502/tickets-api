package com.mariusniemet.eventsservice.repositories;

import com.mariusniemet.eventsservice.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEventRepository extends JpaRepository<Event, Long> {
}
