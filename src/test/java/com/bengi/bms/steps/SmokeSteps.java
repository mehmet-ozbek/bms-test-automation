package com.bengi.bms.steps;

import com.bengi.bms.components.NavigationMenu;
import com.bengi.bms.core.DriverFactory;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import com.bengi.bms.pages.DashboardPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SmokeSteps {
    @When("user clicks {string} from left menu")
    public void user_clicks_from_left_menu(String menuName) {
        WebDriver driver = DriverFactory.getDriver();

        try {
            // 1. ADIM: Hamburger menü (üç çizgi) butonunu bul
            // Bu locator senin sayfandaki o üç çizgiye karşılık gelir
            WebElement hamburger = driver.findElement(By.cssSelector(".navbar-toggler, .menu-toggle, .hamburger-menu"));

            // 2. ADIM: Eğer bu buton ekranda "görünür" ise tıkla (yani menü gizlenmişse)
            if (hamburger.isDisplayed()) {
                hamburger.click();
                Thread.sleep(500); // Menünün animasyonla açılması için kısa bir bekleme
            }
        } catch (Exception e) {
            // Buton bulunamazsa veya görünür değilse zaten menü açıktır, devam et
        }

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
