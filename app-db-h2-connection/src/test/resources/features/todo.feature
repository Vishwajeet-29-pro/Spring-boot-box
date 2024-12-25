Feature: Manage Todo List
  As a user
  I want to manage my todo list
  So that I can keep track of my tasks

  Scenario: Add a new task
    Given I have an empty todo list
    When I add a task "Complete Test case"
    Then the task "Complete Test case" should be in the todo list