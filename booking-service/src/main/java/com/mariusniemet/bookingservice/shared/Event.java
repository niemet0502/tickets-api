package com.mariusniemet.bookingservice.shared;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Date;

public class Event {
    private Long id;

    private String name;

    private String address;

    private Date date;

    private int quantityTotal;

    private int quantityBooked;

    private int price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getQuantityTotal() {
        return quantityTotal;
    }

    public void setQuantityTotal(int quantityTotal) {
        this.quantityTotal = quantityTotal;
    }

    public int getQuantityBooked() {
        return quantityBooked;
    }

    public void setQuantityBooked(int quantityBooked) {
        this.quantityBooked = quantityBooked;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
