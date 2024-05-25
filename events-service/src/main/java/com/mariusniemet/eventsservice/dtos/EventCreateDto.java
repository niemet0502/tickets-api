package com.mariusniemet.eventsservice.dtos;

import com.mariusniemet.eventsservice.shared.TicketType;

import java.util.Date;

public class EventCreateDto {

    private String name;
    private String address;

    private Date date;

    private TicketType[] ticketTypes;

    public EventCreateDto(String name, String address, Date date, TicketType[] ticketTypes) {
        this.name = name;
        this.address = address;
        this.date = date;
        this.ticketTypes = ticketTypes;
    }

    public String getName() {
        return name;
    }


    public String getAddress() {
        return address;
    }


    public Date getDate() {
        return date;
    }

}
