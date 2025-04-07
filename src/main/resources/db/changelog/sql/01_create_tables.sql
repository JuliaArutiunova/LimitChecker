--liquibase formatted sql

--changeset Arutsiunova:1

CREATE TABLE app.limit
(
    limit_id uuid NOT NULL,
    account character varying(10) NOT NULL,
    expense_category character varying(50) NOT NULL,
    currency_shortname character varying(3) NOT NULL,
    sum numeric(15, 2) NOT NULL DEFAULT 1000.00,
    datetime timestamp with time zone NOT NULL,
    current_spent numeric(15, 2) NOT NULL DEFAULT 0.00,
    PRIMARY KEY (limit_id)
);

ALTER TABLE IF EXISTS app.limit
    OWNER to postgres;


--changeset Arutsiunova:2

CREATE SEQUENCE app.transaction_sequence AS bigint
INCREMENT 1;

CREATE TABLE app.transaction
(
    transaction_id bigint NOT NULL DEFAULT nextval('app.transaction_sequence'),
    account_from character varying(10) NOT NULL,
    account_to character varying(10) NOT NULL,
    currency_shortname character varying(3) NOT NULL,
    sum numeric(15, 2) NOT NULL,
    expense_category character varying(50) NOT NULL,
    datetime timestamp with time zone NOT NULL,
    limit_id uuid NOT NULL,
    limit_exceeded boolean NOT NULL,
    PRIMARY KEY (transaction_id),
    CONSTRAINT "limit_FK" FOREIGN KEY (limit_id)
        REFERENCES app.limit (limit_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

ALTER TABLE IF EXISTS app.transaction
    OWNER to postgres;

ALTER SEQUENCE IF EXISTS app.transaction_sequence OWNED BY app.transaction.transaction_id;
