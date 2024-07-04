package com.mariusniemet.bookingservice.services;

import com.mariusniemet.bookingservice.entities.Ticket;
import com.mariusniemet.bookingservice.repositories.ITicketRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TicketsServiceTest {

    @Mock
    private ITicketRepository repository;

    private TicketsService service;

    @BeforeEach
    public  void setup(){
        MockitoAnnotations.openMocks(this);

        service = new TicketsService(repository);
    }

//    @Test
//    public void testFindAll(){
//        // arrange
//        int eventId = 1;
//        List<Ticket> expectedResult =  List.of(new Ticket(), new Ticket());
//        when(repository.findByEventId(eventId)).then(expectedResult);
//
//        // act
//        List<Ticket> result = service.findAll(eventId);
//
//        // assert
//        assertEquals(result.size(), expectedResult.size());
//    }
}
