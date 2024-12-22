package com.spring.webflux.controller;

import com.spring.webflux.dto.TaskRequest;
import com.spring.webflux.dto.TaskResponse;
import com.spring.webflux.exception.TaskNotFoundException;
import com.spring.webflux.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Create a new Task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Task created successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TaskRequest.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid input provided") })
    @PostMapping
    public Mono<ResponseEntity<TaskResponse>> createTask(@RequestBody TaskRequest taskRequest) {
        return taskService.createTask(taskRequest)
                .map(createdTask -> ResponseEntity.status(201).body(createdTask));
    }

    @Operation(summary = "Retrieve List of tasks")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of tasks retrieved successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TaskResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid input provided") })
    @GetMapping
    public Mono<ResponseEntity<Flux<TaskResponse>>> getAllTasks() {
        return Mono.just(ResponseEntity.ok(taskService.findAll()));
    }

    @Operation(summary = "Retrieve task by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task retrieved successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TaskResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid input provided") })
    @GetMapping("/{id}")
    public Mono<ResponseEntity<TaskResponse>> getTaskById(@PathVariable Long id) {
        return taskService.findTaskById(id)
                .map(ResponseEntity::ok);
    }

    @Operation(summary = "Update task by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated task retrieved successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TaskResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid input provided") })

    @PutMapping("/{id}")
    public Mono<ResponseEntity<TaskResponse>> updateTaskById(
            @PathVariable Long id, @RequestBody TaskRequest taskRequest
            ) {
        return taskService.updateTaskById(id, taskRequest)
                .map(ResponseEntity::ok);
    }

    @Operation(summary = "Delete task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete task successfully",
                    content =  @Content ),
            @ApiResponse(responseCode = "400", description = "Invalid input provided") })
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Object>> deleteTaskById(@PathVariable Long id) {
        return taskService.deleteTaskById(id)
                .then(Mono.just(ResponseEntity.noContent().build()))
                .onErrorResume(TaskNotFoundException.class, e ->
                        Mono.just(ResponseEntity.notFound().build())
                );
    }
}
