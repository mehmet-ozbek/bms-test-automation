@smoke
Feature: Smoke tests

  Scenario: Smoke - Dashboard opens and main modules are visible
    Given I open the login page
    When I login with valid credentials
    Then dashboard should be visible

    When user clicks "Customers" from left menu
    Then "Customers" page should be opened
    Then Customers list should have at least 1 item

    When user clicks "Orders" from left menu
    Then "Orders" page should be opened
    Then Orders list should have at least 1 item

    When user clicks "Delivery" from left menu
    Then "Delivery" page should be opened

    When user clicks "Payment" from left menu
    Then "Payment" page should be opened

    When user clicks "Bengi BMS" from left menu
    Then "Bengi BMS" page should be opened

    And I logout
    Then I should be redirected to login page