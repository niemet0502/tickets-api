package com.mariusniemet.eventsservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Event {
    private @Id
    @GeneratedValue Long id;

    private String name;

    private String address;

    private Date date;

    private int quantityTotal;

    private int quantityBooked;

    private int price;

    public Event() {}

    Event(String name, String address, Date date, int quantityTotal, int quantityBooked, int price){
        this.address = address;
        this.name = name;
        this.date = date;
        this.quantityBooked = quantityBooked;
        this.quantityTotal = quantityTotal;
        this.price = price;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setQuantityTotal(int quantityTotal) {
        this.quantityTotal = quantityTotal;
    }

    public void setQuantityBooked(int quantityBooked) {
        this.quantityBooked = quantityBooked;
    }

    public void setPrice(int price) {
        this.price = price;
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

    public int getQuantityTotal() {
        return quantityTotal;
    }

    public int getQuantityBooked() {
        return quantityBooked;
    }

    public int getPrice() {
        return price;
    }
}
