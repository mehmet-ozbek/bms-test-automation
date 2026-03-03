package com.bengi.bms.steps;

import com.bengi.bms.core.ConfigReader;
import com.bengi.bms.pages.DashboardPage;
import com.bengi.bms.pages.LoginPage;
import io.cucumber.java.en.*;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginSteps {

    private final LoginPage loginPage = new LoginPage();
    private final DashboardPage dashboardPage = new DashboardPage();

    @Given("I open the login page")
    public void iOpenTheLoginPage() {
        loginPage.open(ConfigReader.get("base.url"));
        assertThat(loginPage.isDisplayed()).isTrue();
    }

    @When("I login with valid credentials")
    public void iLoginWithValidCredentials() {
        loginPage.login(
                ConfigReader.get("username"),
                ConfigReader.get("password")
        );
    }

    @Then("I should see my user profile name on dashboard")
    public void iShouldSeeMyUserProfileNameOnDashboard() {
        String displayName = ConfigReader.get("user.display.name");
        assertThat(dashboardPage.isLoggedInAs(displayName)).isTrue();
    }
    @Then("dashboard should be visible")
    public void dashboardShouldBeVisible() {
        DashboardPage dashboardPage = new DashboardPage();
        assertThat(dashboardPage.isDashboardLoaded()).isTrue();
    }
    @When("I login with username and password {string}")
    public void iLoginWithUsernameAndPassword(String password) {
        loginPage.login(ConfigReader.get("username"), password);
    }

    @Then("I should see a login error message")
    public void iShouldSeeALoginErrorMessage() {
        assertThat(loginPage.isErrorDisplayed()).isTrue();
        assertThat(loginPage.getErrorMessage()).isEqualTo("Kullanıcı adı veya şifre hatalı");
    }

    @And("I logout")
    public void iLogout(){
        dashboardPage.logout();
    }
    @Then("I should be redirected to login page")
    public void iShouldBeRedirectedToLoginPage(){
        assertThat(loginPage.isDisplayed()).isTrue();
    }
}