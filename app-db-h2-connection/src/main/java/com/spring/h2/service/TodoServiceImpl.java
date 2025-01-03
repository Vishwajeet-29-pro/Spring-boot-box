package com.spring.h2.service;

import com.spring.h2.dto.TodoRequest;
import com.spring.h2.dto.TodoResponse;
import com.spring.h2.exception.TodoNotFoundException;
import com.spring.h2.model.Todo;
import com.spring.h2.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
        Todo retrievedTodo = todoRepository.findById(id).orElseThrow(
                () ->  new TodoNotFoundException("Todo with id "+id+" not found.")
        );
        return TodoResponse.toResponse(retrievedTodo);
    }

    @Override
    public List<TodoResponse> retrieveAll() {
        List<Todo> todos = todoRepository.findAll();
        return todos.stream().map(TodoResponse::toResponse).toList();
    }

    @Override
    public TodoResponse updateTodoById(Long id, TodoRequest todoRequest) {
        Todo updateTodo = todoRepository.findById(id).orElseThrow(() ->
                new TodoNotFoundException("Todo with id "+id+" not found."));
        updateTodo.setTodo(todoRequest.getTodo());
        updateTodo.setComplete(todoRequest.isComplete());

        Todo todo = todoRepository.save(updateTodo);
        return TodoResponse.toResponse(todo);
    }

    @Override
    public void deleteTodoById(Long id) {
        if (!todoRepository.existsById(id)) {
            throw new TodoNotFoundException("Todo with id "+id+" not found");
        }
        todoRepository.deleteById(id);
    }
}
