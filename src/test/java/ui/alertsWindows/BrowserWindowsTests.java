package ui.alertsWindows;

import org.base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

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
    void clickNewTabButtonSwitchAndCheckNewTabTest() {
        originWindow = clickToButtonAndSwitchToChildWindow("tabButton");
        assertUrlAndValue();
        closeChildWindowAndSwitchToOrigin(originWindow);
    }

    @Test
    void clickNewWindowButtonSwitchAndCheckNewWindowTest() {
        originWindow = clickToButtonAndSwitchToChildWindow("windowButton");
        assertUrlAndValue();
        closeChildWindowAndSwitchToOrigin(originWindow);
    }

    @Test
    void clickNewWindowMessageButtonAndCheckNewWindowMessageTest() {
        originWindow = clickToButtonAndSwitchToChildWindow("messageWindowButton");
        Assertions.assertNotEquals(originWindow, getDriver().getWindowHandle());
        closeChildWindowAndSwitchToOrigin(originWindow);
    }

    private String clickToButtonAndSwitchToChildWindow(String tabButton) {
        String originWindow = getDriver().getWindowHandle();
        getDriver().findElement(By.id(tabButton)).click();
        getWebDriverWait().until(ExpectedConditions.numberOfWindowsToBe(2));
        switchToNewWindow(originWindow);
        return originWindow;
    }

    private void switchToNewWindow(String originWindow) {
        for (String windowHandle : getDriver().getWindowHandles()) {
            if (!originWindow.equals(windowHandle)) {
                getDriver().switchTo().window(windowHandle);
                break;
            }
        }
    }

    private void assertUrlAndValue() {
        Assertions.assertTrue(getDriver().getCurrentUrl().contains(SAMPLE_URL));
        Assertions.assertEquals(EXPECTED_TEXT, getDriver().findElement(By.id(SAMPLE_ID)).getText());
    }

    private void closeChildWindowAndSwitchToOrigin(String originWindow) {
        getDriver().close();
        getDriver().switchTo().window(originWindow);
    }
}
