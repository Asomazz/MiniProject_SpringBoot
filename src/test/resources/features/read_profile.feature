Feature: Read Profile


  Scenario: Read Profile should work when logged in
    Given the user with ID 2 already has a KYC profile
    Given I have a token for username "coded"
    When I make a GET request to "/users/v1/kyc/2"
    Then the response status should be 200