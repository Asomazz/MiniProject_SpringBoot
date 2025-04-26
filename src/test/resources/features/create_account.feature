Feature: Create Account

Scenario: Create account for user coded
Given I have a token for username "coded"
When I send a POST request to "/accounts/v1/accounts"
"""json
   {
  "userId": 2,
  "accountNumber": "placeholder",
  "initialBalance": 10000.00
}

    """
Then the response status should be 200


