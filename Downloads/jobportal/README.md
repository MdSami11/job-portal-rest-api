# Job Portal REST API

A secure **Job Portal REST API** built using **Java, Spring Boot, and MySQL**. The project demonstrates backend development practices including **RESTful API design, JWT authentication, role-based authorization, database integration, and API documentation**.

## 🚀 Features

- User registration and authentication using **JWT**
- Role-based access control for different users
- Create, update, delete, and retrieve job postings
- DTO-based request and response handling for clean API communication
- Job application management
- Secure REST API endpoints
- Exception handling and input validation
- Database persistence using **JPA/Hibernate**

## 🛠️ Tech Stack

**Backend**
- Java
- Spring Boot
- Spring Security
- Spring Data JPA
- Data Transfer Objects (DTOs)
- Hibernate
- REST APIs

**Database**
- MySQL
- SQL

**Security**
- JWT Authentication
- Role-Based Authorization

**Tools**
- Maven
- Git & GitHub
- Docker
- Postman
- Swagger/OpenAPI
- JUnit & Mockito

## 🏗️ Architecture

The application follows a layered architecture:

```
Controller Layer
        ↓
Service Layer
        ↓
Repository Layer
        ↓
MySQL Database
```

- Controllers handle API requests and responses
- Services contain business logic
- Repositories manage database operations using JPA/Hibernate

## 📚 API Documentation

Swagger UI is integrated for API testing and documentation.

```
http://localhost:8080/swagger-ui.html
```

## ⚙️ Running the Project

### Prerequisites
- Java 17+
- Maven
- MySQL

### Setup

1. Clone the repository:

```bash
git clone https://github.com/your-username/job-portal-rest-api.git
```

2. Configure MySQL database details in `application.properties`

3. Run the application:

```bash
mvn spring-boot:run
```

The API will be available at:

```
http://localhost:8080
```

## 🧪 Testing

Implemented unit testing using:
- JUnit
- Mockito

## 🔮 Future Enhancements

- Docker Compose deployment
- CI/CD pipeline integration
- Cloud deployment
- Advanced search and filtering

## 👨‍💻 Author

Muhammad Sami

AI & ML Undergraduate | Backend Development Enthusiast