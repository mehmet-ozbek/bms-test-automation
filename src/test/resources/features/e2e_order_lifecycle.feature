@e2e
Feature: End to End Order Lifecycle
  As an admin, I want to manage the complete lifecycle of an order
  from creation to delivery and payment.

  @phase1
  Scenario: Phase 1 - Create Order and Bypass Customer Entry
    # Login ve Müşteri Seçimi
    Given user opens the login page
    And admin logs in with valid credentials
    When user clicks "Customers" from left menu
    And user searches for customer "TestCompany" and clicks the basket

    # Ön Koşul ve Link Gönderimi
    And cart contains at least one "Sultan Baklava" with packaging "200 gr"
    And user selects "12 hours" and sends the order link

    # Müşteri Ekranı (Bypass) ve Sipariş Onayı
    And user bypasses to the order form
    And user enters desired quantity 5 and places the order
    And user accepts the confirmation pop-up
    Then the order success message should be displayed

    # Geri Dönüş ve Sayaç Kontrolü
    When user navigates back to the Dashboard
    Then the order counter next to "Orders" menu should increase by 1