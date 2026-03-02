Job Portal REST API

A scalable backend REST API built using Spring Boot where companies can post jobs and candidates can apply for them. The application follows clean layered architecture and RESTful best practices.

🚀 Tech Stack

Java 17+

Spring Boot

Spring Data JPA

Hibernate

PostgreSQL

Lombok

Jakarta Validation

Maven

🏗️ Architecture

The project follows a clean layered architecture:

Controller → Service → Repository → Database

DTO pattern to prevent entity exposure

Global exception handling using @RestControllerAdvice

Pagination & sorting using Spring Data JPA

Proper JPA relationships (OneToMany, ManyToOne)

Business logic validation to prevent duplicate job applications

📂 Modules
🔹 Company Module

Create Company

Get All Companies (Pagination & Sorting)

Get Company by ID

Update Company

Delete Company

🔹 Job Module

Create Job

Get All Jobs (Pagination & Sorting)

Get Job by ID

Search Jobs by Location

Update Job

Delete Job

🔹 Candidate Module

Create Candidate

Get All Candidates (Pagination & Sorting)

Get Candidate by ID

Update Candidate

Delete Candidate

🔹 Job Application Module

Apply for Job

Get All Applications

Get Applications by Job

Get Applications by Candidate

Prevent Duplicate Applications

🔗 Sample API Endpoints
POST   /api/companies
GET    /api/companies
POST   /api/jobs
GET    /api/jobs/search?location=Bangalore
POST   /api/jobs/{jobId}/apply/{candidateId}
GET    /api/applications
📦 Features

✔ RESTful API design
✔ Layered architecture
✔ DTO mapping
✔ Pagination & sorting
✔ Global exception handling
✔ Validation using annotations
✔ Business logic constraints
✔ Clean and maintainable code structure

⚙️ How to Run the Project

Clone the repository

git clone https://github.com/yourusername/job-portal-rest-api.git

Configure PostgreSQL in application.properties

spring.datasource.url=jdbc:postgresql://localhost:5432/jobportal
spring.datasource.username=your_username
spring.datasource.password=your_password

Run the application

mvn spring-boot:run

Application will start on:

http://localhost:8080
📊 Database Design

Company → OneToMany → Job

Job → ManyToOne → Company

Job → OneToMany → JobApplication

Candidate → OneToMany → JobApplication

JobApplication → ManyToOne → Job & Candidate

🎯 Project Objective

This project demonstrates backend development skills including:

REST API development

JPA relationship mapping

Pagination & sorting

DTO pattern implementation

Exception handling

Business logic implementation

Clean code practices

👨‍💻 Author

Amit
Java Backend Developer
