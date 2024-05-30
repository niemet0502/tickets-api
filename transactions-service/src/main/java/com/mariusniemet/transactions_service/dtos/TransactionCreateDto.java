package com.mariusniemet.transactions_service.dtos;

public class TransactionCreateDto {
    private int quantity;
    private  int ticketId;
    private int userId;
    private int ticketPrice;
    private int eventId;

    public int getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(int ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public TransactionCreateDto(int quantity, int ticketId, int userId, int eventId) {
        this.quantity = quantity;
        this.ticketId = ticketId;
        this.userId = userId;
        this.eventId = eventId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }
}
