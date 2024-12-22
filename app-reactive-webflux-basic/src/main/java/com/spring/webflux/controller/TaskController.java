package com.spring.webflux.controller;

import com.spring.webflux.dto.TaskRequest;
import com.spring.webflux.dto.TaskResponse;
import com.spring.webflux.exception.TaskNotFoundException;
import com.spring.webflux.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public Mono<ResponseEntity<TaskResponse>> createTask(@RequestBody TaskRequest taskRequest) {
        return taskService.createTask(taskRequest)
                .map(createdTask -> ResponseEntity.status(201).body(createdTask));
    }

    @GetMapping
    public Mono<ResponseEntity<Flux<TaskResponse>>> getAllTasks() {
        return Mono.just(ResponseEntity.ok(taskService.findAll()));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<TaskResponse>> getTaskById(@PathVariable Long id) {
        return taskService.findTaskById(id)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<TaskResponse>> updateTaskById(
            @PathVariable Long id, @RequestBody TaskRequest taskRequest
            ) {
        return taskService.updateTaskById(id, taskRequest)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Object>> deleteTaskById(@PathVariable Long id) {
        return taskService.deleteTaskById(id)
                .then(Mono.just(ResponseEntity.noContent().build()))
                .onErrorResume(TaskNotFoundException.class, e ->
                        Mono.just(ResponseEntity.notFound().build()));

    }
}
