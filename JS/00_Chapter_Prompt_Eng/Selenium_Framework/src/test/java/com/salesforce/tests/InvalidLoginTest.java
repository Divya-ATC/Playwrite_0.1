package com.salesforce.tests;

import com.salesforce.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class InvalidLoginTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private final String url = "https://login.salesforce.com/?locale=in";

    @BeforeMethod
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
    public void testLoginWithInvalidPassword() {
        try {
            Assert.assertTrue(loginPage.isLoginPageLoaded(), "Login page failed to load.");
            loginPage.enterUsername("unregistered.user@salesforce.com");
            loginPage.enterPassword("WrongPassword123!");
            loginPage.clickLogin();

            Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message banner is not displayed.");
            String actualError = loginPage.getErrorMessage();
            Assert.assertTrue(actualError.contains("Please check your username and password") || actualError.contains("check your username"),
                    "Expected error message not matched. Actual: " + actualError);
        } catch (Exception e) {
            Assert.fail("Invalid password test failed: " + e.getMessage(), e);
        }
    }

    @Test
    public void testLoginWithBlankCredentials() {
        try {
            Assert.assertTrue(loginPage.isLoginPageLoaded(), "Login page failed to load.");
            loginPage.clickLogin();

            Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message banner is not displayed for blank submission.");
            String actualError = loginPage.getErrorMessage();
            Assert.assertTrue(actualError.contains("Please enter your password") || actualError.contains("Please check your username"),
                    "Expected validation error not matched. Actual: " + actualError);
        } catch (Exception e) {
            Assert.fail("Blank credentials test failed: " + e.getMessage(), e);
        }
    }

    @Test
    public void testLoginWithEmptyPassword() {
        try {
            Assert.assertTrue(loginPage.isLoginPageLoaded(), "Login page failed to load.");
            loginPage.enterUsername("test.user@salesforce.com");
            loginPage.clickLogin();

            Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message was not displayed when password was empty.");
            String actualError = loginPage.getErrorMessage();
            Assert.assertTrue(actualError.contains("Please enter your password"),
                    "Expected 'Please enter your password' message. Actual: " + actualError);
        } catch (Exception e) {
            Assert.fail("Empty password test failed: " + e.getMessage(), e);
        }
    }

    @AfterMethod
    public void tearDown() {
        try {
            if (driver != null) {
                driver.quit();
            }
        } catch (Exception ignored) {
        }
    }
}
