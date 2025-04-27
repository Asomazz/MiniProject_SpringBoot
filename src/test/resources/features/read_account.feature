Feature: Read Account


  Scenario: Read Account should work when logged in
Given I have a token for username "coded"
When I make a GET request to "/users/v1/kyc/1"
Then the response status should be 200