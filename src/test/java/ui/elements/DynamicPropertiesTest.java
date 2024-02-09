package ui.elements;

import org.base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DynamicPropertiesTest extends BaseTest {
    @BeforeEach
    void setUp() {
        getDriver().get("https://demoqa.com/dynamic-properties");
    }

    @Test
    void checkTextIsDisplayed() {
        Assertions.assertEquals("This text has random Id", getDriver().findElement(By.cssSelector("div.row p")).getText());
    }

    @Test
    void waitUntilEnabledAndClickTest() {
        getWebDriverWait().until(ExpectedConditions.elementToBeClickable(getDriver().findElement(By.id("enableAfter"))));
    }

    @Test
    void waitUntilColorChangedAndClickTest() {
        getWebDriverWait().until(ExpectedConditions.attributeContains(getDriver().findElement(By.id("colorChange")), "class", "text-danger"));
    }

    @Test
    void waitUntilVisibleAndClickTest() {
        getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("visibleAfter")));
    }
}
