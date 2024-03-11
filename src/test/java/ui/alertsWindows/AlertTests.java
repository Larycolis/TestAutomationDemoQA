package ui.alertsWindows;

import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import io.qameta.allure.junit5.AllureJunit5;
import org.base.BaseTest;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

@ExtendWith(AllureJunit5.class)
@Feature("Alert Page")
@Story("UI Tests")
public class AlertTests extends BaseTest {
    private static final String ALERT_BUTTON = "alertButton";
    private static final String TIMER_ALERT_BUTTON = "timerAlertButton";
    private static final String CONFIRM_BUTTON = "confirmButton";
    private static final String PROMT_BUTTON = "promtButton";

    private static final String CURRENT_URL = "https://demoqa.com/alerts";

    @BeforeEach
    void setUp() {
        getDriver().get(CURRENT_URL);
    }

    @Test
    @DisplayName("Click button to see alert")
    void clickAlertButtonCheckAndCloseAlertOkTest() {
        Alert alert = clickAlertButtonAndSwitchToAlert(ALERT_BUTTON);
        assertAlertTextAndAccept("You clicked a button", alert);
        aassertCurrentUrl();
    }

    @Test
    @DisplayName("Alert will appear after 5 seconds")
    void clickWaitAlertButtonWaitCheckAndCloseAlertOkTest() {
        Alert alert = clickAlertButtonWaitAndSwitchToAlert();
        assertAlertTextAndAccept("This alert appeared after 5 seconds", alert);
        aassertCurrentUrl();
    }

    @Test
    @DisplayName("Confirm box will appear and Ok")
    void clickConfirmationAlertButtonCheckAndConfirmOkAlertTest() {
        Alert alert = clickAlertButtonAndSwitchToAlert(CONFIRM_BUTTON);
        assertAlertTextAndAccept("Do you confirm action?", alert);
        checkConfermationOk();
    }

    @Test
    @DisplayName("Confirm box will appear and Cancel")
    void clickConfirmationAlertButtonCheckAndConfirmCancelAlertTest() {
        Alert alert = clickAlertButtonAndSwitchToAlert(CONFIRM_BUTTON);
        alert.dismiss();
        checkConfirmationCancel();
    }

    @Test
    @DisplayName("Prompt box will appear")
    void clickAlertButtonAndCheckAlertTextTest() {
        Alert alert = clickAlertButtonAndSwitchToAlert(PROMT_BUTTON);
        checkAlertText(alert);
    }

    @ParameterizedTest
    @ValueSource(strings = {"jen1", "JENY", "123", "!@#)(&", "@3rt", "Jeny"})
    @DisplayName("Prompt box will appear and enter name variants")
    void clickAlertButtonEnterValueCloseAlertOkAndCheckDisplayedTest(String name) throws InterruptedException {
        Alert alert = clickAlertButtonAndSwitchToAlert(PROMT_BUTTON);
        enterValueAndConfirm(name, alert);
        checkConfirmationName(name);
    }

    @Step("Click and switch to alert")
    private Alert clickAlertButtonAndSwitchToAlert(String alertButton) {
        clickJavascriptExecutor(getDriver().findElement(By.id(alertButton)));
        return getDriver().switchTo().alert();
    }

    @Step("Assert text in alert and accept")
    private void assertAlertTextAndAccept(String expected, Alert alert) {
        Assertions.assertEquals(expected, alert.getText());
        alert.accept();
    }

    @Step("Click the delay button, wait 5 minutes and switch to alert")
    private Alert clickAlertButtonWaitAndSwitchToAlert() {
        getDriver().findElement(By.id(TIMER_ALERT_BUTTON)).click();
        getWebDriverWait().until(ExpectedConditions.alertIsPresent());
        return getDriver().switchTo().alert();
    }

    @Step("After the alert is closed, the user is on the current page")
    private void aassertCurrentUrl() {
        Assertions.assertEquals(CURRENT_URL, getDriver().getCurrentUrl());
    }

    @Step("Checking confirmation of OK button selection")
    private void checkConfermationOk() {
        Assertions.assertEquals("You selected Ok", getDriver().findElement(By.id("confirmResult")).getText());
    }

    @Step("Checking confirmation of Cancel button selection")
    private void checkConfirmationCancel() {
        Assertions.assertEquals("You selected Cancel", getDriver().findElement(By.id("confirmResult")).getText());
    }

    @Step("Checking the text in the alert for conformity")
    private void checkAlertText(Alert alert) {
        Assertions.assertEquals("Please enter your name", alert.getText());
    }

    @Step("check the confirmation of the entered name")
    private void checkConfirmationName(String name) {
        Assertions.assertEquals("You entered " + name, getDriver().findElement(By.id("promptResult")).getText());
    }

    @Step("Enter name and confirm")
    private void enterValueAndConfirm(String name, Alert alert) {
        alert.sendKeys(name);
        alert.accept();
    }
}
