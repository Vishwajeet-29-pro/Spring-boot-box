package com.spring.h2.service;

import com.spring.h2.dto.TodoRequest;
import com.spring.h2.dto.TodoResponse;

public interface TodoService {
    TodoResponse saveTodo(TodoRequest todoRequest);
    TodoResponse getTodoById(Long id);
}
