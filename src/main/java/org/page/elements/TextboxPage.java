package org.page.elements;

import io.qameta.allure.Step;
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

    @Step("Send the username value to the field")
    public void sendUserName(String param) {
        userNameField.sendKeys(param);
    }

    @Step("Send the email value to the field")
    public void sendEmail(String param) {
        userEmailField.sendKeys(param);
    }

    @Step("Send the currant address value to the field")
    public void sendCurrentAddress(String param) {
        currentAddressField.sendKeys(param);
    }

    @Step("Send the permanent address value to the field")
    public void sendPermanentAddress(String param) {
        permanentAddressField.sendKeys(param);
    }

    @Step("Click the button submit")
    public void clickSubmit() {
        clickJavascriptExecutor10(submit);
    }

    @Step("Assert the name on the screen")
    public void assertNameTextOnTheScreen(String param) {
        Assertions.assertEquals("Name:" + param, nameOnScreen.getText());
    }

    @Step("Assert the email on the screen")
    public void assertEmailTextOnTheScreen(String param) {
        Assertions.assertEquals("Email:" + param, emailOnScreen.getText());
    }

    @Step("Assert the currant address on the screen")
    public void assertCurrentAddressTextOnTheScreen(String param) {
        Assertions.assertEquals("Current Address :" + param, driver.findElement(By.cssSelector("p[id='currentAddress']")).getText());
    }

    @Step("Assert the permanent address on the screen")
    public void assertPermanentAddressTextOnTheScreen(String param) {
        Assertions.assertEquals("Permananet Address :" + param, driver.findElement(By.cssSelector("p[id='permanentAddress']")).getText());
    }

    private void clickJavascriptExecutor10(WebElement webElement) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", webElement);
    } //TODO: перенести в BasePage
}
