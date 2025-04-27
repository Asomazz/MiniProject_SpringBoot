Feature: Transfer Money

  Scenario: Transfer money from an account to another
    Given I have a token for username "coded"
    When I send a POST request to "/accounts/v1/accounts/transfer"
  """JSON
  {
    "sourceAccountNumber": "6399442994",
    "destinationAccountNumber": "9445778879",
    "amount": 100
  }
  """
    Then the response status should be 200
    And the response should contain "newBalance"
