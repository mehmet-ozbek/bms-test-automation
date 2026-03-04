package com.bengi.bms.pages;

import com.bengi.bms.core.BasePage;
import org.openqa.selenium.By;
import com.bengi.bms.components.NavigationMenu;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DashboardPage extends BasePage {

    // CI ortamında metin eşleşmesini garantilemek için contains kullandık
    private final By dashboardHeader = By.xpath("//*[contains(text(),'Bengi BMS')]");

    private final By userDropdownToggle =
            By.xpath("//button[contains(@onclick,'toggleUserDropdown')]");

    private final By logoutButton =
            By.xpath("//form[@action='/Auth/Logout']//button");

    private By userProfileName(String displayName) {
        // normalize-space yerine daha esnek olan contains metodu
        return By.xpath("//button//p[contains(.,'" + displayName + "')]");
    }

    public boolean isDisplayed() {
        return isVisible(dashboardHeader);
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
        // Başlıklar için normalize-space yerine contains her zaman daha güvenlidir
        By ordersHeader = By.xpath("//h1[contains(.,'Orders')]");
        return isVisible(ordersHeader);
    }

    public boolean hasAnyOrder() {
        By orderDetailsLinks = By.xpath("//a[starts-with(@href,'/Order/Details/') and contains(.,'View')]");
        return !driver.findElements(orderDetailsLinks).isEmpty();
    }

    public boolean isCustomersPageOpened() {
        By customersHeader = By.xpath("//h1[contains(.,'Customers')]");
        return isVisible(customersHeader);
    }