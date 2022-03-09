Feature: POST Create Project

  Background: Precondition - Generate Authorization Token
    Given I Have An Authorization Token

  Scenario: Create Project
    Given I Make A "POST" Request For "endpoints.withoutParam.postProjects" With Specifications "specWithBody" And Body "createProjectBody"
    Then I Verify That the Status Code Is "201"

