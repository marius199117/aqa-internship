Feature: As An User I Want To Manage Employees

  Background: Precondition
    Given I Have An Authorization Token


  Scenario: GET Employees
    Given I Make A "GET" Request For "endpoints.withoutParam.getEmployees" With "basic" Specifications
    Then I Verify That the Status Code Is "200"