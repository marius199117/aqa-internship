Feature: PUT Update Project

  Background: Precondition - Generate Authorization Token And Create A New Project
    Given I Have An Authorization Token
    And I Make A "POST" Request For "endpoints.withoutParam.postProjects" With Specifications "specWithBody" And Body "createProjectBody"

  Scenario: Update Project
    Given I Make A "PUT" Request For "endpoints.withParam.Projects" With Specifications "specWithBodyAndParam" And Body "updateProjectBody"
    Then I Verify That the Status Code Is "204"