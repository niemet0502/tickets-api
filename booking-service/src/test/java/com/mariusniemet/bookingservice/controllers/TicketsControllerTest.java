package com.mariusniemet.bookingservice.controllers;


import com.mariusniemet.bookingservice.dtos.CreateEventDto;
import com.mariusniemet.bookingservice.services.TicketsService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static io.restassured.RestAssured.*;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TicketsControllerTest {

    @LocalServerPort
    private Integer port;

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15-alpine")
                                            .withDatabaseName("testdb")
                                            .withUsername("testuser")
                                            .withPassword("testpass");

    @Autowired
    TicketsService service;

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @BeforeAll
    static void beforeAll(){
        postgres.start();
    }

    @AfterAll
    static void afterAll(){
        postgres.stop();
    }

    @BeforeEach
    void setup(){
        RestAssured.baseURI = "http://localhost:"+ port;
    }

    @Test
    void shouldReturnTicketsByEventId() throws BadRequestException {
        // arrange
        this.service.create(new CreateEventDto(1, "VIP", 1000, 30));
        this.service.create(new CreateEventDto(2, "CLASSIC", 1000, 30));

        // act
        Response response = given()
                .contentType(ContentType.JSON)
                .and()
                .queryParam("eventId",1)
                .when()
                .get("/api/v1/tickets")
                .then()
                .extract().response();

        // assert
        Assertions.assertEquals(200, response.getStatusCode());
        Assertions.assertEquals(1, response.jsonPath().getList("$").size());
        Assertions.assertEquals(1, response.jsonPath().getInt("[0].eventId"));
    }
}
