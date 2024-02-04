package ui.elements;

import org.base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class RadioButtonTests extends BaseTest {
    private static final String TEXT_SUCCESS = "text-success";

    @BeforeEach
    void setUp() {
        getDriver().get("https://demoqa.com/radio-button");
    }

    @Test
    void clickButtonYesAndCheckSelectedTest() {
        getJavascriptExecutor("yesRadio");
        Assertions.assertEquals("Yes", getDriver().findElement(By.className(TEXT_SUCCESS)).getText());
    }

    @Test
    void clickButtonImpressiveAndCheckSelectedTest() {
        getJavascriptExecutor("impressiveRadio");
        Assertions.assertEquals("Impressive", getDriver().findElement(By.className(TEXT_SUCCESS)).getText());
    }

    @Test
    void clickButtonNoAndCheckUnavailabilityTest() {
        Assertions.assertFalse(getDriver().findElement(By.id("noRadio")).isEnabled());
    }

    private void getJavascriptExecutor(String id) {
        clickJavascriptExecutor(getDriver().findElement(By.id(id)));
    }
}
