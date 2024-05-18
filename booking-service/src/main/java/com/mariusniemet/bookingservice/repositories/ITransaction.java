package com.mariusniemet.bookingservice.repositories;

import com.mariusniemet.bookingservice.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITransaction extends JpaRepository<Transaction, Long> {
}
