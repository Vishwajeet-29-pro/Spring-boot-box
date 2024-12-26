package com.spring.h2;

import com.spring.h2.model.Todo;
import com.spring.h2.repository.TodoRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TodoListSteps {

    @Autowired
    private TodoRepository todoRepository;

    private Todo todo;
    private List<Todo> todos;

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


    @Given("I have a list of todos in the database")
    public void iHaveAListOfTodosInTheDatabase() {
        todoRepository.save(new Todo(null, "Task 1", true));
        todoRepository.save(new Todo(null, "Task 2", false));
    }

    @When("I retrieve all todos")
    public void iRetrieveAllTodos() {
        todos = todoRepository.findAll();
    }

    @Then("I should receive the complete list of todos")
    public void iShouldReceiveTheCompleteListOfTodos() {
        assertEquals(2, todos.size());
        assertEquals("Task 1", todos.getFirst().getTodo());
        assertEquals("Task 2", todos.getLast().getTodo());
        assertTrue(todos.getFirst().isComplete());
        assertFalse(todos.get(1).isComplete());
    }
}
