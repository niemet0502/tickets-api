package com.mariusniemet.eventsservice.services;


import com.mariusniemet.eventsservice.dtos.EventCreateDto;
import com.mariusniemet.eventsservice.entities.Event;
import com.mariusniemet.eventsservice.repositories.IEventRepository;
import com.mariusniemet.eventsservice.shared.TicketType;
import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class EventsServiceTest {
    @Mock
    private IEventRepository repository;

    private EventsService service;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        service = new EventsService(repository);
    }

    @Test
    public void testFindAll(){
        // arrange
        List<Event> expectedResult = List.of(new Event(), new Event());
        when(repository.findAll()).thenReturn(expectedResult);

        // act
        List<Event> result = service.findAll();

        // assert
        assertEquals(result.size(), expectedResult.size());
    }

    @Test
    public void testCreateShouldSucceed() throws BadRequestException {
        // arrange
        TicketType[] ticketTypes = null;
        EventCreateDto createDto = new EventCreateDto("Imagine Dragons", "Dakar", new Date(), ticketTypes);
        Event expectedResult = new Event("Imagine Dragons", "Dakar", new Date());
        when(repository.save(any(Event.class))).thenReturn(expectedResult);

        // act
        Event result = service.create(createDto);

        // assert
        assertEquals(expectedResult.getName(), result.getName());
    }

    @Test
    public void testFindOneShouldSucceed() throws BadRequestException {
        // arrange
        Long id = 1L;
        Event expectedResult = new Event("Imagine Dragons", "Dakar", new Date());
        when(repository.findById(id)).thenReturn(Optional.of(expectedResult));

        // act
        Event result = service.findOne(id);

        // assert
        assertEquals(result.getName(), expectedResult.getName());
    }

    @Test
    public void testFindOneShouldThrownException(){
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(BadRequestException.class, () -> service.findOne(id));
    }

    @Test
    public void testRemoveShouldSucceed() throws BadRequestException {
        // arrange
        Long id = 1L;
        Event expectedResult = new Event("Imagine Dragons", "Dakar", new Date());

        when(repository.findById(id)).thenReturn(Optional.of(expectedResult));

        // act
        Event result = service.remove(id);

        // assert
        assertEquals(result.getName(), expectedResult.getName());

    }

    @Test
    public void testRemoveShouldThrownException() {
        // arrange
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.empty());

        // act and assert
        assertThrows(BadRequestException.class, () -> service.remove(id));
    }

    @Test
    public void testUpdateShouldSucceed() throws BadRequestException{
        // arrange
        Long id = 1L;
        TicketType[] types = null;
        EventCreateDto updateDto = new EventCreateDto("Imagine Dragons", "Dakar", new Date(), types);
        Event expectedResult = new Event("Imagine Dragons", "Dakar", new Date());

        when(repository.findById(id)).thenReturn(Optional.of(expectedResult));
        when(repository.save(any(Event.class))).thenReturn(expectedResult);

        // act
        Event result = service.update(id, updateDto);

        // assert
        assertEquals(result.getName(), expectedResult.getName());
    }

    @Test
    public void testUpdateShouldThrownException(){
        // arrange
        Long id = 1L;
        TicketType[] types = null;
        EventCreateDto updateDto = new EventCreateDto("Imagine Dragons", "Dakar", new Date(), types);

        when(repository.findById(id)).thenReturn(Optional.empty());

        // act
        assertThrows(BadRequestException.class, () -> service.update(id, updateDto));
    }
}
