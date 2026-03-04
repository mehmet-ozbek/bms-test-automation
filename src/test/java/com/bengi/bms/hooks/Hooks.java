package com.bengi.bms.hooks;

import com.bengi.bms.core.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Hooks {

    @Before
    public void setUp() {
        DriverFactory.initDriver();
    }

    @After
    public void tearDown(Scenario scenario) {
        WebDriver driver = DriverFactory.getDriver();

        // ADIM 1: Fail olursa delil topla
        if (scenario.isFailed() && driver != null) {
            try {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "failure-screenshot");

                // YENİ EKLEME: Dosya olarak kaydet (Artifacts için)
                File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                Path destination = Paths.get("target/screenshots/" + scenario.getName().replaceAll(" ", "_") + ".png");
                Files.createDirectories(destination.getParent()); // Klasör yoksa oluştur
                Files.copy(source.toPath(), destination);
            } catch (Exception ignored) {}

            try {
                String html = driver.getPageSource();
                scenario.attach(html.getBytes(StandardCharsets.UTF_8), "text/html", "page-source");
            } catch (Exception ignored) {}
        }

        DriverFactory.quitDriver();
    }
}