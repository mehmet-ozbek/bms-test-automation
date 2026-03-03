package com.bengi.bms.components;

import com.bengi.bms.core.BasePage;
import org.openqa.selenium.By;

public class NavigationMenu extends BasePage {

    public NavigationMenu() {
        super();
    }
    public void clickMenu(String menuName) {
        // span label yerine onu saran tıklanabilir linke tıkla
        By menuLocator = By.xpath("//span[normalize-space()='" + menuName + "']/ancestor::a[1]");
        click(menuLocator); // BasePage.click -> wait ile tıklar
    }
}