Feature: CRUD operations on TODO

  Scenario: Save a todo
    Given the following todo details:
      | todo      | completed |
      | Learn BDD | false     |
    When I send a POST request to "/todos" with this todo
    Then the response should contain the saved todo with an auto-generated ID
    And the status code should be 201
    And the saved todo should exists in the database