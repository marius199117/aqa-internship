Feature: As A User
  I Want To Access Fortech Website
  So That I Can Contact The Company

  Scenario: Home Page - Contact Button
    Given I Open The Browser
    And I Navigate To "fortech_page.root" Page
    When I Click On "fortech.contact_button" Element
    Then I Verify That I Am Redirected To "fortech_fortech.details" Page

  Scenario: Tear Down
    Given I Close The Browser