Feature: Login

  @login
  Scenario: User can log in successfully
    Given user opens the login page
    When user logs in with valid credentials
    Then user should see his profile name on dashboard

  @negative
  Scenario: User cannot log in with invalid password
    Given user opens the login page
    When user logs in with username and password "wrongpass123"
    Then user should see a login error message

  @logout
  Scenario: User can log out successfully
    Given user opens the login page
    When user logs in with valid credentials
    And user logs out
    Then user should be redirected to login page