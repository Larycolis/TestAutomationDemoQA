package org.page.elements;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TextboxPage {
    private final WebDriver driver;
    @FindBy(id = "userName")
    private WebElement userNameField;
    @FindBy(id = "userEmail")
    private WebElement userEmailField;
    @FindBy(id = "currentAddress")
    private WebElement currentAddressField;
    @FindBy(id = "permanentAddress")
    private WebElement permanentAddressField;
    @FindBy(id = "submit")
    private WebElement submit;
    @FindBy(id = "name")
    private WebElement nameOnScreen;
    @FindBy(id = "email")
    private WebElement emailOnScreen;

    public TextboxPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void sendUserName(String param) {
        userNameField.sendKeys(param);
    }

    public void sendEmail(String param) {
        userEmailField.sendKeys(param);
    }

    public void sendCurrentAddress(String param) {
        currentAddressField.sendKeys(param);
    }

    public void sendPermanentAddress(String param) {
        permanentAddressField.sendKeys(param);
    }

    public void clickSubmit() {
        clickJavascriptExecutor10(submit);
    }

    public void assertNameTextOnTheScreen(String param) {
        Assertions.assertEquals("Name:" + param, nameOnScreen.getText());
    }

    public void assertEmailTextOnTheScreen(String param) {
        Assertions.assertEquals("Email:" + param, emailOnScreen.getText());
    }

    public void assertCurrentAddressTextOnTheScreen(String param) {
        Assertions.assertEquals("Current Address :" + param, driver.findElement(By.cssSelector("p[id='currentAddress']")).getText());
    }

    public void assertPermanentAddressTextOnTheScreen(String param) {
        Assertions.assertEquals("Permananet Address :" + param, driver.findElement(By.cssSelector("p[id='permanentAddress']")).getText());
    }

    private void clickJavascriptExecutor10(WebElement webElement) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", webElement);
    }
}
