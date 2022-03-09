Feature: As A Manager User
  I Want To Be Able To Login On PAT
  So That I Can View Home Page, Header and Footer Details

  Scenario: HomePage - Precondition-Step
    Given I Open The Browser
    And I Navigate To "PAT_page.home" Page
    When I Login With Valid Token
    Then I Verify That I Am Redirected To "PAT_page.home" Page

  Scenario: HomePage - Header Home Option
    When I Click On "header.homePage" Element
    Then I Verify That "homePage.homeElementsPage" Is Displayed

  Scenario Outline: HomePage - Header Navbar Buttons
    When I Click On "<navBarElement>" Element
    Then I Verify That I Am Redirected To "<navBar>" Page

    Examples:
      | navBarElement             | navBar                 |
      | homePage.projectsBtn      | PAT_page.projects      |
      | homePage.unitsBtn         | PAT_page.units         |
      | homePage.businessUnitsBtn | PAT_page.businessUnits |

  Scenario:  HomePage - Check Footer Is Displayed
    Given I Navigate To "PAT_page.home" Page
    Then I Verify That "footer.footerElements" Is Displayed

  Scenario: HomePage - Check Header About Page
    When I Click On "header.menu" Element
    And I Click On "header.about" Element
    Then I Verify That I Am Redirected To "PAT_page.about" Page

  Scenario: HomePage - Header Menu Option
    When I Click On "header.menu" Element
    And I Click On "header.logOut" Element
    Then I Verify That I Am Logged Out

  Scenario: Tear Down
    Given I Close The Browser
