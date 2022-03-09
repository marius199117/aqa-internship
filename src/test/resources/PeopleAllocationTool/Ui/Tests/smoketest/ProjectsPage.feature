Feature: As A Manager User
  I Want To Access Manage Projects Section
  So That I Can Manage My Projects

  Scenario: Projects - Precondition Step
    Given I Open The Browser
    And I Navigate To "PAT_page.login" Page
    When I Login With Valid Token
    Then I Verify That I Am Redirected To "PAT_page.home" Page

  Scenario: Projects - Projects Page Details
    Given I Navigate To "PAT_page.projects" Page
    Then I Verify That "projectsPage.projectsList" Is Displayed

  Scenario Outline: Projects - Manage Project Actions
    Given I Navigate To "PAT_page.projects" Page
    When I Click On "<actions>" Element
    Then I Verify That "<title>" Is Displayed

    Examples:
      | actions                          | title                           |
      | projectsPage.project_button      | projectsPage.project_title      |
      | projectsPage.manage_team_button  | projectsPage.manage_team_title  |
      | projectsPage.dashboardAVG_button | projectsPage.dashboardAVG_title |
      | projectsPage.dashboardPTO_button | projectsPage.dashboardPTO_title |

  Scenario: Projects - Search For A Project
    Given I Navigate To "PAT_page.projects" Page
    When I Click On "projectsPage.search" Element
    And I Insert Text "Alber" In The "projectsPage.search" Field
    Then I Verify That "projectsPage.projectName" Is Displayed

  Scenario: Projects - Pagination
    Given I Navigate To "PAT_page.projects" Page
    When I Click On Button "pagination.buttonChild" Under "pagination.buttonParent" Element
    Then I Verify That "projectsPage.paginationList" Is Displayed

  Scenario: Tear Down
    Given I Close The Browser