Feature: User Registration

  Scenario: Successful user registration
    When I send a POST request to "/auth/users/v1/register"
    """JSON
    {
    "username": "haya",
    "password": "88888888"
    }
    """
    Then the response status should be 200
    Then the response should have a token
