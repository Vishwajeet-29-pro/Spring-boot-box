package com.spring.webflux.service;

import com.spring.webflux.dto.TaskRequest;
import com.spring.webflux.dto.TaskResponse;
import com.spring.webflux.exception.TaskNotFoundException;
import com.spring.webflux.model.Task;
import com.spring.webflux.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public Mono<TaskResponse> createTask(TaskRequest taskRequest) {
        Mono<Task> task = taskRepository.save(TaskRequest.toTask(taskRequest));
        return task.map(TaskResponse::toTaskResponse);
    }

    @Override
    public Flux<TaskResponse> findAll() {
        Flux<Task> taskFlux = taskRepository.findAll();
        return taskFlux.map(TaskResponse::toTaskResponse);
    }

    @Override
    public Mono<TaskResponse> findTaskById(Long id) {
        Mono<Task> task = taskRepository.findById(id);
        return task
                .map(TaskResponse::toTaskResponse)
                .switchIfEmpty(Mono.error(
                        new TaskNotFoundException("Task with ID " + id + " not found")
                ));
    }

    @Override
    public Mono<TaskResponse> updateTaskById(Long id, TaskRequest taskRequest) {
        Mono<Task> task = taskRepository.findById(id).switchIfEmpty(Mono.error(
                new TaskNotFoundException("Task with ID " + id + " not found")
        )).flatMap(existingTask -> {
            existingTask.setTitle(taskRequest.getTitle());
            existingTask.setDescription(taskRequest.getDescription());
            existingTask.setStatus(taskRequest.getStatus());
            existingTask.setUpdatedAt(LocalDateTime.now());

            return taskRepository.save(existingTask);
        });

        return task.map(TaskResponse::toTaskResponse);
    }

    @Override
    public Mono<?> deleteTaskById(Long id) {
        return null;
    }
}
