## Define "reactive"
The term "reactive" refers to programming models that are built around reacting to change -- network component reacting to I/O events, UI controller reacting to mouse events, etc. In that sense non-blocking is reactive because instead of being blocked we are now in the mode of reacting to notifications as operations complete or data becomes available.
# Spring Reactive Programming
As I am new to reactive programming model, I would highly suggest myself to go through following articles to learn much better reactive programming.
- [Reactive Manifesto](https://www.reactivemanifesto.org/)
-  [Reactive Streams](https://www.reactive-streams.org/)
- [Java 9 Reactive Streams](https://www.reactive-streams.org/)
- [RxJava](https://www.digitalocean.com/community/tutorials/rxjava-tutorial)

###### *Why was Spring WebFlux created?*
Need for a non-blocking web stack to handle concurrency with a small number of threads and scale with fewer hardware resources.
That is important because of servers such as Netty that are well-established in the async, non-blocking space.

The other part of the answer is functional programming.
The addition of lambda expressions in Java 8 created opportunities for functional APIs in Java. This is a boon for non-blocking applications and continuation-style APIs that allow declarative composition of asynchronous logic.

## Spring WebFlux
**Spring WebFlux is built on Project Reactor which implements the Reactive Streams specification.**

Spring WebFlux is the alternative to Spring MVC module. Spring WebFlux is used to create fully asynchronous and non-blocking application built on event-loop execution model. The diagram below is from the official Documentation provides great insight on comparison of Spring WebFlux to Spring Web MVC.
![Spring MVC vs WebFlux](https://journaldev.nyc3.cdn.digitaloceanspaces.com/2018/05/spring-mvc-and-webflux.png)

Want to develop a web application or Rest web service on non-blocking reactive model, then you can look into Spring WebFlux. Spring WebFlux is supported on Tomcat, Jetty, Servlet 3.1+ containers, as well as on non-Servlet runtimes such as Netty and Undertow. Spring WebFlux is built on Project Reactor. Project Reactor is the implementation of Reactive Streams specification. Reactor provides two types:
1. Mono: implements Publisher returns 0 or 1 elements.
2. Flux: implements Publisher and returns N elements.
#### Key Terminologies
1. **Reactive programming**: A programming paradigm focused on handling asynchronous data streams and change propagation. It revolves around the concept of data being pushed to consumers as it becomes available.
2. **Publisher**: A component that emits a sequence of event, In WebFlux, `Mono` and `Flux` are the main types of publishers.
3. **Subscriber**: A component that listens to the events emitted by the `Publisher`. It processes the data or handles errors.
4. **Backpressure**: A mechanism to control the flow of data, ensuring that the `subscriber` is not overwhelmed by the data pushed by the `Publisher`.
5. Mono: A reactive type that represents a single asynchronous value or an empty value (0 or 1 item).
6. Flux: A reactive type that represents a stream of 0 to N items, such as a list of values, where N can be infinite.

##### Reactive Types: Mono and Flux
###### Mono:
**Definition**: A `Mono` is a specialized Publisher that emits at most one item. It can either complete successfully with a single value, complete empty, or terminate with an error.
**Common Methods**
- `Mono.just(T data)`: Creates a `Mono` that emits the provided value.
- `Mono.empty()`: Create an empty `Mono`.
- `Mono.error(Throwable error)`: Creates a `Mono` that terminates with the provided error.
- `Mono.fromCallable(Callable<T>)`: Wraps a callable in a `Mono`.
- `Mono.map(Function<T, R>)`: Transforms the item emitted by this `Mono`.
- `Mono.flatMap(Function<T, Mono<R>>)`: Transforms the item into another `Mono` and flattens the result.
##### Flux
**Definition**: A `Flux` is a Publisher that emits a sequence of 0 to N items. It can represent a finite list, an infinite stream, or an empty sequence.
**Common Methods**:
- `Flux.just(T... data)`: Creates a `Flux` that emits the specified values.
- `Flux.fromiterable(Iterable<T>)`: Create a `Flux` from an `Iterable`.
- `Flux.range(int start, int count)`: Creates a `Flux` that emits a range of integers.
- `Flux.interval(Duration period)`: Creates a `Flux` that emits a sequence of Long values spaced by a given period.
- `Flux.map(Function<T, R>)`: Transforms each item emitted by this `Flux`.
- `Flux.flatMap*Function<T, Publisher<R>>`: Transforms each item into a Publisher and flattens the results.
##### Other Important Concepts
1. Schedulers: WebFlux offers various schedulers to control the execution context of reactive pipelines.
    - `Scheduler.parellel()`: Executes tasks in a parallel thread pool.
    - `Scheduler.elastic()`: Provides a scheduler with an elastic number of threads.
    - `Scheduler.single()`: Executes tasks on a single thread.
2. BackPressure Strategies: Strategies to handle cases where a `Publisher` produces data faster than a `Subscriber` can consume.
    - Drop: Discards items when the buffer is full.
    - Latest: Keep the latest value and drops older ones.
3. Operators: WebFlux provides a rich set of operators to manipulate the data stream. some common categories include:
    1. Filtering: `filter`,`table`, `skip`.
    2. Transforming: `map`,`flatMap`,`buffer`.
    3. Combining: `merge`, `zip`, `concat`.
4. Context Propagation: Using `Context`, you can store and pass around information during reactive stream processing.
# Mono
`Mono<T>` is a reactive type in Project Reactor that represents a single asynchronous value. It can emit:
1. A Single Value (onNext)
2. An empty value (complete without a value)
3. An error (onError)
`Mono` is particularly useful when you expect zero or one result, such as when fetching a single record from a database, making an HTTP request, or handling a user action that either succeeds or fails.
#### Key Methods of Mono in Deep:
1. Creation Methods:
    1. `Mono.just(T value)`: Creates a `Mono` that emits the given value.
       ```
       Mono<String> mono = Mono.just("Hello, World");
       ```
    2. `Mono.empty()`: Returns an empty `Mono` that completes without emitting any value.
       ```
       Mono<Object> mono = Mono.empty();
       ```
    3. `Mono.error(Throwable error)`: Creates a Mono that terminates with specific error.
        ```
        Mono<String> mono = Mono.error(new RuntimeException("Error occurred"));
        ```
    4. `Mono.fromCallable(Callable<T> callable)`: Creates a Mono from a callable, emitting its result.
        ```
        Mono<Integer> mono = Mono.fromCallable(() -> 42);
        ```
    5. `Mono.defer(Supplier<Mono<T>> supplier)`:  Defers the creation of the `Mono` until a subscriber subscribes to it.
        ```
        Mono<String> deferrMono = Mono.defer(() -> Mono.just("Deferred value"));
        ```
2. Utility Methods
    1. `doOnNext(Consumer<? super T> onNext)`: Adds behavior triggered when a value is emitted.
       ```
       Mono<String> mono = Mono.just("Hello")
           .doOnNext(System.out::println); // Prints "Hello"
        ```
    2. `doOnError(Consumer<? super Throwable> onError)`: Adds behavior triggered when an error is emitted.
       ```
       Mono<String> mono = Mono.error(new RuntimeException("Error"))
           .doOnError(error -> System.out.println("Caught error: "+error));
       ```
    3. `block()`: Blocks the current thread until the `Mono` completes and returns the value (or throws an error).
       `String result = Mono.just("blocking operation").block();`
    4. `subscribe(Consumer<? super T> consumer)`: Subscribes to the `Mono`, triggering its execution.
       ```
       Mono.just("Subscribed")
           .subscribe(value -> System.out.println("Received: "+value));
       ```
#### Chaining Operation with Mono
`Mono` allows you to chain multiple operations in a reactive pipeline, which is very powerful for handling complex asynchronous flows, For example:
```
Mono.just("chain")
	.map(String::toUpperCase)
	.flatMap(value -> Mono.just(value + " operations"))
	.switchIfEmpty(Mono.just("No value"))
	.doOnNext(System.out::println)
	.subscribe();
```
### Practical Examples:
##### Example 1: Fetching Data from a Database
```
Mono<User> userMono = userRepository.findById(1)
	.map(user -> {
		user.setLastAccessed(LocalDateTime.now());
		return user;
	})
	.switchIfEmpty(Mono.error(new UserNotFoundException("User not found")));
```
##### Example 2: Handling Errors
```
Mono<String> mono = Mono.just("Hello")
	.flatMap(value -> {
		if(value.isEmpty()) {
			return Mono.error(new IllegalArgumentException("Value is empty"));
		}
		return Mono.just(value);
	})
	.onErrorResume(e -> Mono.just("Fallback value"));
```

# Flux
###### What is Flux?
`Flux<T>` is a reactive type in Project Reactor that represents a stream of 0 to N items. Unlike `Mono`, which deals with a single value or none, `Flux` can emit multiple values over time, making it ideal for handling sequences, collections, or any scenario involving multiple data elements.
#### Key Methods of Flux
##### Creation Methods
1. `Flux.just(T... values)`: Creates a `Flux` that emits the provided values in sequence.
   `Flux<String> flux = Flux.just("Alpha", "Beta","Gemma");`
2. `Flux.fromIterable(Iterable<? extends T> iterable)`: Creates a `Flux` from an `Iterable` source.
   `Flux<Integer> flux = Flux.fromIterable(List.of(1,2,3,4,5));`
3. `Flux.range(int start, int count)`: Creates a `Flux` that emits a range an integers.
   `Flux<Integer> flux = Flux.range(1,5); //Emits 1, 2, 3, 4, 5`
4. `Flux.interval(Duration period)`: Create a `Flux` that emits a sequential numbers with a fixed interval between emissions.
   `Flux<Long> flux = Flux.interval(Duration.ofSeconds(1));`
5. `Flux.empty()`: Returns an empty `Flux` that completes immediately without emitting any items.
   `Flux<Object> flux = Flux.empty();`
6. `Flux.error(Throwable error)`: Creates a `Flux` that terminates with the specified error.
   `Flux<String> flux = Flux.error(new RuntimeException("Error occurred"));`
7. `Flux.defer(Supplier<Flux<T>> supplier)`: Defers the creation of the `Flux` until a subscriber subscribes.
   `Flux<String> flux = Flux.defer(() -> Flux.just("Deferred value"));`

#### Transformation Methods:
### WebFlux provides support for two paradigms:
- Annotation-based Spring Controllers(Similar to SpringMVC)
- Functional Endpoints that allow for functional, fluent API style routing and handler functions.
## Spring WebFlux Dependencies:
The most important dependencies are `spring-boot-starter-webflux` and `spring-boot-starter-parent`.

Gradle Dependency for webflux:
```
implementation("org.springframework.boot:spring-boot-starter-webflux")
```
To leverage a Spring Data Reactive dependency to get full benefit of a reactive, async, non-blocking architecture.
Examples of Spring Data Reactive Libraries:
- Spring Data Reactive for Apache Cassandra
- Spring Data Reactive MongoDB
- Spring Data Reactive Redis
- Spring Data r2dbc
