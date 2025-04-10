--liquibase formatted sql

--changeset Arutsiunova:1

CREATE TABLE app.exchange_rate
(
    exchange_rate_id uuid NOT NULL,
    currency_base character varying(3) NOT NULL,
    currency_quote character varying(3) NOT NULL,
    rate numeric NOT NULL,
    exchange_date date NOT NULL,
    dt_create timestamp with time zone NOT NULL,
    dt_update timestamp with time zone NOT NULL,
    PRIMARY KEY (exchange_rate_id)
);

ALTER TABLE IF EXISTS app.exchange_rate
    OWNER to postgres;