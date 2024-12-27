package com.spring.h2.service;

import com.spring.h2.dto.TodoRequest;
import com.spring.h2.dto.TodoResponse;
import com.spring.h2.model.Todo;
import com.spring.h2.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    @Override
    public TodoResponse saveTodo(TodoRequest todoRequest) {
        Todo response = todoRepository.save(TodoRequest.request_to_todo(todoRequest));
        return TodoResponse.toResponse(response);
    }

    @Override
    public TodoResponse getTodoById(Long id) {
        Todo retrievedTodo = todoRepository.findById(id).orElseThrow();
        return TodoResponse.toResponse(retrievedTodo);
    }
}
