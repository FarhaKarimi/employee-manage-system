# Employee Management System

A simple Employee Management System built with **Java**, **Spring Boot**, and **PostgreSQL**. This project implements CRUD operations with role-based access control (RBAC) using Spring Security.

## Features
- Create, Read, Update, and Delete employee records.
- Role-based authentication (ADMIN and USER roles).
- Secure REST APIs with Spring Security.
- Database integration with PostgreSQL using Spring Data JPA.

## Technologies
- Java 17
- Spring Boot
- PostgreSQL
- Spring Security
- Maven

## Setup
1. Clone the repository: `git clone https://github.com/yourusername/employee-management-system.git`
2. Configure PostgreSQL in `application.properties`.
3. Run the application: `mvn spring-boot:run`

## Usage
- Access APIs at `http://localhost:8080/api/employees`.
- Use credentials: `admin/admin123` (ADMIN) or `user/user123` (USER).
