CREATE TABLE IF NOT EXISTS "transaction" (
                                             id BIGSERIAL NOT NULL,
                                             user_id INT,
                                             ticket_id INT,
                                             event_id INT,
                                             transaction_date DATE,
                                             quantity INT,
                                             total_amount INT, -- Changed from totalAmount to total_amount for consistency
                                             PRIMARY KEY (id)
    );
