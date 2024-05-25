package com.mariusniemet.bookingservice.controllers;


import com.mariusniemet.bookingservice.dtos.TransactionCreateDto;
import com.mariusniemet.bookingservice.entities.Transaction;
import com.mariusniemet.bookingservice.services.TransactionsService;
import jakarta.ws.rs.NotFoundException;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/transactions")
public class TransactionsController {

    TransactionsService service;

    public TransactionsController(TransactionsService service){
        this.service = service;
    }


    @PostMapping
    public ResponseEntity<Transaction> create(@RequestBody TransactionCreateDto createDto) throws BadRequestException {
        Transaction result = this.service.create(createDto);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> findAll(@RequestParam(required = false) Integer userId, @RequestParam(required = false) Integer evenId) {
        List<Transaction> result = this.service.findAll(userId, evenId);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> findOne(@PathVariable Long id){
        Transaction result = this.service.findById(id);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Transaction> remove(@PathVariable Long id) throws NotFoundException {
        Transaction tr = this.service.remove(id);

        return new ResponseEntity<>(tr, HttpStatus.OK);
    }

}
