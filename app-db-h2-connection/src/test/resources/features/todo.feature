Feature: CRUD operations on TODO

  Scenario: Save a todo
    Given the following todo details:
      | todo                    | completed |
      | Learn BDD               | false     |
      | Implement BDD in spring | false     |
    When I send a POST request to "/todos" with this todo
    Then the response should contain the saved todo with an auto-generated ID
    And the status code should be 201
    And the saved todo should exists in the database

  Scenario: Retrieve all todos
    Given the following todo details:
      | todo                    | completed |
      | Learn BDD               | false     |
      | Implement BDD in spring | false     |
    When I send a GET request to "/todos"
    Then the response should contain the following todos:
      | todo                    | completed |
      | Learn BDD               | false     |
      | Implement BDD in spring | false     |
    And the response header "Content-Type" should be "application/json"
    And the status code should be 200
    And the size of list should be 2

  Scenario: Find a todo by ID
    Given the following todo details:
      | todo                    | completed |
      | Learn BDD               | false     |
      | Implement BDD in spring | false     |
    When I send a POST request to "/todos" with this todo
    And I retrieve the ID of the saved todo
    When I send a GET request to "/todos/{id}" with the retrieved ID
    Then the response should contain the saved todo
