package com.mariusniemet.bookingservice.entities;

import jakarta.persistence.*;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "event_id")
    private int eventId;


    @Column(name = "ticket_type")
    private String ticketType;

    @Column
    private int price;

    @Column(name = "quantity_available")
    private int quantityAvailable;

    @Column(name = "quantity_sold", columnDefinition = "integer default 0")
    private int quantitySold;


    public Ticket(int eventId, String ticketType, int price, int quantityAvailable) {
        this.eventId = eventId;
        this.ticketType = ticketType;
        this.price = price;
        this.quantityAvailable = quantityAvailable;
    }

    public Ticket() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(int quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
    }
}
