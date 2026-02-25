package com.bengi.bms.pages;

import com.bengi.bms.core.BasePage;
import org.openqa.selenium.By;

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
}