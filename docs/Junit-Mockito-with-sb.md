# JUnit5 and Mockito
### **JUnit 5 Annotations**

1. **`@Test`**  
   Marks a method as a test case.

2. **`@BeforeEach`**  
   Executes before each test method. Used for setup.

3. **`@AfterEach`**  
   Executes after each test method. Used for cleanup.

4. **`@BeforeAll`**  
   Executes once before all test methods in a test class. Used for one-time setup (static method).

5. **`@AfterAll`**  
   Executes once after all test methods in a test class. Used for one-time cleanup (static method).

6. **`@Disabled`**  
   Disables a test method or class, effectively skipping it.

7. **`@Nested`**  
   Used to create nested test classes for better organization and grouping.

8. **`@DisplayName`**  
   Provides a custom name for test methods or classes, making test output more readable.

9. **`@RepeatedTest`**  
   Runs a test multiple times.

10. **`@ParameterizedTest`**  
    Marks a method as a parameterized test to run the same test logic with different input values.

11. **`@ValueSource`**  
    Supplies a single array of primitive or String values to a parameterized test.

12. **`@CsvSource`**  
    Provides comma-separated values for parameterized tests.

13. **`@Tag`**  
    Tags a test or class for filtering during execution.

14. **`@Timeout`**  
    Fails a test if it takes longer than the specified duration to execute.

15. **`@TestInstance`**  
    Specifies the lifecycle of a test class (`PER_CLASS` or `PER_METHOD`).


---

### **Mockito Annotations**

1. **`@Mock`**  
   Creates a mock object for a class or interface.

2. **`@Spy`**  
   Creates a spy object, which is a partial mock of a real object.

3. **`@InjectMocks`**  
   Automatically injects mocks into the class under test.

4. **`@Captor`**  
   Creates an argument captor to capture arguments passed to a method.

5. **`@MockBeans` (Spring Boot)**  
   Defines a collection of `@MockBean` to replace specific beans in the application context.

6. **`@MockBean` (Spring Boot)**  
   Replaces a bean in the application context with a mock.

7. **`@ExtendWith(MockitoExtension.class)`**  
   Integrates Mockito with JUnit 5 for automatic mock initialization.
