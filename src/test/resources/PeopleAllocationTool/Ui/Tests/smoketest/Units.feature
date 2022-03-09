Feature: As A Manager User
  I Want To Access Manage Units Section
  So That I Can View How Units Are Organized

  Scenario: Precondition-Step
    Given I Open The Browser
    And I Navigate To "PAT_page.home" Page
    When I Login With Valid Token
    Then I Verify That I Am Redirected To "PAT_page.home" Page

  Scenario: Units - Check That Units List Is Showed Up
    Given I Navigate To "PAT_page.units" Page
    Then I Verify That "unitsPage.unitsList" Is Displayed

  Scenario: Units - Check Units Details
    When I Click On "unitsPage.unit" Element
    Then I Verify That "unitsPage.unitsDetail" Is Displayed


  Scenario Outline: Units - Check Buttons For Units Tables
    Given I Navigate To "PAT_page.units" Page
    When I Click On "<actions>" Element
    Then I Verify That "<table>" Is Displayed

    Examples:
      | actions                      | table                       |
      | unitsPage.dashboard_button   | unitsPage.dashboard_table   |
      | unitsPage.chart_button       | unitsPage.chart_table       |
      | unitsPage.people_view_button | unitsPage.people_view_table |

  Scenario: Units - HRIS
    Given I Navigate To "PAT_page.units" Page
    When I Click On "unitsPage.unit_tree" Element
    And I Switch To Open Tab
    Then I Verify That I Am Redirected To "_3rdPartyPages.hris" Page

  Scenario: Units - Search For A Unit
    Given I Navigate To "PAT_page.units" Page
    When I Click On "unitsPage.search" Element
    And I Insert Text "U2.5" In The "unitsPage.search" Field
    Then I Verify That "unitsPage.unit" Is Displayed

  Scenario: Units - Pagination Functionality
    Given I Navigate To "PAT_page.units" Page
    When I Click On Button "pagination.buttonChild" Under "pagination.buttonParent" Element
    Then I Verify That "unitsPage.paginationList" Is Displayed

  Scenario: Tear Down
    Given I Close The Browser