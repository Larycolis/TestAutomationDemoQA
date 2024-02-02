package ui.elements.textbox;

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
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TextboxTests {
    private WebDriver driver;

    @BeforeEach
    void setUp() {
        System.setProperty("webdriver.chrome.driver",
                "C:\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        String path = "https://demoqa.com/text-box";
        driver.get(path);
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @ParameterizedTest
    @ValueSource(strings = {" test", "123test", "123", "i", "TEST", "$*^"})
    void fillTheFullNameFieldAndSubmitTest(String param) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].remove();", driver.findElement(By.id("adplus-anchor")));
        driver.findElement(By.id("userName")).click();
        driver.findElement(By.id("userName")).sendKeys(param);
        executor.executeScript("arguments[0].click();", driver.findElement(By.id("submit")));
        Assertions.assertEquals("Name:" + param, driver.findElement(By.id("name")).getText());
    }

    @Test
    void fillTheFormAndSubmitTest() throws InterruptedException {
        String[] testDate = new String[]{"Ivan Ivanovich", "test@test.ts", "Gorkog, 1", "Lenin, 12"};
        WebElement userName = driver.findElement(By.id("userName"));
        WebElement userEmail = driver.findElement(By.id("userEmail"));
        WebElement currentAddress = driver.findElement(By.id("currentAddress"));
        WebElement permanentAddress = driver.findElement(By.id("permanentAddress"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].remove();", driver.findElement(By.id("adplus-anchor")));
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


}
