package org.page.elements;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RadioButtonPage {
    private final WebDriver driver;
    @FindBy(id = "yesRadio")
    private WebElement yesRadio;
    @FindBy(id = "impressiveRadio")
    private WebElement impressiveRadio;
    @FindBy(id = "noRadio")
    private WebElement noRadio;
    @FindBy(className = "text-success")
    private WebElement actualText;

    public RadioButtonPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Click the radio button Yes and check text on the screen")
    public void assertTextAfterClickButtonYes() {
        clickJavascriptExecutor9(yesRadio);
        Assertions.assertEquals("Yes", actualText.getText());
    }

    @Step("Click the radio button Impressive and check text on the screen")
    public void assertTextAfterClickButtonImpressive() {
        clickJavascriptExecutor9(impressiveRadio);
        Assertions.assertEquals("Impressive", actualText.getText());
    }

    @Step("Check the button No is unavailable")
    public void assertButtonIsUnavailable() {
        Assertions.assertFalse(noRadio.isEnabled());
    }

    private void clickJavascriptExecutor9(WebElement webElement) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", webElement);
    } //TODO: перенести в BasePage
}
