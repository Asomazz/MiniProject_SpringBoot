Feature: Create Account


  Scenario: Read Account should work when logged in
Given I have a token for username "coded"
When I make a GET request to "/accounts/v1/accounts"
Then the response status should be 200