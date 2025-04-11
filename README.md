
This microservice is designed to track and manage user spending, providing real-time information on financial transactions and enforcing spending limits. 
## Key Features

* **Real-time Transaction Tracking:** Receives and stores information about every expenditure in KZT, RUB, USD, and other currencies as they occur. This data is persisted in the application's database.
* **Categorized Monthly Spending Limits:** Maintains monthly spending limits specifically for expense categories, denominated in USD. A default limit of 1000 USD per category is applied if no custom limit is set.
* **Automated Exchange Rate Updates:** Regularly fetches daily exchange rates for KZT/USD and RUB/USD currency pairs. It prioritizes the closing rate ("close") for calculations. These rates are stored in the application's database.
* **Limit Exceeded Flag:** Automatically flags transactions that cause the total monthly spending within their respective category to exceed the defined USD limit. A technical flag (`limit_exceeded`) is set for such transactions.
* **New Limit Creation:** Allows clients to set new monthly spending limits.
* **Exceeded Limit Transaction Retrieval:** Provides an endpoint for clients to request a list of transactions that have exceeded their spending limits. The response includes details of the transaction and the specific limit that was surpassed (date the limit was set, limit amount, and currency (USD)).

## Technologies Used

* **Java 21**
* **Maven**
* **Spring Boot**
* **Hibernate**
* **PostgreSQL**

## API Endpoints

The microservice exposes the following REST API endpoints:

**Transaction API (`/transaction`)**

* `POST /transaction`: Accepts and processes new transaction data.

**User API (`/user`)**

* `GET /user/{account}/limits`: Retrieves the spending limits for a specific user account. Optionally filters by `expenseCategory`.
* `POST /user/{account}/limits`: Sets a new spending limit for a specific user account and expense category.
* `GET /user/{account}/transactions/exceeded-limit`: Retrieves a list of transactions for a specific user account that have exceeded their spending limit. Optionally filters by `expenseCategory`.


