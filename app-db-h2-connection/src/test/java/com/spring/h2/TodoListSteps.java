package com.spring.h2;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TodoListSteps {
    @Given("I have an empty todo list")
    public void iHaveAnEmptyTodoList() {

    }

    @When("I add a task {string}")
    public void iAddATask(String item) {

    }

    @Then("the task {string} should be in the todo list")
    public void theTaskShouldBeInTheTodoList(String item) {

    }
}
