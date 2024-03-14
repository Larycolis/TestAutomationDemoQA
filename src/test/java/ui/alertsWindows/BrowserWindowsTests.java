package ui.alertsWindows;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.junit5.AllureJunit5;
import org.base.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.page.alertsWindows.BrowserWindowsPage;

@ExtendWith(AllureJunit5.class)
@Feature("Brouser Windows Page")
@Story("UI Tests")
public class BrowserWindowsTests extends BaseTest {
    private BrowserWindowsPage browserWindowsPage;

    @BeforeEach
    void setUp() {
        browserWindowsPage = new BrowserWindowsPage(getDriver());
        getDriver().get("https://demoqa.com/browser-windows");
    }

    @Test
    @DisplayName("Click the button to see the tab")
    void clickNewTabButtonSwitchAndCheckNewTabTest() {
        String originWindow = browserWindowsPage.clickToTabButtonAndSwitchToNewWindow();
        browserWindowsPage.assertUrlAndValue();
        browserWindowsPage.closeChildWindowAndSwitchToOrigin(originWindow);
    }

    @Test
    @DisplayName("Click the button to see the window")
    void clickNewWindowButtonSwitchAndCheckNewWindowTest() {
        String originWindow = browserWindowsPage.clickToWindowButtonAndSwitchToChildWindow();
        browserWindowsPage.assertUrlAndValue();
        browserWindowsPage.closeChildWindowAndSwitchToOrigin(originWindow);
    }

    @Test
    @DisplayName("Click the button to see the window message")
    void clickNewWindowMessageButtonAndCheckNewWindowMessageTest() {
        String originWindow = browserWindowsPage.clickToMessageWindowButtonAndSwitchToChildWindow();
        browserWindowsPage.checkTheValuesAreNotEqual(originWindow);
        browserWindowsPage.closeChildWindowAndSwitchToOrigin(originWindow);
    }
}
