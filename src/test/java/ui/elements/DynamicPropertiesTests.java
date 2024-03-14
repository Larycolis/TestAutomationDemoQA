package ui.elements;

import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import io.qameta.allure.junit5.AllureJunit5;
import org.base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

@ExtendWith(AllureJunit5.class)
@Feature("Dynamic Properties")
@Story("UI Tests")
public class DynamicPropertiesTests extends BaseTest {
    @BeforeEach
    void setUp() {
        getDriver().get("https://demoqa.com/dynamic-properties");
    }

    @Test
    @DisplayName("Checking that the text is equals")
    void checkTextIsDisplayed() {
        checkEqualsExpAndActText("This text has random Id", By.cssSelector("div.row p"));
    }

    @Test
    @DisplayName("Wait until the button becomes enable")
    void waitUntilEnabledAndClickTest() {
        checkClickable();
    }

    @Test
    @DisplayName("Wait until the button changes color")
    void waitUntilColorChangedAndClickTest() {
        checkColor();
    }

    @Test
    @DisplayName("Wait until the button becomes visible")
    void waitUntilVisibleAndClickTest() {
        checkVisibility();
    }

    @Step("Wait until the element becomes visible")
    private void checkVisibility() {
        getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("visibleAfter")));
    }

    @Step("Checking that the text is equals")
    private void checkEqualsExpAndActText(String expText, By selector) {
        Assertions.assertEquals(expText, getDriver().findElement(selector).getText());
    }

    @Step("Wait until the element becomes clickable")
    private void checkClickable() {
        getWebDriverWait().until(ExpectedConditions.elementToBeClickable(getDriver().findElement(By.id("enableAfter"))));
    }

    @Step("Wait until the element changes color")
    private void checkColor() {
        getWebDriverWait().until(ExpectedConditions.attributeContains(getDriver().findElement(By.id("colorChange")), "class", "text-danger"));
    }
}
