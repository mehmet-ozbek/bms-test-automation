package com.bengi.bms.steps;

import com.bengi.bms.components.NavigationMenu;
import com.bengi.bms.core.DriverFactory;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import com.bengi.bms.pages.DashboardPage;

public class SmokeSteps {
    @When("user clicks {string} from left menu")
    public void user_clicks_from_left_menu(String menuName) {
        NavigationMenu menu = new NavigationMenu();
        menu.clickMenu(menuName);
    }
    @Then("{string} page should be opened")
    public void page_should_be_opened(String pageName) {

        DashboardPage dashboardPage = new DashboardPage();

        boolean result = switch (pageName) {
            case "Bengi BMS" -> dashboardPage.isDisplayed();
            case "Customers" -> dashboardPage.isCustomersPageOpened();
            case "Orders" -> dashboardPage.isOrdersPageOpened();
            case "Delivery" -> dashboardPage.isDeliveryPageOpened();
            case "Payment" -> dashboardPage.isPaymentPageOpened();
            default -> false;
        };

        org.junit.jupiter.api.Assertions.assertTrue(
                result,
                pageName + " page is not opened."
        );
    }
    @Then("Customers list should have at least 1 item")
    public void customers_list_should_have_at_least_1_item() {
        DashboardPage dashboardPage = new DashboardPage();
        org.junit.jupiter.api.Assertions.assertTrue(
                dashboardPage.hasAnyCustomer(),
                "Customers list is empty."
        );
    }
    @Then("Orders list should have at least 1 item")
    public void orders_list_should_have_at_least_1_item() {
        DashboardPage dashboardPage = new DashboardPage();
        org.junit.jupiter.api.Assertions.assertTrue(
                dashboardPage.hasAnyOrder(),
                "Orders list is empty."
        );
    }
}
