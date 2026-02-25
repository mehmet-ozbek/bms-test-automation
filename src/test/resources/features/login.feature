Feature: Login

  @smoke
  Scenario: User can login successfully
      Given I open the login page
      When I login with valid credentials
      Then I should see my user profile name on dashboard

    @negative
    Scenario: User cannot login with invalid password
      Given I open the login page
      When I login with username and password "wrongpass123"
      Then I should see a login error message

      @logout
      Scenario: User can logout successfully
        Given I open the login page
        When  I login with valid credentials
        And   I logout
        Then  I should be redirected to login page