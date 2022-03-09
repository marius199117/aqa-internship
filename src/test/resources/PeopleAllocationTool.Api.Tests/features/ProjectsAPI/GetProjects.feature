Feature: GET Projects

  Background: Precondition -Generate Authorization Token
    Given I Have An Authorization Token

  Scenario: Get Projects
    Given I Make A "GET" Request For "endpoints.withParam.Projects" With "basic" Specifications
    Then I Verify That the Status Code Is "200"