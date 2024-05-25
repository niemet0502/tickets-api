package com.mariusniemet.bookingservice.repositories;

import com.mariusniemet.bookingservice.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ITransaction extends JpaRepository<Transaction, Long> {

    @Query("SELECT t FROM Transaction t WHERE " +
            "(:userId IS NULL OR t.userId = :userId) AND " +
            "(:eventId IS NULL OR t.eventId = :eventId)")
    List<Transaction> findAllByUserIdAndEventId(@Param("userId") Integer userId,
                                                @Param("eventId") Integer eventId);
}
