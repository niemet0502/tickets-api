CREATE TABLE IF NOT EXISTS "event" (
                                             id BIGSERIAL NOT NULL,
                                             name VARCHAR,
                                             address VARCHAR,
                                             date DATE,
                                             PRIMARY KEY (id)
    );
