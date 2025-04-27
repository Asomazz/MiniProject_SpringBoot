Feature: Close Account

  Scenario: Successfully close an active account
    Given I have a token for username "testuser"
    When I send a POST request to "/accounts/v1/accounts/ACC12345/close" without a body
    Then the response status should be 200