Feature: Create Profile

  Scenario: Create profile for user coded
    Given a user with ID 3 exists and has no profile yet
    Given I have a token for username "coded"
    When I send a POST request to "/users/v1/kyc"
"""json
   {
  "userId": 3,
  "dateOfBirth": "2002-05-07",
  "nationality": "American",
  "salary": 10000.00
}

    """
    Then the response status should be 200