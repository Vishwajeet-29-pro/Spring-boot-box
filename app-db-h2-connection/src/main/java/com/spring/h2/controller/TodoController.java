package com.spring.h2.controller;

import com.spring.h2.dto.TodoRequest;
import com.spring.h2.dto.TodoResponse;
import com.spring.h2.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<TodoResponse> saveTodo(@RequestBody TodoRequest todoRequest) {
        return new ResponseEntity<>(todoService.saveTodo(todoRequest), HttpStatus.CREATED);
    }
}
