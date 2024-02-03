package ui.elements;

import org.base.WebDriverSetup;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TextboxTests {
    private WebDriverSetup webDriverSetup;
    private static final String USER_NAME = "userName";
    private static final String USER_EMAIL = "userEmail";
    private static final String CURRENT_ADDRESS = "currentAddress";
    private static final String PERMANENT_ADDRESS = "permanentAddress";

    @BeforeEach
    void setUp() {
        webDriverSetup = new WebDriverSetup();
        webDriverSetup.setUp("https://demoqa.com/text-box");
    }

    @AfterEach
    void tearDown() {
        webDriverSetup.tearDown();
    }

    @ParameterizedTest
    @ValueSource(strings = {" test", "123test", "123", "i", "TEST", "$*^"})
    void fillTheFullNameFieldAndSubmitTest(String param) {
        WebDriver driver = getDriver();
        JavascriptExecutor executor = getJavascriptExecutor();
        driver.findElement(By.id(USER_NAME)).click();
        driver.findElement(By.id(USER_NAME)).sendKeys(param);
        executor.executeScript("arguments[0].click();", driver.findElement(By.id("submit")));
        Assertions.assertEquals("Name:" + param, driver.findElement(By.id("name")).getText());
    }

    @Test
    void fillTheFormAndSubmitTest() {
        WebDriver driver = getDriver();
        String[] testDate = new String[]{"Ivan Ivanovich", "test@test.ts", "Gorkog, 1", "Lenin, 12"};
        WebElement userName = driver.findElement(By.id(USER_NAME));
        WebElement userEmail = driver.findElement(By.id(USER_EMAIL));
        WebElement currentAddress = driver.findElement(By.id(CURRENT_ADDRESS));
        WebElement permanentAddress = driver.findElement(By.id(PERMANENT_ADDRESS));
        JavascriptExecutor executor = getJavascriptExecutor();
        userName.click();
        userName.sendKeys(testDate[0]);
        userEmail.click();
        userEmail.sendKeys(testDate[1]);
        currentAddress.click();
        currentAddress.sendKeys(testDate[2]);
        permanentAddress.click();
        permanentAddress.sendKeys(testDate[3]);
        executor.executeScript("arguments[0].click();", driver.findElement(By.id("submit")));
        Assertions.assertEquals("Name:" + testDate[0], driver.findElement(By.id("name")).getText());
        Assertions.assertEquals("Email:" + testDate[1], driver.findElement(By.id("email")).getText());
        Assertions.assertEquals("Current Address :" + testDate[2], driver.findElement(By.cssSelector("p[id='currentAddress']")).getText());
        Assertions.assertEquals("Permananet Address :" + testDate[3], driver.findElement(By.cssSelector("p[id='permanentAddress']")).getText());
    }

    private JavascriptExecutor getJavascriptExecutor() {
        WebDriver driver = getDriver();
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].remove();", driver.findElement(By.id("adplus-anchor")));
        return executor;
    }

    private WebDriver getDriver() {
        return webDriverSetup.getDriver();
    }
}
