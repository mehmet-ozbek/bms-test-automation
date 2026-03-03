package com.bengi.bms.pages;

import com.bengi.bms.core.BasePage;
import org.openqa.selenium.By;
import com.bengi.bms.components.NavigationMenu;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DashboardPage extends BasePage {

    private final By dashboardHeader = By.xpath("//*[contains(normalize-space(),'Bengi BMS')]");

    // Sağ üstte kullanıcı menüsünü açan toggle buton
    private final By userDropdownToggle =
            By.xpath("//button[contains(@onclick,'toggleUserDropdown')]");

    // Açılan menüdeki logout butonu
    private final By logoutButton =
            By.xpath("//form[@action='/Auth/Logout']//button");
    private By userProfileName(String displayName) {
        return By.xpath("//button//p[normalize-space()='" + displayName + "']");
    }

    public boolean isDisplayed() {
        return isVisible(dashboardHeader);
    }

    public boolean isLoggedInAs(String displayName) {
        return isVisible(userProfileName(displayName));
    }
    public void logout() {
        click(userDropdownToggle);
        click(logoutButton);
    }
    public void goToOrders(){
        NavigationMenu menu = new NavigationMenu();
        menu.clickMenu("Orders");
    }
    public boolean isOrdersPageOpened() {
        By ordersHeader = By.xpath("//h1[normalize-space()='Orders']");
        return isVisible(ordersHeader);
    }
    public boolean hasAnyOrder() {
        By orderDetailsLinks = By.xpath("//a[starts-with(@href,'/Order/Details/') and normalize-space()='View']");
        return !driver.findElements(orderDetailsLinks).isEmpty();
    }
    public boolean isCustomersPageOpened() {
        By customersHeader = By.xpath("//h1[normalize-space()='Customers']");
        return isVisible(customersHeader);
    }
    public boolean isDeliveryPageOpened() {
        By deliveryHeader = By.xpath("//h1[normalize-space()='Delivery Module']");
        return isVisible(deliveryHeader);
    }
    public boolean isPaymentPageOpened() {
        By paymentHeader = By.xpath("//h1[normalize-space()='All Payments']");
        return isVisible(paymentHeader);
    }
    public boolean hasAnyCustomer() {
        By customerCardTitle = By.xpath("//h3[contains(@class,'font-semibold') and normalize-space()!='']");
        return !driver.findElements(customerCardTitle).isEmpty();
    }
    public void goToDashboard() {
        NavigationMenu menu = new NavigationMenu();
        menu.clickMenu("Bengi BMS");
    }
    private final By dailyRevenueCard = By.xpath("//*[normalize-space()='Daily Revenue']");
    private final By totalOrdersCard  = By.xpath("//*[normalize-space()='Total Orders']");

    public boolean areDashboardWidgetsVisible() {
        return isVisible(dailyRevenueCard) && isVisible(totalOrdersCard);
    }
    public boolean isDashboardLoaded() {
        return isVisible(dailyRevenueCard) && isVisible(totalOrdersCard);
    }
}