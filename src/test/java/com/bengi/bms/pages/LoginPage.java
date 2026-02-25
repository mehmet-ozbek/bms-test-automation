package com.bengi.bms.pages;

import com.bengi.bms.core.BasePage;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {

    // TODO: Bunları senin login ekranına göre netleştireceğiz
    private final By usernameInput = By.id("Username");
    private final By passwordInput = By.id("Password");
    private final By loginButton = By.xpath("(//button[@type='submit'])[1]");

    // Login ekranında bir başlık/etiket varsa onunla doğrulama yapabiliriz
    private final By loginHeader = By.xpath("//h3[normalize-space()='Hoş Geldiniz']");
    // TODO: Hata mesajı elementini inspect ile netleştireceğiz
    private final By loginErrorMessage = By.xpath("//form//div[contains(@class,'validation-summary-errors')]//li");

    public boolean isDisplayed() {
        return isVisible(loginHeader);
    }

    public void login(String username, String password) {
        type(usernameInput, username);
        type(passwordInput, password);
        click(loginButton);
    }
    public boolean isErrorDisplayed() {
        return isVisible(loginErrorMessage);
    }

    public String getErrorMessage() {
        return text(loginErrorMessage);
    }
}