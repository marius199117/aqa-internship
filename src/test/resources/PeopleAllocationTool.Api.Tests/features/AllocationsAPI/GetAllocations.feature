Feature: As An User I Want To Manage Allocations

  Background: Precondition
    Given I Have An Authorization Token


  Scenario: GET Allocations
    Given I Make A "GET" Request For "endpoints.getAllocations" With "basicWithParam" Specifications
    Then I Verify That the Status Code Is "200"