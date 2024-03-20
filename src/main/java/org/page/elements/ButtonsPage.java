package org.page.elements;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ButtonsPage {
    private final WebDriver driver;
    private final Actions action;
    @FindBy(id = "doubleClickBtn")
    private WebElement doubleClickBtn;
    @FindBy(id = "rightClickBtn")
    private WebElement rightClickBtn;
    @FindBy(id = "doubleClickMessage")
    private WebElement doubleClickMessage;
    @FindBy(id = "rightClickMessage")
    private WebElement rightClickMessage;
    @FindBy(id = "dynamicClickMessage")
    private WebElement leftClickMessage;
    @FindBy(css = "div.col-md-6 > div:nth-child(2) >  div:nth-child(4) button")
    private WebElement leftClickBtn;

    public ButtonsPage(WebDriver driver) {
        this.driver = driver;
        this.action = new Actions(this.driver);
        PageFactory.initElements(driver, this);
    }

    @Step("Double click on the left mouse button")
    public void doubleClick() {
        action.doubleClick(doubleClickBtn).perform();
    }

    @Step("Right click on the left mouse button")
    public void rightClick() {
        action.contextClick(rightClickBtn).perform();
    }

    @Step("Assert text appearing after double click")
    public void assertTextAfterDoubleClick() {
        Assertions.assertEquals("You have done a double click", doubleClickMessage.getText());
    }

    @Step("Assert text appearing after right click")
    public void assertTextAfterRightClick() {
        Assertions.assertEquals("You have done a right click", rightClickMessage.getText());
    }

    @Step("Assert text appearing after left click")
    public void clickAndAssertTextAfterLeftClick() {
        clickJavascriptExecutor6(leftClickBtn);
        Assertions.assertEquals("You have done a dynamic click", leftClickMessage.getText());
    }

    private void clickJavascriptExecutor6(WebElement webElement) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", webElement);
    } //TODO: перенести в BasePage
}
