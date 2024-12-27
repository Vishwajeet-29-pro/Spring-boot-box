package com.spring.h2.dto;

import com.spring.h2.model.Todo;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TodoResponse {
    private Long id;
    private String todo;
    private boolean complete;

    public static TodoResponse toResponse(Todo todo) {
        return new TodoResponse(todo.getId(), todo.getTodo(), todo.isComplete());
    }
}
