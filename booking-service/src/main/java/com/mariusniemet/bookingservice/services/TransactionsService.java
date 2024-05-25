package com.mariusniemet.bookingservice.services;

import com.mariusniemet.bookingservice.dtos.TransactionCreateDto;
import com.mariusniemet.bookingservice.entities.Transaction;
import com.mariusniemet.bookingservice.repositories.ITransaction;
import jakarta.ws.rs.NotFoundException;
import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionsService {
    private static final Logger logger = LoggerFactory.getLogger(TransactionsService.class);
    ITransaction iTransaction;

    public  TransactionsService(ITransaction iTransaction){
        this.iTransaction = iTransaction;
    }

    // create => update solded tickets number in the Ticket table

    public Transaction create(TransactionCreateDto createDto) throws BadRequestException {
        logger.info("Creating a transaction ...");
        Transaction toCreate = new Transaction(createDto.getUserId(), createDto.getQuantity(), createDto.getTicketId(), createDto.getEventId());

        toCreate.setTransactionDate(new Date());
        toCreate.setTotalAmount(createDto.getQuantity() * createDto.getTicketPrice());

        try {
            Transaction result = this.iTransaction.save(toCreate);;
            logger.info("Transaction successfully created {}", result);
            return  result;
        }catch (Exception e){
            logger.error("Failed to create the transaction", e);
            throw new BadRequestException("Failed to create the transaction");
        }
    }

    public Transaction findById(Long id) throws NotFoundException{
        logger.info("Finding the transaction...");
        Optional<Transaction> transaction = this.iTransaction.findById(id);

        if(transaction.isEmpty()){
            logger.error("Failed to fetch the Transaction {}", id);
            throw new NotFoundException("Failed to fetch the transaction");
        }
        logger.info("Transaction found");
        return transaction.get();
    }


    // findAll => by user or by events
    public List<Transaction> findAll(Integer userId, Integer eventId){
        List<Transaction> result = null;

        try{
            result = this.iTransaction.findAllByUserIdAndEventId(userId, eventId);
            logger.info("Transactions successfully fetched");
        }catch (Exception e){
            logger.error("Failed to fetch transactions", e);
        }

        return result;
    }

    public Transaction remove(Long id) throws NotFoundException {
        logger.info("Removing a transaction {}", id);
        Optional<Transaction> toRemove = this.iTransaction.findById(id);

        if(toRemove.isEmpty()){
            logger.error("Failed to fetch the transaction {}", id);
            throw  new NotFoundException("Failed to fetch the transaction");
        }

        this.iTransaction.delete(toRemove.get());
        logger.info("Transaction removed");
        return  toRemove.get();
    }
}
