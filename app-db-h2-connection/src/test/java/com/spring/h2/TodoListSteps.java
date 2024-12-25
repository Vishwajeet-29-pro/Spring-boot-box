package com.spring.h2;

import com.spring.h2.model.Todo;
import com.spring.h2.repository.TodoRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoListSteps {

    @Autowired
    private TodoRepository todoRepository;

    private Todo todo;

    @Given("I have an empty todo list")
    public void iHaveAnEmptyTodoList() {
        todoRepository.deleteAll();
    }

    @When("I add a task {string}")
    public void iAddATask(String item) {
        todo = new Todo();
        todo.setTodo(item);
        todo.setComplete(false);
        todoRepository.save(todo);
    }

    @Then("the task {string} should be in the todo list")
    public void theTaskShouldBeInTheTodoList(String item) {
        Todo savedTodo = todoRepository.findByTodo(item);
        assertEquals(item, savedTodo.getTodo());

    }
}
