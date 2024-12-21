package com.spring.webflux.service;

import com.spring.webflux.dto.TaskRequest;
import com.spring.webflux.dto.TaskResponse;
import com.spring.webflux.exception.TaskNotFoundException;
import com.spring.webflux.model.Status;
import com.spring.webflux.model.Task;
import com.spring.webflux.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    private TaskService taskService;

    private Task task;
    private TaskRequest taskRequest;
    private TaskResponse taskResponse;

    @BeforeEach
    public void setup() {
        taskService = new TaskServiceImpl(taskRepository);
        LocalDateTime fixedTime = LocalDateTime.of(2024, 12, 21, 8, 32, 57);

        task = new Task(
                1L, "Add service layer",
                "Create service layer code with tests", Status.TODO,
                fixedTime, fixedTime);

        taskRequest = new TaskRequest("Add service layer", "Create service layer code with tests", Status.TODO);

        taskResponse = new TaskResponse(1L, "Add service layer",
                "Create service layer code with tests", Status.TODO,
                fixedTime, fixedTime);
    }


    @Test
    void test_create_task_should_return_task_response() {
        when(taskRepository.save(any(Task.class))).thenReturn(Mono.just(task));

        Mono<TaskResponse> createTask = taskService.createTask(taskRequest);

        StepVerifier.create(createTask)
                .expectNext(taskResponse)
                .verifyComplete();

        verify(taskRepository, times(1)).save(any(Task.class));
    }

    @Test
    void test_find_all_task_should_return_list_of_task_response() {
        when(taskRepository.findAll()).thenReturn(Flux.just(task));

        Flux<TaskResponse> taskResponseFlux = taskService.findAll();
        
        StepVerifier.create(taskResponseFlux)
                .consumeNextWith(taskResponse -> {
                    assertEquals("Add service layer", taskResponse.getTitle(), "The title should match");
                })
                .verifyComplete();

        verify(taskRepository, times(1)).findAll();
    }

    @Test
    void test_find_by_id_should_return_task_response_with_id() {
        when(taskRepository.findById(anyLong())).thenReturn(Mono.just(task));

        Mono<TaskResponse> taskResponseMono = taskService.findTaskById(1L);

        StepVerifier.create(taskResponseMono)
                .consumeNextWith(taskResponse -> {
                    assertEquals("Add service layer", taskResponse.getTitle(), "The title should match");
                })
                .verifyComplete();

        verify(taskRepository, times(1)).findById(1L);
    }

    @Test
    void test_if_find_task_by_id_not_found_should_throw_task_not_found_exception() {
        when(taskRepository.findById(1L)).thenReturn(Mono.empty());

        Mono<TaskResponse> taskResponseMono = taskService.findTaskById(1L);

        StepVerifier.create(taskResponseMono)
                .expectErrorMatches(throwable -> throwable instanceof TaskNotFoundException
                    && throwable.getMessage().equals("Task with ID 1 not found"))
                .verify();

        verify(taskRepository, times(1)).findById(1L);
    }

    @Test
    void test_update_task_by_id_should_update_task_and_return_updated_task() {
        Task updatedTask = new Task(1L, "Add Service layer code", "Adding tests for update by id",
                Status.IN_PROGRESS,
                LocalDateTime.of(2024, 12,21, 8, 0, 0),
                LocalDateTime.of(2024, 12,21, 8, 0, 0));
        TaskRequest updateRequest = new TaskRequest("Add Service layer code", "Adding tests for update by id", Status.IN_PROGRESS);

        when(taskRepository.findById(1L)).thenReturn(Mono.just(task));
        when(taskRepository.save(any(Task.class))).thenReturn(Mono.just(updatedTask));

        Mono<TaskResponse> updatedTaskResponse = taskService.updateTaskById(1L, updateRequest);

        StepVerifier.create(updatedTaskResponse)
                .consumeNextWith(taskResponse -> {
                    assertEquals("Add Service layer code", taskResponse.getTitle());
                    assertEquals("Adding tests for update by id", taskResponse.getDescription());
                }).verifyComplete();
        verify(taskRepository, times(1)).findById(1L);
        verify(taskRepository, times(1)).save(any(Task.class));
    }

    @Test
    void test_update_task_by_id_not_found_should_throw_task_not_found_exception() {
        when(taskRepository.findById(1L)).thenReturn(Mono.empty());

        Mono<TaskResponse> updatedTaskResponse = taskService.updateTaskById(1L, taskRequest);

        StepVerifier.create(updatedTaskResponse)
                .expectErrorMatches(throwable -> throwable instanceof TaskNotFoundException &&
                        throwable.getMessage().equals("Task with ID 1 not found"))
                .verify();

        verify(taskRepository, times(1)).findById(1L);
    }
}
