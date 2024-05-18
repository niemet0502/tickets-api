package com.mariusniemet.bookingservice.services;

import com.mariusniemet.bookingservice.dtos.TransactionCreateDto;
import com.mariusniemet.bookingservice.entities.Transaction;
import com.mariusniemet.bookingservice.repositories.ITransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TransactionsService {
    private static final Logger logger = LoggerFactory.getLogger(TransactionsService.class);
    ITransaction iTransaction;

    public  TransactionsService(ITransaction iTransaction){
        this.iTransaction = iTransaction;
    }

    // create => update solded tickets number in the Ticket table

    public Transaction create(TransactionCreateDto createDto){

        Transaction toCreate = new Transaction(createDto.getUserId(), createDto.getQuantity(), createDto.getTicketId());
        Transaction result = null;

        toCreate.setTransactionDate(new Date());
        toCreate.setTotalAmount(createDto.getQuantity() * createDto.getTicketPrice());

        try {
            result = this.iTransaction.save(toCreate);;
            logger.info("Transaction successfully created");
        }catch (Exception e){
            logger.error("Failed to create the transaction", e);
        }

        return result;
    }

    // update

    // findAll => by user or by events

    // delete
}
