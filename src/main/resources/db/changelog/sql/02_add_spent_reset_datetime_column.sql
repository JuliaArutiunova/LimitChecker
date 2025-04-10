--liquibase formatted sql

--changeset Arutsiunova:1

ALTER TABLE IF EXISTS app.limit
    ADD COLUMN spent_reset_datetime timestamp with time zone NOT NULL DEFAULT NOW();