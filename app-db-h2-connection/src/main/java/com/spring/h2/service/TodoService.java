package com.spring.h2.service;

import com.spring.h2.dto.TodoRequest;
import com.spring.h2.dto.TodoResponse;

import java.util.List;

public interface TodoService {
    TodoResponse saveTodo(TodoRequest todoRequest);
    TodoResponse getTodoById(Long id);
    List<TodoResponse> retrieveAll();
    TodoResponse updateTodoById(Long id, TodoRequest todoRequest);
    void deleteTodoById(Long id) throws NoSuchFieldException;
}
