Feature: POST Authentication


  Scenario: Create Authentication Token
    Given I Make A "POST" Request For "endpoints.withoutParam.auth" With Specifications "withoutAuth" And Body "authBody"
    Then I Verify That the Status Code Is "200"