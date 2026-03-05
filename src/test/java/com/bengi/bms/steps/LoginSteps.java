package com.bengi.bms.steps;

import com.bengi.bms.core.ConfigReader;
import com.bengi.bms.pages.DashboardPage;
import com.bengi.bms.pages.LoginPage;
import io.cucumber.java.en.*;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginSteps {

    private final LoginPage loginPage = new LoginPage();
    private final DashboardPage dashboardPage = new DashboardPage();

    @Given("user opens the login page")
    public void userOpensTheLoginPage() {
        loginPage.open(ConfigReader.get("base.url"));
        assertThat(loginPage.isDisplayed()).isTrue();
    }

    // PRO DOKUNUŞ: {word} sayesinde feature dosyasında "admin logs in..."
    // veya "user logs in..." yazman fark etmez, ikisi de burayı çalıştırır!
    @When("{word} logs in with valid credentials")
    public void userLogsInWithValidCredentials(String role) {
        loginPage.login(
                ConfigReader.get("username"),
                ConfigReader.get("password")
        );
    }

    @Then("user should see his profile name on dashboard")
    public void userShouldSeeHisProfileNameOnDashboard() {
        String displayName = ConfigReader.get("user.display.name");
        assertThat(dashboardPage.isLoggedInAs(displayName)).isTrue();
    }

    @Then("dashboard should be visible")
    public void dashboardShouldBeVisible() {
        assertThat(dashboardPage.isDashboardLoaded()).isTrue();
    }

    @When("user logs in with username and password {string}")
    public void userLogsInWithUsernameAndPassword(String password) {
        loginPage.login(ConfigReader.get("username"), password);
    }

    @Then("user should see a login error message")
    public void userShouldSeeALoginErrorMessage() {
        assertThat(loginPage.isErrorDisplayed()).isTrue();
        assertThat(loginPage.getErrorMessage()).isEqualTo("Kullanıcı adı veya şifre hatalı");
    }

    @And("user logs out")
    public void userLogsOut(){
        dashboardPage.logout();
    }

    @Then("user should be redirected to login page")
    public void userShouldBeRedirectedToLoginPage(){
        assertThat(loginPage.isDisplayed()).isTrue();
    }
}