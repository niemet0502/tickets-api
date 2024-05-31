package com.mariusniemet.eventsservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Event {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

    private String name;

    private String address;

    private Date date;


    public Event() {}

    public Event(String name, String address, Date date){
        this.address = address;
        this.name = name;
        this.date = date;
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
