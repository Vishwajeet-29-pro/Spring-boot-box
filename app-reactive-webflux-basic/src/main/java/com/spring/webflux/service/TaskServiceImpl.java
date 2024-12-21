package com.spring.webflux.service;

import com.spring.webflux.dto.TaskRequest;
import com.spring.webflux.dto.TaskResponse;
import com.spring.webflux.model.Task;
import com.spring.webflux.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

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
    public Flux<List<TaskResponse>> findAll() {
        return null;
    }

    @Override
    public Mono<TaskResponse> findTaskById(Long id) {
        return null;
    }

    @Override
    public Mono<TaskResponse> updateTaskById(Long id, TaskRequest taskRequest) {
        return null;
    }

    @Override
    public Mono<?> deleteTaskById(Long id) {
        return null;
    }
}
