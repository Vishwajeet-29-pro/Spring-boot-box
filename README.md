# Spring Boot Box

A **modular Spring Boot project** designed to explore, implement, and master various functionalities and database integrations. Each module is self-contained and focuses on a specific feature, built with **Test-Driven Development (TDD)** at its core. This project serves as a learning and demonstration platform for best practices in Spring Boot development.

---

## Project Structure

```graphql
spring-boot-box/
├── settings.gradle.kts          # Gradle settings for module management
├── Makefile                     # Build, test, and run commands using Makefile
├── gradlew                      # Gradle wrapper script
├── gradlew.bat                  # Gradle wrapper for Windows
├── build.gradle.kts             # Root Gradle configuration
├── README.md                    # Project documentation
├── app-db-h2-connection/            # Module for H2 database integration
├── app-db-postgres-connection/      # Module for PostgreSQL integration
├── app-db-mysql-connection/         # Module for MySQL integration
├── app-db-mongo-connection/         # Module for MongoDB integration
├── app-spring-security-basic        # Module for Spring security (RBAC)
├── app-reactive-webflux-basic       # Module for Spring Reactive webflux basic
├── app-(future modules...)          # Additional modules for extended functionalities
```

---

## Purpose and Philosophy

### Key Goals:

1. **Modular Design**:
    - Each module is independent and focuses on a specific feature or database.
    - Enables easy experimentation and integration of new ideas.
2. **TDD-Driven Development**:
    - Writing tests first to drive the implementation.
    - Ensures reliable, maintainable, and bug-free code.
3. **Learning-Focused**:
    - A sandbox for learning Spring Boot features, database integrations, and advanced concepts.

---
## Makefile Integration
This project uses a Makefile to simplify build, test, run, and other operations. The Makefile provides a consistent interface for managing project tasks across different environments. For Windows users, you can install `make` using [Chocolatey](https://docs.chocolatey.org/en-us/choco/setup/#install-with-cmdexe) by running the following command:
```bash
choco install make
```

## Current Modules

### **1. app-db-h2-connection**

- **Purpose**: Demonstrates integration with H2, an in-memory database.
- **Features**:
    - Quick prototyping and testing with in-memory persistence.
    - RESTful APIs for CRUD operations.
    - End-to-end tests for each feature.
    - In the same project **Introduced Behavior-Driven Development (BDD) using Cucumber.** (TODO service)

### **2. app-db-postgres-connection**

- **Purpose**: Demonstrates integration with PostgreSQL.
- **Features**:
    - Production-grade persistence with PostgreSQL.
    - Proper connection pooling and configuration.
    - RESTful APIs and corresponding test cases.

### **3. app-db-mysql-connection**

- **Purpose**: Demonstrates integration with MySQL.
- **Features**:
    - Full MySQL database support for production use.
    - Comprehensive CRUD APIs.
    - Extensive testing for all operations.
 
### **4. app-db-mongodb-connection**

- **Purpose**: Demonstrates integration with MongoDB, a NoSQL database.
- **Features**:
    - Flexible schema design for NoSQL use cases.
    - RESTful APIs for CRUD operations.
    - Comprehensive test cases to ensure functionality.
    - Suitable for projects requiring horizontal scalability.

### **5. app-spring-security-basic**

- **Purpose**: Kick-start with Spring security (1.0).
- **Features**: 
  - A simple application with handle the Role based Access Control.
  - Created RESTful APIs with Role based authorization.
  - Created test to handle Role based authorization.

### **6. app-reactive-webflux-basic**

- **Purpose**: Kick-start with Spring Reactive Webflux.
- **Features**:
    - A simple application with handle the Tasks (same as todo application).
    - Created RESTful APIs with help of Reactive Components such as Mono, Flux.
    - Created test to handle CRUD operations.

---

## Future Plans

### Planned Modules:

1. **Spring Security Integration 2.0**:
    - Implement basic authentication and authorization.
    - Secure endpoints using Spring Security features.
2. **Reactive Programming with WebFlux 2.0**:
    - Add R2DBC for reactive database operations with other databases and with increase level complexity.
3. **Advanced Functionalities**:
    - Explore Spring Cloud for microservices.
    - Build inter-service communication and monitoring.

### Testing Enhancements:

- Add mutation testing with PIT (Pitest).
- Focus on integrating test automation pipelines.

---

## Getting Started

### Prerequisites

- **Java 17+** (or preferred version).
- **Gradle** (build tool).

### Using the Makefile
1. Build and Test All Modules:
```bash
    make all
```
2. Build Modules:
```bash
    make
```
3. Clean All Build Files:
```bash
    make clean
```
4. Run the Root Spring Boot Application: (by default it runs h2 application)
```bash
    make run
```
5. Run the Specific Application: (e.g. h2 application)
```bash
    make run-h2
```
6. Build and Test a Specific Module:
```bash
    make run-module
```
7. Testing
Run all tests for the module:
```
    make test
```
8. Help
```bash
    make help
```
---

## OpenAPI Integration

The project leverages **OpenAPI** to provide interactive API documentation. This makes it easy to explore and test the REST APIs for each module.

### How to Access OpenAPI Documentation:

1. Start the Spring Boot application.
2. Navigate to the following URL in your browser
    ```bash
        http://localhost:8080/swagger-ui/index.html
    ```
   - Replace `8080` with the appropriate port if a different one is configured.
3. Explore the API endpoints, input parameters, and responses in an interactive interface.

**Note:** In Case of **Reactive WebFlux projects** navigate to below URL:
```bash
    http://localhost:8080/swagger-ui.html
```
## Contributions

Contributions are welcome! Feel free to fork the repository, create a new module, or enhance the existing ones.
-  [View Repository on GitHub](https://github.com/vishwajeet-29-pro/spring-boot-box)
---

## Author

**Vishwajeet Kotkar**

- A passionate developer focused on mastering Spring Boot, Test-Driven Development, and modern software practices.

Connect on [LinkedIn](https://www.linkedin.com/in/vishwajeet-kotkar/) or explore my other projects on [GitHub](https://github.com/vishwajeet-29-pro).
