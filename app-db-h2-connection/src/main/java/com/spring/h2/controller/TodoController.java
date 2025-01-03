package com.spring.h2.controller;

import com.spring.h2.dto.TodoRequest;
import com.spring.h2.dto.TodoResponse;
import com.spring.h2.model.Todo;
import com.spring.h2.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<TodoResponse> saveTodo(@RequestBody TodoRequest todoRequest) {
        return new ResponseEntity<>(todoService.saveTodo(todoRequest), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TodoResponse>> retrieveAllTodos() {
        return ResponseEntity.ok(todoService.retrieveAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoResponse> retrieveTodoById(@PathVariable Long id) {
        return ResponseEntity.ok(todoService.getTodoById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoResponse> updateTodoById(@PathVariable Long id, @RequestBody TodoRequest todoRequest) {
        return ResponseEntity.ok(todoService.updateTodoById(id, todoRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodoById(@PathVariable Long id) throws NoSuchFieldException {
        todoService.deleteTodoById(id);
        return ResponseEntity.noContent().build();
    }
}
