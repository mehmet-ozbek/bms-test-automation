package com.bengi.bms.pages;

import com.bengi.bms.core.BasePage;
import org.openqa.selenium.By;
import com.bengi.bms.components.NavigationMenu;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DashboardPage extends BasePage {

    private final By dashboardHeader = By.xpath("//*[contains(text(),'Bengi BMS')] | //h1[contains(.,'Dashboard')]");
    private final By userDropdownToggle = By.xpath("//button[contains(@onclick,'toggleUserDropdown')]");
    private final By logoutButton = By.xpath("//form[@action='/Auth/Logout']//button");
    private final By dailyRevenueCard = By.xpath("//*[contains(.,'Daily Revenue')]");
    private final By totalOrdersCard  = By.xpath("//*[contains(.,'Total Orders')]");

    private By userProfileName(String displayName) {
        return By.xpath("//button//p[contains(.,'" + displayName + "')]");
    }

    public boolean isDisplayed() {
        // Başlık veya Dashboard kartlarından biri görünürse sayfa açılmış demektir
        return isVisible(dashboardHeader) || isVisible(dailyRevenueCard);
    }

    public boolean isLoggedInAs(String displayName) {
        return isVisible(userProfileName(displayName));
    }

    public void logout() {
        click(userDropdownToggle);
        wait.until(ExpectedConditions.visibilityOfElementLocated(logoutButton));
        click(logoutButton);
        wait.until(ExpectedConditions.urlContains("/Auth/Login"));
    }

    public void goToOrders(){
        NavigationMenu menu = new NavigationMenu();
        menu.clickMenu("Orders");
    }

    public boolean isOrdersPageOpened() {
        return isVisible(By.xpath("//h1[contains(.,'Orders')]"));
    }

    public boolean hasAnyOrder() {
        By orderDetailsLinks = By.xpath("//a[starts-with(@href,'/Order/Details/') and contains(.,'View')]");
        return !driver.findElements(orderDetailsLinks).isEmpty();
    }

    public boolean isCustomersPageOpened() {
        return isVisible(By.xpath("//h1[contains(.,'Customers')]"));
    }

    public boolean isDeliveryPageOpened() {
        return isVisible(By.xpath("//h1[contains(.,'Delivery')]"));
    }

    public boolean isPaymentPageOpened() {
        return isVisible(By.xpath("//h1[contains(.,'Payment')]"));
    }

    public boolean hasAnyCustomer() {
        By customerCardTitle = By.xpath("//h3[contains(@class,'font-semibold')]");
        return !driver.findElements(customerCardTitle).isEmpty();
    }

    public void goToDashboard() {
        NavigationMenu menu = new NavigationMenu();
        menu.clickMenu("Bengi BMS");
    }

    public boolean areDashboardWidgetsVisible() {
        return isVisible(dailyRevenueCard) && isVisible(totalOrdersCard);
    }

    public boolean isDashboardLoaded() {
        return isVisible(dailyRevenueCard) && isVisible(totalOrdersCard);
    }
}