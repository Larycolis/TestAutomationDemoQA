package ui.alertsWindows;

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
@Feature("Brouser Windows Page")
@Story("UI Tests")
public class BrowserWindowsTests extends BaseTest {
    private static final String SAMPLE_URL = "https://demoqa.com/sample";
    private static final String SAMPLE_ID = "sampleHeading";
    private static final String EXPECTED_TEXT = "This is a sample page";
    private String originWindow;

    @BeforeEach
    void setUp() {
        getDriver().get("https://demoqa.com/browser-windows");
    }

    @Test
    @DisplayName("Click the button to see the tab")
    void clickNewTabButtonSwitchAndCheckNewTabTest() {
        originWindow = clickToButtonAndSwitchToChildWindow("tabButton");
        assertUrlAndValue();
        closeChildWindowAndSwitchToOrigin(originWindow);
    }

    @Test
    @DisplayName("Click the button to see the window")
    void clickNewWindowButtonSwitchAndCheckNewWindowTest() {
        originWindow = clickToButtonAndSwitchToChildWindow("windowButton");
        assertUrlAndValue();
        closeChildWindowAndSwitchToOrigin(originWindow);
    }

    @Test
    @DisplayName("Click the button to see the window message")
    void clickNewWindowMessageButtonAndCheckNewWindowMessageTest() {
        originWindow = clickToButtonAndSwitchToChildWindow("messageWindowButton");
        checkTheValuesAreNotEqual();
        closeChildWindowAndSwitchToOrigin(originWindow);
    }

    @Step("Click the button, wait, switch and return the child window")
    private String clickToButtonAndSwitchToChildWindow(String tabButton) {
        String originWindow = getDriver().getWindowHandle();
        clickJavascriptExecutor(getDriver().findElement(By.id(tabButton)));
        getWebDriverWait().until(ExpectedConditions.numberOfWindowsToBe(2));
        switchToNewWindow(originWindow);
        return originWindow;
    }

    @Step("Switch to the window")
    private void switchToNewWindow(String originWindow) {
        for (String windowHandle : getDriver().getWindowHandles()) {
            if (!originWindow.equals(windowHandle)) {
                getDriver().switchTo().window(windowHandle);
                break;
            }
        }
    }

    @Step("Checking contains the expected URL and the text in new window")
    private void assertUrlAndValue() {
        Assertions.assertTrue(getDriver().getCurrentUrl().contains(SAMPLE_URL));
        Assertions.assertEquals(EXPECTED_TEXT, getDriver().findElement(By.id(SAMPLE_ID)).getText());
    }

    @Step("Close new window and switch to the origin window")
    private void closeChildWindowAndSwitchToOrigin(String originWindow) {
        getDriver().close();
        getDriver().switchTo().window(originWindow);
    }

    @Step("Checking that the values are not equal")
    private void checkTheValuesAreNotEqual() {
        Assertions.assertNotEquals(originWindow, getDriver().getWindowHandle());
    }
}
