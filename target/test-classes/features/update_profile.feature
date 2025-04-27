Feature: Update Profile

  Scenario: Update existing profile for user coded
    Given I have a token for username "coded"
    Given the user with ID 2 already has a KYC profile
    When I send a POST request to "/users/v1/kyc"
"""json
   {
  "userId": 2,
  "dateOfBirth": "2002-05-07",
  "nationality": "Canadian",
  "salary": 1000.00
}

    """
    Then the response status should be 200
