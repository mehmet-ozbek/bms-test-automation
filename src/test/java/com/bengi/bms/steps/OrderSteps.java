package com.bengi.bms.steps;

import com.bengi.bms.pages.CustomersPage;
import com.bengi.bms.pages.CustomerDraftOrderPage;
import com.bengi.bms.pages.OrderFormPage;
import io.cucumber.java.en.*;
import static org.assertj.core.api.Assertions.assertThat;

public class OrderSteps {

    // İleride buraya Page (Sayfa) sınıflarımızı çağıracağız
    // private final CustomersPage customersPage = new CustomersPage();

    // Sayfa (Page) nesnemizi tanımlıyoruz
    private final CustomersPage customersPage = new CustomersPage();
    private final CustomerDraftOrderPage draftOrderPage = new CustomerDraftOrderPage();
    private final OrderFormPage orderFormPage = new OrderFormPage();

    @When("user searches for customer {string} and clicks the basket")
    public void userSearchesForCustomerAndClicksTheBasket(String customerName) {

        customersPage.searchAndFilterCustomer(customerName);
        customersPage.clickBasket(customerName);
    }

    @And("cart contains at least one {string} with packaging {string}")
    public void cartContainsAtLeastOne(String productName, String packaging) {

        // Burada senin o harika tespitinle oluşturduğumuz Page metodunu çağırıyoruz
        boolean isPresent = draftOrderPage.isProductInCart(productName, packaging);

        // AssertJ ile doğruluyoruz
        assertThat(isPresent)
                .as("Sepette ürün bulunamadı: " + productName + " (" + packaging + ")")
                .isTrue();
    }

    @And("user selects {string} and sends the order link")
    public void userSelectsAndSendsTheOrderLink(String time) {
        // Süre seçimi ve link gönderme // Dropdown'dan süreyi seç
        draftOrderPage.selectTime(time);

        // Gönder butonuna bas
        draftOrderPage.clickSendLink();

    }

    @And("user bypasses to the order form")
    public void userBypassesToTheOrderForm() {
        // Order formuna geçiş (Bypass) Sipariş formuna geçiş butonuna tıkla
        draftOrderPage.bypassToOrderForm();
    }

    @And("user enters desired quantity {int} and places the order")
    public void userEntersDesiredQuantityAndPlacesTheOrder(int targetQuantity) {
        // Sepette varsayılan 1 adet geldiği için, hedeflenen sayıya ulaşmak için (hedef - 1) kere tıklıyoruz
        int clicksNeeded = targetQuantity - 1;
        if (clicksNeeded > 0) {
            orderFormPage.increaseQuantity(clicksNeeded);
        }
        orderFormPage.clickBestellen();
    }

    @And("user accepts the confirmation pop-up")
    public void userAcceptsTheConfirmationPopUp() {
        orderFormPage.acceptJavaScriptAlert();
    }

    @Then("the order success message should be displayed")
    public void theOrderSuccessMessageShouldBeDisplayed() {
        assertThat(orderFormPage.isSuccessMessageVisible())
                .as("Sipariş başarı mesajı ekranda görünmedi!")
                .isTrue();
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