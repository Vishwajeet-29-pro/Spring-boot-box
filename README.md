# Spring Boot Box

A **modular Spring Boot project** designed to explore, implement, and master various functionalities and database integrations. Each module is self-contained and focuses on a specific feature, built with **Test-Driven Development (TDD)** at its core. This project serves as a learning and demonstration platform for best practices in Spring Boot development.

---

## Project Structure

```graphql
spring-boot-box/
├── settings.gradle.kts          # Gradle settings for module management
├── gradlew                      # Gradle wrapper script
├── gradlew.bat                  # Gradle wrapper for Windows
├── build.gradle.kts             # Root Gradle configuration
├── README.md                    # Project documentation
├── db-h2-connection/            # Module for H2 database integration
├── db-postgres-connection/      # Module for PostgreSQL integration
├── (future modules...)          # Additional modules for extended functionalities
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

## Current Modules

### **1. db-h2-connection**

- **Purpose**: Demonstrates integration with H2, an in-memory database.
- **Features**:
    - Quick prototyping and testing with in-memory persistence.
    - RESTful APIs for CRUD operations.
    - End-to-end tests for each feature.

### **2. db-postgres-connection**

- **Purpose**: Demonstrates integration with PostgreSQL.
- **Features**:
    - Production-grade persistence with PostgreSQL.
    - Proper connection pooling and configuration.
    - RESTful APIs and corresponding test cases.

---

## Future Plans

### Planned Modules:

1. **MySQL Integration**:
    - Showcase Spring Boot’s capabilities with MySQL.
2. **MongoDB Integration**:
    - Learn and implement NoSQL database concepts.
3. **Reactive Programming with WebFlux**:
    - Add R2DBC for reactive database operations.
4. **Advanced Functionalities**:
    - Explore Spring Cloud for microservices.
    - Build inter-service communication and monitoring.

### Testing Enhancements:

- Add mutation testing with PIT (Pitest).
- Introduce Behavior-Driven Development (BDD) using Cucumber.
- Focus on integrating test automation pipelines.

---

## Getting Started

### Prerequisites

- **Java 17+** (or preferred version).
- **Gradle** (build tool).

### Running a Module

1. Navigate to the desired module directory:
```
cd db-h2-connection
```

2. Build and run the application:
```bash
    ./gradlew bootRun
```

### Testing

Run all tests for the module:
```
./gradlew test
```
---

## Contributions

Contributions are welcome! Feel free to fork the repository, create a new module, or enhance the existing ones.

---

## Author

**Vishwajeet Kotkar**

- A passionate developer focused on mastering Spring Boot, Test-Driven Development, and modern software practices.

Connect on [LinkedIn](https://www.linkedin.com/in/vishwajeet-kotkar/) or explore my other projects on [GitHub](https://github.com/vishwajeet-29-pro).