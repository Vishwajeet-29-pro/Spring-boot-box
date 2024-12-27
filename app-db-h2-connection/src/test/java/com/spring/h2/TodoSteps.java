package com.spring.h2;

import com.spring.h2.dto.TodoRequest;
import com.spring.h2.dto.TodoResponse;
import com.spring.h2.model.Todo;
import com.spring.h2.service.TodoService;
import io.cucumber.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

public class TodoSteps {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private TodoService todoService;

    private Todo todo;
    private TodoRequest todoRequest;
    private ResponseEntity<TodoResponse> todoResponse;

    @Given("the following todo details:")
    public void theFollowingTodoDetails() {
    }
}
