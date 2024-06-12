CREATE TABLE IF NOT EXISTS ticket (
                                       id BIGSERIAL NOT NULL,
                                       eventId int,
                                       ticketType VARCHAR(100),
                                       price int,
                                       quantity_available int,
                                       quantity_sold int,
                                       PRIMARY KEY (id)
    );
