package com.salesforce.pages;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(xpath = "//input[@id='username']")
    private WebElement usernameField;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//input[@id='Login']")
    private WebElement loginButton;

    @FindBy(xpath = "//input[@id='rememberUn']")
    private WebElement rememberMeCheckbox;

    @FindBy(xpath = "//div[@id='error']")
    private WebElement errorMessage;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void enterUsername(String username) throws TimeoutException {
        try {
            wait.until(ExpectedConditions.visibilityOf(usernameField));
            usernameField.clear();
            usernameField.sendKeys(username);
        } catch (TimeoutException e) {
            throw new TimeoutException("Failed to interact with username field via xpath: //input[@id='username']", e);
        }
    }

    public void enterPassword(String password) throws TimeoutException {
        try {
            wait.until(ExpectedConditions.visibilityOf(passwordField));
            passwordField.clear();
            passwordField.sendKeys(password);
        } catch (TimeoutException e) {
            throw new TimeoutException("Failed to interact with password field via xpath: //input[@id='password']", e);
        }
    }

    public void clickRememberMe() throws TimeoutException {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(rememberMeCheckbox));
            if (!rememberMeCheckbox.isSelected()) {
                rememberMeCheckbox.click();
            }
        } catch (TimeoutException e) {
            throw new TimeoutException("Failed to click remember me checkbox via xpath: //input[@id='rememberUn']", e);
        }
    }

    public void clickLogin() throws TimeoutException {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(loginButton));
            loginButton.click();
        } catch (TimeoutException e) {
            throw new TimeoutException("Failed to click login button via xpath: //input[@id='Login']", e);
        }
    }

    public void doLogin(String username, String password) throws TimeoutException {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    public String getErrorMessage() throws TimeoutException {
        try {
            wait.until(ExpectedConditions.visibilityOf(errorMessage));
            return errorMessage.getText().trim();
        } catch (TimeoutException e) {
            throw new TimeoutException("Error message element not visible via xpath: //div[@id='error']", e);
        }
    }

    public boolean isErrorMessageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(errorMessage));
            return errorMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isLoginPageLoaded() {
        try {
            wait.until(ExpectedConditions.visibilityOf(usernameField));
            return usernameField.isDisplayed() && loginButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
