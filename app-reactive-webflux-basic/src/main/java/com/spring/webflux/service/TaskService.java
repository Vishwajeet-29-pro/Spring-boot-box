package com.spring.webflux.service;

import com.spring.webflux.dto.TaskRequest;
import com.spring.webflux.dto.TaskResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TaskService {

    Mono<TaskResponse> createTask(TaskRequest taskRequest);
    Flux<TaskResponse> findAll();
    Mono<TaskResponse> findTaskById(Long id);
    Mono<TaskResponse> updateTaskById(Long id, TaskRequest taskRequest);
    Mono<Void> deleteTaskById(Long id);
}
