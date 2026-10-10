package com.salesforce.tests;

import com.salesforce.pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class ValidLoginTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private final String url = "https://login.salesforce.com/?locale=in";

    @BeforeTest
    public void setUp() {
        try {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            options.addArguments("--disable-notifications");
            driver = new ChromeDriver(options);
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
            driver.get(url);
            loginPage = new LoginPage(driver);
        } catch (Exception e) {
            Assert.fail("Driver initialization failed: " + e.getMessage(), e);
        }
    }

    @Test
    public void testSuccessfulLoginWithValidCredentials() {
        try {
            Assert.assertTrue(loginPage.isLoginPageLoaded(), "Login page failed to load.");
            loginPage.enterUsername("qa.enterprise.user@salesforce.com");
            loginPage.enterPassword("ValidEnterprisePassword2026!");
            loginPage.clickRememberMe();
            loginPage.clickLogin();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            boolean isRedirected = wait.until(d -> !d.getCurrentUrl().contains("login.salesforce.com") || d.findElements(By.xpath("//div[contains(@class, 'slds-global-header') or contains(@id, 'body') or contains(@class, 'desktop')]")).size() > 0);
            Assert.assertTrue(isRedirected, "User was not redirected after successful authentication.");
        } catch (Exception e) {
            Assert.fail("Valid login test encountered an unexpected exception: " + e.getMessage(), e);
        }
    }

    @AfterTest
    public void tearDown() {
        try {
            if (driver != null) {
                driver.quit();
            }
        } catch (Exception ignored) {
        }
    }
}
