1. What does the `@SpringBootApplication` annotation do?
   - `@SpringBootAnnotation` is a convenience annotation that combines three annotations:
    - `@Configuration` (marks the class as a source of bean definitions),
    - `@EnableAutoConfiguration` (enable Spring Boot's auto-configuration mechanism),
    - `@ComponentScan` (scans the package of the annotated class for Spring components).

2. How can you configure properties in a Spring Boot Application?
    - You can configure properties in a Spring Boot application using `application.properties` or `application.yml` files located
    in the `src/main/resources` directory.

3. How do you handle exceptions in Spring Boot?
    - You can handle exceptions in Spring Boot using `@ControllerAdvice` or `@RestControllerAdvice` and `@ExceptionHandler` annotations to create a global exception handler.

4. What is Spring Boot Actuator and what are its benefits?
   - Spring Boot Actuator provides production-ready features such as health checks, metrics, and monitoring for your Spring Boot application. It includes various endpoint
   that give insights into the application's health and environment.

5. How can you enable and use Actuator endpoints in a Spring Boot application?
    - Need to add the Actuator dependency in your `pom.xml` or `build.gradle` file, namely
    `spring-boot-starter-actuator`.
    - And in `application.properties` configure the endpoints:
    - `management.endpoints.web.exposure.include=health, info`

6. What are Spring Profiles and how do you use them?
    - Spring Profiles allow you to segregate parts of your application configuration and make it only available in certain
    environments. You can activate profiles using the `spring.profiles.active` property.
    - `# application-dev.properties`
    - `spring.datasource.url=jdbc:mysql://localhost:3306/devdb`
    - `# application-prod.properties`
    - `spring.datasource.url=jdbc:mysql://localhost:3306/proddb`

7. How do you test a Spring Boot application?
    - Spring Boot supports testing with various tools and annotations like `@SpringBootTest`, `@WebMvcTest`, and `@DataJpaTest`.
    - Use `MockMvc` to test MVC controllers without starting a full HTTP server.

8. How can you handle different environments in a Spring Boot application?
    - You can handle different environments using Spring Profiles. Define environment-specific properties files like
    - `application-dev.properties`, `application-prod.properties`, and activate a profile using `spring.profiles.active`.

9. What are the differences between `@Component`, `@Service`, `@Repository`, and `@Controller` annotations?
    - These annotations are specialization of `@Component`:
    - `@Component`: Generic stereotype for any Spring-managed component.
    - `@Service`: Specialization for service layer classes.
    - `@Repository`: Specialization for persistence layer classes.
    - `@Controller`: Specialization for presentation layer. (MVC controllers).

10. How can you create a RESTful web service using Spring Boot?
    - Use `@RestController` and `@RequestMapping` annotations to create REST endpoints.
    - For CRUD operations - `@GetMapping`, `PostMapping`, `@PutMapping`, `DeleteMapping` with appropriate logic. 

11. How do you connect to an external REST API using Spring Boot?
    - Connect to an external REST API using `RestTemplate` or `WebClient`.
    - `RestTemplate` is synchronous, while `WebClient` is asynchronous and non-blocking.

12. How do you implement pagination in Spring Boot?
    - Implement Pagination using Spring Data JPA's `Pageable` interface. Define repository methods that accept `Pageable` parameters.
    ```java
        public interface UserRepository extends JpaRepository<User,Long> {
            Page<User> findByLastName(String lastName, Pageable pageable);
    }
    ```
         