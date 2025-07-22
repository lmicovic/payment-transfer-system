# Payment Transfer System

## Overview
A simple and secure [Spring Boot](https://spring.io/projects/spring-boot "Spring Boot") based payment transfer service that allows users to transfer funds between accounts within a digital banking platform. This service includes robust error handling and transaction logging to ensure data integrity and traceability.

## Key Capabilities
- **Secure Transfers:** Enables users to move funds from one internal account to another.

- **Validation:** Ensures the source account has sufficient funds and prevents illogical transactions (e.g., same-source and destination).

- **Transaction Recording:** All transfers are logged for compliance, reporting, and audit purposes.

- **Error Handling:** Graceful and consistent error messages.


## Technology used
- Backend Framework: [Spring Boot (v 3.5.3)](https://spring.io/blog/2025/06/19/spring-boot-3-5-3-available-now "Spring Boot (v 3.5.3)")
- Language: [Java 17+](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html "Java 17+")
- Data Layer: [Spring Data JPA](https://spring.io/projects/spring-data-jpa "Spring Data JPA")
- Database: [H2 In-Memory  Database](https://www.h2database.com/html/main.html "H2 In-Memory  Database")
- Build Tool: [Maven](https://maven.apache.org/ "Maven")

## Importand Endpoints

- ### Transfer funds:
**Endpoint:** POST /transfer
Sample Request:
```json
{
  "sourceAccountId": 1,
  "destinationAccountId": 2,
  "amount": 20
}
```
Typical Responses:
- ```200 OK:``` Transaction successful
- ```404 Not Found:``` Account does not exist
- ```400 Bad Request:``` Invalid input (e.g. negative amount)
- ```406 Not Acceptable:``` Insufficient funds


## Project Structure
```bash
src/
├── configuration/          # Global configs, interceptors, and filters
├── controller/             # REST controllers
├── service/                # Business logic
├── repository/             # Spring Data repositories
├── model/                  # Entity definitions
└── exceptions/             # Custom exception classes
```

## How to run
Run the Application:
```bash
git clone https://github.com/your-username/payment-transfer-system.git
cd payment-transfer-system
mvn spring-boot:run
```
Application will start at URL: http://localhost:8080

