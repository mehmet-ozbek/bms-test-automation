package com.bengi.bms.core;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public abstract class BasePage {

    protected final WebDriver driver;
    protected final WebDriverWait wait;

    protected BasePage() {
        this.driver = DriverFactory.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(12));
    }

    public void open(String url) {
        driver.get(url);
    }

    protected WebElement find(By locator) {
        return wait.until(visibilityOfElementLocated(locator));
    }

    protected void click(By locator) {

        WebElement element = wait.until(visibilityOfElementLocated(locator));

        try {
            wait.until(elementToBeClickable(locator)).click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    protected void type(By locator, String text) {
        WebElement el = find(locator);
        el.clear();
        el.sendKeys(text);
    }

    protected String text(By locator) {
        return find(locator).getText();
    }

    protected boolean isVisible(By locator) {
        try {
            return find(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}