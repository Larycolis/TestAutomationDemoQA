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
import org.openqa.selenium.interactions.Actions;

@ExtendWith(AllureJunit5.class)
@Feature("Buttons")
@Story("UI Tests")
public class ButtonsTests extends BaseTest {
    private final Actions action = new Actions(getDriver());

    @BeforeEach
    void setUp() {
        getDriver().get("https://demoqa.com/buttons");
    }

    @Test
    @DisplayName("Check double left button click action")
    void clickButtonDoubleClickMeAndCheckSelectedTest() {
        doubleClick(By.id("doubleClickBtn"));
        checkEqualsExpAndActText("You have done a double click", By.id("doubleClickMessage"));
    }

    @Test
    @DisplayName("Check right button click action")
    void clickButtonRightClickMeAndCheckSelectedTest() {
        rightClick(By.id("rightClickBtn"));
        checkEqualsExpAndActText("You have done a right click", By.id("rightClickMessage"));
    }

    @Test
    @DisplayName("Check left button click action")
    void clickButtonClickMeAndCheckSelectedTest() {
        clickJavascriptExecutor(getDriver().findElement(By.cssSelector("div.col-md-6 > div:nth-child(2) >  div:nth-child(4) button")));
        checkEqualsExpAndActText("You have done a dynamic click", By.id("dynamicClickMessage"));
    }

    @Step("Double click on the left mouse button")
    private void doubleClick(By selector) {
        action.doubleClick(getDriver().findElement(selector)).perform();
    }

    @Step("Right click on the left mouse button")
    private void rightClick(By selector) {
        action.contextClick(getDriver().findElement(selector)).perform();
    }

    @Step("Checking that the text is equals")
    private void checkEqualsExpAndActText(String expText, By selector) {
        Assertions.assertEquals(expText, getDriver().findElement(selector).getText());
    }
}
