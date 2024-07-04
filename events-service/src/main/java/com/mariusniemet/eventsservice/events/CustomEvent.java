package com.mariusniemet.eventsservice.events;

public class CustomEvent {
    private String name;

    public CustomEvent(){

    }

    public CustomEvent(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}