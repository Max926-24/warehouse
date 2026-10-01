# Warehouse Management API

A REST API for managing a small warehouse: items, categories, suppliers, storage locations, incoming deliveries and outgoing sales (write-offs), secured with JWT authentication and role-based access.

Built as a learning project to practise a full Spring Boot backend: layered architecture, database migrations, validation, error handling, unit testing and Spring Security.

## Tech stack

- **Java 21**
- **Spring Boot 3.3** (Web, Data JPA, Validation, Security)
- **PostgreSQL** (running in Docker)
- **Liquibase** for versioned database migrations
- **MapStruct** for entity ↔ DTO mapping
- **JWT** (jjwt 0.12) for stateless authentication, **BCrypt** for password hashing
- **JUnit 5 + Mockito** for unit tests
- **Maven**

## Features

- CRUD-style endpoints for items, categories, suppliers and storage locations
- **Deliveries** increase an item's stock; the price per unit is checked against the item's price
- **Sales** decrease stock; a sale larger than the available quantity is rejected
- Computed stock value per item and for the whole warehouse
- Soft delete and restore for items
- Request validation (`@Valid`) and a global exception handler with meaningful HTTP status codes
- User registration and login with JWT; two roles, `USER` and `ADMIN`

## Architecture

```
controller  →  service  →  repository  →  PostgreSQL
     ↑            ↑
    DTOs      MapStruct mappers
```

```
src/main/java/de/ait/warehouse
├── controller      REST controllers
├── domain          JPA entities (Item, Category, Supplier, Location, Delivery, Sale, User)
├── dto             request/response DTOs and MapStruct mappers
├── exceptions      custom exceptions + GlobalExceptionHandler
├── repository      Spring Data JPA repositories
├── security        SecurityConfig, JWT TokenService, TokenFilter, auth controller
└── service         business logic
src/main/resources
└── db/changelog    Liquibase migrations (schema + seed data)
```

## Getting started

### Prerequisites

- JDK 21
- Docker
- Maven (or the Maven integration in IntelliJ IDEA)

### 1. Start PostgreSQL

```bash
docker run -d --name warehouse-db \
  -e POSTGRES_PASSWORD=postgres \
  -e POSTGRES_DB=warehouse \
  -p 5432:5432 \
  postgres:16
```

### 2. Create the secret file

The JWT signing key is **not** stored in the repository. Create a file named `secret.properties` in the project root (it is listed in `.gitignore`):

```properties
JWT_SECRET=<your-base64-key>
```

Generate a key with:

```bash
openssl rand -base64 32
```

### 3. Run the application

```bash
mvn spring-boot:run
```

On startup Liquibase creates all tables and inserts seed data (3 categories, 10 suppliers, 6 storage locations, 30 items). The API runs on `http://localhost:8080`.

### 4. Create an admin

Every new user is registered with the role `USER`. To promote a user to admin, run in the database:

```sql
UPDATE account SET role = 'ROLE_ADMIN' WHERE email = 'you@example.com';
```

## Authentication

1. Register: `POST /auth/register`
   ```json
   { "email": "worker@warehouse.de", "password": "123456", "name": "Worker" }
   ```
2. Log in: `POST /auth/login`
   ```json
   { "email": "worker@warehouse.de", "password": "123456" }
   ```
   Response:
   ```json
   { "accessToken": "eyJhbGciOiJIUzI1NiJ9..." }
   ```
3. Send the token with every other request:
   ```
   Authorization: Bearer <accessToken>
   ```

Tokens are valid for 60 minutes (configurable via `jwt.expiration-minutes`).

## API overview

| Method | Endpoint | Description | Access |
|---|---|---|---|
| POST | `/auth/register` | Register a new user | public |
| POST | `/auth/login` | Log in, receive a JWT | public |
| GET | `/items` | List active items | USER, ADMIN |
| GET | `/items/{id}` | Get an item | USER, ADMIN |
| GET | `/items/by-category/{categoryId}` | Items of a category | USER, ADMIN |
| GET | `/items/total-value` | Total stock value of the warehouse | USER, ADMIN |
| POST | `/items` | Create an item | ADMIN |
| PUT | `/items/{id}` | Change an item's price | ADMIN |
| DELETE | `/items/{id}` | Soft-delete an item | ADMIN |
| PUT | `/items/{id}/restore` | Restore a deleted item | ADMIN |
| GET / POST | `/categories` | List / create categories | GET: USER, ADMIN · POST: ADMIN |
| GET / POST | `/suppliers` | List / create suppliers | GET: USER, ADMIN · POST: ADMIN |
| GET / POST | `/locations` | List / create storage locations | GET: USER, ADMIN · POST: ADMIN |
| GET / POST | `/deliveries` | List / register incoming deliveries | USER, ADMIN |
| GET / POST | `/sales` | List / register sales (write-offs) | USER, ADMIN |

### Example: register a delivery

`POST /deliveries`

```json
{
  "itemId": 1,
  "supplierId": 1,
  "quantity": 50,
  "pricePerUnit": 24.99,
  "deliveryDate": "2026-10-01T10:00:00"
}
```

### Response codes

| Code | Meaning |
|---|---|
| 400 | Validation failed, price mismatch, or not enough stock |
| 401 | Missing, invalid or expired token; wrong email or password |
| 403 | Authenticated, but the role is not allowed to do this |
| 404 | Entity not found |
| 409 | Email is already registered |

## Tests

Unit tests with JUnit 5 and Mockito cover the delivery and sale business logic (stock changes, price check, insufficient stock, entity not found):

```bash
mvn test
```

## Possible improvements

- Refresh tokens
- More unit tests (items, categories, suppliers, locations, authentication)
- Integration tests with Testcontainers
- API documentation with Swagger / OpenAPI
