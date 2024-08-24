package com.mariusniemet.transactions_service.controllers;

import com.mariusniemet.transactions_service.dtos.TransactionCreateDto;
import com.mariusniemet.transactions_service.entities.Transaction;
import com.mariusniemet.transactions_service.services.TransactionsService;
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

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.hasSize;

import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransactionsControllerIT {
    @LocalServerPort
    private Integer port;

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15-alpine")
            .withDatabaseName("testdb")
            .withUsername("testuser")
            .withPassword("testpass");

//    @Container
//    static KafkaContainer broker = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:6.2.1"));

    @BeforeAll
    static void beforeAll() {
        postgres.start();
//        broker.start();
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
//        broker.stop();
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Autowired
    TransactionsService service;

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost:" + port;
        this.service.removeAll();
    }

    @Test
    void shouldGetAllTransactions() throws BadRequestException {
        // arrange

        TransactionCreateDto toCreate = new TransactionCreateDto(1,20, 1, 1);
        service.create(toCreate);

        // act and assert
        given()
            .contentType(ContentType.JSON)
            .when()
            .get("/api/v1/transactions")
            .then()
            .statusCode(200)
                .body(".", hasSize(1));
    }

    @Test
    void shouldCreateAnTransaction(){
        // arrange
        String requestBody = """
                {
                  "quantity": "1",
                  "ticketId": "2",
                  "eventId": "1",
                  "userId": "1"\s
                }""";

        // act
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(requestBody)
                .when()
                .post("/api/v1/transactions")
                .then()
                .extract().response();

        // assert
        Assertions.assertEquals(201, response.statusCode());
        Assertions.assertEquals(2, response.jsonPath().getInt("ticketId") );
    }

    @Test
    void shouldReturnATransactionById() throws BadRequestException {
        // arrange
        TransactionCreateDto toCreate = new TransactionCreateDto(1,20, 1, 1);
        service.create(toCreate);
        // act
        Response response = given().contentType(ContentType.JSON)
                .when()
                .get("/api/v1/transactions/1")
                .then()
                .extract()
                .response();

        // assert
        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals(1, response.jsonPath().getInt("id"));
    }

    @Test
    void shouldThrowExceptionForTransactionById() {
        // act
        Response response = given().contentType(ContentType.JSON)
                .when()
                .get("/api/v1/transactions/1")
                .then()
                .extract()
                .response();

        // assert
        Assertions.assertEquals(500, response.statusCode());
    }

    @Test
    void shouldRemoveATransaction() throws BadRequestException {
        // arrange
        TransactionCreateDto toCreate = new TransactionCreateDto(1,20, 1, 1);
        Transaction transaction = service.create(toCreate);

        // act
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .delete("/api/v1/transactions/" + transaction.getId())
                .then()
                .extract()
                .response();

        Assertions.assertEquals(200, response.getStatusCode());

    }

}
