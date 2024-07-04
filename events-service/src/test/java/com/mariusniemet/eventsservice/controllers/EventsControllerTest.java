package com.mariusniemet.eventsservice.controllers;

import com.mariusniemet.eventsservice.dtos.EventCreateDto;
import com.mariusniemet.eventsservice.entities.Event;
import com.mariusniemet.eventsservice.services.EventsService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Date;

import static io.restassured.RestAssured.given;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EventsControllerTest {

    @LocalServerPort
    private Integer port;

    @Container
    private static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15-alpine")
            .withDatabaseName("testdb")
            .withUsername("testuser")
            .withPassword("testpass");

    @Autowired
    private EventsService service;

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
        RestAssured.baseURI = "http://localhost:" + port;
        this.service.removeAll();
    }
    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }


    @Test
    void ShouldCreateAnEvent(){
        // arrange
        String requestBody = "{\n" +
                "  \"name\": \"Imagine dragons show\",\n" +
                "  \"address\": \"Paris\",\n" +
                "  \"date\": \"2024-05-10\",\n}";
        // act
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(requestBody)
                .when()
                .post("/api/v1/events")
                .then()
                .extract().response();

        System.out.println(response.jsonPath().getString("name"));

        // assert
        Assertions.assertEquals(201, response.getStatusCode());
        Assertions.assertEquals("Imagine dragons show", response.jsonPath().getString("name"));
        Assertions.assertNotNull(response.jsonPath().getInt("id"));
    }

    @Test
    void shouldGetEventsList() throws BadRequestException {
        //arrange
        EventCreateDto createDto = new EventCreateDto("Image dragons show", "Paris", new Date(), null);
        this.service.create(createDto);

        // act
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/v1/events")
                .then()
                .extract()
                .response();

        // assert
        Assertions.assertEquals(200, response.getStatusCode());
        Assertions.assertEquals(createDto.getName(), response.jsonPath().getString("[0].name"));
    }

    @Test
    void shouldRemoveEvent() throws BadRequestException {
        // arrange
        EventCreateDto createDto = new EventCreateDto("Image dragons show", "Paris", new Date(), null);
        Event event = this.service.create(createDto);

        // act
        Response response = given()
                .contentType(ContentType.JSON)
                .delete("/api/v1/events/"+ event.getId())
                .then()
                .extract()
                .response();

        // assert
        Assertions.assertEquals(200, response.getStatusCode());
    }

    @Test
    void shouldThrownExceptionRemoveEvent() {
        // act
        Response response = given()
                .contentType(ContentType.JSON)
                .delete("/api/v1/events/1")
                .then()
                .extract()
                .response();

        // assert
        Assertions.assertEquals(400, response.getStatusCode());
    }


    @Test
    void shouldReturnEvent() throws BadRequestException {
        // arrange
        EventCreateDto createDto = new EventCreateDto("Image dragons show", "Paris", new Date(), null);
        Event event = this.service.create(createDto);

        // act
        Response response = given()
                .contentType(ContentType.JSON)
                .get("/api/v1/events/"+ event.getId())
                .then()
                .extract()
                .response();

        // assert
        Assertions.assertEquals(200, response.getStatusCode());
        Assertions.assertEquals(event.getName(), response.jsonPath().getString("name"));
    }

    @Test
    void shouldThrowAnException(){
        given()
                .contentType(ContentType.JSON)
                .get("/api/v1/events/30")
                .then()
                .statusCode(400);

    }

//    @Test
//    void shouldUpdateAnEvent() throws BadRequestException {
//        // arrange
//        EventCreateDto createDto = new EventCreateDto("Image dragons show", "Paris", new Date(), null);
//        Event event = this.service.create(createDto);
//
//        System.out.println(event.getId());
//
//        String requestBody = "{\n" +
//                "  \"name\": \"Image dragons show\",\n" +
//                "  \"address\": \"Paris France\",\n}";
//
//        // act
//        Response response = given()
//                .header("Content-type", "application/json")
//                .and()
//                .body(requestBody)
//                .when()
//                .put("/api/v1/events/1")
//                .then()
//                .extract().response();
//
//        System.out.println(response.getHeaders());
//
//        // assert
//        Assertions.assertEquals(200, response.getStatusCode());
//        Assertions.assertEquals("Paris France", response.jsonPath().getString("address"));
//    }

}
