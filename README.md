# PublicSoft-Coding-Project - Supplier Management System

## Description
This project is a Supplier Management System, and it is built using a microservices architecture. 

It consists of three main components: a MySQL database, a backend service developed in Java Spring, and a frontend application built with Next.js and Bootstrap. All components are containerized using Docker and orchestrated with Docker Compose.

## Components

### Database - MySQL

- Docker: The database is containerized using Docker.
- Initialization: The init.sql file is used to create the database schema and populate it with initial data.
- Volume Binding: A bind mount is used for the database to persist data across container restarts. This allows the database to retain its state even if the container is stopped or removed.

### Backend - Java Spring

- Docker: The backend service is containerized using Docker.
- REST API: The backend service exposes a REST API that allows the frontend application to interact with the database.
- Error Handling: Basic error handling is implemented, and CORS is disabled for simplicity. 
- Testing: Unit tests are provided for the service layer to ensure functionality.

> Note: Security features such as authentication and authorization have not been implemented due to the project's scope. Additionally, Data Transfer Objects (DTOs) were not created as the project is small enough to not require them.

#### Supplier Entity

The Supplier entity has the following attributes:

- ID: The unique identifier for the supplier (auto-generated guid length 36 characters).
- Company Name: The name of the supplier's company (mandatory).
- First Name: The first name of the supplier's contact person (mandatory).
- Last Name: The last name of the supplier's contact person (mandatory).
- VAT Number: The VAT number of the supplier (unique identifier, mandatory).
- Irs Office: The IRS office where the supplier is registered (optional).
- Address: The address of the supplier (optional).
- Zip Code: The zip code of the supplier's location (optional).
- City: The city where the supplier is located (optional).
- Country: The country where the supplier is located (optional).

### Frontend - Next.js

- Docker: The frontend application is containerized using Docker.
- UI: The frontend application provides a simple user interface to interact with the backend service.
- Bootstrap: The application uses Bootstrap for styling and responsiveness.

### Docker Compose

- Orchestration: Docker Compose is used to manage the lifecycle of the containers. It allows for easy setup and teardown of the entire application stack.
- Networking: The containers are connected to the same network to enable communication between them.

#### Startup Order

The containers are started in the following order:
1. The database service starts first, and the backend service waits until the database is healthy before starting.
2. The backend service starts next, and the frontend service depends on the backend, ensuring that the API is available when the frontend is accessed.

## How to Run

### Prerequisites

- Docker: Install Docker on your machine.

### Steps

1. Clone the repository.
2. Navigate to the project directory.
3. Run the following command to start the application:

```
docker-compose up --build
```

4. Access the frontend application at http://localhost:3000.

## How to Stop

To stop the application, run:

```
docker-compose down
```

## API Documentation

### Endpoints

The backend service exposes the following endpoints:

- GET **api/suppliers/find/all**: Get all suppliers.
- GET **api/suppliers/find/companyName/{companyName}**: Find suppliers by company name (empty list if no match).
- GET **api/suppliers/find/vat/{vat}**: Find a supplier by VAT number (error message if not found).
- POST **api/suppliers/save**: Save a new / updates an existing supplier (returns the saved supplier).
- POST **api/suppliers/delete/{id}**: Delete a supplier by ID (no response body).

### Error Message Format

The backend service returns error messages in the following format:

```
{
  "timestamp": "2021-10-01T12:00:00Z",
  "status": 404,
  "error": "Not Found",
  "message": "Supplier not found with VAT number: 123456789",
  "path": "/api/suppliers/find/vat/123456789"
}
```

## Future Improvements

- Security: Implement authentication and authorization mechanisms to secure the application.
- Break in Multiple Tables: Add tables such as Company, IrsOffice, Country, etc., to normalize the database schema and reduce redundancy.
- DTOs: Create Data Transfer Objects to separate the API model from the entity model.
- Validation: Add input validation to ensure data integrity and consistency when saving suppliers is implemented.
- Pagination: Implement pagination for the list of suppliers to improve performance and user experience.

## Easter Egg

There is a small easter egg in the frontend application. Can you find it without looking at the code?