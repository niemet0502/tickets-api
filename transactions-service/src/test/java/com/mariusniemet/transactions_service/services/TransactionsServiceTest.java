package com.mariusniemet.transactions_service.services;


import com.mariusniemet.transactions_service.dtos.TransactionCreateDto;
import com.mariusniemet.transactions_service.entities.Transaction;
import com.mariusniemet.transactions_service.repositories.ITransaction;
import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class TransactionsServiceTest {

    @Mock
    private ITransaction repositoty;

    private TransactionsService service;
    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        service = new TransactionsService(repositoty);
    }

    @Test
    public void testCreateShouldSucceed() throws BadRequestException {
        // arrange
        TransactionCreateDto createDto = new TransactionCreateDto(10,1, 1, 1);
        Transaction expectedResult = new Transaction(1, 10, 1, 1);
        when(repositoty.save(any(Transaction.class))).thenReturn(expectedResult);

        // act
        Transaction result = service.create(createDto);

        // assert
        assertEquals(result.getEventId(), expectedResult.getEventId());
    }

    @Test
    public void testFindByIdShouldSucceed() {
        // arrange
        Long id = 1L;
        Transaction expectedResult = new Transaction(1, 10, 1, 1);
        when(repositoty.findById(id)).thenReturn(Optional.of(expectedResult));

        // act
        Transaction result = service.findById(id);

        // arrange
        assertEquals(result.getEventId(), expectedResult.getEventId());
    }
}
