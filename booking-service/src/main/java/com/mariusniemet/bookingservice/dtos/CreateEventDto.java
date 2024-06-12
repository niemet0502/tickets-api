package com.mariusniemet.bookingservice.dtos;

public class CreateEventDto {
    private int eventId;
    private String ticketType;

    private int price;

    private int quantityAvailable;

    public CreateEventDto(int eventId, String ticketType, int price, int quantityAvailable){
        this.eventId = eventId;
        this.ticketType = ticketType;
        this.price = price;
        this.quantityAvailable = quantityAvailable;
    }

    public int getEventId() {
        return eventId;
    }

    public String getTicketType() {
        return ticketType;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantityAvailable() {
        return quantityAvailable;
    }
}
