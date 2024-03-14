package ui.alertsWindows;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.junit5.AllureJunit5;
import org.base.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.page.alertsWindows.AlertPage;

@ExtendWith(AllureJunit5.class)
@Feature("Alert Page")
@Story("UI Tests")
public class AlertTests extends BaseTest {
    private final String currentUrl = "https://demoqa.com/alerts";
    private AlertPage alertPage;

    @BeforeEach
    void setUp() {
        alertPage = new AlertPage(getDriver());
        getDriver().get(currentUrl);
    }

    @Test
    @DisplayName("Click the button to see the alert")
    void clickAlertButtonCheckAndCloseAlertOkTest() {
        alertPage.assertTextInAlertButtonAlert("You clicked a button");
        alertPage.assertCurrentUrl(currentUrl);
    }

    @Test
    @DisplayName("Alert will appear after 5 seconds")
    void clickWaitAlertButtonWaitCheckAndCloseAlertOkTest() {
        alertPage.assertTextInTimerAlertButtonAlert("This alert appeared after 5 seconds");
        alertPage.assertCurrentUrl(currentUrl);
    }

    @Test
    @DisplayName("Confirm box will appear and Ok")
    void clickConfirmationAlertButtonCheckAndConfirmOkAlertTest() {
        alertPage.assertTextInConfirmButtonAlert("Do you confirm action?");
        alertPage.checkConfirmationOk();
    }

    @Test
    @DisplayName("Confirm box will appear and Cancel")
    void clickConfirmationAlertButtonCheckAndConfirmCancelAlertTest() {
        alertPage.clickAlertButtonAndDismiss();
        alertPage.checkConfirmationCancel();
    }

    @Test
    @DisplayName("Prompt box will appear")
    void clickAlertButtonAndCheckAlertTextTest() {
        alertPage.checkPromtAlertButtonText();
    }

    @ParameterizedTest
    @ValueSource(strings = {"jen1", "JENY", "123", "!@#)(&", "@3rt", "Jeny"})
    @DisplayName("Prompt box will appear and enter name variants")
    void clickAlertButtonEnterValueCloseAlertOkAndCheckDisplayedTest(String name) {
        alertPage.enterValueAndConfirm(name);
        alertPage.checkConfirmationName(name);
    }
}
