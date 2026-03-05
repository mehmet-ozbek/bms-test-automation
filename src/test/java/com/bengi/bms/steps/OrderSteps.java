package com.bengi.bms.steps;

import io.cucumber.java.en.*;

public class OrderSteps {

    // İleride buraya Page (Sayfa) sınıflarımızı çağıracağız
    // private final CustomersPage customersPage = new CustomersPage();

    @When("user searches for customer {string} and clicks the basket")
    public void userSearchesForCustomerAndClicksTheBasket(String customerName) {
        // TODO: Müşteri arama ve sepete tıklama kodları gelecek
        System.out.println(customerName + " aranıyor...");
    }

    @And("cart contains at least one {string}")
    public void cartContainsAtLeastOne(String productName) {
        // TODO: Sepette ürün var mı kontrolü
    }

    @And("user selects {string} and sends the order link")
    public void userSelectsAndSendsTheOrderLink(String time) {
        // TODO: Süre seçimi ve link gönderme
    }

    @And("user bypasses to the order form")
    public void userBypassesToTheOrderForm() {
        // TODO: Order formuna geçiş (Bypass)
    }

    @And("user enters desired quantity and places the order")
    public void userEntersDesiredQuantityAndPlacesTheOrder() {
        // TODO: Miktar girip sipariş verme
    }

    @And("user accepts the confirmation pop-up")
    public void userAcceptsTheConfirmationPopUp() {
        // TODO: Pop-up onaylama
    }

    @Then("the order success message should be displayed")
    public void theOrderSuccessMessageShouldBeDisplayed() {
        // TODO: Sipariş başarılı mesajı doğrulaması
    }

    @When("user navigates back to the Dashboard")
    public void userNavigatesBackToTheDashboard() {
        // TODO: Dashboard'a geri dönüş
    }

    @Then("the order counter next to {string} menu should increase by {int}")
    public void theOrderCounterNextToMenuShouldIncreaseBy(String menuName, int increaseAmount) {
        // TODO: Menüdeki sayacın artış kontrolü
    }
}