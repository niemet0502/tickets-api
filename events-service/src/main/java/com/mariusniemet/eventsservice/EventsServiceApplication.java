package com.mariusniemet.eventsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class EventsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventsServiceApplication.class, args);
	}

}
