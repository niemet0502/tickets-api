package com.mariusniemet.bookingservice.controllers;


import com.mariusniemet.bookingservice.dtos.TransactionCreateDto;
import com.mariusniemet.bookingservice.entities.Transaction;
import com.mariusniemet.bookingservice.services.TransactionsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/transactions")
public class TransactionsController {

    TransactionsService service;

    public TransactionsController(TransactionsService service){
        this.service = service;
    }


    @PostMapping
    public ResponseEntity<Transaction> create(@RequestBody TransactionCreateDto createDto){
        Transaction result = this.service.create(createDto);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

}
