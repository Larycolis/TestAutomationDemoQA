package org.page.alertsWindows;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ModalDialogsPage {
    private final WebDriver driver;
    private final WebDriverWait webDriverWait;
    @FindBy(id = "showSmallModal")
    private WebElement smallModal;
    @FindBy(id = "showLargeModal")
    private WebElement largeModal;
    @FindBy(id = "example-modal-sizes-title-sm")
    private WebElement smallModalTitle;
    @FindBy(id = "closeSmallModal")
    private WebElement closeSmallModal;
    @FindBy(id = "example-modal-sizes-title-lg")
    private WebElement largeModalTitle;
    @FindBy(id = "closeLargeModal")
    private WebElement closeLargeModal;
    @FindBy(className = "modal-body")
    private WebElement modalBody;

    public ModalDialogsPage(WebDriver driver) {
        this.driver = driver;
        webDriverWait = new WebDriverWait(this.driver, Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
    }

    @Step("Click the button and wait small/large modal")
    public void clickAndWaitSmallModalWindow() {
        smallModal.click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.className("modal-content")));
    }

    @Step("Click the button and wait small/large modal")
    public void clickAndWaitLargeModalWindow() {
        largeModal.click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.className("modal-content")));
    }

    @Step("Checking that the title in small modal is equals expected title")
    public void checkEqualsExpAndActTitleSmallModal() {
        Assertions.assertEquals("Small Modal", smallModalTitle.getText());
    }

    @Step("Checking that the body text in small modal is equals expected body text")
    public void checkEqualsExpAndActBodyTextSmallModal() {
        Assertions.assertEquals("This is a small modal. It has very less content", modalBody.getText());
    }

    @Step("Close small modal and check it is invisible")
    public void closeSmallModalAndCheckModalWindowIsInvisibility() {
        closeSmallModal.click();
        webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("modal-content")));
    }

    @Step("Checking that the title in large modal is equals expected title")
    public void checkEqualsExpAndActTitleLargeModal() {
        Assertions.assertEquals("Large Modal", largeModalTitle.getText());
    }

    @Step("Checking that the body text in large modal is equals expected body text")
    public void checkEqualsExpAndActBodyTextLargeModal() {
        String largeModalBodyText = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. " +
                "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, " +
                "when an unknown printer took a galley of type and scrambled it to make a type specimen book. " +
                "It has survived not only five centuries, but also the leap into electronic typesetting, " +
                "remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset " +
                "sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like " +
                "Aldus PageMaker including versions of Lorem Ipsum.";
        Assertions.assertEquals(largeModalBodyText, modalBody.getText());
    }

    @Step("Close large modal and check it is invisible")
    public void closeLargeModalAndCheckModalWindowIsInvisibility() {
        closeLargeModal.click();
        webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("modal-content")));
    }
}
