package com.spring.h2.controller;

import com.spring.h2.dto.ErrorResponse;
import com.spring.h2.dto.ProductResponse;
import com.spring.h2.dto.TodoRequest;
import com.spring.h2.dto.TodoResponse;
import com.spring.h2.model.Todo;
import com.spring.h2.service.TodoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Create a new TODO")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "TODO added successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TodoResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input provided")
    })
    @PostMapping
    public ResponseEntity<TodoResponse> saveTodo(@RequestBody TodoRequest todoRequest) {
        return new ResponseEntity<>(todoService.saveTodo(todoRequest), HttpStatus.CREATED);
    }

    @Operation(summary = "Retrieve all Todos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todos retrieved successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TodoResponse.class)))
    })
    @GetMapping
    public ResponseEntity<List<TodoResponse>> retrieveAllTodos() {
        return ResponseEntity.ok(todoService.retrieveAll());
    }

    @Operation(summary = "Retrieve a Todo by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todo retrieved successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TodoResponse.class))),
            @ApiResponse(responseCode = "404", description = "Todo not found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<TodoResponse> retrieveTodoById(@PathVariable Long id) {
        return ResponseEntity.ok(todoService.getTodoById(id));
    }

    @Operation(summary = "Update a Todo by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todo updated successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TodoResponse.class))),
            @ApiResponse(responseCode = "404", description = "Todo not found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<TodoResponse> updateTodoById(@PathVariable Long id, @RequestBody TodoRequest todoRequest) {
        return ResponseEntity.ok(todoService.updateTodoById(id, todoRequest));
    }

    @Operation(summary = "Delete a product by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Product deleted successfully"),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodoById(@PathVariable Long id) throws NoSuchFieldException {
        todoService.deleteTodoById(id);
        return ResponseEntity.noContent().build();
    }
}
