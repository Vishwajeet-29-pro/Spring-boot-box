package com.spring.h2.dto;

import com.spring.h2.model.Todo;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TodoRequest {
    private String todo;
    private boolean complete;

    public static Todo request_to_todo(TodoRequest request) {
        Todo task = new Todo();
        task.setTodo(request.getTodo());
        task.setComplete(request.isComplete());
        return task;
    }
}
