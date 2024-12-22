package com.spring.webflux.controller;

import com.spring.webflux.dto.TaskRequest;
import com.spring.webflux.dto.TaskResponse;
import com.spring.webflux.model.Status;
import com.spring.webflux.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@WebFluxTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockitoBean
    private TaskService taskService;

    private TaskRequest taskRequest;
    private TaskResponse taskResponse;

    @BeforeEach
    public void setup() {
        LocalDateTime fixedTime = LocalDateTime.of(2024, 12, 21, 8, 32, 57);
        taskRequest = new TaskRequest("Add Controller layer code", "Add endpoints and test for controller", Status.TODO);
        taskResponse = new TaskResponse(1L, "Add Controller layer code", "Add endpoints and test for controller",
                Status.TODO, fixedTime, fixedTime);
    }

    @Test
    void test_create_task_should_create_task_and_return_created_201() {
        when(taskService.createTask(any(TaskRequest.class))).thenReturn(Mono.just(taskResponse));

        webTestClient.post()
                .uri("/api/v1/tasks")
                .bodyValue(taskRequest)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(TaskRequest.class);
    }

    @Test
    void test_find_all_task_should_returns_tasks_and_200() {
        when(taskService.findAll()).thenReturn(Flux.just(taskResponse));

        webTestClient.get()
                .uri("/api/v1/tasks")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(TaskResponse.class)
                .hasSize(1);
    }

    @Test
    void test_find_by_id_should_returns_task_and_200() {
        when(taskService.findTaskById(anyLong())).thenReturn(Mono.just(taskResponse));

        webTestClient.get()
                .uri("/api/v1/tasks/{id}", 1L)
                .exchange()
                .expectBody(TaskResponse.class);
    }

    @Test
    void test_update_by_id_should_update_task_and_return_200() {
        when(taskService.updateTaskById(anyLong(), eq(taskRequest))).thenReturn(Mono.just(taskResponse));

        webTestClient.put()
                .uri("/api/v1/tasks/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(taskRequest)
                .exchange()
                .expectStatus().isOk()
                .expectBody(TaskResponse.class);
    }
}