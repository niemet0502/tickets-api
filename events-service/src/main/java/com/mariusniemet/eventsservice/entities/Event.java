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


    public Event() {}

    public Event(String name, String address, Date date){
        this.address = address;
        this.name = name;
        this.date = date;
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
