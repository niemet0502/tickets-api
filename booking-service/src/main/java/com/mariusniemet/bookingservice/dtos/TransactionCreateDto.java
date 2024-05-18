package com.mariusniemet.bookingservice.dtos;

public class TransactionCreateDto {
    private int quantity;
    private  int ticketId;
    private int userId;
    private int ticketPrice;

    public int getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(int ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public TransactionCreateDto(int quantity, int ticketId, int userId) {
        this.quantity = quantity;
        this.ticketId = ticketId;
        this.userId = userId;
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
}
