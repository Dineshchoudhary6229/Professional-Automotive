 Driver & Vehicle Management System
🚗 Professional Automotive Assignment

A Spring Boot REST API application for managing drivers, vehicles, and their assignments.
Designed as a backend assignment to demonstrate clean architecture, RESTful design, and real-world use cases.

🛠 Tech Stack
Technology	Description
Java 17	Backend language
Spring Boot	Application framework
Spring Data JPA	ORM & database access
H2 / PostgreSQL	Database (configurable)
Maven	Build tool
REST API	Client-server communication
📂 Project Structure
dvm/
 ├── controller/
 ├── service/
 ├── repository/
 ├── entity/
 ├── resources/
 │    └── application.yaml
 └── DriverVehicleManagementApplication.java

 Features:

Manage Drivers

Manage Vehicles

Assign drivers to vehicles

Upload assignments via Excel

Generate reports

Clean layered architecture

RESTful API design

📡 API Endpoints (Sample)
Driver APIs
POST   /drivers
GET    /drivers
GET    /drivers/{id}

Vehicle APIs
POST   /vehicles
GET    /vehicles

Assignment APIs
POST   /assign
GET    /assignments

▶️ How to Run Locally
Prerequisites

Java 17+

Maven

Steps
git clone https://github.com/Dineshchoudhary6229/Professional-Automotive.git
cd Professional-Automotive/dvm
mvn spring-boot:run


Application will start at:

http://localhost:8080

🧪 Testing
mvn test

👨‍💻 Author

Dinesh Choudhary
Java Backend Developer

GitHub:
https://github.com/Dineshchoudhary6229
