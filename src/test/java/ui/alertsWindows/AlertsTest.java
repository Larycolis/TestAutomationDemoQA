package ui.alertsWindows;

import org.base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AlertsTest extends BaseTest {
    private static final String ALERT_BUTTON = "alertButton";
    private static final String TIMER_ALERT_BUTTON = "timerAlertButton";
    private static final String CONFIRM_BUTTON = "confirmButton";
    private static final String PROMT_BUTTON = "promtButton";

    @BeforeEach
    void setUp() {
        getDriver().get("https://demoqa.com/alerts");
    }

    @Test
    void clickAlertButtonCheckAndCloseAlertOkTest() {
        Alert alert = clickAlertButtonAndSwitchToAlert(ALERT_BUTTON);
        assertAlertTextAndAccept("You clicked a button", alert);
        Assertions.assertEquals("https://demoqa.com/alerts", getDriver().getCurrentUrl());
    }

    @Test
    void clickWaitAlertButtonWaitCheckAndCloseAlertOkTest() {
        Alert alert = clickAlertButtonWaitAndSwitchToAlert();
        assertAlertTextAndAccept("This alert appeared after 5 seconds", alert);
        Assertions.assertEquals("https://demoqa.com/alerts", getDriver().getCurrentUrl());
    }

    @Test
    void clickConfirmationAlertButtonCheckAndConfirmOkAlertTest() {
        Alert alert = clickAlertButtonAndSwitchToAlert(CONFIRM_BUTTON);
        assertAlertTextAndAccept("Do you confirm action?", alert);
        Assertions.assertEquals("You selected Ok", getDriver().findElement(By.id("confirmResult")).getText());
    }

    @Test
    void clickConfirmationAlertButtonCheckAndConfirmCancelAlertTest() {
        Alert alert = clickAlertButtonAndSwitchToAlert(CONFIRM_BUTTON);
        alert.dismiss();
        Assertions.assertEquals("You selected Cancel", getDriver().findElement(By.id("confirmResult")).getText());
    }

    @Test
    void clickAlertButtonAndCheckAlertTextTest() {
        Alert alert = clickAlertButtonAndSwitchToAlert(PROMT_BUTTON);
        Assertions.assertEquals("Please enter your name", alert.getText());
    }

    @ParameterizedTest
    @ValueSource(strings = {"Jeny", "jen1", "JENY", "123", "!@#)(&", "@3rt"})
    void clickAlertButtonEnterValueCloseAlertOkAndCheckDisplayedTest(String name) {
        Alert alert = clickAlertButtonAndSwitchToAlert(PROMT_BUTTON);
        alert.sendKeys(name);
        alert.accept();
        Assertions.assertEquals("You entered " + name, getDriver().findElement(By.id("promptResult")).getText());
    }

    private Alert clickAlertButtonAndSwitchToAlert(String alertButton) {
        getDriver().findElement(By.id(alertButton)).click();
        return getDriver().switchTo().alert();
    }

    private void assertAlertTextAndAccept(String expected, Alert alert) {
        Assertions.assertEquals(expected, alert.getText());
        alert.accept();
    }

    private Alert clickAlertButtonWaitAndSwitchToAlert() {
        getDriver().findElement(By.id(TIMER_ALERT_BUTTON)).click();
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofMillis(5000));
        wait.until(ExpectedConditions.alertIsPresent());
        return getDriver().switchTo().alert();
    }
}
