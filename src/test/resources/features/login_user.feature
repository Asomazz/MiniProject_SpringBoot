Feature: User Login

  Scenario: Successful User Login
    When I send a POST request to "/auth/users/v1/login"
"""JSON
    {
    "username": "fatma113",
    "password": "88888888"
    }
    """
    Then the response status should be 200
    Then the response should have a token