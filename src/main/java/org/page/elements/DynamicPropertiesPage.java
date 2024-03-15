package org.page.elements;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DynamicPropertiesPage {
    private final WebDriver driver;
    private final WebDriverWait webDriverWait;

    public DynamicPropertiesPage(WebDriver driver) {
        this.driver = driver;
        webDriverWait = new WebDriverWait(this.driver, Duration.ofSeconds(5));
    }

    @Step("Checking the text displayed on screen")
    public void assertDisplayedTextWithRandomId() {
        Assertions.assertEquals("This text has random Id", driver.findElement(By.cssSelector("div.row p")).getText());
    }

    @Step("Wait until the element becomes clickable")
    public void checkClickableWillEnabledButton() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("enableAfter")));
    }

    @Step("Wait until the element changes color")
    public void checkColorChange() {
        webDriverWait.until(ExpectedConditions.attributeContains(By.id("colorChange"), "class", "text-danger"));
    }

    @Step("Wait until the element becomes visible")
    public void checkVisibility() {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("visibleAfter")));
    }
}
