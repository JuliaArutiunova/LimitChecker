--liquibase formatted sql

--changeset Arutsiunova:1

INSERT INTO app.exchange_rate(
	exchange_rate_id, currency_base, currency_quote, rate, exchange_date, dt_create, dt_update)
VALUES
	('eff9cd88-eee4-4c97-b8f7-f382401428b1', 'KZT', 'USD', 0.001937205, '2025-04-10', '2025-04-10T16:57:09.257828500+03:00', '2025-04-10T16:57:09.257828500+03:00'),
	('f642b77c-5190-44ff-89fa-d0aac772d894', 'RUB', 'USD', 0.011870250, '2025-04-10', '2025-04-10T16:57:09.257828500+03:00', '2025-04-10T16:57:09.257828500+03:00')