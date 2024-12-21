package com.spring.webflux.service;

import com.spring.webflux.dto.TaskRequest;
import com.spring.webflux.dto.TaskResponse;
import com.spring.webflux.model.Status;
import com.spring.webflux.model.Task;
import com.spring.webflux.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
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
}