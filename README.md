# Bank Management System

A REST API for a small banking system, built with Spring Boot and MySQL.

I built this to practice Java and OOP, and kept extending it: it started as a
console program and ended up as a REST API with a real database behind it.

## What it does

- Two account types: savings and current, each with different withdrawal rules
- Deposit and withdraw, with validation on every operation
- Every deposit and withdrawal is saved as a transaction, so each account has
  a full statement
- Current accounts can be frozen and unfrozen
- Savings accounts can earn interest

## Tech

- Java 21
- Spring Boot (Web, Data JPA, Validation)
- MySQL 8 (H2 for tests)
- Maven
- JUnit 5

## Design notes

`Account` is an abstract class. It holds everything the two account types
share: owner, balance, deposit, and the transaction list. `withdraw` is
abstract, because the rule is different for each type:

- `SavingsAccount` — balance can't go below zero
- `CurrentAccount` — balance can go down to `-overdraftLimit`

Making `withdraw` abstract means a new account type can't be added without
deciding its own withdrawal rule.

Both types are stored in one `accounts` table with a discriminator column, and
`transactions` has a foreign key back to the account.

Freezing is a `Freezable` interface rather than something on `Account`,
because not every account type should be freezable.

## API

| Method | Endpoint | What it does |
|---|---|---|
| GET | `/api/accounts` | List all accounts |
| GET | `/api/accounts/{id}` | Get one account |
| POST | `/api/accounts` | Create an account |
| DELETE | `/api/accounts/{id}` | Close an account |
| POST | `/api/accounts/{id}/deposit` | Deposit |
| POST | `/api/accounts/{id}/withdraw` | Withdraw |
| GET | `/api/accounts/{id}/transactions` | Account statement |
| POST | `/api/accounts/{id}/freeze` | Freeze |
| POST | `/api/accounts/{id}/unfreeze` | Unfreeze |

### Creating an account

```http
POST /api/accounts
Content-Type: application/json

{
  "ownerName": "Ahmed",
  "initialBalance": 5000,
  "type": "SAVINGS",
  "interestRate": 0.10
}
```

Response:

```json
{
  "id": 1,
  "ownerName": "Ahmed",
  "balance": 5000.0,
  "interestRate": 0.1,
  "transactions": []
}
```

### Errors

All exceptions are handled in one class, so the client never sees a stack
trace:

| Problem | Status |
|---|---|
| Account not found | 404 |
| Not enough money, over the overdraft limit, or frozen account | 409 |
| Bad request body | 400 |

Example:

```json
{
  "status": 409,
  "message": "الرصيد غير كافٍ. الرصيد الحالي: 5000.0"
}
```

## Running it

1. Create the database:

   ```sql
   CREATE DATABASE bank_db;
   ```

2. Set your MySQL password as an environment variable (it is not in the repo):

   ```
   DB_PASSWORD=your_password
   ```

3. Start the app:

   ```
   ./mvnw spring-boot:run
   ```

It runs on `http://localhost:8080`.

## Tests

```
./mvnw test
```

The tests use an in-memory H2 database, so you don't need MySQL running to
run them.