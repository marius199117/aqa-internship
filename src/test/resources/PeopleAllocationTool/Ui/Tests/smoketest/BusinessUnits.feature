Feature: As A Manager User
  I Want To Access Business Units Section
  So That I Can View How Business Units Are Organized

  Scenario: BusinessUnitsPage - Precondition-Step
    Given I Open The Browser
    And I Navigate To "PAT_page.login" Page
    When I Login With Valid Token
    Then I Verify That I Am Redirected To "PAT_page.home" Page

  Scenario: BusinessUnitsPage - Check BusinessUnits is Showed Up
    Given I Navigate To "PAT_page.businessUnits" Page
    Then I Verify That "businessUnitsPage.BUList" Is Displayed

  Scenario: BusinessUnitsPage - Check BusinessUnits Details
    When I Click On "businessUnitsPage.businessUnit" Element
    Then I Verify That "businessUnitsPage.businessUnitsDetails" Is Displayed

  Scenario Outline: BusinessUnitsPage - Verify "BU2" buttons are functional
    Given I Navigate To "PAT_page.businessUnits" Page
    When I Click On "<actions>" Element
    Then I Verify That "<table>" Is Displayed

    Examples:
      | actions                              | table                               |
      | businessUnitsPage.dashboard_button   | businessUnitsPage.dashboard_table   |
      | businessUnitsPage.chart_button       | businessUnitsPage.chart_table       |
      | businessUnitsPage.people_view_button | businessUnitsPage.people_view_table |


  Scenario: BusinessUnitsPage - Search for a BusinessUnit
    Given I Navigate To "PAT_page.businessUnits" Page
    When I Click On "businessUnitsPage.search" Element
    And I Insert Text "BU2" In The "businessUnitsPage.search" Field
    Then I Verify That "businessUnitsPage.businessUnitResult" Is Displayed

  Scenario: BusinessUnitsPage - Pagination Functionality
    Given I Navigate To "PAT_page.businessUnits" Page
    When I Click On Button "pagination.buttonChild" Under "pagination.buttonParent" Element
    Then I Verify That "businessUnitsPage.paginationList" Is Displayed

  Scenario: Tear Down
    Given I Close The Browser