DROP TABLE IF EXISTS payment_methods;
CREATE TABLE payment_methods
(
    id           int          NOT NULL AUTO_INCREMENT,
    name         varchar(255) NOT NULL,
    display_name varchar(255) NOT NULL,
    payment_type varchar(255) NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO payment_methods
VALUES (1, 'credit card', 'credit card', 'CREDIT CARD');

DROP TABLE IF EXISTS payment_plans;
CREATE TABLE payment_plans
(
    id                int                        NOT NULL AUTO_INCREMENT,
    payment_method_id int                        NOT NULL,
    net_amount        decimal(8, 2) DEFAULT 0.00 NOT NULL,
    tax_amount        int           DEFAULT 0    NOT NULL,
    gross_amount      decimal(8, 2) DEFAULT 0.00 NOT NULL,
    currency          varchar(10)                NOT NULL,
    duration          varchar(10)                NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_payment_method
        FOREIGN KEY (payment_method_id)
            REFERENCES payment_methods (id)
            ON UPDATE CASCADE
            ON DELETE CASCADE
);

INSERT INTO payment_plans
VALUES (1, 1, 5.99, 0, 5.99, 'USD', 'Month');